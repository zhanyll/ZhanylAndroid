package com.example.zhanylandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zhanylandroid.database.Episodes

class MyAdapter(private val click: (episode: Episodes) -> Unit): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var list: List<Episodes> = mutableListOf()

    fun setData(list: List<Episodes>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(itemView, click)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View,
                     private val click: (episode: Episodes) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Episodes) {
            val txt = itemView.findViewById<AppCompatTextView>(R.id.item_txt)
            txt.text = item.title

            itemView.setOnClickListener {
                click.invoke(item)
            }
        }
    }
}