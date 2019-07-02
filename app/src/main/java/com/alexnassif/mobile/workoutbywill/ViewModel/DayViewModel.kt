package com.alexnassif.mobile.workoutbywill.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexnassif.mobile.workoutbywill.Model.ExerciseDetail
import com.alexnassif.mobile.workoutbywill.Services.DataService

class DayViewModel: ViewModel() {

    private val dayList : MutableLiveData<List<ExerciseDetail>> = MutableLiveData()

    fun getDayList(day: String, workout: String): MutableLiveData<List<ExerciseDetail>> {

        if(dayList.value == null){
            DataService.getDayList(workout, day){

                dayList.postValue(it)

            }
        }


        return dayList
    }


}