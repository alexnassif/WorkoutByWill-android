package com.alexnassif.mobile.workoutbywill.Model

/**
 * Created by alexnassif on 2/3/18.
 */
data class ExerciseDetail(val exerciseName: String,
                     val reps: String, val rest: String, val sets: String,
                          val imageLocation: String, val exerciseId: String, val exercisePic: List<String> ) {
}