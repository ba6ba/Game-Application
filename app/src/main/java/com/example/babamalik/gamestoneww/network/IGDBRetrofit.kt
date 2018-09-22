package com.example.babamalik.gamestoneww.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object IGDBRetrofit {
    private var retrofit: Retrofit? = null
    var BASE_URL = "https://api-endpoint.igdb.com/"
    fun getRetrofit(): Retrofit {

//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        val client = OkHttpClient.Builder()
//                .addInterceptor(httpLoggingInterceptor)
//                .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        return retrofit!!
    }
}
