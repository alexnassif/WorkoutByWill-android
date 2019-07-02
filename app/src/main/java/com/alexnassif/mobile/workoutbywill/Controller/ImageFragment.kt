package com.alexnassif.mobile.workoutbywill.Controller


import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alexnassif.mobile.workoutbywill.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_image.*


/**
 * A simple [Fragment] subclass.
 * Use the [ImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageFragment : Fragment() {

    private var mPageNumber: Int? = null
    private var mImageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mPageNumber = arguments!!.getInt(arg_pageNumber)
            mImageUrl = arguments!!.getString(imageUrl)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_image, container, false)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        beforeAfterTextView.text = (mPageNumber!! + 1).toString()
        Glide.with(context!!).load(mImageUrl).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                imageProgressBar.visibility = View.INVISIBLE
                return false
            }

        }).into(beforeAfterImageView)
        //imageProgressBar.visibility = View.INVISIBLE
    }

    fun getPageNumber(): Int? {
        return mPageNumber
    }

    companion object {
        private val arg_pageNumber = "pagenumber"
        private val imageUrl = "image"
        fun newInstance(pageNumber: Int, image: String): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putInt(arg_pageNumber, pageNumber)
            args.putString(imageUrl, image)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
