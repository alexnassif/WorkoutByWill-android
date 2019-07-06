package com.alexnassif.mobile.workoutbywill.Controller


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.alexnassif.mobile.workoutbywill.Model.Exercise
import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Utilities.InjectUtils
import com.alexnassif.mobile.workoutbywill.ViewModel.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_exercise_detail.*


/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseDetailFragment : Fragment() {

    private var exerciseId: Int? = null
    var exercise: Exercise = Exercise("", "", "", mutableListOf(), "")
    private var pagerAdapter: ScreenSlidePagerAdapter? = null
    lateinit var viewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            exerciseId = arguments!!.getInt(EXERCISE_KEY)
        }

        val factory = exerciseId?.let { InjectUtils.provideExerciseViewModelFactory(it) }
        viewModel = ViewModelProviders.of(this, factory).get(ExerciseViewModel::class.java)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getExercise().observe(this, Observer {
            exercise = it
            how.text = it.how
            why.text = it.why

            pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
            viewPager.adapter = pagerAdapter
            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }
                override fun onPageSelected(position: Int) {

                    if(position == 0) {
                        leftArrowImageView.visibility = View.INVISIBLE
                        rightArrowImageView.visibility = View.VISIBLE
                    }

                    else if(position == exercise!!.imageLocation.lastIndex){
                        leftArrowImageView.visibility = View.VISIBLE
                        rightArrowImageView.visibility = View.INVISIBLE
                    }
                    else{
                        leftArrowImageView.visibility = View.VISIBLE
                        rightArrowImageView.visibility = View.VISIBLE
                    }

                }

            })
        })




        leftArrowImageView.visibility = View.INVISIBLE

        leftArrowImageView.setOnClickListener{

            viewPager.currentItem -= 1

        }
        rightArrowImageView.setOnClickListener{

            viewPager.currentItem += 1

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_detail, container, false)
    }

    companion object {
       
        private val EXERCISE_KEY = "exercise"

        fun newInstance(exercise: Int): ExerciseDetailFragment {
            val fragment = ExerciseDetailFragment()
            val args = Bundle()
            args.putInt(EXERCISE_KEY, exercise)
            fragment.arguments = args
            return fragment
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {

            return ImageFragment.newInstance(position, image = exercise.imageLocation.get(position))
        }

        override fun getCount(): Int {
            return exercise.imageLocation.size

        }
    }

}// Required empty public constructor


