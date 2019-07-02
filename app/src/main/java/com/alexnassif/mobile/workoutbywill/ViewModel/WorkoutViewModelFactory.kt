package com.alexnassif.mobile.workoutbywill.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexnassif.mobile.workoutbywill.Repositories.WorkoutRepository

class WorkoutViewModelFactory(private val repository: WorkoutRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WorkoutViewModel(repository) as T
    }
}