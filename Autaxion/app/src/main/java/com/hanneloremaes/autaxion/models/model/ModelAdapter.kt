package com.hanneloremaes.autaxion.models.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanneloremaes.autaxion.R
import kotlinx.android.synthetic.main.fragment_model_card.view.*

class ModelAdapter(private val models: List<Model>) : RecyclerView.Adapter<ModelAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val modelCar: TextView = itemView.modelCar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_model_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = models[position]
        holder.modelCar.text = model.name
    }

    override fun getItemCount() = models.size

}
