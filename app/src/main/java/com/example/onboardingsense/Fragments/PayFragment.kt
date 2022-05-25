package com.example.onboardingsense.Fragments

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.onboardingsense.AdaptersAndViewModel.ReviewsAdapter
import com.example.onboardingsense.R
import com.example.onboardingsense.databinding.FragmentPayBinding
import java.util.*

class PayFragment : Fragment() {

    private var _binding: FragmentPayBinding? = null
    private val binding get() = _binding!!
    private var itemPosition : Int = 0
    private val handler = Handler()
    private val swipeTimer = Timer()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPayBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val review: List <String> = resources.getStringArray(R.array.review_list).toList()
        val reviewText: List <String> = resources.getStringArray(R.array.review_text_list).toList()
        val userName: List <String> = resources.getStringArray(R.array.name_list).toList()
        val adapter = ReviewsAdapter(review,reviewText,userName)

        binding.monthly.setOnClickListener{
            binding.monthly.setBackgroundResource(R.drawable.stroke_for_pay)
            binding.yearly.setBackgroundResource(0)
        }

        binding.yearly.setOnClickListener{
            binding.monthly.setBackgroundResource(0)
            binding.yearly.setBackgroundResource(R.drawable.stroke_for_pay)
        }
        binding.reviewsViewPager.adapter = adapter
        binding.reviewsViewPager.clipToPadding = false
        binding.reviewsViewPager.clipChildren = false
        binding.reviewsViewPager.offscreenPageLimit = 1
        binding.reviewsViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
       var compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r : Float = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        })
        binding.reviewsViewPager.setPageTransformer(compositePageTransformer)

        val update = Runnable {
            if (binding.reviewsViewPager.currentItem == 12) {
                binding.reviewsViewPager.clearAnimation()
                swipeTimer.cancel()
            }
            itemPosition++
            binding.reviewsViewPager.setCurrentItem(itemPosition, 1300, interpolator = AccelerateDecelerateInterpolator())
        }
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 2000, 2000)
        handler.removeCallbacks(update)
    }
    fun ViewPager2.setCurrentItem(
        item: Int,
        duration: Long,
        interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(),
        pagePxWidth: Int = (width * 0.60).toInt() // Default value taken from getWidth() from ViewPager2 view
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
    override fun onPause() {
        super.onPause()
        swipeTimer.cancel()
    }
}

