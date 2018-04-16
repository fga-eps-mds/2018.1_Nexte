package com.nexte.nexte

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.DecimalFormat

/**
 * Created by geovannioliveiradejesus on 08/04/2018.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        Assert.assertEquals("com.nexte.nexte", appContext.packageName)
    }
//    @Test
//    fun testClickProfileButton() {
//        onView(withId(R.id.showProfileButton)).perform(click())
//        onView(withId(R.id.username)).check(matches(withText("gabrielalbino")))
//    }
}
