package com.example.onboardingsense.Fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.onboardingsense.R
import com.example.onboardingsense.databinding.FragmentViewPagerBinding
import android.view.MotionEvent
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.example.onboardingsense.AdaptersAndViewModel.DataViewModel
import com.example.onboardingsense.AdaptersAndViewModel.FragmentScreens
import com.example.onboardingsense.AdaptersAndViewModel.ViewPagerAdapterSense
import kotlinx.coroutines.*

class ViewPagerFragment : Fragment() {

    private val dataModel: DataViewModel by activityViewModels()
    private var _binding: FragmentViewPagerBinding? = null
    val binding get() = _binding!!
    private var animation_duration : Long = 1200
    var backgroundPostiton: Int = 1450

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var fadeOut = AnimationUtils.loadAnimation(activity, R.anim.fade_out)
        var fadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        binding.welcomeTextView.delayOnLifecycle(2100){
            binding.welcomeTextView.startAnimation(fadeOut)
            binding.welcomeTextView.delayOnLifecycle(1900){
                binding.welcomeTextView.clearAnimation()
                binding.welcomeTextView.visibility = View.GONE
            }
        }
        binding.imageViewVelcome.delayOnLifecycle(4100){
            binding.imageViewVelcome.startAnimation(fadeOut)
            binding.imageViewVelcome.visibility = View.GONE
            binding.btnCont.startAnimation(fadeIn)
        }
        binding.welcomeTextView.delayOnLifecycle(100){
            binding.scrollView.scrollX = FragmentScreens.FRAGMENT_BRING_BACKGROUND.currentFragmentScreen
            binding.welcomeTextView.visibility = View.VISIBLE
            binding.welcomeTextView.startAnimation(fadeIn)
        }

        val fragmentList = arrayListOf<Fragment>(
            BringFragment(),
            StressTypeFragment(),
            WorriesFragment(),
            PersonalizeFragment(),
            PayFragment()
        )
        val adapter = ViewPagerAdapterSense(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle)
        binding.viewPagerFragment.isUserInputEnabled = false
        binding.viewPagerFragment.adapter = adapter
        binding.indicator.createIndicators(3, 0)
        dataModel.posFrag.observe(viewLifecycleOwner, {
            binding.viewPagerFragment.currentItem = it
            when(it){
                FragmentScreens.FRAGMENT_BRING.currentFragmentScreen->{
                    backgroundPostiton = FragmentScreens.FRAGMENT_BRING_BACKGROUND.currentFragmentScreen
                    binding.indicator.animatePageSelected(0)
                    binding.textView.text = getString(R.string.what_brings)
                    binding.textView2.text = getString(R.string.we_recognize)
                    binding.button4.startAnimation(fadeIn)
                    binding.button7.startAnimation(fadeIn)
                    animation()
                }
                FragmentScreens.FRAGMENT_STRESS.currentFragmentScreen->{
                    binding.indicator.animatePageSelected(1)
                    binding.textView.text = getString(R.string.typeofstress)
                    binding.textView2.text = getString(R.string.we_recognize_2)
                    binding.button4.startAnimation(fadeOut)
                    binding.button7.startAnimation(fadeOut)
                    binding.textButton2.text = getString(R.string.physical)
                    binding.textButton3.text = getString(R.string.emotional)
                    binding.textButton4.text = getString(R.string.acute)
                    binding.textButton5.text = getString(R.string.chronic)
                    backgroundPostiton = FragmentScreens.FRAGMENT_STRESS_BACKGROUND.currentFragmentScreen
                    animation()
                }
                FragmentScreens.FRAGMENT_WORRIES.currentFragmentScreen->{
                    binding.indicator.animatePageSelected(2)
                    binding.textView.text = getString(R.string.oftenworries)
                    binding.textView2.text = getString(R.string.we_recognize_2)
                    binding.textButton2.text = getString(R.string.health_button)
                    binding.textButton3.text = getString(R.string.work)
                    binding.textButton4.text = getString(R.string.relationship)
                    binding.textButton5.text = getString(R.string.elsesmth)
                    backgroundPostiton = FragmentScreens.FRAGMENT_WORRIES_BACKGROUND.currentFragmentScreen
                    animation()
                }
                FragmentScreens.FRAGMENT_PERSONALIZE.currentFragmentScreen->{
                    binding.indicator.removeAllViews()
                    binding.textView.startAnimation(fadeOut)
                    binding.textView2.startAnimation(fadeOut)
                    binding.btnCont.startAnimation(fadeOut)
                    binding.button2.startAnimation(fadeOut)
                    binding.button3.startAnimation(fadeOut)
                    binding.button5.startAnimation(fadeOut)
                    binding.button6.startAnimation(fadeOut)
                    backgroundPostiton = FragmentScreens.FRAGMENT_PERSONALIZE_BACKGROUND.currentFragmentScreen
                    animation()
                }
                FragmentScreens.FRAGMENT_PAY.currentFragmentScreen-> {
                    binding.btnCont.startAnimation(fadeIn)
                    binding.btnCont.text = getString(R.string.tryforfree)
                    backgroundPostiton = FragmentScreens.FRAGMENT_PAY_BACKGROUND.currentFragmentScreen
                    animation()
                }
            }
        })
        binding.btnCont.setOnClickListener {
            binding.btnCont.isClickable = false
            binding.btnCont.delayOnLifecycle(animation_duration){
                binding.btnCont.isClickable = true
            }
            binding.btnCont.clearAnimation()
            binding.btnCont.visibility = View.VISIBLE
            binding.imageViewVelcome.clearAnimation()
            binding.imageViewVelcome.visibility = View.GONE
            when(binding.viewPagerFragment.currentItem){
                FragmentScreens.FRAGMENT_BRING.currentFragmentScreen -> {
                    dataModel.posFrag.value = FragmentScreens.FRAGMENT_STRESS.currentFragmentScreen
                }
                FragmentScreens.FRAGMENT_STRESS.currentFragmentScreen -> {
                    dataModel.posFrag.value = FragmentScreens.FRAGMENT_WORRIES.currentFragmentScreen
                }
                FragmentScreens.FRAGMENT_WORRIES.currentFragmentScreen ->{
                    binding.button4.removeAllViews()
                    binding.button7.removeAllViews()
                    dataModel.posFrag.value = FragmentScreens.FRAGMENT_PERSONALIZE.currentFragmentScreen
                }
                FragmentScreens.FRAGMENT_PERSONALIZE.currentFragmentScreen -> {
                    dataModel.posFrag.value = FragmentScreens.FRAGMENT_PAY.currentFragmentScreen
                    binding.button2.removeAllViews()
                    binding.button3.removeAllViews()
                    binding.button5.removeAllViews()
                    binding.button6.removeAllViews()
                    binding.button7.removeAllViews()
                    binding.button4.removeAllViews()
                }
            }
        }
        binding.scrollView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }
        })
    }
    private fun animation(){
        val animator = ObjectAnimator.ofInt(binding.scrollView, "scrollX", backgroundPostiton)
        animator.duration = animation_duration
        animator.start()
        when(backgroundPostiton){
            FragmentScreens.FRAGMENT_PAY_BACKGROUND.currentFragmentScreen -> {
                binding.scrollView.delayOnLifecycle(animation_duration){
                    animator.cancel()
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding.imageViewVelcome.clearAnimation()
        binding.indicator.clearAnimation()
        binding.btnCont.clearAnimation()
        binding.textView.clearAnimation()
        binding.textView2.clearAnimation()
        binding.button2.clearAnimation()
        binding.button3.clearAnimation()
        binding.button4.clearAnimation()
        binding.button5.clearAnimation()
        binding.button6.clearAnimation()
        binding.button7.clearAnimation()
    }
    private fun View.delayOnLifecycle(
        durationInMillis: Long,
        dispatcher : CoroutineDispatcher = Dispatchers.Main,
        block: () -> Unit
        ): Job? = findViewTreeLifecycleOwner()?.let {lifecycleOwner ->
        lifecycleOwner.lifecycle.coroutineScope.launch(dispatcher){
            delay(durationInMillis)
            if(isActive){
                block()
            }
        }
    }
    companion object{
        @JvmStatic
        fun newInstance() = ViewPagerFragment()
    }
}
