package com.example.babamalik.gamestoneww.UI.Dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.babamalik.gamestoneww.R

class DashboardFragment : Fragment(){
    internal var rootView: View?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return rootView
    }

}