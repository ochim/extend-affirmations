package com.example.affirmations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.affirmations.adapter.CollectionAdapter
import com.example.affirmations.databinding.FragmentBlankBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {

    private var _binding: FragmentBlankBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var collectionAdapter: CollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectionAdapter = CollectionAdapter(this)
        viewPager = binding.pager
        viewPager.apply {
            adapter = collectionAdapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

                private var realPosition = -1

                override fun onPageScrollStateChanged(state: Int) {
                    if (state == ViewPager2.SCROLL_STATE_IDLE && realPosition >= 0) {
                        viewPager.setCurrentItem(realPosition, false)
                        realPosition = -1
                    }
                }

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> realPosition = collectionAdapter.getRealCount()
                        collectionAdapter.getRealCount() + 1 -> realPosition = 1
                        else -> {
                        }
                    }
                }
            })
            setCurrentItem(1, false)
        }

        val tabLayout = binding.tabLayout
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val i = collectionAdapter.getRealPosition(position)
            tab.text = "PAGE ${(i + 1)}"
        }.attach()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment BlankFragment.
         */
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}