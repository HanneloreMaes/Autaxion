package com.example.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.school.autaxion.R

class RecyclerViewAdaptor (var carlist: List<Car>): RecyclerView.Adapter<RecyclerViewAdaptor.ViewHolder>(){

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        val txtCarBrand: TextView

        init {
            txtCarBrand = view.findViewById(R.id.brandCar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_layout_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val woorden = carlist[position].car

        if(woorden.count() > 10){
            holder.txtCarBrand.textSize = 20f
        }

        holder.txtCarBrand.text = woorden
    }

    override fun getItemCount(): Int {
        return carlist.size
    }
}