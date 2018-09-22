package com.example.babamalik.gamestoneww

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.babamalik.gamestoneww.Activities.Login_Activity
import kotlinx.android.synthetic.main.splash_screen.*

class SplashActivity : AppCompatActivity(){
    private var fromtop : Animation?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        fromtop = AnimationUtils.loadAnimation(this, R.anim.from_top)

     //   splash_image_logo.animation = fromtop

        splash_image_logo.setOnClickListener {
            val intent = Intent(this, Login_Activity::class.java)
            startActivity(intent)
            finish()
        }

    }
}