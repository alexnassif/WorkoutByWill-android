package com.alexnassif.mobile.workoutbywill.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexnassif.mobile.workoutbywill.Model.Exercise
import com.alexnassif.mobile.workoutbywill.Repositories.WorkoutRepository

class ExerciseViewModel(private val repository: WorkoutRepository, private val exerciseId: Int): ViewModel() {

    private var exerciseList: MutableLiveData<Exercise> = repository.getExercise(exerciseId)

    fun getExercise(): MutableLiveData<Exercise>{

        return exerciseList

    }




}