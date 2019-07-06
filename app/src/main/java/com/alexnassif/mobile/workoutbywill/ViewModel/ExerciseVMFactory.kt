package com.alexnassif.mobile.workoutbywill.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexnassif.mobile.workoutbywill.Repositories.WorkoutRepository

class ExerciseVMFactory(private val repository: WorkoutRepository, private val exerciseId: Int): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExerciseViewModel(repository, exerciseId) as T
    }

}