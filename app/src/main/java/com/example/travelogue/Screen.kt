package com.example.travelogue


sealed class Screen(val route:String) {
      object Homescreen:Screen("homescreen")
      object LoginScreen:Screen("loginscreen")
      object SignupScreen:Screen("signupscreen")
      object MainMenScreen:Screen("mainmenuscreen")
      object ChatScreen:Screen("chatscreen")
}