package com.example.babamalik.gamestoneww.UI.Notifications

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.babamalik.gamestoneww.R
import kotlinx.android.synthetic.main.fragment_notifications.*


class NotificationsFragment : Fragment(){
    internal var rootView: View?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.signup_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //image.setImageResource(R.drawable.cover)
    }
}