//package com.example.android_app_demo.hilt2.repo
//
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ActivityComponent
//
//@InstallIn(ActivityComponent::class)
//@Module
//class UserModule {
//    @Provides
//    fun providesUserRepository(): UserRepository {
//        return FireBaseRepository()
//    }
//}