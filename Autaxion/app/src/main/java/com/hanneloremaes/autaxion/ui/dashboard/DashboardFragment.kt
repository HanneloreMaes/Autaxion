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
import com.hanneloremaes.autaxion.CarAdapter
import com.hanneloremaes.autaxion.databinding.FragmentDashboardBinding
import com.hanneloremaes.autaxion.models.Car
import com.hanneloremaes.autaxion.models.CarApi
import kotlinx.android.synthetic.main.fragment_dashboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-anon-a867bc34bb-carsapi1.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(CarApi::class.java)

        api.fetchAllCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                showData(response.body()!!)
                Log.d("Hannelore", "onResponse: ${response.body()!![0].make}")
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                Log.d("Hannelore", "onFailure")
            }
        })

//        val recyclerView: RecyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this.context)
//        recyclerView.adapter = CarAdapter(cars)

//        val cars = mutableListOf<Car>()
//        for (i in 0..100){
//            cars.add(Car("Ford"))
//        }

        return root
    }

    private fun showData(cars: List<Car>) {
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = CarAdapter(cars)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}