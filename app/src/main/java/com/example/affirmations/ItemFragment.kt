package com.example.affirmations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.data.Datasource

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_INDEX = "index"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemFragment : Fragment() {

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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myDataset = Datasource().loadAffirmations(index ?: 0)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(requireActivity(), myDataset)

        recyclerView.setHasFixedSize(true)
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