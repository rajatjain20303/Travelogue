package com.example.travelogue

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController,authViewModel: AuthViewModel) {
      NavHost(
            navController = navController, // Corrected equals sign
            startDestination = Screen.Homescreen.route // Corrected equals sign
      ) {
            composable(Screen.Homescreen.route) {
                  // Define the UI for the home screen here
                  Homescreen(navController = navController)

            }
            composable(Screen.LoginScreen.route){
                  LoginScreen( authViewModel=authViewModel,{navController.navigate(Screen.SignupScreen.route) },
                        {navController.navigate(Screen.MainMenScreen.route)})
            }
            composable(Screen.SignupScreen.route)
            {
                  SignUpScreen(authViewModel=authViewModel
                        ,{navController.navigate(Screen.LoginScreen.route)})
            }

            composable(Screen.MainMenScreen.route)
            {
                  mainMenuScreen()
            }
      }
}