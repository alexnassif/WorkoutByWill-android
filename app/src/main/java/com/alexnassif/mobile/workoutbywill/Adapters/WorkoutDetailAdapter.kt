package com.alexnassif.mobile.workoutbywill.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.alexnassif.mobile.workoutbywill.Model.Workout
import android.view.View
import android.widget.TextView
import com.alexnassif.mobile.workoutbywill.Model.ExerciseDetail
import com.alexnassif.mobile.workoutbywill.R
import kotlinx.android.synthetic.main.workout_detail_cell.view.*

/**
 * Created by alexnassif on 2/1/18.
 */
class WorkoutDetailAdapter(val context: Context, val workoutDetailList: MutableList<ExerciseDetail>, val itemClick: (String) -> Unit)
    : RecyclerView.Adapter<WorkoutDetailAdapter.WorkoutDetailHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WorkoutDetailHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: WorkoutDetailHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class WorkoutDetailHolder(v: View?, val itemClick: (String) -> Unit): RecyclerView.ViewHolder(v){

        val reps = v?.findViewById<TextView>(R.id.repsTextView)
        val sets = v?.findViewById<TextView>(R.id.setsTextView)
        val rest = v?.findViewById<TextView>(R.id.restTextView)
        val exName = v?.findViewById<TextView>(R.id.exerciseWkDetailLbl)

        fun bindWorkoutDetail(context: Context, exerciseIns: ExerciseDetail){
            reps?.text = exerciseIns.reps
            sets?.text = exerciseIns.sets
            rest?.text = exerciseIns.rest
            exName?.text = exerciseIns.exerciseName

            itemView.setOnClickListener {
                itemClick(exerciseIns.exerciseName)
            }
        }



    }
}