package com.arildojr.appband.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.arildojr.appband.R
import com.bumptech.glide.Glide

class MainViewPagerAdapter(private val list: List<String>) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_viewpager_full, null)
        val imageView = view.findViewById<ImageView>(R.id.ivBanner)

        Glide.with(container.context).load(list[position]).into(imageView)

        container.addView(view)

        return view
    }

    override fun getCount() = list.size

    override fun isViewFromObject(view: View, any: Any) = view == any

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) =
        container.removeView(any as View)
}