package com.picpay.desafio.android.feature.home.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest
import com.picpay.desafio.android.feature.home.R
import org.junit.Rule
import org.junit.Test

class HomeActivityTest : ScreenshotTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<HomeActivity> = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun shouldDisplayTitle() {
        onView(withId(R.id.title)).check(matches(withText("Cavaleiros")))

        compareScreenshot(activityRule.scenario.waitForActivity())
    }
}