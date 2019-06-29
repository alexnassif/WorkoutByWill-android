package com.alexnassif.mobile.workoutbywill.Services

import com.alexnassif.mobile.workoutbywill.Model.Program
import retrofit2.Call
import retrofit2.http.GET

interface ExerciseService {

    @GET("programs/")
    fun getExercises(): Call<List<Program>>


}