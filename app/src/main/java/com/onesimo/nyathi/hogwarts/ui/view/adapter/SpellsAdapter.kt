package com.onesimo.nyathi.hogwarts.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.data.Spell

class SpellsAdapter constructor(
    private var spells: List<Spell>,
    private val onItemClicked: (spell: Spell) -> Unit
) : RecyclerView.Adapter<SpellsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.item_imaged_list_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = spells[position]
        holder.text.text = item.name
        holder.itemView.setOnClickListener {
            onItemClicked(item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.item_text)
    }

    override fun getItemCount(): Int {
        return spells.size
    }
}
