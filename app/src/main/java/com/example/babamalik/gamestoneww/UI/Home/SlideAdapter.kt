package com.example.sarwan.myapplication

import android.content.Context
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.babamalik.gamestoneww.R
import kotlinx.android.synthetic.main.slider.*
import kotlinx.android.synthetic.main.activity_main.*

class SlideAdapter(internal var context: Context) : PagerAdapter(){

    internal var layoutInflater: LayoutInflater? = null

    var images = intArrayOf(R.drawable.image1,
            R.drawable.image2, R.drawable.image3,
            R.drawable.image4)

    var images_title = arrayOf("FIRST",
            "SECOND", "THIRD",
            "FOURTH")

    var images_desc = arrayOf("DESC_FIRST",
            "DESC_SECOND", "DESC_THIRD",
            "DESC_FOURTH")

    var bg_colors = intArrayOf(Color.rgb(55, 55, 55),
            Color.rgb(45, 45, 45),
            Color.rgb(25, 25, 25),
            Color.rgb(88, 88, 88))



    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj as LinearLayout
    }

    override fun getCount(): Int {
        return images_title.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater?.inflate(R.layout.slider, container, false)

        val linearLayout = view?.findViewById(R.id.SlidelinearLayout) as LinearLayout
        val imageView = view.findViewById(R.id.slideimg) as ImageView
        val titletextView = view.findViewById(R.id.title) as TextView
        val desctextView = view.findViewById(R.id.description) as TextView

        linearLayout.setBackgroundColor(bg_colors[position])
        imageView.setImageResource(images[position])
        titletextView.setText(images_title[position])
        desctextView.setText(images_desc[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}