package com.alexnassif.mobile.workoutbywill.Controller


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.alexnassif.mobile.workoutbywill.Adapters.WorkoutRecyclerAdapter

import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Services.DataService
import com.alexnassif.mobile.workoutbywill.ViewModel.PaidViewModel
import kotlinx.android.synthetic.main.fragment_paid_workout.*


/**
 * A simple [Fragment] subclass.
 * Use the [PaidWorkoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaidWorkoutFragment : Fragment() {

    lateinit var paidAdapter: WorkoutRecyclerAdapter
    lateinit var viewModel: PaidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProviders.of(this).get(PaidViewModel::class.java)

        viewModel.getPaidList().observe(this, Observer {individualList ->
            val layoutIndividualWk = GridLayoutManager(context, 2)
            paid_recyclerView.layoutManager = layoutIndividualWk
            paidWorkoutProgressBar.visibility = View.INVISIBLE
            paidAdapter = WorkoutRecyclerAdapter(context!!, individualList!!) { workout ->

                val fragment = WorkoutPaidDetailFragment.newInstance(workout.name)

                fragmentManager!!
                        .beginTransaction()
                        .replace(R.id.content_frame, fragment, fragment.javaClass.getSimpleName())
                        .addToBackStack(fragment.javaClass.getSimpleName())
                        .commit()

            }

            paid_recyclerView.adapter = paidAdapter
            paid_recyclerView.scheduleLayoutAnimation()
            paid_recyclerView.setHasFixedSize(true)

        })


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paid_workout, container, false)
    }

    companion object {

        fun newInstance(): PaidWorkoutFragment {
            val fragment = PaidWorkoutFragment()

            return fragment
        }
    }

}// Required empty public constructor
