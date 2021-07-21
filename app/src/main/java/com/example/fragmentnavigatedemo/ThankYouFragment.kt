package com.example.fragmentnavigatedemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ThankYouFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_thank_you, container, false)

        val tvPercentage: TextView= view.findViewById(R.id.tvPercentage)

        val args= ThankYouFragmentArgs.fromBundle(requireArguments())

        tvPercentage.text= "Your score: "+String.format("%.2f", args.score) +"%"

        return view
    }

}