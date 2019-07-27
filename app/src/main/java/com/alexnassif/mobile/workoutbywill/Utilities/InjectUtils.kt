package com.alexnassif.mobile.workoutbywill.Utilities

import com.alexnassif.mobile.workoutbywill.Repositories.WorkoutRepository
import com.alexnassif.mobile.workoutbywill.Services.ProgramService
import com.alexnassif.mobile.workoutbywill.Utilities.data.LoginDataSource
import com.alexnassif.mobile.workoutbywill.Utilities.data.LoginRepository
import com.alexnassif.mobile.workoutbywill.Utilities.ui.login.LoginViewModelFactory
import com.alexnassif.mobile.workoutbywill.ViewModel.DayVMFactory
import com.alexnassif.mobile.workoutbywill.ViewModel.ExerciseVMFactory
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

    fun provideExerciseViewModelFactory(exerciseId: Int): ExerciseVMFactory {
        return ExerciseVMFactory(getExerciseRepository(), exerciseId)
    }
    fun getLoginRepository(): LoginRepository {
        val loginDataSource = LoginDataSource()
        return LoginRepository.getInstance(loginDataSource)
    }

    fun provideLoginViewModelFactory(): LoginViewModelFactory {
        return LoginViewModelFactory(getLoginRepository())
    }
}