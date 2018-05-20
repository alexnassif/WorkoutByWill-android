package com.alexnassif.mobile.workoutbywill.Controller


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexnassif.mobile.workoutbywill.Adapters.ExerciseAdapter
import com.alexnassif.mobile.workoutbywill.Model.Exercise

import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Services.DataService
import com.alexnassif.mobile.workoutbywill.ViewModel.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_exercise_type.*


/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseTypeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseTypeFragment : Fragment() {

    private var type: String? = null
    private lateinit var adapter: ExerciseAdapter
    private lateinit var viewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            type = arguments!!.getString(exersise_type)
        }

        viewModel = ViewModelProviders.of(activity!!).get(ExerciseViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exerciseRecyclerView.layoutManager = GridLayoutManager(context, 2)

        adapter = ExerciseAdapter(context!!, mutableListOf()){exercise ->

            val intent = Intent(context, ExerciseDetailActivity::class.java)
            intent.putExtra("exercise", exercise)
            startActivity(intent)

        }
        exerciseRecyclerView.adapter = adapter
        exerciseRecyclerView.setHasFixedSize(true)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_type, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getExercises(type!!).observe(activity!!, Observer {
            adapter.setList(it!!)
            exTypeProgressBar.visibility = View.INVISIBLE
            exerciseRecyclerView.scheduleLayoutAnimation()
        })


    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val exersise_type = "type"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExerciseTypeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(type: String): ExerciseTypeFragment {
            val fragment = ExerciseTypeFragment()
            val args = Bundle()
            args.putString(exersise_type, type)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
