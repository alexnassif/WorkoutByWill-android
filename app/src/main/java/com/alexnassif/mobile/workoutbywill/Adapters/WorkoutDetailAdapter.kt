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
import com.bumptech.glide.Glide

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
        holder.bindWorkoutDetail(context, workoutDetailList[position])
    }

    inner class WorkoutDetailHolder(v: View, private val itemClick: (Int) -> Unit): RecyclerView.ViewHolder(v){

        private val reps = v.findViewById<TextView>(R.id.repsTextView)
        private val sets = v.findViewById<TextView>(R.id.setsTextView)
        private val rest = v.findViewById<TextView>(R.id.restTextView)
        private val exName = v.findViewById<TextView>(R.id.exerciseWkDetailLbl)
        private val image = v.findViewById<ImageView>(R.id.workoutDetailImageView)

        fun bindWorkoutDetail(context: Context, exerciseIns: ExerciseDetail){
            exName.text = exerciseIns.exerciseName
            reps.text = exerciseIns.reps
            sets.text = exerciseIns.sets
            rest.text = exerciseIns.rest
            Glide.with(context).load(exerciseIns.exercisePic[0]).into(image!!)

            itemView.setOnClickListener {
                itemClick(Integer.parseInt(exerciseIns.exerciseId))
            }
        }



    }
}