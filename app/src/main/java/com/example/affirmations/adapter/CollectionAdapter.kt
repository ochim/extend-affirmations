package com.example.affirmations.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.affirmations.ItemFragment

private val REAL_PAGE_SIZE = 10

class CollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = REAL_PAGE_SIZE + 2

    fun getRealCount() = REAL_PAGE_SIZE

    fun getRealPosition(position: Int): Int {
        return when (position) {
            0 -> getRealCount() - 1
            getRealCount() + 1 -> 0
            else -> position - 1
        }
    }

    // Return a NEW fragment instance in createFragmt
    override fun createFragment(position: Int): Fragment {
        val i = getRealPosition(position)
        return ItemFragment.newInstance("$i")
    }
}