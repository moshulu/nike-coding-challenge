package org.mattdrew.nikecodingchallenge


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.PerformException
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.withClassName
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class GoldenRuleTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(AlbumActivity::class.java)

    @Test
    fun goldenRuleTest() {
        var position = 0
        while(true){
            try{
                val recyclerView = onView(allOf(withId(R.id.albumRecyclerView), childAtPosition(withClassName(`is`("android.support.constraint.ConstraintLayout")),0)))
                recyclerView.perform(actionOnItemAtPosition<ViewHolder>(position, click()))

                pressBack()
                position++
                println("next")
            } catch(e: PerformException){
                println("The GoldenRuleTest has run out of albums in the RecyclerView. Exiting...")
                break
            }

        }




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
