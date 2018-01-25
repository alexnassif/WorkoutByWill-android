package com.alexnassif.mobile.workoutbywill.Controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexnassif.mobile.workoutbywill.Model.Exercise

import com.alexnassif.mobile.workoutbywill.R
import kotlinx.android.synthetic.main.fragment_exercise_detail.*
import kotlinx.android.synthetic.main.fragment_image.*


/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseDetailFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var exercise: Exercise? = null

    private var pagerAdapter: ScreenSlidePagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            exercise = arguments.getParcelable(EXERCISE_KEY)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
            }

        })

        how.text = exercise?.how
        why.text = exercise?.why

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_exercise_detail, container, false)
    }

    companion object {
       
        private val EXERCISE_KEY = "exercise"
        private val NUM_PAGES = 2

        fun newInstance(exercise: Exercise): ExerciseDetailFragment {
            val fragment = ExerciseDetailFragment()
            val args = Bundle()
            args.putParcelable(EXERCISE_KEY, exercise)
            fragment.arguments = args
            return fragment
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return ImageFragment.newInstance(position)
        }

        override fun getCount(): Int {
            return NUM_PAGES//To change body of created functions use File | Settings | File Templates.
        }
    }

}// Required empty public constructor


