package com.alexnassif.mobile.workoutbywill.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alexnassif.mobile.workoutbywill.Model.Program
import com.alexnassif.mobile.workoutbywill.Services.ProgramService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class WorkoutRepository private constructor(private val exerciseService: ProgramService){


    fun getExercises(): MutableLiveData<List<Program>> {

        val data = MutableLiveData<List<Program>>()

        exerciseService.getExercises().enqueue(object : Callback<List<Program>>{
            override fun onResponse(call: Call<List<Program>>, response: Response<List<Program>>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                } else {

                    Log.d("itsnull", response.errorBody()!!.string())

                }
            }

            override fun onFailure(call: Call<List<Program>>, t: Throwable) {
                Log.d("retrofitex", t.localizedMessage)
            }

        })
        return data
    }

    companion object{
        @Volatile private var INSTANCE: WorkoutRepository ? = null
        fun getInstance(exerciseService: ProgramService): WorkoutRepository {
            return INSTANCE?: synchronized(this){
                WorkoutRepository(exerciseService).also {
                    INSTANCE = it
                }
            }
        }
    }
}