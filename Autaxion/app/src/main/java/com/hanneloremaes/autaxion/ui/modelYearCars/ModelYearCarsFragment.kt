package com.hanneloremaes.autaxion.ui.modelYearCars

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
import com.android.volley.toolbox.Volley
import com.hanneloremaes.autaxion.R
import com.hanneloremaes.autaxion.databinding.FragmentModelYearCarsBinding
import com.hanneloremaes.autaxion.model.CarAdapter
import com.hanneloremaes.autaxion.model.DetailCar
import com.hanneloremaes.autaxion.model.YearModel
import com.hanneloremaes.autaxion.model.YearModelAdapter
import com.hanneloremaes.autaxion.ui.detailCar.CarDetailFragment

class ModelYearCarsFragment : Fragment(), YearModelAdapter.OnItemClickListener{
    private val ARG_BRAND = "argBrand"
    private val ARG_MODEL = "argModel"
    private val ARG_Year = "argYear"
    var modelYearList: MutableList<YearModel> = mutableListOf()
    private var _binding: FragmentModelYearCarsBinding? = null
    private val binding get() = _binding!!
    private val api_key: String = "9gLyMNOM1kYEpyMKkLpoAg==ERVCQ4Gl57qfMsEK"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentModelYearCarsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val args = this.arguments
        val brandData = args?.get("argName")
        val modelData = args?.get("argModel")
        Log.d("Arguments-ModelYearCar", "$brandData : $modelData")
        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 begin*/
        val queue = Volley.newRequestQueue(this.context)
        //val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformake/chrysler?format=json"
//        val url = "https://api.api-ninjas.com/v1/cars?make=chrysler&model=cirrus&year=1997"
        val url = "https://api.api-ninjas.com/v1/cars?limit=15&make=$brandData&model=$modelData"
//
        val detailRequest: JsonArrayRequest = object: JsonArrayRequest(
            Request.Method.GET, url, null, Response.Listener { response ->
                Log.d("User-ModelYearCars", "${response}")
                if(response.length() == 0){
                    for(car in 0 until response.length()){
                        Log.d("SUCCES-ModelYearCars", "If-statement modelYearCars == 1")
                        //val objRes = response.getJSONObject(car)
//                        val carBrandName = objRes.getString("make")
//                        val carModelName = objRes.getString("model")
//                        val carYear = objRes.getInt("year")
//                        val displacementCar = objRes.getInt("displacement")
//
//                        Log.d("User-Year", "Info: $carBrandName : $carModelName : $carYear : $displacementCar")
//
//                        modelYearList.add(YearModel(carBrandName, carModelName, carYear, displacementCar))
                    }
                }else{
                    Log.d("SUCCES-ModelYearCars", "If-statement modelYearCars != 1")
                    for (car in 0 until response.length()){
                        val objRes = response.getJSONObject(car)
                        val carBrandName = objRes.getString("make")
                        val carModelName = objRes.getString("model")
                        val carYear = objRes.getInt("year")
                        val displacementCar = objRes.getInt("displacement")

                        Log.d("User-Year", "Info: $carBrandName : $carModelName : $carYear : $displacementCar")

                        modelYearList.add(YearModel(carBrandName, carModelName, carYear, displacementCar))
                    }
                }


                val recyclerView: RecyclerView = binding.recyclerView
                recyclerView.layoutManager = LinearLayoutManager(this.context)
                recyclerView.adapter = YearModelAdapter(modelYearList, this)

            }, Response.ErrorListener { Log.d("User-Error-ModelYear", "Something went wrong") })
        /*https://syntaxfix.com/question/16326/how-to-set-custom-header-in-volley-request By Unknown begin*/
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params = HashMap<String, String>()
                params["X-Api-Key"] = api_key
                return params
            }
        }
        /*https://syntaxfix.com/question/16326/how-to-set-custom-header-in-volley-request By Unknown eind*/

        queue.add(detailRequest)
        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 eind*/

        return root
    }

    override fun onItemClick(position: Int) {
        val brand = modelYearList.get(position).make
        val model = modelYearList.get(position).model
        val year = modelYearList.get(position).year
        /*https://gist.github.com/codinginflow/a05784462aa8e2a60961539ecd803110 By Coding in Flow begin*/
        val args = Bundle()
        args.putString(ARG_BRAND, brand.lowercase())
        args.putString(ARG_MODEL, model.lowercase())
        args.putString(ARG_Year, year.toString().lowercase())
        /*https://gist.github.com/codinginflow/a05784462aa8e2a60961539ecd803110 By Coding in Flow eind*/

        /*https://stackoverflow.com/questions/7793576/switching-between-fragment-view By Mats Hofman begin*/
        val fragment: Fragment = CarDetailFragment()
        fragment.arguments = args
        val fm: FragmentManager = parentFragmentManager
        val trans: FragmentTransaction = fm.beginTransaction()
        trans.replace(R.id.recYear, fragment).commit()
        /*https://stackoverflow.com/questions/7793576/switching-between-fragment-view By Mats Hofman eind*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}