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


    fun getDayList(workout: String, day: String, completion: (MutableList<ExerciseDetail>) -> Unit){
        println(" this is workout name " + workout)
        var dayRef = database.getReference(workout).child(day)
        var detailList = mutableListOf<ExerciseDetail>()
        dayRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot?) {
                val children = snapshot!!.children
                //println(" this is the snapshot " + snapshot)
                children.forEach { childx ->

                    val name = childx.child("exerciseName").value.toString()
                    val reps = childx.child("reps").value.toString()
                    val rest = childx.child("rest").value.toString()
                    val sets = childx.child("sets").value.toString()
                    var exerciseDetail = ExerciseDetail(name, reps, rest, sets)
                    detailList.add(exerciseDetail)

                }

                completion(detailList)
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