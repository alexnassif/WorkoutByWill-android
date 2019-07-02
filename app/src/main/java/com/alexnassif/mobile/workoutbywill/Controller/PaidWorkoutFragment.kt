package com.alexnassif.mobile.workoutbywill.Controller


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexnassif.mobile.workoutbywill.Adapters.WorkoutRecyclerAdapter
import com.alexnassif.mobile.workoutbywill.R
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


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paid_recyclerView.layoutManager = GridLayoutManager(context, 2)
        paidAdapter = WorkoutRecyclerAdapter(context!!, mutableListOf()) { workout ->

            val fragment = WorkoutPaidDetailFragment.newInstance(workout.name)

            fragmentManager!!
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment, fragment.javaClass.getSimpleName())
                    .addToBackStack(fragment.javaClass.getSimpleName())
                    .commit()

        }

        paid_recyclerView.adapter = paidAdapter
        paid_recyclerView.setHasFixedSize(true)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getPaidList().observe(this, Observer { individualList ->

            paidWorkoutProgressBar.visibility = View.INVISIBLE
            //paidAdapter.setList(individualList!!)
            paid_recyclerView.scheduleLayoutAnimation()
        })

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
