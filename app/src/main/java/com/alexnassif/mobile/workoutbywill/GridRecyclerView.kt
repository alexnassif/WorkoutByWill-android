package com.alexnassif.mobile.workoutbywill

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.view.animation.GridLayoutAnimationController
import android.view.ViewGroup


class GridRecyclerView : RecyclerView {

    /** @see View.View
     */
    constructor(context: Context) : super(context) {}

    /** @see View.View
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    /** @see View.View
     */
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun setLayoutManager(layout: RecyclerView.LayoutManager) {
        if (layout is GridLayoutManager) {
            super.setLayoutManager(layout)
        } else {
            throw ClassCastException("You should only use a GridLayoutManager with GridRecyclerView.")
        }
    }

    override fun attachLayoutAnimationParameters(child: View?, params: ViewGroup.LayoutParams?, index: Int, count: Int) {

            val layoutManager = layoutManager
            if (adapter != null && layoutManager is GridLayoutManager) {


                var animationParams: GridLayoutAnimationController.AnimationParameters?


                if (params?.layoutAnimationParameters == null) {
                    // If there are no animation parameters, create new once and attach them to
                    // the LayoutParams.
                    animationParams = GridLayoutAnimationController.AnimationParameters()
                    params!!.layoutAnimationParameters = animationParams
                }else{
                    animationParams = params!!.layoutAnimationParameters as GridLayoutAnimationController.AnimationParameters
                }

                // Next we are updating the parameters

                // Set the number of items in the RecyclerView and the index of this item
                animationParams.count = count
                animationParams.index = index

                // Calculate the number of columns and rows in the grid
                val columns = layoutManager.spanCount
                animationParams.columnsCount = columns
                animationParams.rowsCount = count / columns

                // Calculate the column/row position in the grid
                val invertedIndex = count - 1 - index
                animationParams.column = columns - 1 - invertedIndex % columns
                animationParams.row = animationParams.rowsCount - 1 - invertedIndex / columns

            } else {

                // Proceed as normal if using another type of LayoutManager
                super.attachLayoutAnimationParameters(child, params, index, count)
            }

    }
}