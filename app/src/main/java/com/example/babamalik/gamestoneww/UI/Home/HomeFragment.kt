package com.example.babamalik.gamestoneww.UI.Home

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.babamalik.gamestoneww.R
import com.example.babamalik.gamestoneww.model.GameHomeData
import com.example.babamalik.gamestoneww.model.Games
import com.example.babamalik.gamestoneww.network.IGDBInterface
import com.example.babamalik.gamestoneww.network.IGDBNetwork
import com.example.babamalik.gamestoneww.UI.Home.MainAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.row.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap
import com.example.babamalik.gamestoneww.network.IGDBRetrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment(){

    internal var rootView: View?=null
    private lateinit var  mContext : HomeFragment

    private lateinit var mLayoutManager : RecyclerView.LayoutManager
    private lateinit var mAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>
    internal var games: List<Games>? = null
    internal var gamesDetails: MutableList<List<GameHomeData>>? = null

    private var list = arrayListOf<List<GameHomeData>>()
    private var list1 = arrayListOf<List<GameHomeData>>()

    private lateinit var progessDialog : ProgressDialog
    private  val data = HashMap<String, String>()

    private var igdbRetrofit : IGDBRetrofit?=null

    private lateinit var igdbNetwork : IGDBNetwork
    private companion object {
        init {
            var current_page : Int = 1
        }
    }
    private var mDataSet : ArrayList<String> = ArrayList()
    private var ival = 1
    private var loadLimit = 10

    private var finalData : List<GameHomeData> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        mContext = this@HomeFragment
        igdbNetwork = IGDBNetwork(context as Context)
        progessDialog = ProgressDialog(rootView?.context)
        progessDialog.setMessage("Loading")
        progessDialog.show()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getLatestData()
        getMostFeaturedData()
    }

    override fun onResume() {
        super.onResume()
    }

     override fun onStart() {
        super.onStart()
     }

    fun settingupUI(response: ArrayList<List<GameHomeData>>,flag : Boolean){

        val dataList : ArrayList<GameHomeData> = ArrayList()
        for (j in 0 until response.size) {
            for (i in 0 until response[0].size) {
                dataList.add(GameHomeData(response[j][i].id as Long, response[j][i].name,response[j][i].rating as Double,
                        response[j][i].genres, response[j][i].cover))
            }
        }
        if(flag){
            Log.d("BASIT","BABA")
            buildViewLatest(dataList)
        }
        else if(!flag){
            buildViewFeatured(dataList)
        }

    }

    private fun QueryingGamesLatest() : HashMap<String, String> {
        data["filter[cover][exists]"] = "true"
        data["filter[rating][exists]"] = "true"
        data["filter[genres][exists]"] = "true"
        data["fields"] = "id"
        data["order"] = "release_dates.date:asc:max"
        data["limit"] = 10.toString()
        data["offset"] = 0.toString()

        return data
    }

    private fun QueryingGamesFeatured() : HashMap<String, String> {
        data["filter[cover][exists]"] = "true"
        data["filter[rating][exists]"] = "true"
        data["filter[genres][exists]"] = "true"
        data["fields"] = "id"
        data["order"] = "popularity:asc:max"
        data["limit"] = 10.toString()
        data["offset"] = 0.toString()

        return data
    }


    fun getLatestData(){


       val builder = Retrofit.Builder().baseUrl(IGDBRetrofit.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .client(igdbNetwork.getIGDB())
               .build()

        val igdbInterface = builder.create(IGDBInterface::class.java)
        val gamesCall = igdbInterface.games(QueryingGamesLatest())
        gamesCall.enqueue(object : Callback<List<Games>> {
            override fun onResponse(call: Call<List<Games>>, response: Response<List<Games>>) {
                games = response.body()
                for (i in games!!.indices) {
                    val getgameHomeCall =
                            igdbInterface.getGamesHomeDetails(games?.get(i)?.id as Long,"name,rating,cover,genres")

                    getgameHomeCall.enqueue(object : Callback<List<GameHomeData>> {
                        override fun onResponse(call: Call<List<GameHomeData>>, response: Response<List<GameHomeData>>) {
                            list.add(response.body() as List<GameHomeData>)
                            Log.d("add",list.size.toString())
                            if(list.size.equals(games?.size)){
                                settingupUI(list,true)
                            }
                            if(response.raw().cacheResponse()!=null){
                                Log.d("True","from cache")
                            }

                            if(response.raw().networkResponse()!=null){
                                Log.d("False","from server")
                            }
                        }

                        override fun onFailure(call: Call<List<GameHomeData>>, t: Throwable) {
                            Log.d("chutya","hai")
                        }
                    })
                }
            }

            override fun onFailure(call: Call<List<Games>>, t: Throwable) {
                Log.d("FAAA",t.localizedMessage)
            }
        })
    }




    fun getMostFeaturedData(){

        val builder = Retrofit.Builder().baseUrl(IGDBRetrofit.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(igdbNetwork.getIGDB())
                .build()

        val igdbInterface = builder.create(IGDBInterface::class.java)
        val gamesCall = igdbInterface?.games(QueryingGamesFeatured())
        gamesCall?.enqueue(object : Callback<List<Games>> {
            override fun onResponse(call: Call<List<Games>>, response: Response<List<Games>>) {
                games = response.body()
                for (i in games!!.indices) {
                    val getgameHomeCall =
                            igdbInterface.getGamesHomeDetails(games?.get(i)?.id as Long,"name,rating,cover,genres")

                    getgameHomeCall.enqueue(object : Callback<List<GameHomeData>> {
                        override fun onResponse(call: Call<List<GameHomeData>>, response: Response<List<GameHomeData>>) {
                            list1.add(response.body() as List<GameHomeData>)
                            Log.d("add",list1.size.toString())
                            if(list1.size.equals(games?.size)){
                                settingupUI(list1,false)
                            }
                        }

                        override fun onFailure(call: Call<List<GameHomeData>>, t: Throwable) {
                            Log.d("ganda","lad")

                        }
                    })
                }
            }

            override fun onFailure(call: Call<List<Games>>, t: Throwable) {
                Log.d("BHAHA","SSS")

            }
        })
    }

    fun buildViewFeatured(data: ArrayList<GameHomeData>) {
        mLayoutManager = GridLayoutManager(rootView?.context,1,GridLayoutManager.HORIZONTAL,false)
        GamesrecyclerFeaturedView.setHasFixedSize(true)
        GamesrecyclerFeaturedView.layoutManager = mLayoutManager
        mAdapter = MainAdapter(data,mContext)
        GamesrecyclerFeaturedView.setAdapter(mAdapter)
        progessDialog.dismiss()
        Log.d("SIZE",data.size.toString())
        //updatingUI()
    }


    fun buildViewLatest(data: ArrayList<GameHomeData>) {
        mLayoutManager = GridLayoutManager(rootView?.context,1,GridLayoutManager.HORIZONTAL,false)
            GamesrecyclerLatestView.setHasFixedSize(true)
            GamesrecyclerLatestView.layoutManager = mLayoutManager
            mAdapter = MainAdapter(data,mContext)
            GamesrecyclerLatestView.setAdapter(mAdapter)
            progessDialog.dismiss()
        Log.d("SIZE",data.size.toString())
    }


    fun generateFeatured(data: ArrayList<GameHomeData>) {

        GamesrecyclerFeaturedView.setHasFixedSize(true)
        //mLayoutManager = GridLayoutManager(rootView?.context,2,GridLayoutManager.HORIZONTAL,false)
        mLayoutManager = LinearLayoutManager(rootView?.context, LinearLayoutManager.HORIZONTAL,false)
        GamesrecyclerFeaturedView.layoutManager = mLayoutManager
        mAdapter = MainAdapter(data,mContext)
        GamesrecyclerFeaturedView.setAdapter(mAdapter)
//loadData(ival)
//        GamesrecyclerLatestView.setOnScrollListener(object : EndlessRecyclerOnScrollListener(
//                mLayoutManager as GridLayoutManager) {
//            override fun onLoadMore(current_page: Int) {
//                // do somthing...
//
//                loadMoreData(current_page)
//
//            }
//
//        })
    }


    //    private fun loadData(current_page: Int,response: Response<List<GameHomeData>>) {
//
//        // I have not used current page for showing demo, if u use a webservice
//        // then it is useful for every call request
//
//        for (i in ival..loadLimit) {
//            var dataList : ArrayList<GameHomeData> = ArrayList()
//            dataList.add(GameHomeData(response.body()?.get(i)?.id
//            ,response.body()!!.get(i).name,response.body()!!.get(i).rating,response.body().get(i).genres)
//
//            ival++
//        }
//
//    }
//
//    private fun loadMoreData(current_page: Int) {
//
//        // I have not used current page for showing demo, if u use a webservice
//        // then it is useful for every call request
//
//        loadLimit = ival + 10
//
//        for (i in ival..loadLimit) {
//            val st = Student("Student $i", "androidstudent" + i
//                    + "@gmail.com", false)
//
//            studentList.add(st)
//            ival++
//        }
//
//        mAdapter.notifyDataSetChanged()
//
//    }
//


}