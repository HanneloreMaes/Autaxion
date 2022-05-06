package com.school.autaxion.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.school.autaxion.R
import org.json.JSONArray

class BrandActivity: AppCompatActivity() {

    val url = "https://private-anon-a867bc34bb-carsapi1.apiary-mock.com/cars"
    var brandTxt = findViewById<TextView>(R.id.brandCar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.car_layout_recycler)

        //DATA FETCHING
        getData()

    }

    fun getData(){
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val data = response.toString()
                var jArrya = JSONArray(data)
                for(i in 0..jArrya.length()-1){
                    var jObject = jArrya.getJSONObject(i)
                    var brandCar = jObject.getString("make") as TextView
                    brandTxt = brandCar
                }
            }, {  })
        queue.add(request)
    }
}