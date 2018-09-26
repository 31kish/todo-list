package jp.co.keishikuwabara.todo.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import jp.co.keishikuwabara.todo.R
import jp.co.keishikuwabara.todo.model.TodoEntity

class TodoAdapter(private val items: List<TodoEntity>, private val listener: TodoListener) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false))

        holder.itemView.setOnLongClickListener {
            Log.d("adapter", "long click")
            listener.onLongClick(it, items[holder.adapterPosition])
            true
        }

        holder.itemView.setOnClickListener { listener.onClickRow(it, items[holder.adapterPosition]) }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.text = items[position].content

        holder.checkBox.setOnCheckedChangeListener(null)
        holder.checkBox.isChecked = items[position].isChecked
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            listener.onCheck(isChecked, holder.adapterPosition)
        }
    }

    interface TodoListener {
        fun onClickRow(view: View, rowData: TodoEntity)
        fun onCheck(isChecked: Boolean, position: Int)
        fun onLongClick(view: View, rowData: TodoEntity)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val content: TextView = view.findViewById(R.id.todo_content)
    val checkBox: CheckBox = view.findViewById(R.id.todo_checkbox)
}