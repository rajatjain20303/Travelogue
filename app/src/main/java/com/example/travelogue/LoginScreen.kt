package com.example.travelogue

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState

val customTextStyle3= TextStyle(
      fontFamily =nunitoFontFamily,
      fontWeight = FontWeight.Bold,
      fontStyle = FontStyle.Normal,
      fontSize = 30.sp,
      letterSpacing = 0.sp
)
val customTextStyle4= TextStyle(
      fontFamily =nunitoFontFamily,
      fontWeight = FontWeight.Normal,
      fontStyle = FontStyle.Normal,
      fontSize = 18.sp,
      letterSpacing = 0.sp
)
val customTextStyle5= TextStyle(
      fontFamily =nunitoFontFamily,
      fontWeight = FontWeight.SemiBold,
      fontStyle = FontStyle.Normal,
      fontSize = 20.sp,
      letterSpacing = 0.sp
)
@Composable
fun LoginScreen(authViewModel: AuthViewModel,onClickSignUp:()->Unit,onClickSignIn:()->Unit){
      val context = LocalContext.current
      var password by remember { mutableStateOf("") }
      var passwordVisible by remember { mutableStateOf(false) }
      var userName by remember{mutableStateOf("")}

      val result by authViewModel.authResult.observeAsState()
      BackHandler {
            // Exit the app instead of going back to the home screen
            (context as? Activity)?.finish()
      }
      Column(
            modifier = Modifier
                  .fillMaxSize()
                  .background(Color.White)
                  .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
      ) {
            Spacer(modifier = Modifier.height(30.dp))
            /* Top Image */
            Image(
                  painter = painterResource(R.drawable.around_the_world),  // Replace with your drawable
                  contentDescription = "Top Image",
                  modifier = Modifier
                        .size(200.dp)
                        .padding(12.dp),
                  contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Sign in Text
            Text(
                  text = "Sign In",
                  style = customTextStyle3,
                  color = Color(60,103,103)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Email input field
            OutlinedTextField(
                  value =userName,
                  onValueChange = {userName=it},
                  textStyle = customTextStyle4,
                  singleLine =true,
                  placeholder = { Text("Email",
                        color = Color(0,0,0,50),
                        style = customTextStyle4
                  )},
                  leadingIcon = {
                        Icon(Icons.Outlined.Person , contentDescription = "Email Icon",
                              tint = Color(48,136,126)
                        )
                  },
                  modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .border(border = BorderStroke(2.dp,Color(48,136,126)),
                              RoundedCornerShape(15.dp)
                        ),
                  shape = RoundedCornerShape(15.dp)

            )

            Spacer(modifier = Modifier.height(20.dp))

            // Password input field
            OutlinedTextField(
                  value = password,
                  onValueChange = {password=it},
                  textStyle = customTextStyle4,
                  singleLine = true,
                  placeholder = { Text("Password",
                        color = Color(0,0,0,50),
                        style = customTextStyle4) },
                  visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                  leadingIcon = {
                        Icon(Icons.Outlined.Lock, contentDescription = "Password Icon",
                              tint = Color(48,136,126))
                  },
                  trailingIcon = {
                        val image = if (passwordVisible)
                              Icons.Outlined.Visibility
                        else Icons.Outlined.VisibilityOff
                        val description = if (passwordVisible) "Hide password" else "Show password"

                        // Toggle button to hide or display password
                        IconButton(onClick = {passwordVisible = !passwordVisible}){
                              Icon(imageVector  = image, description,tint = Color(48,136,126))
                        }
                  },
                  modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .border(border = BorderStroke(2.dp,Color(48,136,126)),
                              RoundedCornerShape(15.dp)
                        ),
                  shape = RoundedCornerShape(15.dp)

            )

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot password text
            Text(
                  text = "Forget Password ?",
                  style= customTextStyle4,
                  modifier = Modifier
                        .align(Alignment.End)
                        .padding(horizontal = 16.dp),
                  color = Color(60,103,103),
                  fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))
            val context= LocalContext.current
            // Sign in Button
            Button(
                  onClick = {
                        authViewModel.login(userName,password)
                        when(result){
                              is Result.Success ->{
                                    onClickSignIn()
                              }
                              is Result.Error->{
                                    Toast.makeText(context,"Username/Password incorrect",Toast.LENGTH_SHORT).show()
                              }
                              else->{

                              }
                        }
                            },
                  colors = ButtonDefaults.buttonColors(containerColor = Color(58,166,153)),
                  modifier = Modifier
                        .width(200.dp)
                        .padding(horizontal = 16.dp)
                        .height(50.dp),
                  shape = RoundedCornerShape(15.dp)
            ) {
                  Text(text = "Sign In", style = customTextStyle5, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = " Or sign in with", style = customTextStyle4, color = Color(48,136,126))

            Spacer(modifier = Modifier.height(8.dp))


            // Social Media Sign in options
            Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.Center
            ) {
                  // Google Icon
                  IconButton(onClick = { /*TODO*/ }) {
                        Image(
                              painter = painterResource(id = R.drawable.search), // Replace with Google Icon
                              contentDescription = "Google Sign In",
                              modifier = Modifier.size(40.dp)
                        )
                  }
                  Spacer(modifier = Modifier.width(16.dp))
                  // Facebook Icon
                  IconButton(onClick = { /*TODO*/ }) {
                        Image(
                              painter = painterResource(id = R.drawable.facebook), // Replace with Facebook Icon
                              contentDescription = "Facebook Sign In",
                              modifier = Modifier.size(40.dp)
                        )
                  }
                  Spacer(modifier = Modifier.width(16.dp))
                  // X Icon (Twitter)
                  IconButton(onClick = { /*TODO*/ }) {
                        Image(
                              painter = painterResource(id = R.drawable.instagram), // Replace with X (Twitter) Icon
                              contentDescription = "Twitter Sign In",
                              modifier = Modifier.size(40.dp)
                        )
                  }

            }

            Spacer(modifier = Modifier.height(30.dp))

            // Sign Up text
            Row(
                  horizontalArrangement = Arrangement.Center,
                  modifier = Modifier.fillMaxWidth()
            ) {
                  Text(text = "Don’t have an account ?", color = Color.Gray)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(text = "Sign Up", color = Color(0xFF3DA98E), fontWeight = FontWeight.Bold,
                        modifier=Modifier.clickable { onClickSignUp() })
            }
      }
}