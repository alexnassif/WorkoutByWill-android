package com.alexnassif.mobile.workoutbywill.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.alexnassif.mobile.workoutbywill.Model.Exercise
import com.alexnassif.mobile.workoutbywill.Model.Workout
import com.alexnassif.mobile.workoutbywill.Services.DataService

class ExerciseViewModel: ViewModel() {

    private val exerciseList: MutableLiveData<List<Exercise>> = MutableLiveData()

    fun getExercises(type: String): MutableLiveData<List<Exercise>>{

        DataService.getExercises(type){
            exerciseList.postValue(it)
        }

        return exerciseList

    }


}