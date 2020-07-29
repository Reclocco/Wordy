package com.example.wordy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordy.database.WordReaderDbHelper
import com.example.wordy.models.Constraint
import com.example.wordy.models.ConstraintAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup.view.*
import java.io.Serializable
import kotlin.collections.ArrayList
import com.example.wordy.FindActivity as FindActivity1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //recyclerView adapter
        val constraints = ArrayList<Constraint>()

        constraintsList.adapter = ConstraintAdapter(constraints)
        constraintsList.layoutManager = LinearLayoutManager(this)
        constraintsList.setHasFixedSize(false)

        //add button window on click
        addButton.setOnClickListener{
            val mAddView = LayoutInflater.from(this).inflate(R.layout.popup, null)

            val mBuilder = AlertDialog.Builder(this)
                .setView(mAddView)
                .setTitle("Dodaj Warunek")

            //show dialog
            val mAlertDialog = mBuilder.show()

            mAddView.constraintAddButton.setOnClickListener {
                mAlertDialog.dismiss()

                val pos = mAddView.letPos.text.toString().toInt()
                val let = mAddView.letter.text.toString()

                println("values: $pos, $let")
                val c = Constraint(let, pos)

                constraints.add(c)
                (constraintsList.adapter as ConstraintAdapter).notifyItemInserted(constraints.size - 1)
            }
        }

        findButton.setOnClickListener{
            val dbHelper = WordReaderDbHelper(this)

            val myLetters: ArrayList<String> = ArrayList()
            editText.text.toString().toCharArray().forEach {
                myLetters.add(it.toString())
            }

            val myLength = editText2.text.toString().toInt()

            println("MA len: $myLength")
            myLetters.forEach(){
                println(it)
            }

            val intent = Intent(this@MainActivity, FindActivity1::class.java)
            intent.putExtra("EXTRA_CONSTRAINTS", constraints as Serializable)
            intent.putExtra("LENGTH", myLength)
            intent.putExtra("LETTERS", myLetters)

            startActivity(intent)
        }
    }
}