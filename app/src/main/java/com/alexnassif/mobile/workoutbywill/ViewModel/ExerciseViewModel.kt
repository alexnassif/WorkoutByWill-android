package com.alexnassif.mobile.workoutbywill.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexnassif.mobile.workoutbywill.Model.Exercise
import com.alexnassif.mobile.workoutbywill.Repositories.WorkoutRepository

class ExerciseViewModel(private val repository: WorkoutRepository): ViewModel() {

    private var exerciseList: MutableLiveData<List<Exercise>> = repository.getExerciseList()

    fun getExercises(type: String): MutableLiveData<List<Exercise>>{

        return exerciseList

    }




}