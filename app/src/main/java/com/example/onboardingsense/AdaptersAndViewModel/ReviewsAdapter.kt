package com.example.onboardingsense.AdaptersAndViewModel


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardingsense.R



class ReviewsAdapter ( private var review: List<String>, private var textReview: List<String>, private var name: List<String>)
    : RecyclerView.Adapter<ReviewsAdapter.Pager2ViewHolder>() {
    inner class Pager2ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tv_rewiev_first : TextView = itemView.findViewById(R.id.tv_rewiev_first)
        val tv_rewiev : TextView = itemView.findViewById(R.id.tv_rewiev)
        val tv_name : TextView = itemView.findViewById(R.id.tv_name)
    }
    override fun getItemCount(): Int = 13

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewsAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_reviews, parent, false))
    }

    override fun onBindViewHolder(holder: ReviewsAdapter.Pager2ViewHolder, position: Int) {
        holder.tv_rewiev_first.text = review[position]
        holder.tv_rewiev.text = textReview[position]
        holder.tv_name.text = name[position]

    }
}