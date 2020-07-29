package com.example.wordy.models

class PossibleWord (private val possibleWordIn: String) {
    private val possibleWord = possibleWordIn

    fun getPossibleWord(): String {
        return possibleWord
    }
}