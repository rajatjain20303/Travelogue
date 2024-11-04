package com.example.travelogue

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.travelogue.presentation.sign_in.GoogleAuthUiClient
import com.example.travelogue.presentation.sign_in.SignInViewmodel
import kotlinx.coroutines.launch


@Composable
fun Navigation(navController: NavHostController,authViewModel: AuthViewModel
               ,googleAuthClient:GoogleAuthUiClient, userViewModel: UserViewModel) {
      val lifecycleOwner = LocalLifecycleOwner.current // Access the lifecycle owner
      val context= LocalContext.current
      NavHost(
            navController = navController, // Corrected equals sign
            startDestination = Screen.Homescreen.route // Corrected equals sign
      ) {
            composable(Screen.Homescreen.route) {
                  // Define the UI for the home screen here
                  Homescreen(navController = navController)

            }
            composable(Screen.LoginScreen.route){
                  val viewmodel= viewModel<SignInViewmodel>()
                  val state by viewmodel.state.collectAsStateWithLifecycle()
                  LaunchedEffect(key1 = Unit){
                        if(googleAuthClient.getSignedInUser()!=null)
                              navController.navigate(Screen.MainMenuScreen.route)
                  }
                  val launcher= rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result->
                              if(result.resultCode==RESULT_OK){
                                    lifecycleOwner.lifecycleScope.launch{
                                          val signInResult= googleAuthClient.signInWithIntent(
                                                intent=result.data ?:return@launch
                                          )
                                          viewmodel.onSignInResult(signInResult)
                                    }
                              }
                        }
                  )

                  LaunchedEffect(key1 = state.isSignInSuccessful){
                        if(state.isSignInSuccessful){
                              Toast.makeText(context,"Sign in Successful!",
                                    Toast.LENGTH_SHORT).show()

                              navController.navigate(Screen.MainMenuScreen.route)
                              viewmodel.resetState()
                        }
                  }
                  LoginScreen( authViewModel=authViewModel,{navController.navigate(Screen.SignupScreen.route) },
                        {navController.navigate(Screen.MainMenuScreen.route)},state,{
                              lifecycleOwner.lifecycleScope.launch{
                                    val signInIntentSender=googleAuthClient.signIn()
                                    launcher.launch(IntentSenderRequest.Builder(
                                          signInIntentSender?:return@launch
                                          ).build()
                                    )
                              }
                        }
                  )
            }
            composable(Screen.SignupScreen.route)
            {
                  SignUpScreen(authViewModel=authViewModel
                        ,{navController.navigate(Screen.LoginScreen.route)})
            }

            composable(Screen.MainMenuScreen.route)
            {
                  val email= it.arguments?.getString("email")
                  mainMenuScreen(
                        userData = googleAuthClient.getSignedInUser(), // Pass user data as needed
                        email = email ?: "",  // Pass the email, default to empty string if null
                        viewModel = userViewModel, // Pass the UserViewModel instance
                        onSignOut = {
                              lifecycleOwner.lifecycleScope.launch {
                                    googleAuthClient.signOut()
                                    Toast.makeText(
                                          context,
                                          "User Signed Out",
                                          Toast.LENGTH_SHORT
                                    ).show()

                                    navController.popBackStack() // Navigate back after sign out
                              }
                        }
                  )
            }
      }
}