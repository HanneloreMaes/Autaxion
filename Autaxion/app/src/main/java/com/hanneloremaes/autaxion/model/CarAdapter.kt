package com.hanneloremaes.autaxion.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.hanneloremaes.autaxion.R
import com.hanneloremaes.autaxion.onClickFragments.ModelCarsFragment
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

//        holder.itemView.setOnClickListener(object: View.OnClickListener{
//            override fun onClick(v: View?) {
//                if(position == 1){
//                    val activity = v!!.context as AppCompatActivity
//                    val modelcarsFragment = ModelCarsFragment()
//                    activity.supportFragmentManager.beginTransaction().replace(R.id.recDashboard, modelcarsFragment).addToBackStack(null).commit()
//                }
//            }
//        })

    }

    override fun getItemCount() = cars.size

}
