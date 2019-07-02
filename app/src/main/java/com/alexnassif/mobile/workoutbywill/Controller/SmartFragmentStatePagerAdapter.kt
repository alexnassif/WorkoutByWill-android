package com.alexnassif.mobile.workoutbywill.Controller

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.view.ViewGroup
import android.util.SparseArray
import androidx.fragment.app.FragmentStatePagerAdapter
import android.view.View


abstract class SmartFragmentStatePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    // Sparse array to keep track of registered fragments in memory
    private val registeredFragments = SparseArray<Fragment>()

    // Register the fragment when the item is instantiated
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    // Returns the fragment for the position (if instantiated)
    fun getRegisteredFragment(position: Int): Fragment {
        return registeredFragments.get(position)
    }
}