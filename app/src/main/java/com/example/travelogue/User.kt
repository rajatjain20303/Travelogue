package com.example.travelogue

import androidx.compose.foundation.Image

data class User(
      val name:String="",
      val email:String="",
      val pass: String="",
      val conpass:String="",
      val image:Int=R.drawable.skeleton
)
