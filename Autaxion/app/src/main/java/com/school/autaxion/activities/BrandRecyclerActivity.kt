package com.school.autaxion.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.*
import com.school.autaxion.R

class BrandRecyclerActivity: AppCompatActivity() {
    private val carViewModel: MainModelView by viewModels {
        CarBrandsViewModelFactory((application as CarsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dashboard)

        //Database connection
        var carlist = listOf<Car>()

        val recyclerView: RecyclerView = findViewById(R.id.carRecycler)
        var carsAdapter = RecyclerViewAdaptor(carlist)

        carViewModel.allCarBrands.observe(this) { carBrand ->
            carsAdapter.carlist = carBrand
            carsAdapter.notifyDataSetChanged()
        }
        recyclerView.adapter = carsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}