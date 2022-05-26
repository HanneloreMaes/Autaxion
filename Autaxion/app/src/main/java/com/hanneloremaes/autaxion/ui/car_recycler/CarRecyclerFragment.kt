package com.hanneloremaes.autaxion.ui.car_recycler

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanneloremaes.autaxion.databinding.FragmentCarRecyclerBinding
import com.hanneloremaes.autaxion.databinding.FragmentDashboardBinding
import com.hanneloremaes.autaxion.models.car.Car
import com.hanneloremaes.autaxion.models.car.CarAdapter
import com.hanneloremaes.autaxion.models.car.CarApi
import com.hanneloremaes.autaxion.models.model.Model
import com.hanneloremaes.autaxion.models.model.ModelAdapter
import com.hanneloremaes.autaxion.models.model.ModelApi
import com.hanneloremaes.autaxion.ui.dashboard.CarRecyclerViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarRecyclerFragment : Fragment() {

    private var _binding: FragmentCarRecyclerBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(CarRecyclerViewModel::class.java)

        _binding = FragmentCarRecyclerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-anon-46d9d46b75-carsapi1.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ModelApi::class.java)

        api.fetchAllCars().enqueue(object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                showData(response.body()!!)
                Log.d("Hannelore", "onResponse: ${response.body()!!}")
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                Log.d("Hannelore", "onFailure")
            }
        })

        return root
    }

    private fun showData(models: List<Model>) {
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = ModelAdapter(models)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}