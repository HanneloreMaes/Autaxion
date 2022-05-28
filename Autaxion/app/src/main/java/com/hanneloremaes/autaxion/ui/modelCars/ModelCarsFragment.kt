package com.hanneloremaes.autaxion.ui.modelCars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.hanneloremaes.autaxion.R
import com.hanneloremaes.autaxion.databinding.FragmentModelCarsBinding
import com.hanneloremaes.autaxion.model.*
import com.hanneloremaes.autaxion.ui.modelYearCars.ModelYearCarsFragment
import org.json.JSONObject

class ModelCarsFragment : Fragment(), ModelCarAdapter.OnItemClickListener {
    private val ARG_NAME = "argName"
    private val ARG_MODEL = "argModel"
    private val api_key: String = "9gLyMNOM1kYEpyMKkLpoAg==ERVCQ4Gl57qfMsEK"
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

        val args = this.arguments
        val brandData = args?.get("argName")
        Log.d("Arguments-Model", "Brand: $brandData")

        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 begin*/
        val queue = Volley.newRequestQueue(this.context)
//        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformake/${brandData}?format=json"
        val url = "https://api.api-ninjas.com/v1/cars?limit=30&make=${brandData}"

        val modelRequest: JsonArrayRequest = object: JsonArrayRequest(
            Request.Method.GET, url, null, Response.Listener { response ->
                Log.d("User-ModelCars", "Info: $response")
                for (car in 0 until response.length()){
                    val objRes = response.getJSONObject(car)
                    val carBrandName = objRes.getString("make")
                    val carModelName = objRes.getString("model")

                    carsModelsList.add(ModelCar(carBrandName, carModelName))
                }

                val recyclerView: RecyclerView = binding.recyclerView
                recyclerView.layoutManager = LinearLayoutManager(this.context)
                recyclerView.adapter = ModelCarAdapter(carsModelsList, this)

            }, Response.ErrorListener { Log.d("User-Error-ModelCars", "Something went wrong") })
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["X-Api-Key"] = api_key
                return params
            }
        }

        queue.add(modelRequest)
        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 eind*/

        return root
    }

    override fun onItemClick(position: Int) {
        val brand = carsModelsList.get(position).make
        val model = carsModelsList.get(position).model
        /*https://gist.github.com/codinginflow/a05784462aa8e2a60961539ecd803110 By Coding in Flow begin*/
        val args = Bundle()
        args.putString(ARG_NAME, brand.lowercase())
        args.putString(ARG_MODEL, model.lowercase())
        /*https://gist.github.com/codinginflow/a05784462aa8e2a60961539ecd803110 By Coding in Flow eind*/

        /*https://stackoverflow.com/questions/7793576/switching-between-fragment-view By Mats Hofman begin*/
        val fragment: Fragment = ModelYearCarsFragment()
        fragment.arguments= args
        val fm: FragmentManager = parentFragmentManager
        val trans: FragmentTransaction = fm.beginTransaction()
        trans.replace(R.id.recModel, fragment).commit()
        /*https://stackoverflow.com/questions/7793576/switching-between-fragment-view By Mats Hofman eind*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}