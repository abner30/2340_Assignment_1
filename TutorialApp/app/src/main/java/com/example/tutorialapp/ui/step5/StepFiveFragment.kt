package com.example.tutorialapp.ui.step5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import  androidx.fragment.app.Fragment
import com.example.tutorialapp.R

class StepFiveFragment : Fragment()  {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stepfive,container,false)

        return view;
    }
}