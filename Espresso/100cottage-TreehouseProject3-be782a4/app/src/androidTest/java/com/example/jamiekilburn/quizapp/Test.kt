package com.example.jamiekilburn.quizapp


import android.support.test.espresso.AmbiguousViewMatcherException
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField // http://stackoverflow.com/questions/29945087/kotlin-and-new-activitytestrule-the-rule-must-be-public
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun test() {
        // arrange

        for (i in 1..100) {
            //Thread.sleep(1000)
            val questionText = getText(viewPlusMatcher)
            val numbers = Regex("\\d+").findAll(questionText)
            if (numbers.count() != 2)
                throw WrongNumberOfAddendsException()

            val addends = Pair(numbers.first().value.toInt(), numbers.last().value.toInt())
            val firstAddend = addends.first
            val secondAddend = addends.second

            val answerChoices: MutableList<String> = mutableListOf()

            val root: ViewGroup = activityTestRule.activity.findViewById(android.R.id.content) as ViewGroup
            val contentRoot: ViewGroup = root.getChildAt(0) as ViewGroup
            for (j in 0..contentRoot.childCount-1) {
                val view = contentRoot.getChildAt(j)
                if (view is ViewGroup) {
                    val viewsInGroup = view.childCount
                    for (k in 0..viewsInGroup - 1) {
                        val view2 = view.getChildAt(k)
                        if (view2 is Button && Regex("\\d+").containsMatchIn(view2.text)) {
                            answerChoices.add(view2.text.toString())
                        }
                    }
                } else if (view is Button && Regex("\\d+").containsMatchIn(view.text)) {
                    answerChoices.add(view.text.toString())
                }
            }

            val sumMatcher = SumMatcher(firstAddend + secondAddend)
            try {
                onView(sumMatcher).perform(click())
            } catch (e: AmbiguousViewMatcherException) {
                throw DuplicateCorrectAnswers(questionText, answerChoices)
            }

            //Thread.sleep(3000)
            try {
                onView(withText("submit")).perform(click())
            } catch (e: Exception) {}
        }




        // act

        // assert

    }

}

fun getText(matcher: BoundedMatcher<View, TextView>): String {
    var string = ""
    onView(matcher).perform(
            object  : ViewAction {
                override fun getConstraints(): Matcher<View> = isAssignableFrom(TextView::class.java)

                override fun getDescription(): String = "getting text from a TextView."

                override fun perform(uiController: UiController, view: View) {
                    val textView = view as TextView
                    string = textView.text.toString()
                }

            }
    )
    return string
}

val viewPlusMatcher = object : BoundedMatcher<View, TextView>(TextView::class.java) {
    override fun matchesSafely(item: TextView): Boolean = item.text.contains("+")

    override fun describeTo(description: Description) = Unit
}

class SumMatcher(val sum: Int) : BoundedMatcher<View, Button>(Button::class.java) {
    override fun matchesSafely(item: Button): Boolean = item.text.contains("$sum")
    override fun describeTo(description: Description?) = Unit
}

fun d(it: Any) = println("` $it")

