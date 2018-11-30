package com.example.danielhorowitz.clean.domain.model

data class NearbyPlaces(
   val restaurants: List<Place>,
   val bars: List<Place>,
   val cafes: List<Place>
)