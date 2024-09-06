package com.rntbci.composesample

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val TAG = "TestCompose"

//@Preview(showBackground = true)
@Composable
fun PreviewFunction() {
    //TextModifier()
    //CircularImage()
   //TextInput()
    /*Column {
        ListViewItem(R.drawable.emp1, "Emp first", "Engineer", Modifier)
        ListViewItem(R.drawable.emp2, "Emp second", "Senior Engineer", Modifier)
        ListViewItem(R.drawable.emp3, "Emp third", "Manager", Modifier)
    }*/
    ReComposable()
}

@Composable
fun TextModifier() {
    Text(
        text = "Hello Compose",
        color = Color.Red,
        modifier = Modifier
            .background(Color.Blue)
            .size(200.dp)
            .border(4.dp, Color.Red)
            .clip(CircleShape)
            .background(Color.Cyan)
            .clickable { }
    )
}

@Composable
fun CircularImage() {
    Image(
        painter = painterResource(id = R.drawable.dog_img),
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .clip(shape = CircleShape)
            .border(2.dp, Color.Red, CircleShape)
    )
}

@Composable
fun ListViewItem(imgId: Int, name: String, designation: String, modifier: Modifier) {
    Row(modifier.padding(2.dp,2.dp,4.dp,8.dp),horizontalArrangement = Arrangement.Start ) {

        Image(
            painter = painterResource(id = imgId),
            contentDescription = "",
            modifier = Modifier.size(40.dp)
        )
        Column{
            Text(text = name, fontWeight = FontWeight.Bold)
            Text(text = designation, fontWeight = FontWeight.Thin, fontSize = 12.sp)
        }

    }
}

@Composable
fun TextInput() {
    // remember previous state while recomposition
    val state = remember { mutableStateOf("") }
    Log.d(TAG, "TextInput state: ${state.value}")

    TextField(
        value = state.value,
        onValueChange = {
            Log.d(TAG, "TextInput: $it")
            state.value = it
        },
        label = { Text(text = "Enter Message") }
    )
}

@Composable
fun ReComposable() {
    // this will maintain in composition
    val state = remember { mutableStateOf(0.0) }
    // this is maintain in bundle - in case of screen rotation
    //val state2 = rememberSaveable { mutableStateOf(0.0) }
    Log.d(TAG, "initial composition")

    Button(onClick = {
        state.value = Math.random()
    }) {
        Log.d(TAG, "both composition and recomposition")
        Text(text = state.value.toString())
    }
}
