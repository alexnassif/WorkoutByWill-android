package com.alexnassif.mobile.workoutbywill

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.alexnassif.mobile.workoutbywill.Controller.ExerciseFragment
import com.alexnassif.mobile.workoutbywill.Controller.SignInActivity
import com.alexnassif.mobile.workoutbywill.Controller.WorkoutFragment
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
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
            R.id.navigation_sign_in -> {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)

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
        selectedFragment = WorkoutFragment.newInstance("hello", "world")
        supportFragmentManager
                .beginTransaction()
                .add(R.id.content_frame, selectedFragment, selectedFragment.javaClass.getSimpleName())
                .commit()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setSupportActionBar(app_toolbar)



    }

    override fun onStart() {
        super.onStart()

        Log.d("currentuser", auth.currentUser?.email)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(currentUser != null) {
            menuInflater.inflate(R.menu.so_menu, menu)
            //return true
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item!!.itemId == R.id.sign_out){
            auth.signOut()
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        val auth = FirebaseAuth.getInstance()!!
        val currentUser = auth.currentUser
    }
}
