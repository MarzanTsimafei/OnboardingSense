package com.example.onboardingsense.Fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import com.example.onboardingsense.AdaptersAndViewModel.DataViewModel
import com.example.onboardingsense.AdaptersAndViewModel.FragmentScreens
import com.example.onboardingsense.R
import com.example.onboardingsense.databinding.FragmentPersonalizeBinding


class PersonalizeFragment : Fragment() {

    private val dataModel: DataViewModel by activityViewModels()
    private var _binding: FragmentPersonalizeBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalizeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.indicatorPerson.show()

        val fadeIn = AnimationUtils.loadAnimation(activity,
            R.anim.fade_in);
        val fadeOut = AnimationUtils.loadAnimation(activity,
            R.anim.fade_out);
        binding.tvUniq.startAnimation(fadeIn)
        Handler().postDelayed(Runnable {
            binding.tvUniq.startAnimation(fadeOut)
            binding.tvNature.startAnimation(fadeIn)

        }, 2500)

        Handler().postDelayed(Runnable{
            binding.tvUniq.clearAnimation()
            binding.tvNature.startAnimation(fadeOut)
            binding.tvMeassure.startAnimation(fadeIn)
        }, 5000)

        Handler().postDelayed(Runnable {
            binding.tvMeassure.startAnimation(fadeOut)
            binding.tvNature.clearAnimation()
        }, 7500)


        Handler().postDelayed(Runnable {
            dataModel.posFrag.value = FragmentScreens.FRAGMENT_PAY.currentFragmentScreen
        }, 8000)
    }
    override fun onStop() {
        super.onStop()
        binding.indicatorPerson.hide()
    }
}
