package com.example.travelogue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.travelogue.presentation.sign_in.UserData

@Composable
fun mainMenuScreen(
      userData: UserData?,
      email:String,
      viewModel: UserViewModel,
      onSignOut:()->Unit,
)
{
      Column(
            modifier= Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
      ) {
            if (userData?.profilePicture != null) {
                  AsyncImage(
                        model = userData.profilePicture,
                        contentDescription = "profile",
                        modifier = Modifier
                              .size(150.dp)
                              .clip(CircleShape),
                        contentScale = ContentScale.Crop
                  )
                  Spacer(modifier = Modifier.height(16.dp))
            }
            if (userData?.userName != null) {
                  Text(
                        text = userData.userName,
                        style = customTextStyle3
                  )
                  Spacer(modifier = Modifier.height(16.dp))

            } else {
                  LaunchedEffect(email) {
                        viewModel.fetchUserData(email)
                  }
                  val user by viewModel.userDataState
                  user.let { user ->
                        // Display user image (assuming it's an integer resource ID or URL)
                        if (user != null) {
                              AsyncImage(
                                    model = user.image,
                                    contentDescription = "profile",
                                    modifier = Modifier
                                          .size(150.dp)
                                          .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                              )
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        // Display user's name
                        if (user != null) {
                              Text(
                                    text = user.name,
                                    style = customTextStyle3
                              )
                        }
                  }
            }


            Spacer(modifier = Modifier.height(16.dp))


            Button(onClick = { onSignOut() }) {
                  Text(text = "Sign Out", style = customTextStyle5)

            }
      }
}