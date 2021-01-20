package com.example.affirmations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs

class ItemDetailFragment : Fragment() {

    val args: ItemDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val affirmation = args.myAffirmation ?: return
        view.findViewById<ImageView>(R.id.item_image).setImageResource(affirmation.imageResourceId)
        view.findViewById<TextView>(R.id.item_title).text = getString(affirmation.stringResourceId)
    }

    companion object {
    }
}