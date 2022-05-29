package com.hanneloremaes.autaxion.model.ModelCar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanneloremaes.autaxion.R
import kotlinx.android.synthetic.main.fragment_model_card.view.*

class ModelCarAdapter(private val models: List<ModelCar>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ModelCarAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val modelCar: TextView = itemView.modelCar
        val brandCar: TextView = itemView.brandCar

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_model_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = models[position]
        holder.modelCar.text = model.model.uppercase()
        holder.brandCar.text = model.model
    }

    override fun getItemCount() = models.size

}