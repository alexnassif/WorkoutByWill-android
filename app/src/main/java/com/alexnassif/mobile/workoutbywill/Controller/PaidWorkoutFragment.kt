package com.alexnassif.mobile.workoutbywill.Controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexnassif.mobile.workoutbywill.Adapters.WorkoutRecyclerAdapter

import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Services.DataService
import kotlinx.android.synthetic.main.fragment_paid_workout.*


/**
 * A simple [Fragment] subclass.
 * Use the [PaidWorkoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaidWorkoutFragment : Fragment() {

    lateinit var paidAdapter: WorkoutRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        DataService.getPaidWorkoutList { individualList ->

            var layoutIndividualWk = GridLayoutManager(context, 2)
            paid_recyclerView.layoutManager = layoutIndividualWk


            paidAdapter = WorkoutRecyclerAdapter(context!!, individualList) { workout ->

                var fragment = WorkoutPaidDetailFragment.newInstance(workout.name)

                fragmentManager!!
                        .beginTransaction()
                        .replace(R.id.content_frame, fragment, fragment.javaClass.getSimpleName())
                        .addToBackStack(fragment.javaClass.getSimpleName())
                        .commit()

            }

            paid_recyclerView.adapter = paidAdapter
            paid_recyclerView.setHasFixedSize(true)


        }
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
