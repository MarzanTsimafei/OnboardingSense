package com.example.onboardingsense.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onboardingsense.databinding.FragmentBringBinding

class BringFragment : Fragment() {

    private var _binding: FragmentBringBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBringBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}