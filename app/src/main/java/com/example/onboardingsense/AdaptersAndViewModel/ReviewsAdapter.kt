package com.example.onboardingsense.AdaptersAndViewModel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardingsense.databinding.FragmentReviewsBinding

class ReviewsAdapter(private var review: List<String>, private var reviewText: List<String>,private var userName: List<String>,)
    : RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>(){
    class ReviewsViewHolder(val binding: FragmentReviewsBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        return  ReviewsViewHolder(FragmentReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.binding.tvName.text = userName[position]
        holder.binding.tvRewiev.text = reviewText[position]
        holder.binding.tvRewievFirst.text = review[position]
    }
    override fun getItemCount(): Int {
        return review.size
    }
}