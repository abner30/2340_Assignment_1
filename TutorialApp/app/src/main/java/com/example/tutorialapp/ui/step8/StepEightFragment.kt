package com.example.tutorialapp.ui.step8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import  androidx.fragment.app.Fragment
import com.example.tutorialapp.R

class StepEightFragment : Fragment()  {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stepeight,container,false)

        return view;
    }
}