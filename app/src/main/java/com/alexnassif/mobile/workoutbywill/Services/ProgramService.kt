package com.alexnassif.mobile.workoutbywill.Services

import com.alexnassif.mobile.workoutbywill.Model.*
import com.alexnassif.mobile.workoutbywill.Utilities.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface ProgramService {

    @GET("programs")
    fun getExercises(): Call<List<Program>>

    @GET("workoutdetails/")
    fun getProgramDetails(@QueryMap filter: Map<String, String>): Call<List<ExerciseDetail>>

    @GET("exercises/{id}")
    fun getExercise(@Path("id") id: Int): Call<Exercise>

    @POST("rest-auth/login/")
    fun login(@Body loginUser: LoginEntity): Call<Token>

    companion object Factory{
        fun create(): ProgramService {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            return retrofit.create(ProgramService::class.java)

        }
    }


}