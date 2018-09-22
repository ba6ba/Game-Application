package com.example.babamalik.gamestoneww.model

class GamesDetails(var id: Long?, var name: String, var slug: String,
                   var url: String, var summary: String, var storyline: String,
                   var rating: Double?, var popularity: Double?,
                   var games: List<GamesDetails>, var keywords: List<Long>,
                   var themes: List<Theme>, var genres: List<Genre>,
                   var release_dates: List<Release_Date>, var screenshots: List<Screenshot>,
                   var videos: List<Video>, var cover: List<Cover>, var pegi: List<PEGI>,
                   var websites: List<Website>)
