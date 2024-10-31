package com.example.travelogue

import android.widget.Toast
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
import androidx.compose.material.icons.outlined.MailOutline
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(authViewModel: AuthViewModel, onClickSignInText:()->Unit)
{
      var fullName by remember{ mutableStateOf("") }
      var emailName by remember{ mutableStateOf("") }
      var password by remember{ mutableStateOf("") }
      var confirmPassword by remember{ mutableStateOf("") }
      var passwordVisible by remember{mutableStateOf(false)}
      var confirmPasswordVisible by remember{mutableStateOf(false)}


      Column(
            modifier = Modifier
                  .fillMaxSize()
                  .background(Color.White)
                  .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
      ) {
            Spacer(modifier = Modifier.height(20.dp))
            /* Top Image */
            Image(
                  painter = painterResource(R.drawable.around_the_world),  // Replace with your drawable
                  contentDescription = "Top Image",
                  modifier = Modifier
                        .size(200.dp)
                        .padding(12.dp),
                  contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Sign in Text
            Text(
                  text = "Sign Up",
                  style = customTextStyle3,
                  color = Color(60, 103, 103)
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                  value =fullName,
                  onValueChange = {fullName=it},
                  textStyle = customTextStyle4,
                  singleLine =true,
                  placeholder = { Text("Full Name",
                        color = Color(0,0,0,50),
                        style = customTextStyle4
                  )},
                  leadingIcon = {
                        Icon(
                              Icons.Outlined.Person , contentDescription = "Person Icon",
                              tint = Color(48,136,126)
                        )
                  },
                  modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .border(
                              border = BorderStroke(2.dp, Color(48, 136, 126)),
                              RoundedCornerShape(15.dp)
                        ),
                  shape = RoundedCornerShape(15.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                  value =emailName,
                  onValueChange = {emailName=it},
                  textStyle = customTextStyle4,
                  singleLine =true,
                  placeholder = { Text("Email or User Name",
                        color = Color(0,0,0,50),
                        style = customTextStyle4
                  )},
                  leadingIcon = {
                        Icon(Icons.Outlined.MailOutline , contentDescription = "Email Icon",
                              tint = Color(48,136,126)
                        )
                  },
                  modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .border(
                              border = BorderStroke(2.dp, Color(48, 136, 126)),
                              RoundedCornerShape(15.dp)
                        ),
                  shape = RoundedCornerShape(15.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))

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
                        .border(
                              border = BorderStroke(2.dp, Color(48, 136, 126)),
                              RoundedCornerShape(15.dp)
                        ),
                  shape = RoundedCornerShape(15.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                  value = confirmPassword,
                  onValueChange = {confirmPassword=it},
                  textStyle = customTextStyle4,
                  singleLine = true,
                  placeholder = { Text("Confirm Password",
                        color = Color(0,0,0,50),
                        style = customTextStyle4) },
                  visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                  leadingIcon = {
                        Icon(Icons.Outlined.Lock, contentDescription = "Password Icon",
                              tint = Color(48,136,126))
                  },
                  trailingIcon = {
                        val image = if (confirmPasswordVisible)
                              Icons.Outlined.Visibility
                        else Icons.Outlined.VisibilityOff
                        val description = if (confirmPasswordVisible) "Hide password" else "Show password"

                        // Toggle button to hide or display password
                        IconButton(onClick = {confirmPasswordVisible = !confirmPasswordVisible}){
                              Icon(imageVector  = image, description,tint = Color(48,136,126))
                        }
                  },
                  modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .border(
                              border = BorderStroke(2.dp, Color(48, 136, 126)),
                              RoundedCornerShape(15.dp)
                        ),
                  shape = RoundedCornerShape(15.dp)

            )
            Spacer(modifier = Modifier.height(24.dp))

            val context= LocalContext.current
            Button(
                  onClick = {
                              if(password==confirmPassword) {
                                    authViewModel.signUp(emailName, fullName, password, confirmPassword)
                                    onClickSignInText()
                              }
                              else{
                                    val text="Passwords do not match!"
                                    Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
                              }
                            },
                  colors = ButtonDefaults.buttonColors(containerColor = Color(58,166,153)),
                  modifier = Modifier
                        .width(200.dp)
                        .padding(horizontal = 16.dp)
                        .height(50.dp),
                  shape = RoundedCornerShape(15.dp)
            ) {
                  Text(text = "Sign Up", style = customTextStyle5, color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                  horizontalArrangement = Arrangement.Center,
                  modifier = Modifier.fillMaxWidth()
            ) {
                  Text(text = "Already have an account ?", color = Color.Gray)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(text = "Sign In", color = Color(0xFF3DA98E), fontWeight = FontWeight.Bold,
                        modifier=Modifier.clickable { onClickSignInText() })
            }

      }
}