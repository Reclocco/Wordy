package com.example.wordy.models

class Constraint (var letter: String, var position: Int){
    val myConstraint = "Litera: $letter, pozycja: $position"

    fun getConstraint(): String {
        return myConstraint
    }
}