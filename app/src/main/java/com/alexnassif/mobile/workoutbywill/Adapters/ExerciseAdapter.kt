package com.alexnassif.mobile.workoutbywill.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alexnassif.mobile.workoutbywill.Model.Exercise
import com.alexnassif.mobile.workoutbywill.R
import com.bumptech.glide.Glide

/**
 * Created by raymond on 1/22/18.
 */
class ExerciseAdapter(val context: Context, val exercises: MutableList<Exercise>, val itemClick: (Exercise) -> Unit)
    : RecyclerView.Adapter<ExerciseAdapter.ExerciseHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ExerciseHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.ex_type_cell_grid, parent, false)
        return ExerciseHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        holder.bindExercise(exercises.get(position), context)
    }

    override fun getItemCount(): Int {
        return exercises.count()
    }

    inner class ExerciseHolder(v: View?, val itemClick: (Exercise) -> Unit): RecyclerView.ViewHolder(v){
        val exerciseName = v?.findViewById<TextView>(R.id.exerciseTextView)
        val exerciseImage = v?.findViewById<ImageView>(R.id.exerciseImageView)

        fun bindExercise(exercise: Exercise, context: Context){
            exerciseName?.text = exercise.name
            Glide.with(context).load(exercise.images.get(0)).into(exerciseImage!!)

            itemView.setOnClickListener {
                itemClick(exercise)
            }
        }


    }
}