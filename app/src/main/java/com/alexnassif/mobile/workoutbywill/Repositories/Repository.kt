package com.alexnassif.mobile.workoutbywill.Repositories

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.alexnassif.mobile.workoutbywill.Model.Program
import com.alexnassif.mobile.workoutbywill.Services.ExerciseService
import com.alexnassif.mobile.workoutbywill.Utilities.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory



class Repository {

    lateinit var exService: ExerciseService;


    fun getExercises(): MutableLiveData<List<Program>> {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        exService = retrofit.create(ExerciseService::class.java)
        val data = MutableLiveData<List<Program>>()

        exService.getExercises().enqueue(object : Callback<List<Program>>{
            override fun onResponse(call: Call<List<Program>>, response: Response<List<Program>>) {
                if(response.isSuccessful){
                data.value = response.body()
                Log.d("exfromser" , response.body()!!.get(0).name)}
                else {

                    //val jObjError = JSONObject(response.errorBody()?.string())
                    Log.d("itsnull", response.errorBody()!!.string())

                }
            }

            override fun onFailure(call: Call<List<Program>>, t: Throwable) {
                Log.d("retrofitex", t.localizedMessage)
            }

        })
        return data
    }
}