package com.alexnassif.mobile.workoutbywill.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexnassif.mobile.workoutbywill.Model.Program
import com.alexnassif.mobile.workoutbywill.R
import com.bumptech.glide.Glide

/**
 * Created by raymond on 1/27/18.
 */
class WorkoutRecyclerAdapter(val context: Context, var workoutList: List<Program>, val itemClick: (Program) -> Unit)
    : RecyclerView.Adapter<WorkoutRecyclerAdapter.WorkoutHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.workout_cell, parent, false)
        return WorkoutHolder(view, itemClick)
    }

    override fun getItemCount(): Int {

        return workoutList.count()
    }

    override fun onBindViewHolder(holder: WorkoutHolder, position: Int) {
        holder.bindWorkout(workoutList.get(position), context)

    }

    fun setList(workoutList: List<Program>){
        this.workoutList = workoutList
        notifyDataSetChanged()
    }

    inner class WorkoutHolder(v:View, val itemClick:(Program) -> Unit): RecyclerView.ViewHolder(v) {
        private val workoutName = v.findViewById<TextView>(R.id.workoutNameTextView)
        private val workoutImage = v.findViewById<ImageView>(R.id.workoutImageView)
        private val programWeeks = v.findViewById<TextView>(R.id.weeks)

        fun bindWorkout(program: Program, context: Context){
            workoutName!!.text = program.name
            programWeeks!!.text = "Weeks: " + program.weeks
            Glide.with(context).load(R.drawable.placeholderpic).into(workoutImage!!)
            itemView.setOnClickListener { itemClick(program) }

        }

    }

}