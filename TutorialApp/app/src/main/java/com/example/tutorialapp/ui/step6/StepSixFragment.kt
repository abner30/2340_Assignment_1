package com.example.tutorialapp.ui.step6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import  androidx.fragment.app.Fragment
import com.example.tutorialapp.R

class StepSixFragment : Fragment()  {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stepsix,container,false)

        return view;
    }
}