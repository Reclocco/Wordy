package com.example.wordy.database

import android.provider.BaseColumns

class WordReaderContract {
    object WordEntry : BaseColumns {
        const val TABLE_NAME = "words"
        const val COLUMN_NAME_TITLE = "word"
    }
}