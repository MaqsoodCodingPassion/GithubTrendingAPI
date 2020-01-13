package com.msk.github.trend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.msk.github.trend.R
import com.msk.github.trend.model.GitHubRepositoryEntity
import com.msk.github.trend.util.collapse
import com.msk.github.trend.util.expand

class GithubTrendRepositoryAdapter(
    private val context: Context,
    private var repDataList: List<GitHubRepositoryEntity>
) : RecyclerView.Adapter<GithubTrendRepositoryAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var author: TextView = view.findViewById(R.id.author)
        var profileImageView: ImageView = view.findViewById(R.id.profileImageView)
        var description: TextView = view.findViewById(R.id.description)
        var language: TextView = view.findViewById(R.id.language)
        var starsCount: TextView = view.findViewById(R.id.starsCount)
        var gitCount: TextView = view.findViewById(R.id.gitCount)
        var detailView: RelativeLayout = view.findViewById(R.id.detailView)
    }

    fun setDataList(repDataList: List<GitHubRepositoryEntity>){
        this.repDataList = repDataList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_row_item, parent, false)

        return RepositoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repoItem = repDataList[position]
        holder.name.text = repoItem.name
        holder.author.text = repoItem.author
        holder.description.text = repoItem.description
        if(repoItem.language.isNullOrEmpty()){
            holder.language.text = context.resources.getString(R.string.NA)
        }else{
            holder.language.text = repoItem.language
        }

        holder.starsCount.text = repoItem.stars.toString()
        holder.gitCount.text = repoItem.forks.toString()

        holder.itemView.setOnClickListener(){
            if(holder.detailView.visibility == View.VISIBLE){
                collapse(holder.detailView)
            }else{
                expand(holder.detailView)
            }
        }

        Glide.with(context)
            .load(repoItem.avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.profileImageView)
    }

    override fun getItemCount(): Int {
        return repDataList.size
    }
}
