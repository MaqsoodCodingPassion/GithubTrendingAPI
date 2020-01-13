package com.msk.github.trend

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.msk.github.trend.R
import com.msk.github.trend.model.GitHubRepositoryEntity
import com.msk.github.trend.util.ViewModelFactory
import com.msk.github.trend.util.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.github_repository_layout.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    private var mAdapter: GithubTrendRepositoryAdapter? = null
    private lateinit var mViewModel: GithubTrendViewModel
    private var mRepoList: MutableList<GitHubRepositoryEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(GithubTrendViewModel::class.java)
        whiteNotificationBar(appBarLayout)
        initToolBar()
        initAdapter()
        initSwipeRefreshRecyclerView()
        retryBtn.setOnClickListener {
            if (isNetworkConnected(this)) callGithubTrendAPI()
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.fetchGithubRepoRecordsFromDB().observe(this, Observer {
           if(it.isNotEmpty() && getTimeDiff(Date().time, it.get(0).time) < 2){
                getRepositoryDataFromDB(it)
            }else if(it.isEmpty() || (it.isNotEmpty() && getTimeDiff(Date().time, it.get(0).time) >= 2)){
                handleNetworkAPICall()
            }else{
                Toast.makeText(this, "Oops there is a network problem", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleNetworkAPICall() {
        if (isNetworkConnected(this)) {
            getRepoDataFromNetwork()
        } else {
            showOfflineView()
        }
    }

    private fun getRepositoryDataFromDB(repoList: List<GitHubRepositoryEntity>) {
        hideShimmer()
        updateUI(repoList)
    }

    private fun callGithubTrendAPI() {
        showShimmer()
        mViewModel.fetchGithubRepoDataFromAPI().observe(this, Observer {
            hideShimmer()
            if (it != null && it.isNotEmpty()) {
                prepareDataForDB(it)
                updateUI(it)
            } else {
                swipeRefreshRecyclerView.isRefreshing = false
                showOfflineView()
            }
        })
    }


    private fun prepareDataForDB(repoList: List<GitHubRepositoryEntity>) {
        mViewModel.saveDataToDB(repoList)
        updateUI(repoList)
    }

    private fun updateUI(repoList: List<GitHubRepositoryEntity>) {
        swipeRefreshRecyclerView.isRefreshing = false
        mRepoList?.clear()
        mRepoList?.addAll(repoList!!)
        mRepoList?.let { it1 -> mAdapter?.setDataList(it1) }
        mAdapter?.notifyDataSetChanged()
        githubRepoRecyclerView.scrollToPosition(0)
    }

    private fun initSwipeRefreshRecyclerView() {
        swipeRefreshRecyclerView.setOnRefreshListener(this)
        swipeRefreshRecyclerView.setColorSchemeResources(
            R.color.colorAccent,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark
        )
    }

    private fun initAdapter() {
        mRepoList = ArrayList()
        mAdapter = GithubTrendRepositoryAdapter(
            this,
            mRepoList as ArrayList<GitHubRepositoryEntity>
        )
        githubRepoRecyclerView.itemAnimator = DefaultItemAnimator()
        githubRepoRecyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        githubRepoRecyclerView.adapter = mAdapter
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        toolbarTitle?.text = resources.getString(R.string.toolbar_title)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun whiteNotificationBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            window.statusBarColor = Color.WHITE
        }
    }

    private fun showShimmer() {
        offlineView.showView(false)
        retryBtn.showView(false)
        shimmerViewContainer.showView(true)
        shimmerViewContainer.startShimmerAnimation()
        swipeRefreshRecyclerView.showView(false)
    }

    private fun hideShimmer() {
        shimmerViewContainer.stopShimmerAnimation()
        swipeRefreshRecyclerView.showView(true)
        shimmerViewContainer.showView(false)
        retryBtn.showView(false)
        offlineView.showView(false)
    }
    private fun showOfflineView() {
        offlineView.showView(true)
        retryBtn.showView(true)
        shimmerViewContainer.showView(false)
        swipeRefreshRecyclerView.showView(false)
    }

    private fun getRepoDataFromNetwork() {
        callGithubTrendAPI()
    }

    override fun onRefresh() {
        callGithubTrendAPI()
    }

    private fun sortList(sortType: String) {
        var sortedList: List<GitHubRepositoryEntity>? = null
        if (sortType?.equals(SORT_BY_NAMES)!!) {
            sortedList = mRepoList?.sortedWith(
                compareBy(String.CASE_INSENSITIVE_ORDER) { v -> v.name.toString() })
        } else if (sortType.equals(SORT_BY_STARS)) {
            sortedList =
                mRepoList?.sortedWith(compareBy(GitHubRepositoryEntity::stars))!!.reversed()
        }
        sortedList?.let { mAdapter?.setDataList(it) }
        mAdapter?.notifyDataSetChanged()
        githubRepoRecyclerView.scrollToPosition(0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sortByStar -> {
                sortList(SORT_BY_STARS)
            }
            R.id.sortByName -> {
                sortList(SORT_BY_NAMES)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}



