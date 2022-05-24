package com.example.onboardingsense.AdaptersAndViewModel
import com.example.onboardingsense.databinding.FragmentPayBinding
import com.example.onboardingsense.databinding.FragmentReviewsBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object ReviewsDelegate {
    val reviewsDelegate =  adapterDelegateViewBinding<ReviewsItems, ListItem, FragmentPayBinding>(
        {inflater, container -> FragmentPayBinding.inflate(inflater,container,false).apply {
            reviewsViewPager.adapter = adapter
        }}
    ) {
        bind {
            (binding.reviewsViewPager.adapter as ListDelegationAdapter<List<ListItem>>).apply {
                items = item.reviews
                notifyDataSetChanged()
            }
        }
    }
    private val reviewsContainerDelegate =  adapterDelegateViewBinding<ReviewsItemData, ListItem, FragmentReviewsBinding>(
        {inflater, container -> FragmentReviewsBinding.inflate(inflater,container,false).apply {
        }}
    ) {
        bind {
            binding.tvRewievFirst.text = item.review
            binding.tvRewiev.text = item.reviewText
            binding.tvName.text = item.userName
        }
    }
    private val adapter = ListDelegationAdapter(reviewsContainerDelegate)
}