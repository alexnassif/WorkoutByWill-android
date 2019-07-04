package com.alexnassif.mobile.workoutbywill.Utilities

import com.alexnassif.mobile.workoutbywill.Repositories.WorkoutRepository
import com.alexnassif.mobile.workoutbywill.Services.ProgramService
import com.alexnassif.mobile.workoutbywill.ViewModel.DayVMFactory
import com.alexnassif.mobile.workoutbywill.ViewModel.WorkoutViewModelFactory

object InjectUtils {

    private fun getExerciseRepository(): WorkoutRepository {
        val exerciseService: ProgramService = ProgramService.create()
        return WorkoutRepository.getInstance(exerciseService)
    }

    fun provideExerciseViewModelFactory(): WorkoutViewModelFactory {
        val repository = getExerciseRepository()
        return WorkoutViewModelFactory(repository)
    }

    fun provideDayViewModelFactory(): DayVMFactory {
        return DayVMFactory(getExerciseRepository())
    }
}