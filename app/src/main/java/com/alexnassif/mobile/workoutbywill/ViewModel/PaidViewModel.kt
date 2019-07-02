package com.alexnassif.mobile.workoutbywill.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexnassif.mobile.workoutbywill.Model.Workout
import com.alexnassif.mobile.workoutbywill.Services.DataService

class PaidViewModel: ViewModel() {

    private val paidList: MutableLiveData<List<Workout>> = MutableLiveData()

    fun getPaidList(): MutableLiveData<List<Workout>> {

        if(paidList.value == null){
            DataService.getPaidWorkoutList {paidL ->
                paidList.postValue(paidL)

            }
        }

        return paidList

    }



}