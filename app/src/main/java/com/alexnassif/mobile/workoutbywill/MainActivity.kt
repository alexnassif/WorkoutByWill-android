package com.alexnassif.mobile.workoutbywill

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.alexnassif.mobile.workoutbywill.Controller.ExerciseFragment
import com.alexnassif.mobile.workoutbywill.Controller.WorkoutFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var selectedFragment: Fragment
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {

        item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                selectedFragment = WorkoutFragment.newInstance("hello", "world")
                addFragment(selectedFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_exercises -> {
                selectedFragment = ExerciseFragment.newInstance()
                addFragment(selectedFragment)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment, fragment.javaClass.getSimpleName())
                .addToBackStack(fragment.javaClass.getSimpleName())
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setSupportActionBar(app_toolbar)
        supportActionBar?.setLogo(R.drawable.logo)
        selectedFragment = WorkoutFragment.newInstance("hello", "world")
        addFragment(selectedFragment)
    }
}
