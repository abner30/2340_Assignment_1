package com.example.tutorialapp.ui.step9

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import  androidx.fragment.app.Fragment
import com.example.tutorialapp.R

class StepNineFragment : Fragment()  {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stepnine,container,false)

        return view;
    }
}