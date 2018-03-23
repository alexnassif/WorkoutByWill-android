package com.alexnassif.mobile.workoutbywill

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.alexnassif.mobile.workoutbywill.Controller.ExerciseFragment
import com.alexnassif.mobile.workoutbywill.Controller.WorkoutFragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.BuildConfig
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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
        if(auth.currentUser != null){
            selectedFragment = WorkoutFragment.newInstance("hello", "world")
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.content_frame, selectedFragment, selectedFragment.javaClass.getSimpleName())
                    .commit()

        }
        else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                            .setAvailableProviders(
                                    Arrays.asList(AuthUI.IdpConfig.EmailBuilder().build()))
                            .setLogo(R.drawable.logo)      // Set logo drawable
                            .setTheme(R.style.AppTheme)
                            .build(),
                    RC_SIGN_IN)
        }

    }

    override fun onStart() {
        super.onStart()


    }

    override fun onResume() {
        super.onResume()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.so_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item!!.itemId == R.id.sign_out){
            AuthUI.getInstance().signOut(this).addOnCompleteListener {
                var ft = supportFragmentManager.fragments
                var fragManager = supportFragmentManager.beginTransaction()
                for (x in ft){
                    fragManager.remove(x)
                }
                fragManager.commit()

                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                                .setAvailableProviders(
                                        Arrays.asList(AuthUI.IdpConfig.EmailBuilder().build()))
                                .build(),
                        RC_SIGN_IN)

            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            /*
                this checks if the activity result we are getting is for the sign in
                as we can have more than activity to be started in our Activity.
             */
            val response = IdpResponse.fromResultIntent(data)
            if(resultCode == Activity.RESULT_OK){
                /*
                    Checks if the User sign in was successful
                 */
//                startActivity(Next Activity)
                Toast.makeText(this, "Signed in", Toast.LENGTH_LONG).show()
                /*val frag = supportFragmentManager.findFragmentByTag("WorkoutFragment")
                val ft = supportFragmentManager.beginTransaction()
                ft.detach(frag)
                ft.attach(frag)
                ft.commit()*/

                selectedFragment = WorkoutFragment.newInstance("hello", "world")
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.content_frame, selectedFragment, selectedFragment.javaClass.getSimpleName())
                        .commit()

                return
            }
            else {
                if(response == null){
                    //If no response from the Server
                    Toast.makeText(this, "Not Signed in", Toast.LENGTH_LONG).show()
                    return
                }

            }
        }
        Toast.makeText(this, "unknown Sign-in", Toast.LENGTH_LONG).show() //if the sign in response was unknown
    }

    companion object {
        val RC_SIGN_IN = 123
        val auth = FirebaseAuth.getInstance()!!
    }
}
