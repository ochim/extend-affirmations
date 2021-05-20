package com.example.affirmations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource
import com.example.affirmations.databinding.FragmentItemBinding
import com.example.affirmations.model.Affirmation

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_INDEX = "index"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemFragment : Fragment() {

    private var _binding: FragmentItemBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var index: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            index = it.getString(ARG_INDEX)?.toInt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myDataset = Datasource().loadAffirmations(index ?: 0)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 1)
        recyclerView.adapter = ItemAdapter(requireActivity(), myDataset,
            { affirmation -> adapterOnClick(affirmation) })

        recyclerView.setHasFixedSize(true)
    }

    private fun adapterOnClick(affirmation: Affirmation) {
        val action = BlankFragmentDirections.actionBlankFragmentToItemDetailFragment(affirmation)
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.findNavController().navigate(action)
    }

    companion object {
        @JvmStatic
        fun newInstance(index: String?) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_INDEX, index)
                }
            }
    }
}