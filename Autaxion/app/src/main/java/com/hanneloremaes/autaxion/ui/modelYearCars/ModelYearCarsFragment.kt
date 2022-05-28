package com.hanneloremaes.autaxion.ui.modelYearCars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.hanneloremaes.autaxion.R
import com.hanneloremaes.autaxion.databinding.FragmentCarDetailBinding
import com.hanneloremaes.autaxion.databinding.FragmentModelYearCarsBinding
import com.hanneloremaes.autaxion.model.DetailCar
import com.hanneloremaes.autaxion.model.YearModel
import com.hanneloremaes.autaxion.model.YearModelAdapter
import com.hanneloremaes.autaxion.ui.detailCar.CarDetailFragment

class ModelYearCarsFragment : Fragment(), YearModelAdapter.OnItemClickListener{

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


        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 begin*/
        val queue = Volley.newRequestQueue(this.context)
        //val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformake/chrysler?format=json"
//        val url = "https://api.api-ninjas.com/v1/cars?make=chrysler&model=cirrus&year=1997"
//
//        val detailRequest: JsonArrayRequest = object: JsonArrayRequest(
//            Request.Method.GET, url, null, Response.Listener { response ->
//                Log.d("Hannelore", "Info: $response")
////                val results = response.getJSONArray("Results")
////                for (car in 0..(results.length()-1)){
////                    val objRes = results[car].toString()
////                    Log.d("Hannelore", "Info: $objRes")
////                    val gsonConverter = Gson()
////                    val modelCar = gsonConverter.fromJson(objRes, DetailCar::class.java)
////
////                    carsDetailList.add(modelCar)
////                }
//
//            }, Response.ErrorListener { Log.d("User", "Something went wrong") })
//        /*https://syntaxfix.com/question/16326/how-to-set-custom-header-in-volley-request By Unknown begin*/
//        {
//            @Throws(AuthFailureError::class)
//            override fun getHeaders(): Map<String, String> {
//                val params = HashMap<String, String>()
//                params["X-Api-Key"] = api_key
//                return params
//            }
//        }
//        /*https://syntaxfix.com/question/16326/how-to-set-custom-header-in-volley-request By Unknown eind*/
//
//        queue.add(detailRequest)
//        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 eind*/

        return root
    }

    override fun onItemClick(position: Int) {
        /*https://stackoverflow.com/questions/7793576/switching-between-fragment-view By Mats Hofman begin*/
        val fragment: Fragment = CarDetailFragment()
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