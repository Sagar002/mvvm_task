package com.example.gitissuelist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitissuelist.RecycleViewItemClick
import com.example.gitissuelist.databinding.ItemCommentsBinding
import com.example.gitissuelist.databinding.ItemIssueBinding
import com.example.gitissuelist.model.comment.Comment

class CommentListAdapter(val list:List<Comment>)
    : RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

    lateinit var binding: ItemCommentsBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCommentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(
        private val binding: ItemCommentsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Comment) {
            binding.model = model
        }
    }
}