package com.alexnassif.mobile.workoutbywill.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.alexnassif.mobile.workoutbywill.Model.Workout
import com.alexnassif.mobile.workoutbywill.Services.DataService

class PaidViewModel: ViewModel() {

    private val paidList: MutableLiveData<List<Workout>> = MutableLiveData()

    fun getPaidList(): LiveData<List<Workout>> {

        if(paidList.value == null){
            DataService.getPaidWorkoutList {paidL ->
                paidList.postValue(paidL)

            }
        }

        return paidList

    }



}