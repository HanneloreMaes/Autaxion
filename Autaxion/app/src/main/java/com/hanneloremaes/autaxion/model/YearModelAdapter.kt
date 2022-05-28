package com.hanneloremaes.autaxion.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanneloremaes.autaxion.R
import kotlinx.android.synthetic.main.fragment_model_card.view.*
import kotlinx.android.synthetic.main.fragment_model_year_card.view.*

class YearModelAdapter(private val years: List<YearModel>, private val listener: OnItemClickListener) : RecyclerView.Adapter<YearModelAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val yearCar: TextView = itemView.yearCar

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_model_year_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val year = years[position]
        holder.yearCar.text = year.year.toString()
    }

    override fun getItemCount() = years.size

}