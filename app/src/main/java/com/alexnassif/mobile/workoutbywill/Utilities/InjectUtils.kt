package com.alexnassif.mobile.workoutbywill.Utilities

import android.content.Context
import com.alexnassif.mobile.workoutbywill.Repositories.WorkoutRepository
import com.alexnassif.mobile.workoutbywill.Services.ProgramService
import com.alexnassif.mobile.workoutbywill.ViewModel.WorkoutViewModelFactory

object InjectUtils {

    private fun getExerciseRepository(): WorkoutRepository {
        val exerciseService: ProgramService = ProgramService.create()
        return WorkoutRepository.getInstance(exerciseService)
    }

    fun provideExerciseViewModelFactory(context: Context): WorkoutViewModelFactory {
        val repository = getExerciseRepository()
        return WorkoutViewModelFactory(repository)
    }
}