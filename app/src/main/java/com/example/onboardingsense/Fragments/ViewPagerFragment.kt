package com.example.onboardingsense.Fragments

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.onboardingsense.R
import com.example.onboardingsense.databinding.FragmentViewPagerBinding
import android.animation.ObjectAnimator
import android.os.Handler
import android.view.MotionEvent
import com.example.onboardingsense.AdaptersAndViewModel.DataViewModel
import com.example.onboardingsense.AdaptersAndViewModel.FragmentScreens
import com.example.onboardingsense.AdaptersAndViewModel.ViewPagerAdapterSense


class ViewPagerFragment : Fragment() {

    private val dataModel: DataViewModel by activityViewModels()
    private var _binding: FragmentViewPagerBinding? = null
    val binding get() = _binding!!

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

        Handler().postDelayed(Runnable {
            binding.welcomeTextView.startAnimation(fadeOut)
            Handler().postDelayed(Runnable {
                binding.welcomeTextView.clearAnimation()
                binding.welcomeTextView.visibility = View.GONE
            }, 1900)
        }, 2100)


        Handler().postDelayed(Runnable {
            binding.imageViewVelcome.startAnimation(fadeOut)
            binding.imageViewVelcome.visibility = View.GONE
            binding.btnCont.startAnimation(fadeIn)

        }, 4100)

        Handler().postDelayed(Runnable {
            binding.scrollView.scrollX = 1450
            binding.welcomeTextView.visibility = View.VISIBLE
            binding.welcomeTextView.startAnimation(fadeIn)
        }, 100)

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
            lifecycle
        )

        binding.viewPagerFragment.isUserInputEnabled = false
        binding.viewPagerFragment.adapter = adapter
        binding.indicator.createIndicators(3, 0)
        dataModel.posFrag.observe(viewLifecycleOwner, {
            binding.viewPagerFragment.setCurrentItem(it, 1300, interpolator = AccelerateDecelerateInterpolator())
            when(it){
                FragmentScreens.FRAGMENT_BRING.currentFragmentScreen->{
                    val animator = ObjectAnimator.ofInt( binding.scrollView, "scrollX", 1450)
                    animator.duration = 1200
                    animator.start()
                    binding.indicator.animatePageSelected(0)
                    binding.textView.text = getString(R.string.what_brings)
                    binding.textView2.text = getString(R.string.we_recognize)
                    binding.button4.startAnimation(fadeIn)
                    binding.button7.startAnimation(fadeIn)
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
                    val animator = ObjectAnimator.ofInt( binding.scrollView, "scrollX", 2100)
                    animator.duration = 1200
                    animator.start()

                }
                FragmentScreens.FRAGMENT_WORRIES.currentFragmentScreen->{
                    binding.indicator.animatePageSelected(2)
                    binding.textView.text = getString(R.string.oftenworries)
                    binding.textView2.text = getString(R.string.we_recognize_2)
                    binding.textButton2.text = getString(R.string.health_button)
                    binding.textButton3.text = getString(R.string.work)
                    binding.textButton4.text = getString(R.string.relationship)
                    binding.textButton5.text = getString(R.string.elsesmth)
                    val animator = ObjectAnimator.ofInt( binding.scrollView, "scrollX", 2500)
                    animator.duration = 1200
                    animator.start()
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
                    val animator = ObjectAnimator.ofInt( binding.scrollView, "scrollX", 3000)
                    animator.duration = 1200
                    animator.start()
                }
                FragmentScreens.FRAGMENT_PAY.currentFragmentScreen-> {
                    val animator = ObjectAnimator.ofInt( binding.scrollView, "scrollX", 3500)
                    animator.duration = 1200
                    animator.start()
                    binding.btnCont.startAnimation(fadeIn)
                    binding.btnCont.text = getString(R.string.tryforfree)
                }
            }
        })

        binding.btnCont.setOnClickListener {
            binding.btnCont.isClickable = false
            Handler().postDelayed(Runnable {
                binding.btnCont.isClickable = true
            }, 1500)

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
                FragmentScreens.FRAGMENT_PERSONALIZE.currentFragmentScreen -> dataModel.posFrag.value = FragmentScreens.FRAGMENT_PAY.currentFragmentScreen
            }
        }
        binding.scrollView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }
        })
    }

    fun ViewPager2.setCurrentItem(
        item: Int,
        duration: Long,
        interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(),
        pagePxWidth: Int = width
    ) {
        val pxToDrag: Int = pagePxWidth * (item - currentItem)
        val animator = ValueAnimator.ofInt(0, pxToDrag)
        var previousValue = 0
        animator.addUpdateListener { valueAnimator ->
            val currentValue = valueAnimator.animatedValue as Int
            val currentPxToDrag = (currentValue - previousValue).toFloat()
            fakeDragBy(-currentPxToDrag)
            previousValue = currentValue
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) { beginFakeDrag() }
            override fun onAnimationEnd(animation: Animator?) { endFakeDrag() }
            override fun onAnimationCancel(animation: Animator?) { /* Ignored */ }
            override fun onAnimationRepeat(animation: Animator?) { /* Ignored */ }
        })
        animator.interpolator = interpolator
        animator.duration = duration
        animator.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.btnCont.clearAnimation()
        binding.textView.clearAnimation()
        binding.textView2.clearAnimation()
        binding.btnCont.clearAnimation()
        binding.button2.clearAnimation()
        binding.button3.clearAnimation()
        binding.button5.clearAnimation()
        binding.button6.clearAnimation()
        binding.button7.clearAnimation()
    }

    companion object{
        @JvmStatic
        fun newInstance() = ViewPagerFragment()
    }
}
