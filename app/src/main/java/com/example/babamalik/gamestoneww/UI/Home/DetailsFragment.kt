package com.example.babamalik.gamestoneww.UI.Home

import android.app.Fragment
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SlidingPaneLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.babamalik.gamestoneww.R
import com.example.babamalik.gamestoneww.UI.Home.HomeFragment
import com.example.babamalik.gamestoneww.UI.Home.MainAdapter
import kotlinx.android.synthetic.main.game_details_layout.*

class DetailsFragment : android.support.v4.app.Fragment(){

    internal var rootView: View?=null
    private lateinit var  mContext : DetailsFragment

    private lateinit var mLayoutManager : RecyclerView.LayoutManager
    private lateinit var mAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>

    private lateinit var mDataSet : ArrayList<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.game_details_layout, container, false)
        mContext = this@DetailsFragment
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        settingupUI()
    }


    fun settingupUI() {
        mDataSet = ArrayList()

        for (i in 1..8){
            mDataSet.add("New Title # $i");
        }
        generateLatest(mDataSet)

       // settingupSlidePaneLayout()
    }

//    private fun settingupSlidePaneLayout() {
//        slidingPaneLayout.setPanelSlideListener(object : SlidingPaneLayout.SimplePanelSlideListener() {
//            override fun onPanelClosed(panel: View) {
//                when(panel.id){
//                    R.id.detailsImage1 -> {
//                        slidingPaneLayout.closePane()
//                    }
//                    R.id.detailsImage2 -> {
//                        slidingPaneLayout.isSlideable
//                    }
//                }
//            }
//
//            override fun onPanelOpened(panel: View) {
//                when(panel.id){
//                    R.id.detailsImage1 -> {
//                        detailsImage1.setImageResource(R.drawable.image2)
//                    }
//                    R.id.detailsImage2 -> {
//                        detailsImage1.setImageResource(R.drawable.image3)
//                    }
//                }
//            }
//
//            override fun onPanelSlide(panel: View, slideOffset: Float) {
//
//            }
//        })
//    }


    fun generateLatest(data: ArrayList<String>) {
//
//        GamesrecyclerLatestView.setHasFixedSize(true)
//        mLayoutManager = LinearLayoutManager(rootView?.context, LinearLayoutManager.HORIZONTAL,false)
//        GamesrecyclerLatestView.layoutManager = mLayoutManager
//        mAdapter = MainAdapter(data,mContext)
//        GamesrecyclerLatestView.setAdapter(mAdapter)

    }


}