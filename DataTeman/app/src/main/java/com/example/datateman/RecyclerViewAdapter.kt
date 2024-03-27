package com.example.datateman

import android.annotation.SuppressLint
import android.app.LauncherActivity.ListItem
import android.view.LayoutInflater
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import java.math.MathContext

class RecyclerViewAdapter(private val dataTeman: ArrayList<data_teman>, context: MyListData) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val context: Context
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val Nama: TextView
        val Alamat: TextView
        val NoHP: TextView
        val ListItem: LinearLayout

        init {
            Nama = itemView.findViewById(R.id.namax)
            Alamat = itemView.findViewById(R.id.alamatx)
            NoHP = itemView.findViewById(R.id.no_hpx)
            ListItem = itemView.findViewById(R.id.list_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val V: View = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.view_design, parent, false)
        return ViewHolder(V)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Nama: String? = dataTeman.get(position).nama
        val Alamat: String? = dataTeman.get(position).alamat
        val NoHP: String? = dataTeman.get(position).no_hp

        holder.Nama.text = "Nama: $Nama"
        holder.Alamat.text = "Alamat: $Alamat"
        holder.NoHP.text = "No Hp : $NoHP"
        holder.ListItem.setOnLongClickListener(
            object : View.OnLongClickListener {
                override fun onLongClick(v: View?): Boolean {
                    return true
                }
            }
        )
    }

    override fun getItemCount(): Int {
        return dataTeman.size
    }

    init {
        this.context = context
    }
}