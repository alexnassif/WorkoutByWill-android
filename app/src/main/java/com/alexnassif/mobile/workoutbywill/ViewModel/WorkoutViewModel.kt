package com.alexnassif.mobile.workoutbywill.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexnassif.mobile.workoutbywill.Model.Program
import com.alexnassif.mobile.workoutbywill.Repositories.WorkoutRepository

class WorkoutViewModel(private val repository: WorkoutRepository) : ViewModel() {

    private var wellnessList: MutableLiveData<List<Program>> = MutableLiveData()

    fun getWellnessList(): MutableLiveData<List<Program>> {

        if(wellnessList.value == null){

            wellnessList = repository.getExercises()
        }

        return wellnessList
    }

}