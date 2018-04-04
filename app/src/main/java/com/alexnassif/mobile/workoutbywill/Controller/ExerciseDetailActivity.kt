package com.alexnassif.mobile.workoutbywill.Controller

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alexnassif.mobile.workoutbywill.Model.Exercise
import com.alexnassif.mobile.workoutbywill.R
import kotlinx.android.synthetic.main.activity_exercise_detail.*

class ExerciseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        val exercise = intent.getParcelableExtra<Exercise>("exercise")
        val fragment = ExerciseDetailFragment.newInstance(exercise)

        supportFragmentManager.beginTransaction()
                .add(R.id.exerciseDetailActivityFrame, fragment, fragment.javaClass.simpleName)
                .commit()


        toolbar_title.text = exercise.name

        setSupportActionBar(ex_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }
}
