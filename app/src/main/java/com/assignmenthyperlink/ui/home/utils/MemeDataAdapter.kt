package com.assignmenthyperlink.ui.home.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignmenthyperlink.R
import com.assignmenthyperlink.apputils.Utils
import com.assignmenthyperlink.databinding.ItemMemeBinding
import com.assignmenthyperlink.databinding.ItemSideMenuBinding
import com.assignmenthyperlink.ui.home.datamodel.MemeData

class MemeDataAdapter() : RecyclerView.Adapter<MemeDataAdapter.MyViewHolder>() {

    private lateinit var mEventListener: EventListener

    private var data = mutableListOf<MemeData.Meme>()
    lateinit var context: Context

    constructor(context: Context) : this() {
        this.context = context
    }

    fun setEventListener(eventListener: EventListener) {
        mEventListener = eventListener
    }

    interface EventListener {
        fun onItemClick(pos: Int, item: MemeData.Meme)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemBinding = DataBindingUtil.inflate<ItemMemeBinding>(
            inflater,
            R.layout.item_meme, parent, false
        )
        return MyViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    fun getItem(p: Int): MemeData.Meme {
        return data[p]

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        try {
            Utils.loadImage(
                holder.itemBinding.ivImage,
                item.url!!,
                context,
                R.drawable.placeholder
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        holder.itemBinding.root.setOnClickListener {

        }
    }

    fun addAll(mData: List<MemeData.Meme>?) {
        data.clear()
        data.addAll(mData!!)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(internal var itemBinding: ItemMemeBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}