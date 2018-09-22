package com.example.babamalik.gamestoneww.Activities

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentActivity
import com.example.babamalik.gamestoneww.R
import com.example.babamalik.gamestoneww.UI.Dashboard.DashboardFragment
import com.example.babamalik.gamestoneww.UI.Home.HomeFragment
import com.example.babamalik.gamestoneww.UI.Notifications.NotificationsFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MenuItem
import com.example.babamalik.gamestoneww.Adapter.ViewPagerAdapter
import com.example.babamalik.gamestoneww.model.Games
import kotlinx.android.synthetic.main.signup_fragment.*




class MainActivity : FragmentActivity() {


    //private var slideAdapter : SlideAdapter?= null
    private var prevMenuItem : MenuItem?=null
    private lateinit var  mContext : Context
    private var games : List<Games> = ArrayList()

    private var mList : List<Long> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mOnNavigationItemSelectedListener =
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_home -> {
                            viewPager.setCurrentItem(0)
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.navigation_dashboard -> {
                            viewPager.setCurrentItem(1)
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.navigation_notifications -> {
                            viewPager.setCurrentItem(2)
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    false
                }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem?.setChecked(false)
                } else {
                    navigation.getMenu().getItem(0).setChecked(false)
                }
                Log.d("page", "onPageSelected: $position")
                navigation.getMenu().getItem(position).setChecked(true)
                prevMenuItem = navigation.getMenu().getItem(position)
//                val fragment = (viewPager.getAdapter() as ViewPagerAdapter).getFragment(position)
//
//                if (position ==1 && fragment != null) {
//                    fragment.onResume()
//                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


//        slideAdapter = SlideAdapter(this)
//        viewPager.setAdapter(slideAdapter)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setupViewPager(viewPager)
        viewPager.getAdapter()?.notifyDataSetChanged()
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val homeFragment = HomeFragment()
        val dashboardFragment = DashboardFragment()
        val notificationsFragment = NotificationsFragment()
        adapter.addFragment(homeFragment)
        adapter.addFragment(dashboardFragment)
        adapter.addFragment(notificationsFragment)
        viewPager?.setAdapter(adapter)
    }


    override fun onStart() {
        super.onStart()
        //updatingUI()
    }
}