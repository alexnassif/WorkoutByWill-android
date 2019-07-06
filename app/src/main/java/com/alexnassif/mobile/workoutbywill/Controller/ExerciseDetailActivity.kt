package com.alexnassif.mobile.workoutbywill.Controller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexnassif.mobile.workoutbywill.R
import kotlinx.android.synthetic.main.activity_exercise_detail.*

class ExerciseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        val exercise = intent.getIntExtra("exercise", 1)
        val fragment = ExerciseDetailFragment.newInstance(exercise)

        supportFragmentManager.beginTransaction()
                .add(R.id.exerciseDetailActivityFrame, fragment, fragment.javaClass.simpleName)
                .commit()


        //toolbar_title.text = exercise.name

        setSupportActionBar(ex_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }
}
