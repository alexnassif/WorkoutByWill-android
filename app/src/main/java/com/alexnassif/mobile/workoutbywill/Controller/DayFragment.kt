package com.alexnassif.mobile.workoutbywill.Controller


//import android.app.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexnassif.mobile.workoutbywill.Adapters.WorkoutDetailAdapter
import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Services.DataService
import com.alexnassif.mobile.workoutbywill.Utilities.InjectUtils
import com.alexnassif.mobile.workoutbywill.ViewModel.DayViewModel
import kotlinx.android.synthetic.main.fragment_day.*


class DayFragment : Fragment() {

    private var mDay: String? = null
    private var mWorkout: String? = null
    private lateinit var adapter: WorkoutDetailAdapter
    private lateinit var viewModel: DayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mDay = arguments!!.getString(DAY_PARAM)
            mWorkout = arguments!!.getString(WOROUT_PARAM)
        }
        val dayVMFactory = InjectUtils.provideDayViewModelFactory()
        viewModel = ViewModelProviders.of(this, dayVMFactory).get(DayViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dayRecyclerView.layoutManager = LinearLayoutManager(context)

        adapter = WorkoutDetailAdapter(context!!, mutableListOf()) {
            keyName, category   ->

            DataService.getSingleExercise(category, keyName){ exercise
                -> val intent = Intent(context, ExerciseDetailActivity::class.java)
                intent.putExtra("exercise", exercise)
                startActivity(intent)

            }


        }
        dayRecyclerView.adapter = adapter
        dayRecyclerView.setHasFixedSize(true)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setDay(mWorkout!!, mDay!!)
        viewModel.getDay().observe(this, Observer {
            adapter.setList(it!!)
            dayFragmentProgressBar.visibility = View.INVISIBLE
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("destroyDay", mDay)
    }

    companion object {

        private val DAY_PARAM = "day"
        private val WOROUT_PARAM = "workout"

        fun newInstance(workoutName: String, day: String): DayFragment {
            val fragment = DayFragment()
            val args = Bundle()
            args.putString(WOROUT_PARAM, workoutName)
            args.putString(DAY_PARAM, day)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
