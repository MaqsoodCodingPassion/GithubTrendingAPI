package com.msk.github.trend.di.module

import com.msk.github.trend.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract fun bindActivityMain(): MainActivity
}