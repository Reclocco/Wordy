package com.example.wordy.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.wordy.R
import kotlinx.android.synthetic.main.constraint.view.*


class ConstraintAdapter(constraintListIn: ArrayList <Constraint>) : RecyclerView.Adapter<ConstraintAdapter.ConstraintViewHolder>() {
    var constraintList = constraintListIn

    class ConstraintViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.conItem
        val deleteButton: ImageButton = itemView.deleteButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConstraintViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.constraint, parent, false)

        return ConstraintViewHolder(itemView)
    }

    override fun getItemCount() = constraintList.size

    override fun onBindViewHolder(holder: ConstraintViewHolder, position: Int) {
        val currentItem = constraintList[position]

        holder.textView.text = currentItem.getConstraint()
        holder.deleteButton.setOnClickListener {
            constraintList.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}