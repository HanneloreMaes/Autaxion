package com.hanneloremaes.autaxion.ui.detailCar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.firestore.FirebaseFirestore
import com.hanneloremaes.autaxion.databinding.FragmentCarDetailBinding
import com.hanneloremaes.autaxion.model.DetailCar
import kotlinx.android.synthetic.main.fragment_car_detail.*

class CarDetailFragment : Fragment(){

    private var position: Int = 0
    var carsDetailList: MutableList<DetailCar> = mutableListOf()
    private var _binding: FragmentCarDetailBinding? = null
    private val binding get() = _binding!!
    private val api_key: String = "9gLyMNOM1kYEpyMKkLpoAg==ERVCQ4Gl57qfMsEK"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCarDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //FireStore
        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("Favorites").document("Saved")
        docRef.get()
            .addOnSuccessListener { result ->
                if(result != null){
                    Log.d("Firebase-Succes", "${result.id} => ${result.data}")
                }else{
                    Log.d("Firebase-NotSucces", "No result")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Firebase-Error", "Error getting documents.", exception)
            }

        val args = this.arguments
        val brandData = args?.get("argBrand")
        val modelData = args?.get("argModel")
        val yearData = args?.get("argYear")
        Log.d("Arguments-Year", "Brand: $brandData : $modelData: $yearData")

        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 begin*/
        val queue = Volley.newRequestQueue(this.context)
//        val url = "https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformake/chrysler?format=json"
        val url = "https://api.api-ninjas.com/v1/cars?make=${brandData}&model=${modelData}&year=${yearData}"

        val detailRequest: JsonArrayRequest = object: JsonArrayRequest(
            Request.Method.GET, url, null, Response.Listener { response ->
                Log.d("User-Detail", "Info: $response")
                for (car in 0 until response.length()){
                    val objRes = response.getJSONObject(car)
                    val fuel = objRes.getString("fuel_type").uppercase()
                    val carBrandName = objRes.getString("make").uppercase()
                    val carModelName = objRes.getString("model").uppercase()
                    val carYear = objRes.getInt("year")
                    val cylinderCar = objRes.getInt("cylinders")
                    val displacementCar = objRes.getInt("displacement")
                    val driveCar = objRes.getString("drive").uppercase()
                    if(driveCar != null){
                        carsDetailList.add(DetailCar(fuel, carBrandName, carModelName, carYear, cylinderCar, displacementCar, driveCar))
                    }else{
                        carsDetailList.add(DetailCar(fuel, carBrandName, carModelName, carYear, cylinderCar, displacementCar, "Not available"))
                    }

                }
                detail_brandCar.text = carsDetailList.get(position).make
                detail_modelCar.text = carsDetailList.get(position).model
                detail_horsepower.text = carsDetailList.get(position).fuel_type
                detail_engine.text = carsDetailList.get(position).cylinders.toString()
                detail_year.text = carsDetailList.get(position).year.toString()
                accelerate.text = carsDetailList.get(position).displacement.toString()
                topSpeed.text = carsDetailList.get(position).drive


            }, Response.ErrorListener { Log.d("User-Error-Detail", "Something went wrong") })
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}