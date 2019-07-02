package com.alexnassif.mobile.workoutbywill.Controller


import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Services.DataService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_workout_detail.*


/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutDetailFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mWorkoutName: String? = null
    private var dayPagerAdapter: DaySlidePagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mWorkoutName = arguments!!.getString(WORKOUT_NAME)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dayPagerAdapter = DaySlidePagerAdapter(childFragmentManager)
        workoutViewPager.adapter = dayPagerAdapter
        workoutViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
                //workoutViewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                workoutViewPager.currentItem = tab!!.position
            }

        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
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

    private inner class DaySlidePagerAdapter(fm: FragmentManager?) : SmartFragmentStatePagerAdapter(fm!!) {

        var array = arrayOfNulls<DayFragment>(NUM_PAGES)


        override fun getItem(position: Int): Fragment {
            println("the position here is " + position)
            when (position){
                0 -> return DayFragment.newInstance(mWorkoutName!!,"monday")
                1 -> return DayFragment.newInstance(mWorkoutName!!,"tuesday")
                2-> return DayFragment.newInstance(mWorkoutName!!,"wednesday")
                3 -> return DayFragment.newInstance(mWorkoutName!!,"thursday")
                4 -> return DayFragment.newInstance(mWorkoutName!!,"friday")
                5 -> return DayFragment.newInstance(mWorkoutName!!,"saturday")
                6 -> return DayFragment.newInstance(mWorkoutName!!,"sunday")
            }
            return DayFragment.newInstance(mWorkoutName!!,"monday")
        }



        override fun getCount(): Int {
            return NUM_PAGES
        }

    }// Required empty public constructor
}
