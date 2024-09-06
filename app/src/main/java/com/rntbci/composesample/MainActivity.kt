package com.rntbci.composesample

import android.content.res.Configuration
import android.graphics.Paint.Style
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rntbci.composesample.ui.theme.ComposeSampleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "TestCompose"


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {

            //AppThemeTest()

           /* Box(modifier = Modifier.fillMaxSize().background(color = Color.Yellow).offset(500.dp,500.dp)) {
                ConfigChangeScreen()
            }*/

            //CoroutineScopeComposable()

            //Loader()

            //ShowLotteAnimation()

            //DrawShapes()
            //AdvancedDraw()

            //ConfigChangeScreen()

            /*PreviewItem{
                Log.d(TAG, "onCreate: $it")
            }*/

        }
    }

}



@Preview(showBackground = true)
@Composable
fun ShowPreview() {

    //AppThemeTest()

    //Counter()
    //CoroutineScopeComposable()

    Loader()

    //ShowLotteAnimation()

    //DrawShapes()
    //AdvancedDraw()

    //ConfigChangeScreen()

}


@Composable
fun Counter() {
    val count = remember { mutableStateOf(0) }

    //launch Effect - when you want to execute only one time / on some condition.
    val key = count.value % 3 == 0
    LaunchedEffect(key1 = key) {
        Log.d(TAG, "Counter: count ${count.value}")
    }

    Button(onClick = { count.value++ }) {
        Text(color = Color.Red, text = "increment count ${count.value}")
    }

}

@Composable
fun CoroutineScopeComposable() {
    val count = remember { mutableStateOf(0) }

    // with this "scope" we can run our side effects in compose
    // lifecycle of this "scope" is associated with this compose
    val scope = rememberCoroutineScope()

    var text = "Counter is running ${count.value}"
    if (count.value == 10) {
        text = "counter stopped"
    }

    Column {
        Text(text = text)
        Button(onClick = {
            scope.launch {
                Log.d(TAG, "CoroutineScopeComposable: Started..")
                try {
                    for (i in 1..10) {
                        count.value++
                        delay(1000)
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "CoroutineScopeComposable Exception - ${e.message.toString()}")
                }
            }
        }) {
            Text(text = "Start")
        }
    }

    val key = count.value % 3 == 0
    LaunchedEffect(key1 = key) {
        Log.d(TAG, "Counter: count ${count.value}")
    }

    Button(onClick = { count.value++ }) {
        Text(text = "increment count")
    }
}


@Composable
fun AppThemeTest() {

    val theme = remember { mutableStateOf(false) }

    ComposeSampleTheme(theme.value) {

        Column {

            Text(text = "compose", style = MaterialTheme.typography.bodyLarge, color = Color.Red)

            Button(onClick = {
                theme.value = !theme.value
            }) {
                Text(text = "change theme")
            }
        }

    }
}


@Composable
fun Loader() {

    // "produceState" - it will produce state object where we can update its value asynchronously
    val state = produceState(initialValue = 0) {
        while (true) {
            delay(16)
            value = (value + 15) % 360
        }
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(1f),
        content = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.Red),
                    modifier = Modifier
                        .size(60.dp)
                        .rotate(state.value.toFloat())
                )
                Text(text = "Loading..", color = Color.Red)
            }
        })


}


@Composable
fun DrawShapes(){
    Canvas(modifier = Modifier.fillMaxSize()) {

        // Draw a circle
        drawCircle(
            color = Color.Red,
            radius = 100f,
            center = center
        )

        // Draw a rectangle
        drawRect(
            color = Color.Green,
            topLeft = center.copy(x = center.x - 150f, y = center.y - 150f),
            size = size.copy(width = 300f, height = 200f)
        )

        // Draw a line
        drawLine(
            color = Color.Blue,
            start = center,
            end = center.copy(x = center.x + 200f, y = center.y + 200f),
            strokeWidth = 5f
        )

        // Draw an arc
        drawArc(
            color = Color.Magenta,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = center.copy(x = center.x - 200f, y = center.y - 100f),
            size = size.copy(width = 400f, height = 200f)
        )
    }
}


@Composable
fun AdvancedDraw() {
    Canvas(modifier = Modifier.fillMaxSize()) {

        val path = Path().apply {
            moveTo(center.x, center.y)
            lineTo(center.x + 100f, center.y + 200f)
            lineTo(center.x - 100f, center.y + 200f)
            close()
        }

        drawPath(
            path = path,
            color = Color.Red,
            style = Stroke(width = 12f)
        )

        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                color = Color.Blue
                asFrameworkPaint().textSize = 50f
            }

            canvas.nativeCanvas.drawText("Hello Canvas", center.x - 120f, center.y - 100f, paint.asFrameworkPaint())
        }
    }
}

@Composable
fun ShowLotteAnimation() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Asset("animations/dog.json"))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}


@Composable
fun ConfigChangeScreen() {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeLayout()
        }
        Configuration.ORIENTATION_PORTRAIT -> {
            PortraitLayout()
        }
    }
}

@Composable
fun LandscapeLayout() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Landscape", color = Color.Red)
        // Your landscape-specific UI goes here
    }
}

@Composable
fun PortraitLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Portrait", color = Color.Red)
        // Your portrait-specific UI goes here
    }
}