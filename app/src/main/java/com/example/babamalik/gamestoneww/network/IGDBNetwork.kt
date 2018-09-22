package com.example.babamalik.gamestoneww.network

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import com.example.babamalik.gamestoneww.model.Games
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.http.QueryMap

class IGDBNetwork (internal var context: Context){
     private val cacheSize = 10 * 1024 * 1024
    var  mContext = context

    private var igdbRetrofit1: IGDBRetrofit?=null

    private val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    fun getIGDB() : OkHttpClient{

        val cache = Cache(mContext.cacheDir, cacheSize.toLong())
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor { chain ->
                    var request = chain.request()
                    if (!isNetworkAvailable) {
                        val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale \
                        request = request
                                .newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                                .build()
                    }
                    chain.proceed(request)
                }
                .build()

        return client
    }


}