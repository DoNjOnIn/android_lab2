package com.lab2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lab2.ui.theme.Lab2Theme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                MainActivityScreen()
            }
        }
    }
}

@Composable
fun MainActivityScreen() {
    val itemList = remember { mutableStateListOf<Item>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0D47A1)),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(2.0f)
                .background(Color(0xFFBBDEFB)),
        ) {
            val textFieldName = remember { mutableStateOf("") }

            Button(
                onClick = {
                    val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"))
                    itemList.add(Item(name = textFieldName.value, timestamp = currentDateTime))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF5722))
            ) {
                Text(text = "ADD", color = Color(0xFFFFFFFF))
            }

            TextField(
                value = textFieldName.value,
                onValueChange = { newName -> textFieldName.value = newName },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color(0xFFFFFFFF), RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
            )

        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(7.0f)
                .background(Color(0xFFE3F2FD)),
        ) {
            items(itemList) { item ->
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF64B5F6))
                        .padding(top = 12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Text(
                            text = item.timestamp,
                            fontSize = 12.sp,
                            color = Color(0xFF0D47A1),
                        )
                        // Name below the timestamp
                        Text(
                            text = item.name,
                            color = Color(0xFF0D47A1),
                            fontSize = 20.sp,
                        )
                    }

                    Button(
                        onClick = {
                            itemList.remove(item)
                        },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFFD32F2F)),
                    ) {
                        Text(text = "X", color = Color(0xFFFFFFFF))
                    }
                }
            }
        }
    }
}

data class Item(val name: String, val timestamp: String)

@Preview
@Composable
fun MainActivityPreview() {
    Lab2Theme {
        MainActivityScreen()
    }
}
