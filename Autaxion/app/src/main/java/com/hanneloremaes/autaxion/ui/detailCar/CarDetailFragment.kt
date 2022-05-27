package com.hanneloremaes.autaxion.ui.detailCar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hanneloremaes.autaxion.databinding.FragmentCarDetailBinding
import com.hanneloremaes.autaxion.model.DetailCar

class CarDetailFragment : Fragment(){

    var carsDetailList: MutableList<DetailCar> = mutableListOf()
    private var _binding: FragmentCarDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCarDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root


        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 begin*/
        val queue = Volley.newRequestQueue(this.context)
        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformake/chrysler?format=json"

        val detailRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response ->
                val results = response.getJSONArray("Results")
                for (car in 0..(results.length()-1)){
                    val objRes = results[car].toString()
                    val gsonConverter = Gson()
                    val modelCar = gsonConverter.fromJson(objRes, DetailCar::class.java)

                    carsDetailList.add(modelCar)
                }

            }, { Log.d("User", "Something went wrong") })

        queue.add(detailRequest)
        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 eind*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}