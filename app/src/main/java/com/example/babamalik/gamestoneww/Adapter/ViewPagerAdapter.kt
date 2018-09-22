package com.example.babamalik.gamestoneww.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import java.nio.file.Files.size
import java.util.ArrayList
import android.view.ViewGroup




class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private var mFragmentTags  = HashMap<Int,String>()
    private var Manager = manager

//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val obj = super.instantiateItem(container, position)
//        if (obj is Fragment) {
//            // record the fragment tag here.
//            val tag = obj.tag
//            mFragmentTags[position] = tag as String
//        }
//        return obj
//    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

//    fun getFragment(position: Int): Fragment? {
//        val tag = mFragmentTags[position] ?: return null
//        return Manager.findFragmentByTag(tag)
//    }

    private val mFragmentList = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

}