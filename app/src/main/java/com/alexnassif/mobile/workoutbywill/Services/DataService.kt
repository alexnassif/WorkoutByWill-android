package com.alexnassif.mobile.workoutbywill.Services

import com.alexnassif.mobile.workoutbywill.Model.Category
import com.alexnassif.mobile.workoutbywill.Model.Exercise
import com.alexnassif.mobile.workoutbywill.Model.ExerciseDetail
import com.google.firebase.database.*


/**
 * Created by raymond on 1/21/18.
 */
object DataService {

    private var database = FirebaseDatabase.getInstance()!!
    private var exercisesRef = database.getReference("exercises")
    private var randomWorkout = database.getReference("randomWorkout")


    fun getDayList(day: String, completion: (MutableList<ExerciseDetail>) -> Unit){

        var dayRef = randomWorkout.child(day)
        var detailList = mutableListOf<ExerciseDetail>()
        dayRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot?) {
                val children = snapshot!!.children
                println(snapshot)
            }

            override fun onCancelled(error: DatabaseError?) {
                println(error!!.message)
            }
        })

    }

    fun getExercises(type: String, completion: (MutableList<Exercise>) -> Unit) {

        var referenceType = exercisesRef.child(type)
        var exerciseList = mutableListOf<Exercise>()
        referenceType.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError?) {
                println(error!!.message)
            }

            override fun onDataChange(snapshot: DataSnapshot?) {
                val children = snapshot!!.children
                children.forEach {
                    childx ->

                    val exercise = childx.getValue(Exercise::class.java)
                    /*val name = childx.child("name").value.toString()
                    val how = childx.child("how").value.toString()
                    val why = childx.child("why").value.toString()
                    val imageBefore = childx.child("imageBefore").value.toString()
                    val imageAfter = childx.child("imageAfter").value.toString()
                    var exercise = Exercise(name, how, why, imageBefore, imageAfter)*/
                    exerciseList.add(exercise!!)

                }

                completion(exerciseList)

            }

        })



    }


    val bpCategories = listOf(

            Category("neckandshoulders", "Neck and Shoulders" ,"placeholderpic" ),
            Category("kneeandankle", "Knee and Ankle", "placeholderpic" ),
            Category("lowerbackandhip", "Lower Back and Hip", "placeholderpic" )

    )
}