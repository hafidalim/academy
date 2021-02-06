package com.example.academy.ui.reader.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.academy.data.ModuleEntity
import com.example.academy.databinding.ItemsModuleListCustomBinding

class ModulelistAdapter internal constructor(private val listener : MyAdapterClickListener) : RecyclerView.Adapter<ModulelistAdapter.ModuleViewHolder>(){
    private val listModules = ArrayList<ModuleEntity>()

    inner class ModuleViewHolder(private val binding : ItemsModuleListCustomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(module : ModuleEntity){
            binding.tvTitle.text = module.title
        }
    }

    interface MyAdapterClickListener {
        fun onItemClicked(position: Int, moduleId : String)
    }

    internal fun setModules(modules : List<ModuleEntity>?){
        if (modules == null) return
        this.listModules.clear()
        this.listModules.addAll(modules)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ModuleViewHolder {
        val binding = ItemsModuleListCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return listModules.size
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = listModules[position]
        holder.bind(module)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(holder.adapterPosition, listModules[holder.adapterPosition].moduleId)
        }
    }
}