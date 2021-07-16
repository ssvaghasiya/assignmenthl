package com.assignmenthyperlink.ui.task3.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignmenthyperlink.R
import com.assignmenthyperlink.apputils.Utils
import com.assignmenthyperlink.databinding.ItemMemeBinding
import com.assignmenthyperlink.databinding.ItemTask3Binding
import com.assignmenthyperlink.ui.task3.datamodel.Task3Data

class RightDataAdapter() : RecyclerView.Adapter<RightDataAdapter.MyViewHolder>() {

    private lateinit var mEventListener: EventListener

    private var data = mutableListOf<Task3Data>()
    lateinit var context: Context

    constructor(context: Context) : this() {
        this.context = context
    }

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

    interface EventListener {
        fun onItemClick(pos: Int, item: Task3Data)
        fun onItemSelectedClick(pos: Int, item: Task3Data)
        fun onItemNotSelectedClick(pos: Int, item: Task3Data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemBinding = DataBindingUtil.inflate<ItemTask3Binding>(
            inflater,
            R.layout.item_task_3, parent, false
        )
        return MyViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(p: Int): Task3Data {
        return data[p]

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        try {
            holder.itemBinding.tvName.text = item.name

            if (item.isSelected) {
                holder.itemBinding.imgSelected.visibility = View.VISIBLE
            } else {
                holder.itemBinding.imgSelected.visibility = View.INVISIBLE
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        holder.itemBinding.root.setOnClickListener {
            item.isSelected = !item.isSelected
            if (item.isSelected) {
                holder.itemBinding.imgSelected.visibility = View.VISIBLE
                mEventListener.onItemSelectedClick(position, item)
            } else {
                holder.itemBinding.imgSelected.visibility = View.INVISIBLE
                mEventListener.onItemNotSelectedClick(position, item)
            }
        }
    }

    fun addAll(mData: List<Task3Data>?) {
//        data.clear()
        data.addAll(mData!!)
        notifyDataSetChanged()
    }

    fun add(mData: Task3Data) {
        data.add(mData)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun getDataRight(): MutableList<Task3Data> {
        return data
    }

    fun removeAt(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, data.size)
    }

    inner class MyViewHolder(internal var itemBinding: ItemTask3Binding) :
        RecyclerView.ViewHolder(itemBinding.root)
}