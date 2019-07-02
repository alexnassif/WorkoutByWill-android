package com.alexnassif.mobile.workoutbywill.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexnassif.mobile.workoutbywill.Model.Category
import com.alexnassif.mobile.workoutbywill.R
import com.bumptech.glide.Glide

/**
 * Created by raymond on 1/21/18.
 */
class BodyPartRecyclerAdapter(private val context: Context, private val exCategories: List<Category>, private val itemClick: (Category) -> Unit)
    : RecyclerView.Adapter<BodyPartRecyclerAdapter.CellHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.bp_ex_rowcell, parent, false)
        return CellHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return exCategories.count()
    }

    override fun onBindViewHolder(holder: CellHolder, position: Int) {
        holder.bindCategory(exCategories[position], context)
    }

    inner class CellHolder(v: View, private val itemClick: (Category) -> Unit): RecyclerView.ViewHolder(v){
        private val categoryName = v.findViewById<TextView>(R.id.bodypartTextView)
        private val categoryImage = v. findViewById<ImageView>(R.id.bp_ex_imageView)

        fun bindCategory(category: Category, context: Context){
            val resourceId = context.resources.getIdentifier(category.image, "drawable", context.packageName)
            categoryName?.text = category.uiName

            Glide.with(context).load(resourceId).into(categoryImage!!)

            itemView.setOnClickListener {
                itemClick(category)
            }
        }
    }



}