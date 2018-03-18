package com.alexnassif.mobile.workoutbywill.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alexnassif.mobile.workoutbywill.Model.Workout
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alexnassif.mobile.workoutbywill.Model.ExerciseDetail
import com.alexnassif.mobile.workoutbywill.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.workout_detail_cell.view.*

/**
 * Created by alexnassif on 2/1/18.
 */
class WorkoutDetailAdapter(val context: Context, val workoutDetailList: MutableList<ExerciseDetail>, val itemClick: (String, String) -> Unit)
    : RecyclerView.Adapter<WorkoutDetailAdapter.WorkoutDetailHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WorkoutDetailHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.workout_detail_cell, parent, false)
        return WorkoutDetailHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return workoutDetailList.count()
    }

    override fun onBindViewHolder(holder: WorkoutDetailHolder, position: Int) {
        holder.bindWorkoutDetail(context, workoutDetailList.get(position))
    }

    inner class WorkoutDetailHolder(v: View?, val itemClick: (String, String) -> Unit): RecyclerView.ViewHolder(v){

        val reps = v?.findViewById<TextView>(R.id.repsTextView)
        val sets = v?.findViewById<TextView>(R.id.setsTextView)
        val rest = v?.findViewById<TextView>(R.id.restTextView)
        val exName = v?.findViewById<TextView>(R.id.exerciseWkDetailLbl)
        val image = v?.findViewById<ImageView>(R.id.workoutDetailImageView)

        fun bindWorkoutDetail(context: Context, exerciseIns: ExerciseDetail){
            reps?.text = exerciseIns.reps
            sets?.text = exerciseIns.sets
            rest?.text = exerciseIns.rest
            exName?.text = exerciseIns.exerciseName
            Glide.with(context).load(exerciseIns.imageLocation).into(image!!)

            itemView.setOnClickListener {
                itemClick(exerciseIns.keyName, exerciseIns.category)
            }
        }



    }
}