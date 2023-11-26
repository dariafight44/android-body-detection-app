package com.strangerwill.bda.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.strangerwill.bda.R

class MainFragment: Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated (view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val tambudetknopka = view.findViewById<Button>(R.id.button_start)
        tambudetknopka.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_mainFragment_to_secondFragment)
        }
    }


}