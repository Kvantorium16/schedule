package com.example.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class Adapter(newModels: List<Model?>?, private var context: Context?) : PagerAdapter() {

    private var models: List<Model>? = null
    private lateinit var layoutInflater: LayoutInflater

    init {
        this.models = newModels as List<Model>?
    }

    override fun getCount(): Int {
        return models!!.size

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.card_item, container, false)

        val imageView: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)

        models?.get(position)?.let { imageView.setImageResource(it.getImage()) }
        title.setText(models?.get(position)?.getTitle())
        description.setText(models?.get(position)?.getDesc())


        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}