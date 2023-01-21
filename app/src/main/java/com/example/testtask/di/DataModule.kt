package com.example.testtask.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.testtask.common.Constants
import com.example.testtask.data.network.API
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideApi(@ApplicationContext context: Context): API {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(ChuckerInterceptor.Builder(context).build()).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }

}