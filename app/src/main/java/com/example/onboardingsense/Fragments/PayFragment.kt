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
    val binding get() = _binding!!
    var curI : Int = 0
    private  var reviewList = mutableListOf<String>()
    private  var reviewTextList = mutableListOf<String>()
    private  var nameList = mutableListOf<String>()

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
        binding.monthly.setOnClickListener{
            binding.monthly.setBackgroundResource(R.drawable.stroke_for_pay)
            binding.yearly.setBackgroundResource(0)
        }

        binding.yearly.setOnClickListener{
            binding.monthly.setBackgroundResource(0)
            binding.yearly.setBackgroundResource(R.drawable.stroke_for_pay)
        }

        addToList()
        binding.reviewsViewPager.adapter = ReviewsAdapter(reviewList,reviewTextList,nameList)
        binding.reviewsViewPager.clipToPadding = false
        binding.reviewsViewPager.clipChildren = false
        binding.reviewsViewPager.offscreenPageLimit = 3
        binding.reviewsViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

       var compositePageTransformer: CompositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            val r : Float = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        })
        binding.reviewsViewPager.setPageTransformer(compositePageTransformer)

        val handler = Handler()
        val Update = Runnable {
            if (binding.reviewsViewPager.currentItem == 13) {
                binding.reviewsViewPager.currentItem  = 0
            }
            curI++
            binding.reviewsViewPager.setCurrentItem(curI, 1300, interpolator = AccelerateDecelerateInterpolator())
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 2000, 2000)
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

    fun addToList(){
        reviewList.add(0,getString(R.string.review0))
        reviewList.add(1,getString(R.string.review1))
        reviewList.add(2,getString(R.string.review2))
        reviewList.add(3,getString(R.string.review3))
        reviewList.add(4,getString(R.string.review4))
        reviewList.add(5,getString(R.string.review5))
        reviewList.add(6,getString(R.string.review6))
        reviewList.add(7,getString(R.string.review7))
        reviewList.add(8,getString(R.string.review8))
        reviewList.add(9,getString(R.string.review9))
        reviewList.add(10,getString(R.string.review10))
        reviewList.add(11,getString(R.string.review11))
        reviewList.add(12,getString(R.string.review12))

        reviewTextList.add(0,getString(R.string.reviewtext0))
        reviewTextList.add(1,getString(R.string.reviewtext1))
        reviewTextList.add(2,getString(R.string.reviewtext2))
        reviewTextList.add(3,getString(R.string.reviewtext3))
        reviewTextList.add(4,getString(R.string.reviewtext4))
        reviewTextList.add(5,getString(R.string.reviewtext5))
        reviewTextList.add(6,getString(R.string.reviewtext6))
        reviewTextList.add(7,getString(R.string.reviewtext7))
        reviewTextList.add(8,getString(R.string.reviewtext8))
        reviewTextList.add(9,getString(R.string.reviewtext9))
        reviewTextList.add(10,getString(R.string.reviewtext10))
        reviewTextList.add(11,getString(R.string.reviewtext11))
        reviewTextList.add(12,getString(R.string.reviewtext12))

        nameList.add(0,getString(R.string.name0))
        nameList.add(1,getString(R.string.name1))
        nameList.add(2,getString(R.string.name2))
        nameList.add(3,getString(R.string.name3))
        nameList.add(4,getString(R.string.name4))
        nameList.add(5,getString(R.string.name5))
        nameList.add(6,getString(R.string.name6))
        nameList.add(7,getString(R.string.name7))
        nameList.add(8,getString(R.string.name8))
        nameList.add(9,getString(R.string.name9))
        nameList.add(10,getString(R.string.name10))
        nameList.add(11,getString(R.string.name11))
        nameList.add(12,getString(R.string.name12))
    }
}

