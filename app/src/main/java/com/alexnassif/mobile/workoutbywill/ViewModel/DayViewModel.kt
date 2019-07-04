package com.alexnassif.mobile.workoutbywill.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alexnassif.mobile.workoutbywill.Model.ExerciseDetail
import com.alexnassif.mobile.workoutbywill.Repositories.WorkoutRepository

class DayViewModel(private val repository: WorkoutRepository): ViewModel() {

    private var workoutParams: MutableLiveData<Map<String, String>> = MutableLiveData()
    private var workoutDetail: LiveData<List<ExerciseDetail>>

    init {
        workoutDetail = Transformations.switchMap(workoutParams) { input -> getDayList(input) }
    }

    fun getDayList(map: Map<String, String>): MutableLiveData<List<ExerciseDetail>> {
        return repository.getWorkoutdetail(map)
    }

    fun setDay(programId: String, day: String){

        val map = HashMap<String, String>()
        map.put("program", programId)
        map.put("day", day)

        workoutParams.value = map
    }

    fun getDay(): LiveData<List<ExerciseDetail>>{
        return workoutDetail
    }

}