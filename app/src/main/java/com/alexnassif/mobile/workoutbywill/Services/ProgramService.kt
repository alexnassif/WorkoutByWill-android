package com.alexnassif.mobile.workoutbywill.Services

import com.alexnassif.mobile.workoutbywill.Model.Program
import com.alexnassif.mobile.workoutbywill.Utilities.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ProgramService {

    @GET("programs/")
    fun getExercises(): Call<List<Program>>

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