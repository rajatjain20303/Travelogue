package com.example.travelogue

import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travelogue.ui.theme.TravelogueTheme
import androidx.navigation.compose.rememberNavController
import com.example.travelogue.presentation.sign_in.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity


class MainActivity : ComponentActivity() {
      private val googleAuthUiClient by lazy {
            GoogleAuthUiClient(
                  context = applicationContext,
                  oneTapClient = Identity.getSignInClient(applicationContext)
            )
      }
      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                  val  navController= rememberNavController()
                  val authViewModel:AuthViewModel=viewModel()
                  val userViewModel:UserViewModel=viewModel()

                  TravelogueTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                              modifier = Modifier.fillMaxSize(),
                              color = MaterialTheme.colorScheme.background
                        ) {
                              Navigation(navController = navController,authViewModel=authViewModel,googleAuthUiClient,userViewModel)
                        }
                  }
            }
      }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
      Text(
            text = "Hello $name!",
            modifier = modifier
      )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
      TravelogueTheme {
            Greeting("Android")
      }
}