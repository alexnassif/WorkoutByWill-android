package com.alexnassif.mobile.workoutbywill.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexnassif.mobile.workoutbywill.Model.Exercise
import com.alexnassif.mobile.workoutbywill.Services.DataService

class ExerciseViewModel: ViewModel() {

    private val exerciseList: MutableLiveData<List<Exercise>> = MutableLiveData()

    fun getExercises(type: String): MutableLiveData<List<Exercise>>{

        if(exerciseList.value == null) {
            DataService.getExercises(type) {
                exerciseList.postValue(it)
            }
        }

        return exerciseList

    }


}