package com.example.wordy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordy.database.WordReaderDbHelper
import com.example.wordy.logic.MainLogic
import com.example.wordy.models.Constraint
import com.example.wordy.models.PossibleWord
import com.example.wordy.models.PossibleWordAdapter
import kotlinx.android.synthetic.main.results.*

class FindActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results)

        val intent = intent
        val letters = intent.getStringArrayListExtra("LETTERS")
        val length = intent.getIntExtra("LENGTH", 0)
        val constraints = intent.getSerializableExtra("EXTRA_CONSTRAINTS")

        val logic = MainLogic()
        val dbHelper = WordReaderDbHelper(this)

        val start = System.currentTimeMillis()
        val possibleWords = logic.combs(letters, length).distinct()
        println("WORD GENERATOR EXECUTION: " + (System.currentTimeMillis() - start) + " milliseconds")

        val actualWordsWords: ArrayList<String> = ArrayList()
        val wordsObjects: ArrayList<PossibleWord> = ArrayList()

        possibleWords.forEach() {
            val start2 = System.currentTimeMillis()
            if (dbHelper.ifWordExist(it) == 1) {
                actualWordsWords.add(it)
                wordsObjects.add(PossibleWord(it))
            }
            println("WORD CHECK EXECUTION: " + (System.currentTimeMillis() - start2) + " milliseconds")
        }

        finalWords.adapter = PossibleWordAdapter(wordsObjects)
        finalWords.layoutManager = LinearLayoutManager(this)
        finalWords.setHasFixedSize(false)
    }
}