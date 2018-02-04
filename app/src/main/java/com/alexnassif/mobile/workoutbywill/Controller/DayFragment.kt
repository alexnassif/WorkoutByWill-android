package com.alexnassif.mobile.workoutbywill.Controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexnassif.mobile.workoutbywill.Adapters.WorkoutDetailAdapter

import com.alexnassif.mobile.workoutbywill.R
import com.alexnassif.mobile.workoutbywill.Services.DataService
import kotlinx.android.synthetic.main.fragment_day.*


/**
 * A simple [Fragment] subclass.
 * Use the [DayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DayFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mDay: String? = null
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: WorkoutDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mDay = arguments.getString(DAY_PARAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_day, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        layoutManager = LinearLayoutManager(context)
        dayRecyclerView.layoutManager = layoutManager
        DataService.getDayList(mDay!!) {
            adapter = WorkoutDetailAdapter(context, it) {

            }

            dayRecyclerView.adapter = adapter
            dayRecyclerView.setHasFixedSize(true)
        }

    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val DAY_PARAM = "day"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DayFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(day: String): DayFragment {
            val fragment = DayFragment()
            val args = Bundle()
            args.putString(DAY_PARAM, day)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
