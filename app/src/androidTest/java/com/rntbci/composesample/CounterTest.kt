package com.rntbci.composesample

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CounterTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun counter_with_zero(){
        composeTestRule.onNodeWithText("increment count 0").assertExists()
    }

    @Test
    fun counter_increment(){
        composeTestRule.onNodeWithText("increment count 0").performClick()
        composeTestRule.onNodeWithText("increment count 1").assertExists()
    }

}