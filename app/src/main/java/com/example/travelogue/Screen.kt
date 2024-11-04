package com.example.travelogue


sealed class Screen(val route:String) {
      object Homescreen:Screen("homescreen")
      object LoginScreen:Screen("loginscreen")
      object SignupScreen:Screen("signupscreen")
      object MainMenuScreen:Screen("mainmenuscreen/{email}"){
            fun createRoute(email:String)="mainMenuScreen/$email"
      }

}