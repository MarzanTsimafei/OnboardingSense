package com.example.onboardingsense.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.onboardingsense.Activities.MainActivity
import com.example.onboardingsense.Activities.PayActivity
import com.example.onboardingsense.Activities.PersonalizeActivity
import com.example.onboardingsense.AdaptersAndViewModel.DataViewModel
import com.example.onboardingsense.databinding.FragmentWorriesBinding


class WorriesFragment : Fragment() {

        private val dataModel: DataViewModel by activityViewModels()

        private var _binding: FragmentWorriesBinding? = null
        val binding get() = _binding!!


        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentWorriesBinding.inflate(inflater, container, false)
            val view = binding.root
            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.btnCont.setOnClickListener {

                val intent = Intent (getActivity(), PayActivity::class.java)
                getActivity()?.startActivity(intent)
            }


            }
        }

