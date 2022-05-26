package com.hanneloremaes.autaxion.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.hanneloremaes.autaxion.databinding.FragmentDashboardBinding
import com.hanneloremaes.autaxion.model.Car
import com.hanneloremaes.autaxion.model.CarAdapter

class DashboardFragment : Fragment() {

    var carsBrandsList: MutableList<Car> = mutableListOf()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        

//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://vpic.nhtsa.dot.gov/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val api = retrofit.create(CarApi::class.java)
//
//        api.fetchAllCars().enqueue(object : Callback<List<Car>> {
//            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
//                showData(response.body()!!)
//                Log.d("Hannelore", "onResponse: ${response.body()!!}")
//            }
//
//            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
//                Log.d("Hannelore", "onFailure")
//            }
//        })

//        val recyclerView: RecyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this.context)
//        recyclerView.adapter = CarAdapter(cars)

//        val cars = mutableListOf<Car>()
//        for (i in 0..100){
//            cars.add(Car("Ford"))
//        }

        return root
    }

//    private fun showData(cars: List<Car>) {
//        val recyclerView: RecyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this.context)
//        recyclerView.adapter = CarAdapter(cars)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}