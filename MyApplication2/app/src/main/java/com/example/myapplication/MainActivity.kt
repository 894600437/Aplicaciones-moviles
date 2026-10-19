package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                app()
            }
        }
    }
}

@Composable
fun app() {
    var counter by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF00ECE5))
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp)
                .clickable { counter++ } // Incrementa al tocar el icono o el número
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_favorite_24),
                contentDescription = "Like"
            )
            Text(
                text = counter.toString(),
                color = Color.White,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Text(
            text = "VERA",
            fontSize = 32.sp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text = "jaja",
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "ARY",
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            item { Text(text = "Java", color = Color.Black) }
            item { Text(text = "C++", color = Color.Black) }
            item { Text(text = "Kotlin", color = Color.Black) }
            item { Text(text = "PHP", color = Color.Black) }
            item { Text(text = "PHP", color = Color.Black) }
            item { Text(text = "PHP", color = Color.Black) }
            item { Text(text = "PHP", color = Color.Black) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        app()
    }
}