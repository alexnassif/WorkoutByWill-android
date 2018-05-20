package com.alexnassif.mobile.workoutbywill.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.alexnassif.mobile.workoutbywill.Model.Workout
import com.alexnassif.mobile.workoutbywill.Services.DataService

class WorkoutViewModel: ViewModel() {

    private val wellnessList: MutableLiveData<List<Workout>> = MutableLiveData()

    fun getWellnessList(): MutableLiveData<List<Workout>> {

        if(wellnessList.value == null){

            DataService.getWellnessPrograms {list ->

                wellnessList.postValue(list)

            }

        }

        return wellnessList
    }

}