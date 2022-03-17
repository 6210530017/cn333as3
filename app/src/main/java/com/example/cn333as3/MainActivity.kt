package com.example.cn333as3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Page1()
        }
    }
}
var randomNO: Int = Random.nextInt(1,1000)
var countAns = 0
var inGame: Boolean = true
@Preview
@Composable
fun Page1() {
    val header = remember { mutableStateOf("Try to guess the number I'm thinking of from 1 - 1000!")}
    val ans = remember { mutableStateOf(TextFieldValue()) }
    val buttonText = remember { mutableStateOf("") }

    fun reset() {
        randomNO = Random.nextInt(1, 1000)
        header.value = "Try to guess the number I'm thinking of from 1 - 1000!"
        inGame = true
        countAns = 0
    }

    fun process() {
        if (inGame) {
            if (ans.value.text.isEmpty()) {
                header.value = "Please Enter your guess number"
            } else {
                if (ans.value.text.toInt() < randomNO) {
                    header.value = "Hint: It's higher!"
                    countAns++

                } else if (ans.value.text.toInt() > randomNO) {
                    header.value = "Hint: It's lower!"
                    countAns++

                } else {
                    header.value = "Congratulation! you guess $countAns times before it's correct"
                    inGame = false
                }
            }
        } else {
            reset()
        }
    }

    Column(
        modifier = Modifier.padding(all = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {   Text( header.value,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    )
        if(inGame) {
            TextField(
                value = ans.value,
                onValueChange = {ans.value = it },
                singleLine = true,
                placeholder = { Text("Your guess")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                        .padding(20.dp)
            )
        }


        Button( onClick = { process() } ) {
            if(inGame) {
                buttonText.value = "Enter"
                Text(buttonText.value)
            } else{
                buttonText.value = "Play again"
                Text(buttonText.value)
            }

        }


    }
}
