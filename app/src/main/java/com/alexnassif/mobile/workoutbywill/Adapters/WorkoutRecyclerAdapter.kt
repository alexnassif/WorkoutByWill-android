package com.alexnassif.mobile.workoutbywill.Adapters

import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.alexnassif.mobile.workoutbywill.Model.Workout
import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Services.DataService
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.workout_cell.view.*

/**
 * Created by raymond on 1/27/18.
 */
class WorkoutRecyclerAdapter(val context: Context, var workoutList: List<Workout>, val itemClick: (Workout) -> Unit)
    :RecyclerView.Adapter<WorkoutRecyclerAdapter.WorkoutHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WorkoutHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.workout_cell, parent, false)
        return WorkoutHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return workoutList.count()
    }

    override fun onBindViewHolder(holder: WorkoutHolder, position: Int) {
        holder.bindWorkout(workoutList.get(position), context)

    }

    fun setList(workoutList: List<Workout>){
        this.workoutList = workoutList
        notifyDataSetChanged()
    }

    inner class WorkoutHolder(v:View?, val itemClick:(Workout) -> Unit):RecyclerView.ViewHolder(v) {
        private val workoutName = v?.findViewById<TextView>(R.id.workoutNameTextView)
        private val workoutImage = v?.findViewById<ImageView>(R.id.workoutImageView)

        fun bindWorkout(workout: Workout, context: Context){
            workoutName!!.text = workout.name
            Glide.with(context).load(R.drawable.placeholderpic).into(workoutImage!!)
            itemView.setOnClickListener { itemClick(workout) }

        }

    }

}