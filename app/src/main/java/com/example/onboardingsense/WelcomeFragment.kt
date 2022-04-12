package com.example.onboardingsense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.onboardingsense.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private val dataModel: DataViewModel by activityViewModels()

    private var _binding: FragmentWelcomeBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.button80.setOnClickListener {
            dataModel.posFrag.value = FragmentScreens.FRAGMENT_BRING.currentFragmentScreen
            dataModel.posFrag.observe(activity as LifecycleOwner , {
                binding.qqq.text = it.toString()
            })
        }

    }

}