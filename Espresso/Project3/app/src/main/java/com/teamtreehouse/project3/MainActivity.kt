package com.teamtreehouse.project3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.style
import java.util.*

class MainActivity : AppCompatActivity() {
    var txtView: TextView? = null
    var choiceA: RadioButton? = null
    var choiceB: RadioButton?  = null
    var choiceC: RadioButton?  = null
    var question: String? = null;
    var answer: Int = 0
    var incorrect1: Int = 0
    var incorrect2: Int = 0
    var answerIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newQuestion()

        verticalLayout {
            padding = dip(16)
            gravity = Gravity.CENTER

            txtView = textView() {
                text = question
                textSize = 22f
            }.lparams { bottomMargin = dip(8) }

            choiceA = radioButton() {
                if (answerIndex == 0) {
                    text = answer.toString()
                } else {
                    text = incorrect1.toString()
                }
                textSize = 22f
                onClick {
                    choiceB!!.isChecked = false
                    choiceC!!.isChecked = false
                }
            }.lparams { topMargin = dip(8) }
            choiceB = radioButton() {
                if (answerIndex == 1) {
                    text = answer.toString()
                } else {
                    if (answerIndex == 0) {
                        text = incorrect1.toString()
                    } else {
                        text = incorrect2.toString()
                    }
                }
                textSize = 22f
                onClick {
                    choiceA!!.isChecked = false
                    choiceC!!.isChecked = false
                }
            }.lparams { topMargin = dip(8) }
            choiceC = radioButton() {
                if (answerIndex == 2) {
                    text = answer.toString()
                } else {
                    text = incorrect2.toString()
                }
                textSize = 22f
                onClick {
                    choiceA!!.isChecked = false
                    choiceB!!.isChecked = false
                }
            }.lparams { topMargin = dip(8) }
            button() {
                text = "Submit"
                textSize = 22f
                onClick {
                    checkAnswer()
                }
            }.lparams { topMargin = dip(8) }
        }.applyRecursively { view ->
            when(view) {
                is RadioButton -> view.layoutParams.width = wrapContent
            }
        }
    }

    private fun newQuestion() {
        val add1 = Random().nextInt(50)
        val add2 = Random().nextInt(50)
        answer = add1 + add2
        var dir = if(Random().nextBoolean()) 1 else -1
        incorrect1 = (add1 + add2) + (dir * (add1 + add2) * Random().nextFloat()).toInt()
        dir = if(Random().nextBoolean()) 1 else -1
        incorrect2 = (add1 + add2) + (dir * (add1 + add2) * Random().nextFloat()).toInt()

        question = "What is $add1 + $add2?"
        answerIndex = Random().nextInt(3)
    }

    private fun checkAnswer() {
        if (choiceA!!.isChecked && answerIndex == 0) {
            toast("Correct!")
        } else if (choiceB!!.isChecked && answerIndex == 1) {
            toast("Correct!")
        } else if (choiceC!!.isChecked && answerIndex == 2) {
            toast("Correct!")
        } else {
            toast("Nope!")
        }

        newQuestion()
        updateUI()
    }

    private fun updateUI() {
        choiceA!!.isChecked = false
        choiceB!!.isChecked = false
        choiceC!!.isChecked = false

        txtView!!.text = question
        if (answerIndex == 0) {
            choiceA!!.text = answer.toString()
            choiceB!!.text = incorrect1.toString()
            choiceC!!.text = incorrect2.toString()
        } else if (answerIndex == 1){
            choiceA!!.text = incorrect1.toString()
            choiceB!!.text = answer.toString()
            choiceC!!.text = incorrect2.toString()
        } else if (answerIndex == 2){
            choiceA!!.text = incorrect1.toString()
            choiceB!!.text = incorrect2.toString()
            choiceC!!.text = answer.toString()
        }
    }
}
