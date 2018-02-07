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

    fun getDayList(workout: String, day: String, completion: (MutableList<ExerciseDetail>) -> Unit){

        var dayRef = database.getReference(workout).child(day)
        var detailList = mutableListOf<ExerciseDetail>()
        dayRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot?) {
                val children = snapshot!!.children
                children.forEach { childx ->
                    val keyName = childx.key
                    val name = childx.child("exerciseName").value.toString()
                    val reps = childx.child("reps").value.toString()
                    val rest = childx.child("rest").value.toString()
                    val sets = childx.child("sets").value.toString()
                    val category = childx.child("category").value.toString()
                    var exerciseDetail = ExerciseDetail(keyName, name, reps, rest, sets, category)
                    detailList.add(exerciseDetail)

                }

                completion(detailList)
            }

            override fun onCancelled(error: DatabaseError?) {
                println(error!!.message)
            }
        })

    }

    fun getSingleExercise(category: String, keyName: String, completion: (Exercise) -> Unit){

        val exRef = exercisesRef.child(category).child(keyName)

        exRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot?) {


                var exercise: Exercise? = null

                exercise = snapshot!!.getValue(Exercise::class.java)

                completion(exercise!!)

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