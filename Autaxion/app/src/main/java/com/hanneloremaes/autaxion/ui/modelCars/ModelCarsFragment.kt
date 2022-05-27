package com.hanneloremaes.autaxion.ui.modelCars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hanneloremaes.autaxion.R
import com.hanneloremaes.autaxion.databinding.FragmentDashboardBinding
import com.hanneloremaes.autaxion.databinding.FragmentModelCarsBinding
import com.hanneloremaes.autaxion.model.Car
import com.hanneloremaes.autaxion.model.CarAdapter
import com.hanneloremaes.autaxion.model.ModelCar
import com.hanneloremaes.autaxion.model.ModelCarAdapter
import com.hanneloremaes.autaxion.ui.dashboard.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_model_card.*

class ModelCarsFragment : Fragment() {

    var carsModelsList: MutableList<ModelCar> = mutableListOf()
    private var _binding: FragmentModelCarsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentModelCarsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 begin*/
        val queue = Volley.newRequestQueue(this.context)
        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformake/chrysler?format=json"

        val carRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response ->
                val results = response.getJSONArray("Results")
                for (car in 0..(results.length()-1)){
                    val objRes = results[car].toString()
                    val gsonConverter = Gson()
                    val modelCar = gsonConverter.fromJson(objRes, ModelCar::class.java)

                    carsModelsList.add(modelCar)
                }

                val recyclerView: RecyclerView = binding.recyclerView
                recyclerView.layoutManager = LinearLayoutManager(this.context)
                recyclerView.adapter = ModelCarAdapter(carsModelsList)
            }, { Log.d("User", "Something went wrong") })

        queue.add(carRequest)
        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 eind*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}