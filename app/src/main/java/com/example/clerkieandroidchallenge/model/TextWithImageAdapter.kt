package com.example.clerkieandroidchallenge.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clerkieandroidchallenge.R
import de.hdodenhof.circleimageview.CircleImageView

class TextWithImageAdapter (val context: Context, val items: ArrayList<TextWithImageModel>) :
    RecyclerView.Adapter<TextWithImageAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)

        holder.title.text = item.titleText
        holder.title.textSize = item.titleFontSize.toFloat()

        holder.subtitle.text = item.subtitleText
        holder.subtitle.textSize = item.subtitleFontSize.toFloat()

    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title : TextView = view.findViewById(R.id.title_textView)
        val subtitle : TextView = view.findViewById(R.id.subtitle_textView)
        val image : CircleImageView = view.findViewById(R.id.circleImage)

    }
}