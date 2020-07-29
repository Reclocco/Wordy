package com.example.wordy.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wordy.R
import kotlinx.android.synthetic.main.constraint.view.*

class PossibleWordAdapter (private val possibleWordList: List <PossibleWord>) : RecyclerView.Adapter<PossibleWordAdapter.PossibleWordViewHolder>() {

    class PossibleWordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.conItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PossibleWordViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.constraint, parent, false)

        return PossibleWordViewHolder(itemView)
    }

    override fun getItemCount() = possibleWordList.size

    override fun onBindViewHolder(holder: PossibleWordViewHolder, position: Int) {
        val currentItem = possibleWordList[position]

        holder.textView.text = currentItem.getPossibleWord()
    }
}