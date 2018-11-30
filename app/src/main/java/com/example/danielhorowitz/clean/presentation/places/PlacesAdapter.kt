package com.example.danielhorowitz.clean.presentation.places

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.danielhorowitz.clean.R
import com.example.danielhorowitz.clean.domain.model.Place
import com.example.danielhorowitz.clean.presentation.common.load


/**
 * Created by danielhorowitz on 21/03/2018.
 */

class PlacesAdapter(
    private val items: List<Place>,
    private val itemClicked: (Place) -> Unit
) : RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], itemClicked)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvSubtitle = itemView.findViewById<TextView>(R.id.tvSubtitle)
        private val tvRating = itemView.findViewById<TextView>(R.id.tvItemRating)
        private val ivPlace = itemView.findViewById<ImageView>(R.id.ivPlace)
        private val ivOpenClosed = itemView.findViewById<ImageView>(R.id.ivOpenClosed)

        fun bind(place: Place, itemClicked: (Place) -> Unit) {
            ivPlace.load(place.images.firstOrNull() ?: "")
            tvTitle.text = place.name
            tvSubtitle.text = place.vicinity
            tvRating.text = place.rating.toString()
            ivOpenClosed.setImageResource(if (place.openNow) R.drawable.ic_open else R.drawable.ic_closed)
            itemView.setOnClickListener { itemClicked(place) }
        }
    }
}

