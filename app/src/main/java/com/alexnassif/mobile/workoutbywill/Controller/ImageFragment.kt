package com.alexnassif.mobile.workoutbywill.Controller


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alexnassif.mobile.workoutbywill.R


/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageFragment : Fragment() {

    private var mPageNumber: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mPageNumber = arguments.getInt(arg_pageNumber)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val rootView = inflater!!.inflate(R.layout.fragment_image, container, false)

        return rootView
    }

    public fun getPageNumber(): Int? {
        return mPageNumber
    }

    companion object {
        private val arg_pageNumber = "pagenumber"
        fun newInstance(pageNumber: Int): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putInt(arg_pageNumber, pageNumber)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
