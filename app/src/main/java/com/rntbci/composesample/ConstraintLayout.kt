package com.rntbci.composesample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Preview(showBackground = true)
@Composable
fun ConstraintLayoutTest() {

    val constraints = ConstraintSet{

        val box1 = createRefFor("box1")
        val box2 = createRefFor("box2")

        val guideline = createGuidelineFromTop(0.5f)

        constrain(box1){
            top.linkTo(guideline)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(box2){
            top.linkTo(parent.top)
            start.linkTo(box1.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        createHorizontalChain(box1,box2, chainStyle = ChainStyle.Packed)
    }

    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()){
        Box(modifier = Modifier.background(Color.Blue).layoutId("box1"))
        Box(modifier = Modifier.background(Color.Red).layoutId("box2"))
    }


}