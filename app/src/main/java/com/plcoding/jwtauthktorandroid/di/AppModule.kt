package com.plcoding.jwtauthktorandroid.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.plcoding.jwtauthktorandroid.auth.AuthApi
import com.plcoding.jwtauthktorandroid.auth.AuthRepository
import com.plcoding.jwtauthktorandroid.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

     @Provides
     @Singleton
     fun providesAuthApi(): AuthApi {
         return Retrofit.Builder()
             .baseUrl("http://10.0.2.2:8080/")
             .addConverterFactory(MoshiConverterFactory.create())
             .build()
             .create()
     }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefs: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(api, prefs)
    }
}