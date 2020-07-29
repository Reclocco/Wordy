package com.example.wordy.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wordy.R
import kotlinx.android.synthetic.main.constraint.view.*

class ConstraintAdapter(private val constraintList: List <Constraint>) : RecyclerView.Adapter<ConstraintAdapter.ConstraintViewHolder>() {

    class ConstraintViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.conItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstraintViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.constraint, parent, false)

        return ConstraintViewHolder(itemView)
    }

    override fun getItemCount() = constraintList.size

    override fun onBindViewHolder(holder: ConstraintViewHolder, position: Int) {
        val currentItem = constraintList[position]

        holder.textView.text = currentItem.getConstraint()
    }
}