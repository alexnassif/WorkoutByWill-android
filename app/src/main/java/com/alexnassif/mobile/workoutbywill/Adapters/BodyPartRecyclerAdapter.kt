package com.alexnassif.mobile.workoutbywill.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alexnassif.mobile.workoutbywill.Model.Category
import com.alexnassif.mobile.workoutbywill.R
import com.bumptech.glide.Glide

/**
 * Created by raymond on 1/21/18.
 */
class BodyPartRecyclerAdapter(val context: Context, val exCategories: List<Category>, val itemClick: (Category) -> Unit)
    : RecyclerView.Adapter<BodyPartRecyclerAdapter.CellHolder>() {
    override fun getItemCount(): Int {
        return exCategories.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CellHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bp_ex_rowcell, parent, false)
        return CellHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: CellHolder, position: Int) {
        holder?.bindCategory(exCategories[position], context)
    }

    inner class CellHolder(v: View?, val itemClick: (Category) -> Unit): RecyclerView.ViewHolder(v){
        val categoryName = v?.findViewById<TextView>(R.id.bodypartTextView)
        val categoryImage = v?. findViewById<ImageView>(R.id.bp_ex_imageView)

        fun bindCategory(category: Category, context: Context){
            val resourceId = context.resources.getIdentifier(category.image, "drawable", context.packageName)
            categoryName?.text = category.title
            if (categoryImage != null) {
                Glide.with(context).load(resourceId).into(categoryImage)
            }

            itemView.setOnClickListener {
                itemClick(category)
            }
        }
    }



}