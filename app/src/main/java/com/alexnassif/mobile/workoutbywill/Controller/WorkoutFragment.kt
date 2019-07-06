package com.alexnassif.mobile.workoutbywill.Controller


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.alexnassif.mobile.workoutbywill.Adapters.WorkoutRecyclerAdapter
import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Utilities.InjectUtils
import com.alexnassif.mobile.workoutbywill.ViewModel.WorkoutViewModel
import com.alexnassif.mobile.workoutbywill.ViewModel.WorkoutViewModelFactory
import kotlinx.android.synthetic.main.fragment_workout.*


/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutFragment : Fragment() {

    lateinit var adapter: WorkoutRecyclerAdapter
    lateinit var viewModel: WorkoutViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutRecyclerView.layoutManager = GridLayoutManager(context, 2)

        adapter = WorkoutRecyclerAdapter(context!!, mutableListOf()) { workout ->
            val fragment = WorkoutDetailFragment.newInstance(workout.id)

            fragmentManager!!
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment, fragment.javaClass.getSimpleName())
                    .addToBackStack(fragment.javaClass.getSimpleName())
                    .commit()
        }


        workoutRecyclerView.adapter = adapter
        workoutRecyclerView.setHasFixedSize(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val exerciseViewModelFactory: WorkoutViewModelFactory = InjectUtils.provideExerciseViewModelFactory()
        viewModel = ViewModelProviders.of(this, exerciseViewModelFactory).get(WorkoutViewModel::class.java)

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

        viewModel.getWellnessList().observe(this, Observer {wellnessList ->

            adapter.setList(wellnessList!!)
            workoutProgressBar.visibility = View.INVISIBLE
            workoutRecyclerView.scheduleLayoutAnimation()

        })


    }

    companion object {

        fun newInstance(): WorkoutFragment {

            return WorkoutFragment()
        }
    }

}// Required empty public constructor
