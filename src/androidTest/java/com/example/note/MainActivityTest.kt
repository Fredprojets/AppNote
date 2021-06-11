package com.example.note


import android.view.View
import android.view.ViewGroup
import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val appCompatTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.NoteList),
                        childAtPosition(
                                withId(R.id.coordinatorLayout),
                                2)))
                .atPosition(0)
        appCompatTextView.perform(click())

        val actionMenuItemView = onView(
                allOf(withId(R.id.action_suivant), withContentDescription("Suivant"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                1),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        val actionMenuItemView2 = onView(
                allOf(withId(R.id.action_suivant), withContentDescription("Suivant"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                1),
                        isDisplayed()))
        actionMenuItemView2.perform(click())

        val actionMenuItemView3 = onView(
                allOf(withId(R.id.action_suivant), withContentDescription("Suivant"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                1),
                        isDisplayed()))
        actionMenuItemView3.perform(click())

        val actionMenuItemView4 = onView(
                allOf(withId(R.id.action_precedent), withContentDescription("Précédent"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView4.perform(click())

        val actionMenuItemView5 = onView(
                allOf(withId(R.id.action_precedent), withContentDescription("Précédent"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView5.perform(click())

        val actionMenuItemView6 = onView(
                allOf(withId(R.id.action_precedent), withContentDescription("Précédent"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView6.perform(click())

        val appCompatEditText = onView(
                allOf(withId(R.id.Titre), withText("titre1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("titre1asd"))

        val appCompatEditText2 = onView(
                allOf(withId(R.id.Titre), withText("titre1asd"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()))
        appCompatEditText2.perform(closeSoftKeyboard())

        val appCompatEditText3 = onView(
                allOf(withId(R.id.Note), withText("À remplir 1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText3.perform(replaceText("À remplir 1fre"))

        val appCompatEditText4 = onView(
                allOf(withId(R.id.Note), withText("À remplir 1fre"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText4.perform(closeSoftKeyboard())

        val appCompatSpinner = onView(
                allOf(withId(R.id.spinnerCours),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        appCompatSpinner.perform(click())

        val appCompatCheckedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(`is`("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1)
        appCompatCheckedTextView.perform(click())

        val floatingActionButton = onView(
                allOf(withId(R.id.SaveNote),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()))
        floatingActionButton.perform(click())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
