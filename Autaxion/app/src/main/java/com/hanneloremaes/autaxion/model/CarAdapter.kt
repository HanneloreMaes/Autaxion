package com.hanneloremaes.autaxion.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.hanneloremaes.autaxion.R
import com.hanneloremaes.autaxion.onClickFragments.ModelCarsFragment
import com.hanneloremaes.autaxion.ui.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.fragment_car_card.view.*

class CarAdapter(private val cars: List<Car>, private val listener: OnItemClickListener) : RecyclerView.Adapter<CarAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_car_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val car = cars[position]
        holder.brandCar.text = car.name.uppercase()
    }

    override fun getItemCount() = cars.size

}
