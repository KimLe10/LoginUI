package com.example.loginui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginui.ui.theme.LoginUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                            .padding(innerPadding)
                    ) {
                        myLogoView(Modifier.align(Alignment.TopCenter))
                        
                        // Centered Column to hold Greeting and the features list
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 24.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Greeting()

                            FeatureItem("Car health updates")
                            FeatureItem("Request a rescue online")
                            FeatureItem("Policy information")
                        }

                        CreateAccBtn(Modifier.align(Alignment.BottomCenter))
                    }
                }
            }
        }
    }
}

@Composable
fun myLogoView(modifier: Modifier = Modifier) {
    val logo = painterResource(id = R.drawable.logo_green_flag)

    Image(
        painter = logo,
        contentDescription = "Logo",
        modifier = modifier
            .size(300.dp)
            .padding(top = 50.dp)
    )
}

@Composable
fun FeatureItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.tick),
            contentDescription = "Tick",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    LoginUITheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            myLogoView(Modifier.align(Alignment.TopCenter))
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 50.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Greeting()
                FeatureItem("Car health updates")
                FeatureItem("Request a rescue online")
                FeatureItem("Policy information")
            }
            CreateAccBtn(Modifier
                .padding(horizontal = 30.dp)
                .align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = "GreenFlag customers can create an account to access",
        fontSize = 20.sp,
        color = Color.White,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(bottom = 16.dp)

    )
}

@Composable
fun CreateAccBtn(modifier: Modifier = Modifier) {
    Button(
        onClick = { /* Handle click */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(), // Allows the image to fill the button space
        modifier = modifier
            .padding(bottom = 50.dp)
            .paint(
                painter = painterResource(id = R.drawable.gradient_button_background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Text(
            text = "Create an account",
            color = Color.Black,
            fontSize = 23.sp,
            modifier = Modifier.padding(horizontal = 40.dp, vertical = 12.dp)
        )
    }
}

@Preview
@Composable
fun CreateAccountPreview() {

    RegisterColumn()

}

@Composable
fun RegisterBanner(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /* Handle click */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(), // Allows the image to fill the button space
            modifier = modifier
                .padding(bottom = 50.dp)
                .paint(
                    painter = painterResource(id = R.drawable.gradient_button_background),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            Text(
                text = "Return",
                color = Color.Black,
                fontSize = 23.sp,
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 12.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Create an account",
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun RegisterColumn(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Email address",
            textAlign = TextAlign.Left,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 40.dp)

        )
        TextField(
            label = {Text("johndoe@gmail.com")},
            value = "",
            onValueChange = {}
        )

        Text(
            text = "Create password",
            color = Color.White,
            fontSize = 20.sp ,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 40.dp)

        )
        TextField(
            label = {Text("See criteria below")},
            value = "",
            onValueChange = {}
        )

        Text(
            text = "Repeat password",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 40.dp)
        )
        TextField(
            label = {Text("")},
            value = "",
            onValueChange = {}
        )
    }
}
