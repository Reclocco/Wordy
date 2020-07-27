package com.example.wordy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordy.database.WordReaderDbHelper
import com.example.wordy.models.Constraint
import com.example.wordy.models.ConstraintAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup.view.*
import java.util.ArrayList

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
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()

            //test database
            var myText = ""

            val dbHelper = WordReaderDbHelper(this)
            val cursor = dbHelper.getText()
            cursor!!.moveToFirst()

            while (cursor.moveToNext()) {
                myText = myText.plus(cursor.getString(cursor.getColumnIndex(WordReaderDbHelper.COLUMN_NAME)))
            }

            Toast.makeText(this, myText, Toast.LENGTH_LONG).show()
        }
    }
}