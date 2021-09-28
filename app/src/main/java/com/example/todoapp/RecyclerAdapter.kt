package com.example.todoapp

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class RecyclerAdapter(private val noteToDisplay: ArrayList<String>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.tv_noteBody.text = noteToDisplay[position]
    }

    override fun getItemCount() = noteToDisplay.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var tv_noteBody: TextView
        lateinit var bt_noteCheckBox: CheckBox

        init {
            tv_noteBody = itemView.findViewById(R.id.tv_noteBody)
            bt_noteCheckBox = itemView.findViewById(R.id.bt_noteCheckBox)

            bt_noteCheckBox.setOnClickListener { handleCheckBox(tv_noteBody, bt_noteCheckBox) }
        }
    }

    fun handleCheckBox (tv_noteBody: TextView, bt_noteCheckBox: CheckBox) {
        Log.d("RecycleAdapter", "I was clicked!")

        if (bt_noteCheckBox.isChecked) {
            tv_noteBody.setTextColor(Color.rgb(192,192,192))
        } else {
            tv_noteBody.setTextColor(Color.rgb(0,0,0))
        }



    }
}