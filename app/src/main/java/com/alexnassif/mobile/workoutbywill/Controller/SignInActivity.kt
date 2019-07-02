package com.alexnassif.mobile.workoutbywill.Controller

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.alexnassif.mobile.workoutbywill.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.*
import com.firebase.ui.auth.BuildConfig
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class SignInActivity : AppCompatActivity() {

    fun showSnackbar(id : String){
        Snackbar.make(findViewById(R.id.sign_in_container), id, Snackbar.LENGTH_LONG).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        if(auth.currentUser != null){ //If user is signed in
//                startActivity(Next Activity)
            finish()
        }
        else {
            startActivityForResult(
                    getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                            .setAvailableProviders(
                                    Arrays.asList(IdpConfig.EmailBuilder().build()))
                            .build(),
                    RC_SIGN_IN)
        }
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
                showSnackbar("signed in")
                finish()
                return
            }
            else {
                if(response == null){
                    //If no response from the Server
                    showSnackbar("sign in cancelled")
                    return
                }

            }
        }
        showSnackbar("unknown sign in") //if the sign in response was unknown
    }

    companion object {
        val RC_SIGN_IN = 123
        val auth = FirebaseAuth.getInstance()!!
    }
}
