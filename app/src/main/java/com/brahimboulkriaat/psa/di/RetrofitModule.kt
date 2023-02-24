package com.brahimboulkriaat.psa.di

import com.brahimboulkriaat.psa.retrofit.CityService
import com.brahimboulkriaat.psa.retrofit.WeatherService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Provides
    @Singleton
    fun providesGsonBuilder(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Provides
    @Singleton
    fun providePkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    @Named("MockApi")
    fun providesRetrofitMockApi(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://63f9026123ad39d6dd9804d8.mockapi.io/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    @Singleton
    @Named("OpenWeatherMap")
    fun providesRetrofitOpenWeatherMap(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    @Singleton
    fun providesCityService(@Named("MockApi") retrofit: Retrofit): CityService =
        retrofit.create(CityService::class.java)

    @Provides
    @Singleton
    fun providesWeatherService(@Named("WeatherApi") retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}