package com.example.babamalik.gamestoneww.network

import com.example.babamalik.gamestoneww.model.GameHomeData
import com.example.babamalik.gamestoneww.model.Games
import com.example.babamalik.gamestoneww.model.GamesDetails
import retrofit2.Call
import retrofit2.http.*

interface IGDBInterface {

    @Headers("user-key: 314541471c710258de3e4bf7672482bf", "accept: application/json")
    @GET("games/")
    fun games(@QueryMap options : Map<String, String>): Call<List<Games>>


    @Headers("user-key: 314541471c710258de3e4bf7672482bf", "accept: application/json")
    @GET("games/{id}")
    fun getGamesHomeDetails(@Path("id") id: Long, @Query("fields") fields : String): Call<List<GameHomeData>>

    @Headers("user-key: 314541471c710258de3e4bf7672482bf", "accept: application/json")
    @GET("games/{id}")
    fun getGamesDetails(@Path("id") id: Long): Call<List<GamesDetails>>



}
