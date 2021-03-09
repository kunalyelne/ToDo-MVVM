package com.kyodude.todo_mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private lateinit var notes : List<Note>
    private lateinit var listener: OnItemClickListner

    inner class NoteHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView
        var textViewDesc: TextView
        var textViewPriority: TextView
        init {
            textViewTitle = itemView.findViewById(R.id.tvTitle)
            textViewDesc = itemView.findViewById(R.id.tvDescription)
            textViewPriority = itemView.findViewById(R.id.tvPriority)
            itemView.setOnClickListener{
                val position = adapterPosition
                if(listener != null && position != RecyclerView.NO_POSITION)
                {
                    listener.onItemClick(notes[position])
                }
            }

        }

    }

    interface OnItemClickListner {
        fun onItemClick(note: Note)
    }
    fun setOnItemClickListener(onItemClickListner: OnItemClickListner){
        this.listener = onItemClickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote: Note = notes.get(position)
        holder.textViewTitle.setText(currentNote.getTitle())
        holder.textViewDesc.setText(currentNote.getDescription())
        holder.textViewPriority.setText(currentNote.getPriority().toString())
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(list: List<Note>){
        this.notes = list
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note {
        return notes[position]
    }
}