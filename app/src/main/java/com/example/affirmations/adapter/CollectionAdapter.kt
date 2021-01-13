package com.example.affirmations.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.affirmations.ItemFragment

class CollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragmt)
        return ItemFragment.newInstance("$position")
    }
}