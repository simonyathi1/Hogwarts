package com.onesimo.nyathi.hogwarts.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onesimo.nyathi.hogwarts.R
import com.onesimo.nyathi.hogwarts.data.MovieCharacter
import com.onesimo.nyathi.hogwarts.util.setCircularPictureFromUrl

class CharactersAdapter constructor(
    private var characters: List<MovieCharacter>,
    private val onItemClicked: (spell: MovieCharacter) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.item_imaged_list_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = characters[position]
        holder.text.text = item.name
        setCircularPictureFromUrl(item.imageUrl, holder.image, holder.itemView.context)
        holder.itemView.setOnClickListener {
            onItemClicked(item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val text: TextView = itemView.findViewById(R.id.item_text)
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}
