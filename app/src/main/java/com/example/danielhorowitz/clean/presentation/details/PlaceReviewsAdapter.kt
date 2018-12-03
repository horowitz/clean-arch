package com.example.danielhorowitz.clean.presentation.details

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Reviews
import com.example.danielhorowitz.clean.presentation.common.loadCircular


/**
 * Created by danielhorowitz on 21/03/2018.
 */

class PlaceReviewsAdapter(private val items: List<Reviews>) : RecyclerView.Adapter<PlaceReviewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.place_review_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        private val tvReview = itemView.findViewById<TextView>(R.id.tv_review)
        private val tvTimestamp = itemView.findViewById<TextView>(R.id.tv_timestamp)
        private val ivProfile = itemView.findViewById<ImageView>(R.id.iv_profile)
        private val ratingContainer = itemView.findViewById<LinearLayout>(R.id.rating_container)

        fun bind(review: Reviews) {
            tvName.text = review.authorName
            tvReview.text = review.text
            tvTimestamp.text = review.relativeTimeDescription
            ivProfile.loadCircular(review.profilePhotoUrl)
            setRating(review.rating)
        }

        private fun setRating(rating: Int) {
            val inflater = LayoutInflater.from(itemView.context)
            ratingContainer.removeAllViews()
            for (stars in 1..rating) {
                val view = inflater.inflate(R.layout.review_rating_star,ratingContainer,false)
                ratingContainer.addView(view)
            }
        }
    }
}

