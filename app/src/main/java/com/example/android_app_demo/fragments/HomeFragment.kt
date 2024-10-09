package com.example.android_app_demo.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.android_app_demo.R

class HomeFragment : Fragment() {


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (childFragmentManager.backStackEntryCount > 0) {
                    childFragmentManager.popBackStack()
                    return
                }
                parentFragmentManager.popBackStack()
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val fragmentbtn1 = view.findViewById<Button>(R.id.fragment1_btn)
        val fragmentbtn2 = view.findViewById<Button>(R.id.fragment2_btn)

        fragmentbtn1.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.home_fragment_container, HomeFragment1_1())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        fragmentbtn2.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.home_fragment_container, HomeFragment1_2())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }


}