package com.msk.github.trend.di.component

import com.msk.github.trend.GithubTrendApplication
import com.msk.github.trend.di.module.ActivityBuilder
import com.msk.github.trend.di.module.ApplicationModule
import com.msk.github.trend.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (NetworkModule::class),
        (ApplicationModule::class),
        (ActivityBuilder::class)]
)

interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: GithubTrendApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: GithubTrendApplication)
}
