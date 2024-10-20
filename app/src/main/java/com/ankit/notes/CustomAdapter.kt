package com.ankit.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<NotesModel>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        //TODO("Not yet implemented")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.listnotes,parent,false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val notesModel = mList[position]
        holder.textNotes.text = notesModel.notes
        holder.textDate.text = notesModel.dateTime

    }

    override fun getItemCount(): Int {
        return mList.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textNotes: TextView = itemView.findViewById(R.id.txtNotes)
        val textDate: TextView = itemView.findViewById(R.id.txtDate)
    }
}