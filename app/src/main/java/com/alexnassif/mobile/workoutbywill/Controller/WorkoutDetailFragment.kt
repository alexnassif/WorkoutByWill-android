package com.alexnassif.mobile.workoutbywill.Controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Services.DataService
import kotlinx.android.synthetic.main.fragment_workout_detail.*


/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutDetailFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mWorkoutName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mWorkoutName = arguments.getString(WORKOUT_NAME)
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_workout_detail, container, false)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val WORKOUT_NAME = "workoutName"
        private val NUM_PAGES = 7

        // TODO: Rename and change types and number of parameters
        fun newInstance(workoutName: String): WorkoutDetailFragment {
            val fragment = WorkoutDetailFragment()
            val args = Bundle()
            args.putString(WORKOUT_NAME, workoutName)
            fragment.arguments = args
            return fragment
        }
    }

    private inner class DaySlidePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return DayFragment.newInstance("monday")
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }

    }// Required empty public constructor
}
