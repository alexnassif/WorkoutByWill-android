package com.alexnassif.mobile.workoutbywill.Utilities.data

import android.util.Log
import com.alexnassif.mobile.workoutbywill.Model.LoginEntity
import com.alexnassif.mobile.workoutbywill.Model.Token
import com.alexnassif.mobile.workoutbywill.Services.ProgramService
import com.alexnassif.mobile.workoutbywill.Utilities.data.model.LoggedInUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource{
    val exerciseService: ProgramService = ProgramService.create()
    fun login(username: String, password: String): Result<LoggedInUser> {

        //val exerciseService: ProgramService = ProgramService.create()
        try {
            val user = LoginEntity(username, password)
            exerciseService.login(user).enqueue(object : Callback<Token>{
                override fun onFailure(call: Call<Token>, t: Throwable) {

                }

                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    Log.d("token", response.body()!!.key)
                }

            })

            return Result.Success(LoggedInUser(username, username))
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        exerciseService.logout()
    }
}

