package com.alexnassif.mobile.workoutbywill.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alexnassif.mobile.workoutbywill.Model.Exercise
import com.alexnassif.mobile.workoutbywill.R

class ExerciseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        val exercise = intent.getParcelableExtra<Exercise>("exercise")
        var fragment = ExerciseDetailFragment.newInstance(exercise)

        supportFragmentManager.beginTransaction()
                .add(R.id.exerciseDetailActivityFrame, fragment, fragment.javaClass.simpleName)
                .commit()

    }
}
