package com.school.autaxion.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.school.autaxion.R
import com.squareup.picasso.Picasso


class CarAdapter(courseModalArrayList: ArrayList<CarModel>, context: Context) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {
    // creating a variable for array list and context.
    private val courseModalArrayList: ArrayList<CarModel>
    private val context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // below line is to inflate our layout.
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.carbrand_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // setting data to our views of recycler view.
        val modal: CarModel = courseModalArrayList[position]
        holder.courseNameTV.setText(modal.getCourseName())
        Picasso.get().load(modal.getCourseimg()).into(holder.courseIV)
    }

    override fun getItemCount(): Int {
        // returning the size of array list.
        return courseModalArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating variables for our views.
        val courseNameTV: TextView
        val courseIV: ImageView

        init {

            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.carBrandName)
            courseIV = itemView.findViewById(R.id.carBrandImage)
        }
    }

    // creating a constructor for our variables.
    init {
        this.courseModalArrayList = courseModalArrayList
        this.context = context
    }
}