package com.alexnassif.mobile.workoutbywill.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexnassif.mobile.workoutbywill.Model.ExerciseDetail
import com.alexnassif.mobile.workoutbywill.R

/**
 * Created by alexnassif on 2/1/18.
 */
class WorkoutDetailAdapter(val context: Context, var workoutDetailList: List<ExerciseDetail>, private val itemClick: (Int) -> Unit)
    : RecyclerView.Adapter<WorkoutDetailAdapter.WorkoutDetailHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutDetailHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.workout_detail_cell, parent, false)
        return WorkoutDetailHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return workoutDetailList.count()
    }

    fun setList(list: List<ExerciseDetail>){
        workoutDetailList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: WorkoutDetailHolder, position: Int) {
        holder.bindWorkoutDetail(context, workoutDetailList.get(position))
    }

    inner class WorkoutDetailHolder(v: View, val itemClick: (Int) -> Unit): RecyclerView.ViewHolder(v){

        val reps = v.findViewById<TextView>(R.id.repsTextView)
        val sets = v.findViewById<TextView>(R.id.setsTextView)
        val rest = v.findViewById<TextView>(R.id.restTextView)
        val exName = v.findViewById<TextView>(R.id.exerciseWkDetailLbl)
        val image = v.findViewById<ImageView>(R.id.workoutDetailImageView)

        fun bindWorkoutDetail(context: Context, exerciseIns: ExerciseDetail){
            reps?.text = exerciseIns.reps
            sets?.text = exerciseIns.sets
            rest?.text = exerciseIns.rest
           // Glide.with(context).load(exerciseIns.imageLocation).into(image!!)

            itemView.setOnClickListener {
                this.itemClick(Integer.parseInt(exerciseIns.exerciseId))
            }
        }



    }
}