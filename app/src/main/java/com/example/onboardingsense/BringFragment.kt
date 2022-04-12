package com.example.onboardingsense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.onboardingsense.databinding.FragmentBringBinding
import com.example.onboardingsense.databinding.FragmentWelcomeBinding


class BringFragment : Fragment() {
    private val dataModel: DataViewModel by activityViewModels()

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.btnCont.setOnClickListener {
            dataModel.posFrag.value = FragmentScreens.FRAGMENT_STRESS.currentFragmentScreen

        }

    }

}