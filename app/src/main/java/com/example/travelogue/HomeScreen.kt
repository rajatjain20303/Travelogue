package com.example.travelogue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getDrawable
import androidx.navigation.NavController
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import kotlinx.coroutines.delay

val nunitoFontFamily = FontFamily(
      Font(R.font.black,FontWeight.Black),
      Font(R.font.bold,FontWeight.Bold),
      Font(R.font.extrabold,FontWeight.ExtraBold),
      Font(R.font.extralight,FontWeight.ExtraLight),
      Font(R.font.light,FontWeight.Light),
      Font(R.font.medium,FontWeight.Medium),
      Font(R.font.regular,FontWeight.Normal),
      Font(R.font.semibold,FontWeight.SemiBold),
      Font(R.font.blackitalic,FontWeight.Black,FontStyle.Italic),
      Font(R.font.bolditalic,FontWeight.Bold,FontStyle.Italic),
      Font(R.font.extrabolditalic,FontWeight.ExtraBold,FontStyle.Italic),
      Font(R.font.extralightitalic,FontWeight.ExtraLight,FontStyle.Italic),
      Font(R.font.italic,FontWeight.Normal,FontStyle.Italic),
      Font(R.font.mediumitalic,FontWeight.Medium,FontStyle.Italic),
      Font(R.font.semibolditalic,FontWeight.SemiBold,FontStyle.Italic),
)
val customTextStyle1= TextStyle(
      fontFamily =nunitoFontFamily,
      fontWeight = FontWeight.Bold,
      fontStyle = FontStyle.Normal,
      fontSize = 40.sp,
      letterSpacing = 0.sp
)
val customTextStyle2= TextStyle(
      fontFamily =nunitoFontFamily,
      fontWeight = FontWeight.SemiBold,
      fontStyle = FontStyle.Normal,
      fontSize = 26.sp,
      letterSpacing = 0.sp
)
@Composable
fun Homescreen(navController: NavController)
{
      val gifDuration = 1500L // Duration of the GIF in milliseconds (3 seconds for example)

      // Launch a coroutine to navigate to the next screen after the GIF duration
      LaunchedEffect(Unit) {
            delay(gifDuration)
            navController.navigate(Screen.LoginScreen.route) // Navigate to the next screen
      }
      Column(modifier  = Modifier
            .fillMaxSize()
            .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Travelogue",
                  style= customTextStyle1, textAlign = TextAlign.Center,
                  color = Color(60,103,103)
            )
//            Spacer(modifier = Modifier.height(20.dp))
//            Box(
//                  contentAlignment = Alignment.Center,
//                  modifier = Modifier.fillMaxSize()
//            ) {
//                  AsyncImage(
//                        R.drawable.around_the_world,  // Replace with your GIF file path
//                        contentDescription = "Splash Animation",
//                        modifier = Modifier.size(200.dp)R.drawable.around_the_world
//                  )
//            }

            Image(
                  modifier = Modifier.padding(15.dp)
                        .size(350.dp),   //crops the image to circle shape
                  painter = rememberDrawablePainter(
                        drawable = getDrawable(
                              LocalContext.current,
                              R.drawable.around_the_world__2_
                        )
                  ),
                  contentDescription = "Loading animation",
                  contentScale = ContentScale.FillWidth,
            )


//            Spacer(modifier = Modifier.height(20.dp))
//
//                  Button(
//                        onClick = { onNavigateToLogin() },
//                        shape = RoundedCornerShape(10.dp),
//                        colors = ButtonDefaults.buttonColors(
//                              containerColor = Color(58, 166, 153), // Teal background
//                              contentColor = Color.White,
//                              disabledContainerColor = Color.Gray,  // Disabled background color
//                              disabledContentColor = Color.DarkGray
//                        )
//                  ) {
//                        Text(
//                              text = "Get Started",
//                              style = customTextStyle2,
//                              color = Color.White,
//                              textAlign = TextAlign.Center,
//                              modifier=Modifier.padding(8.dp)
//                        )
//                  }

      }
}