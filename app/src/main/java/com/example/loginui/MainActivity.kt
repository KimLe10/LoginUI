package com.example.loginui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginui.ui.theme.LoginUITheme

enum class Screen {
    Welcome, Register
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginUITheme {
                var currentScreen by rememberSaveable { mutableStateOf(Screen.Welcome) }

                when (currentScreen) {
                    Screen.Welcome -> WelcomeScreen(
                        onRegisterClick = { currentScreen = Screen.Register }
                    )
                    Screen.Register -> RegisterScreen(
                        onBackClick = { currentScreen = Screen.Welcome }
                    )
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(onRegisterClick: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
        ) {
            LogoSection(Modifier.align(Alignment.TopCenter))

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

            GradientButton(
                text = "Create an account",
                onClick = onRegisterClick,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 30.dp, end = 30.dp, bottom = 50.dp)
            )
        }
    }
}

@Composable
fun RegisterScreen(onBackClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    fun isPasswordValid(pass: String): Boolean {
        return pass.length >= 8 &&
                pass.any { it.isUpperCase() } &&
                pass.any { it.isLowerCase() } &&
                pass.any { it.isDigit() }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppBanner(
                title = "Create an account",
                onBackClick = onBackClick,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                RegisterField(
                    label = "Email address",
                    value = email,
                    placeholder = "johndoe@gmail.com",
                    onValueChange = { 
                        email = it 
                        errorMessage = null
                    }
                )
                RegisterField(
                    label = "Create password",
                    value = password,
                    placeholder = "See criteria below",
                    onValueChange = { 
                        password = it 
                        errorMessage = null
                    }
                )
                RegisterField(
                    label = "Repeat password",
                    value = repeatPassword,
                    placeholder = "",
                    onValueChange = { 
                        repeatPassword = it 
                        errorMessage = null
                    }
                )
            }

            if (errorMessage != null) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 4.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cross),
                        contentDescription = "Error icon",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = errorMessage!!,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }
            }

            Text(
                text = "Your password should have a minimum of 8 characters and contain at least one number, one uppercase and one lower case letter. You can use special characters.",
                color = Color.White,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 20.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.weight(1f))

            GradientButton(
                text = "Next",
                onClick = {
                    when {
                        email.isBlank() -> {
                            errorMessage = "Email is required"
                        }
                        !isPasswordValid(password) -> {
                            errorMessage = "Password does not meet requirements"
                        }
                        password != repeatPassword -> {
                            errorMessage = "Passwords do not match"
                        }
                        else -> {
                            errorMessage = null
                            // Handle registration success
                        }
                    }
                },
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, bottom = 50.dp)
            )
        }
    }
}

@Composable
fun RegisterField(label: String, value: String, placeholder: String, onValueChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(bottom = 20.dp).padding(horizontal = 24.dp)) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
    }
}

@Composable
fun AppBanner(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    backButtonText: String = "Return"
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onBackClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(backButtonText, color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LogoSection(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_green_flag),
        contentDescription = "Logo",
        modifier = modifier
            .size(300.dp)
            //.padding(top = 10.dp)
    )
}

@Composable
fun Greeting() {
    Text(
        text = "GreenFlag customers can create an account to access",
        fontSize = 20.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp)
    )
}

@Composable
fun FeatureItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.tick),
            contentDescription = "Tick icon",
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, color = Color.White, fontSize = 16.sp)
    }
}

@Composable
fun GradientButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .paint(
                painter = painterResource(id = R.drawable.gradient_button_background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Text(text = text, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    LoginUITheme {
        WelcomeScreen {}
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    LoginUITheme {
        RegisterScreen {}
    }
}
