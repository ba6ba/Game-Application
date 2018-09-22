package com.example.babamalik.gamestoneww.UI.Home

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.babamalik.gamestoneww.R
import com.example.babamalik.gamestoneww.UI.Home.DetailsFragment
import java.util.ArrayList
import com.example.babamalik.gamestoneww.UI.Home.HomeFragment
import com.example.babamalik.gamestoneww.model.GameHomeData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.*
import kotlinx.android.synthetic.main.row.view.*

internal class MainAdapter(private var mDataSet: ArrayList<GameHomeData> ,
private var mContext : Fragment) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    val light_font = Typeface.createFromAsset(mContext.activity?.assets, "fonts/titilliumwebextralight.ttf")
    val regular_font = Typeface.createFromAsset(mContext.activity?.assets, "fonts/titilliumwebregular.ttf")


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MainAdapter.ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MainAdapter.ViewHolder, i: Int) {
        viewHolder.bindData(i)
        viewHolder.updatingUI()
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun updatingUI(){
            itemView.rating.setTypeface(light_font)
            itemView.GameTitle.setTypeface(regular_font)
            itemView.GameGenretext.setTypeface(light_font)
        }

        fun bindData(i : Int) {
            itemView.GameTitle.text = (mDataSet.get(i).name)
            itemView.GameGenretext.text = (mDataSet[i].genres.get(0).toString())
            itemView.rating.text = mDataSet[i].rating.toString()

            Picasso.with(mContext.context).
                    load("https://images.igdb.com/igdb/image/upload/t_cover_big/"+mDataSet[i].cover.cloudinary_id+".jpg")
//                    .centerCrop()
                    .into(itemView.cardViewImage)

            //itemView.cardViewImage.setImageResource(R.drawable.cover)
            itemView.row_card.setOnClickListener {

//                Toast.makeText(mContext,"chal ha",Toast.LENGTH_SHORT).show()
                var fragment = DetailsFragment()

                var manager: FragmentManager? =
                        (mContext as Fragment).fragmentManager

                manager?.beginTransaction()
                        ?.replace(R.id.container,DetailsFragment() as Fragment)
                        ?.addToBackStack(null)?.commit()
            }

        }

    }


}

