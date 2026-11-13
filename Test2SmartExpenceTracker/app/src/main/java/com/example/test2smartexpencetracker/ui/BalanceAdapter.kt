package com.example.test2smartexpencetracker.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test2smartexpencetracker.R
import com.example.test2smartexpencetracker.data.BalanceRecord
import java.text.NumberFormat
import java.util.Locale

class BalanceAdapter(
    private var records: List<BalanceRecord>,
    private val onDeleteClick: (BalanceRecord) -> Unit
) : RecyclerView.Adapter<BalanceAdapter.BalanceViewHolder>() {

    inner class BalanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBalance: TextView = itemView.findViewById(R.id.tvRecordBalance)
        val tvTimestamp: TextView = itemView.findViewById(R.id.tvRecordTimestamp)
        val tvNote: TextView = itemView.findViewById(R.id.tvRecordNote)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_balance_record, parent, false)
        return BalanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: BalanceViewHolder, position: Int) {
        val record = records[position]

        val formatter = NumberFormat.getCurrencyInstance(Locale.US)
        holder.tvBalance.text = formatter.format(record.balance)

        holder.tvTimestamp.text = record.timestamp

        if (record.note.isNotBlank()) {
            holder.tvNote.text = "Note: ${record.note}"
            holder.tvNote.visibility = View.VISIBLE
        } else {
            holder.tvNote.visibility = View.GONE
        }

        holder.btnDelete.setOnClickListener {
            onDeleteClick(record)
        }
    }

    override fun getItemCount(): Int = records.size

    fun updateRecords(newRecords: List<BalanceRecord>) {
        records = newRecords
        notifyDataSetChanged()
    }
}
