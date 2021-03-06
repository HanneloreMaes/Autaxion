package com.hanneloremaes.autaxion.ui.modelCars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.hanneloremaes.autaxion.R
import com.hanneloremaes.autaxion.databinding.FragmentModelCarsBinding
import com.hanneloremaes.autaxion.model.ModelCar.ModelCar
import com.hanneloremaes.autaxion.model.ModelCar.ModelCarAdapter
import com.hanneloremaes.autaxion.ui.dashboard.DashboardFragment
import com.hanneloremaes.autaxion.ui.modelYearCars.ModelYearCarsFragment

class ModelCarsFragment : Fragment(), ModelCarAdapter.OnItemClickListener {
    private val ARG_NAME = "argBrand"
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

        val toolbar: Toolbar = binding.recToolbar

        val text: TextView = binding.nameFragmentCar
        text.text = getString(R.string.title_dashboard)

        val btn: Button = binding.backBtnFragmentCar
        btn.setOnClickListener { void: View? ->
            val fragment: Fragment = DashboardFragment()
            val fr: FragmentManager = childFragmentManager
            fr.beginTransaction().replace(R.id.recModel, fragment).commit()
            btn.visibility = View.GONE
        }

        val args = this.arguments
        val brandData = args?.get("argBrand")
        val brandData2 = args?.get("argBrand2")

        Log.d("Arguments-Model", "Brand: $brandData")
        Log.d("Arguments-Model2", "Brand: $brandData2")

        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 begin*/
        val queue = Volley.newRequestQueue(this.context)
//        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformake/${brandData}?format=json"
        if (brandData != null){
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
        }else{
            val url = "https://api.api-ninjas.com/v1/cars?limit=30&make=${brandData2}"
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
        }




        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 eind*/

        toolbar.setOnClickListener {
            Toast.makeText(requireActivity(), "Awesome!", Toast.LENGTH_SHORT).show()
        }

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