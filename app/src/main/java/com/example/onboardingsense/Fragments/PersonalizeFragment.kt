package com.example.onboardingsense.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.example.onboardingsense.AdaptersAndViewModel.DataViewModel
import com.example.onboardingsense.AdaptersAndViewModel.FragmentScreens
import com.example.onboardingsense.R
import com.example.onboardingsense.databinding.FragmentPersonalizeBinding
import kotlinx.coroutines.*

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
        binding.tvUniq.delayOnLifecycle(2500){
            binding.tvUniq.startAnimation(fadeOut)
            binding.tvNature.startAnimation(fadeIn)
        }
        binding.tvNature.delayOnLifecycle(5000){
            binding.tvUniq.clearAnimation()
            binding.tvNature.startAnimation(fadeOut)
            binding.tvMeassure.startAnimation(fadeIn)
        }
        binding.tvMeassure.delayOnLifecycle(7500){
            binding.tvMeassure.startAnimation(fadeOut)
            binding.tvNature.clearAnimation()
            dataModel.posFrag.value = FragmentScreens.FRAGMENT_PAY.currentFragmentScreen
        }
    }
    override fun onStop() {
        super.onStop()
        binding.indicatorPerson.hide()
        binding.tvMeassure.clearAnimation()
    }
    private fun View.delayOnLifecycle(
        durationInMillis: Long,
        dispatcher : CoroutineDispatcher = Dispatchers.Main,
        block: () -> Unit
    ): Job? = findViewTreeLifecycleOwner()?.let { lifecycleOwner ->
        lifecycleOwner.lifecycle.coroutineScope.launch(dispatcher){
            delay(durationInMillis)
            if(isActive){
                block()
            }
        }
    }
}
