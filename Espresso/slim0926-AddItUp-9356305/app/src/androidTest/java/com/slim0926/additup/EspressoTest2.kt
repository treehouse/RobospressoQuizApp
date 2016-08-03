package com.slim0926.additup

import android.support.test.espresso.AmbiguousViewMatcherException
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoTest2 {
    var questionText = ""
    val buttons: MutableMap<Button, String> = mutableMapOf()
    val correctAnswerButtons: MutableList<Button> = mutableListOf()
    var submitButton: Button? = null

    @Rule @JvmField // http://stackoverflow.com/questions/29945087/kotlin-and-new-activitytestrule-the-rule-must-be-public
    val activityTestRule = ActivityTestRule<AddItUpActivity>(AddItUpActivity::class.java)

    @Test
    fun test() {
        val root: ViewGroup = activityTestRule.activity.findViewById(android.R.id.content) as ViewGroup
        for (i in 1..10) {
            questionText = ""
            buttons.clear()
            correctAnswerButtons.clear()
            submitButton = null

            analyzeViews(root)
            val numbers = Regex("\\d+").findAll(questionText)
            val addends = Pair(numbers.first().value.toInt(), numbers.last().value.toInt())
            val sum = addends.first + addends.second

            println("_____________________________")
            println(questionText)
            println(buttons.size)
            buttons.forEach {
                println(it.value)
                if (it.value.toInt() == sum) {
                    correctAnswerButtons.add(it.key)
                }
            }

            if (correctAnswerButtons.size == 0) {
                throw NoCorrectAnswersException(questionText, buttons.values.toMutableList())
            }

            if (correctAnswerButtons.size != 1) {
                throw DuplicateCorrectAnswersException(questionText, buttons.values.toMutableList())
            }

            val sumMatcher = SumMatcher(sum)
            onView(sumMatcher).perform(click())
            onView(withText("SubmIT")).perform(ViewActions.click())
        }
    }

    fun analyzeViews(viewGroup: ViewGroup) {
        for (i in 0..viewGroup.childCount - 1) {
            val view = viewGroup.getChildAt(i)
            if (view is ViewGroup) analyzeViews(view)
            if (view is Button) {
                if (Regex("\\d+").containsMatchIn(view.text))
                    buttons.put(view, view.text.toString())
                if (view.text.contains("Submit"))
                    submitButton = view
            }
            if (view is TextView && view.text.contains("+")) {
                questionText = view.text.toString()
            }
        }
    }
}
