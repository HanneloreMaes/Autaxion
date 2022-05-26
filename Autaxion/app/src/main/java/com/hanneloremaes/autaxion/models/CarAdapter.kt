package com.hanneloremaes.autaxion.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hanneloremaes.autaxion.R
import kotlinx.android.synthetic.main.fragment_car_card.view.*

class CarAdapter(private val cars: List<Car>) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val brandCar: TextView = itemView.brandCar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_car_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = cars[position]
        holder.brandCar.text = car.make
    }

    override fun getItemCount() = cars.size

}