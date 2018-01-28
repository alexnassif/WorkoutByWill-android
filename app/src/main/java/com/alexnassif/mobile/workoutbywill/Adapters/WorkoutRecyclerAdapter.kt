package com.alexnassif.mobile.workoutbywill.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alexnassif.mobile.workoutbywill.Model.Workout
import com.alexnassif.mobile.workoutbywill.R
import kotlinx.android.synthetic.main.workout_cell.view.*

/**
 * Created by raymond on 1/27/18.
 */
class WorkoutRecyclerAdapter(val context: Context, val workoutList: MutableList<Workout>, val itemClick: (Workout) -> Unit)
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

    inner class WorkoutHolder(v:View?, itemClick:(Workout) -> Unit):RecyclerView.ViewHolder(v) {
        val workoutName = v?.findViewById<TextView>(R.id.workoutNameTextView)
        val workoutImage = v?.findViewById<ImageView>(R.id.workoutImageView)

        fun bindWorkout(workout: Workout, context: Context){

            itemView.setOnClickListener { itemClick(workout) }

        }

    }

}