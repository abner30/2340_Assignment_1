package com.example.tutorialapp.ui.step3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import  androidx.fragment.app.Fragment
import com.example.tutorialapp.R

class StepThreeFragment : Fragment()  {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stepthree,container,false)

        return view;
    }
}