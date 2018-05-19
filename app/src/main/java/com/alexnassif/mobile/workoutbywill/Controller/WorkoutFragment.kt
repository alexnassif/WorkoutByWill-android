package com.alexnassif.mobile.workoutbywill.Controller


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexnassif.mobile.workoutbywill.Adapters.WorkoutRecyclerAdapter
import com.alexnassif.mobile.workoutbywill.Model.Workout

import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Services.DataService
import com.alexnassif.mobile.workoutbywill.ViewModel.WorkoutViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_workout.*


/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutFragment : Fragment() {

    lateinit var adapter: WorkoutRecyclerAdapter
    lateinit var viewModel: WorkoutViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(WorkoutViewModel::class.java)

        viewModel.getWellnessList().observe(this, Observer {wellnessList ->

            val layoutMan = GridLayoutManager(context, 2)
            workoutRecyclerView.layoutManager = layoutMan

            adapter = WorkoutRecyclerAdapter(context!!, wellnessList!!) { workout ->
                val fragment = WorkoutDetailFragment.newInstance(workout.name)

                fragmentManager!!
                        .beginTransaction()
                        .replace(R.id.content_frame, fragment, fragment.javaClass.getSimpleName())
                        .addToBackStack(fragment.javaClass.getSimpleName())
                        .commit()
            }
            workoutProgressBar.visibility = View.INVISIBLE
            workoutRecyclerView.adapter = adapter
            workoutRecyclerView.scheduleLayoutAnimation()
            workoutRecyclerView.setHasFixedSize(true)

        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    companion object {

        fun newInstance(): WorkoutFragment {
            val fragment = WorkoutFragment()

            return fragment
        }
    }

}// Required empty public constructor
