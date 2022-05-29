package com.hanneloremaes.autaxion.ui.detailCar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.firestore.FirebaseFirestore
import com.hanneloremaes.autaxion.databinding.FragmentCarDetailBinding
import com.hanneloremaes.autaxion.model.DetailCar.DetailCar
import kotlinx.android.synthetic.main.fragment_car_detail.*
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap

class CarDetailFragment : Fragment(){

    private val db = FirebaseFirestore.getInstance().collection("Favorites")

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

        val random2 = Random().nextInt(10000) + 1

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
                    val idCar = random2
                    val fuel = objRes.getString("fuel_type").uppercase()
                    val carBrandName = objRes.getString("make").uppercase()
                    val carModelName = objRes.getString("model").uppercase()
                    val carYear = objRes.getInt("year")
                    val cylinderCar = objRes.getInt("cylinders")
                    val displacementCar = objRes.getInt("displacement")
                    val driveCar = objRes.getString("drive").uppercase()
                    if(driveCar != null){
                        carsDetailList.add(DetailCar(idCar, fuel, carBrandName, carModelName, carYear, cylinderCar, displacementCar, driveCar))
                    }else{
                        carsDetailList.add(DetailCar(idCar, fuel, carBrandName, carModelName, carYear, cylinderCar, displacementCar, "Not available"))
                    }

                }
                idCar.text = carsDetailList.get(position).idCar.toString()
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


        val btnStore: CheckBox? = binding.saveBtn

        btnStore?.setOnClickListener {
                view: View? -> store()
        }

        return root
    }

    /*https://www.youtube.com/watch?v=yKSuB5COWL4 By Winision begin*/
    private fun store(){
        val id = idCar.text
        val brand = detail_brandCar.text
        val model = detail_modelCar.text
        val horsepower = detail_horsepower.text
        val enginge = detail_engine.text
        val year = detail_year.text
        val accelerate = accelerate.text
        val speed = topSpeed.text

        if (brand != null){
            try {
                val items = HashMap<String, Any>()
                items.put("Id", id)
                items.put("Brand", brand)
                items.put("Model", model)
                items.put("Horsepower", horsepower)
                items.put("Enginge", enginge)
                items.put("Year", year)
                items.put("Accelerate", accelerate)
                items.put("Speed", speed)
                db.document("CarSaved${id}").set(items).addOnSuccessListener {
                        void: Void? -> Toast.makeText(this.context, "Liked", Toast.LENGTH_LONG).show()
                }.addOnFailureListener {
                        exception: java.lang.Exception -> Toast.makeText(this.context, exception.toString(), Toast.LENGTH_LONG).show()
                }
            }catch (e: Exception){
                Toast.makeText(this.context, e.toString(), Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this.context, "Something went wrong", Toast.LENGTH_LONG).show()
        }
    }
    /*https://www.youtube.com/watch?v=yKSuB5COWL4 By Winision eind*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}