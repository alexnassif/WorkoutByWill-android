package com.alexnassif.mobile.workoutbywill.Services

import android.util.Log
import com.alexnassif.mobile.workoutbywill.Model.Category
import com.alexnassif.mobile.workoutbywill.Model.Exercise
import com.alexnassif.mobile.workoutbywill.Model.ExerciseDetail
import com.alexnassif.mobile.workoutbywill.Model.Workout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


/**
 * Created by raymond on 1/21/18.
 */
object DataService {

    private var database = FirebaseDatabase.getInstance()!!
    private var exercisesRef = database.getReference("exercises")

    fun getPaidWorkoutList(completion: (List<Workout>) -> Unit){

        val individualWorkouts = database.getReference(FirebaseAuth.getInstance().uid).child("listWorkouts")
        val individualWList = mutableListOf<Workout>()

        individualWorkouts.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(workouts: DataSnapshot?) {
                val children = workouts!!.children
                children.forEach { x ->
                    val workout = Workout(x.value.toString())
                    individualWList.add(workout)
                }
                completion(individualWList)
            }

        })

    }
    fun getPaidWorkout(workout: String, day:String, completion: (MutableList<ExerciseDetail>) -> Unit){

        val dayRef = database.getReference(FirebaseAuth.getInstance().uid).child(workout).child(day)
        val detailList = mutableListOf<ExerciseDetail>()
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
                    val imageLocation = childx.child("image").value.toString()
                    var exerciseDetail = ExerciseDetail(keyName, name, reps, rest, sets, category, imageLocation)
                    detailList.add(exerciseDetail)

                }

                completion(detailList)
            }

            override fun onCancelled(error: DatabaseError?) {
                println(error!!.message)
            }
        })

    }
    fun getDayList(workout: String, day: String, completion: (MutableList<ExerciseDetail>) -> Unit){

        val dayRef = database.getReference(workout).child(day)
        val detailList = mutableListOf<ExerciseDetail>()
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
                    val imageLocation = childx.child("image").value.toString()
                    var exerciseDetail = ExerciseDetail(keyName, name, reps, rest, sets, category, imageLocation)
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

                val exercise = snapshot!!.getValue(Exercise::class.java)

                completion(exercise!!)

            }

            override fun onCancelled(error: DatabaseError?) {
                println(error!!.message)
            }
        })
    }

    fun getExercises(type: String, completion: (MutableList<Exercise>) -> Unit) {

        val referenceType = exercisesRef.child(type)
        val exerciseList = mutableListOf<Exercise>()
        referenceType.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError?) {
                println(error!!.message)
            }

            override fun onDataChange(snapshot: DataSnapshot?) {
                val children = snapshot!!.children
                children.forEach {
                    childx ->

                    val exercise = childx.getValue(Exercise::class.java)
                    exerciseList.add(exercise!!)

                }

                completion(exerciseList)

            }

        })



    }

    fun getWellnessPrograms(completion: (List<Workout>) -> Unit){

        val wellnessRef = database.getReference("wellnessprograms")
        val list = mutableListOf<Workout>()

        wellnessRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(wellnessSnapshot: DataSnapshot?) {
                val children = wellnessSnapshot!!.children

                children.forEach { childx ->
                    list.add(Workout(childx.value.toString()))
                }
                completion(list)
            }

        })
    }

    val bpCategories = listOf(

            Category("neckandshoulders", "Neck and Shoulders" ,"placeholderpic" ),
            Category("kneeandankle", "Knee and Ankle", "placeholderpic" ),
            Category("lowerbackandhip", "Lower Back and Hip", "placeholderpic" )

    )
}