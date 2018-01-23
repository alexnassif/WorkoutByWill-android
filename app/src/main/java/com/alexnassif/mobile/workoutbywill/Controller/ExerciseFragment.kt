package com.alexnassif.mobile.workoutbywill.Controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexnassif.mobile.workoutbywill.Adapters.BodyPartRecyclerAdapter
import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Services.DataService
import kotlinx.android.synthetic.main.fragment_exercise.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ExerciseFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseFragment : Fragment() {


    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: BodyPartRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context)
        recyclerViewBpEx.layoutManager = linearLayoutManager
        adapter = BodyPartRecyclerAdapter(context, DataService.bpCategories) {

            exType ->
            var fragment = ExerciseTypeFragment.newInstance("hello")

                    fragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment, fragment.javaClass.getSimpleName())
                    .addToBackStack(fragment.javaClass.getSimpleName())
                    .commit()
        }
        recyclerViewBpEx.adapter = adapter
        recyclerViewBpEx.setHasFixedSize(true)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_exercise, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }


    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ExerciseFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): ExerciseFragment {
            val fragment = ExerciseFragment()

            return fragment
        }
    }
}// Required empty public constructor
