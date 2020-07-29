package com.example.wordy.models

import java.io.Serializable

class Constraint (private var letterIn: String, private var positionIn: Int) : Serializable{
    private val myConstraint = "Litera: $letterIn, pozycja: $positionIn"
    private val letter = letterIn
    private val position = positionIn

    fun getConstraint(): String {
        return myConstraint
    }

    fun getConstraintLetter(): String {
        return letter
    }

    fun getConstraintPosition() : Int {
        return position
    }
}