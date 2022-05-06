package com.school.autaxion

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.school.autaxion.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val url = "https://private-anon-a867bc34bb-carsapi1.apiary-mock.com/cars"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    //NAVIGTION ACTIONS
        val navView: BottomNavigationView = binding.navView
        //Zetten van juiste kleur voor nav
        navView.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    //DATA FETCHING
        getData()

    }

    fun getData(){
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(Request.Method.GET, url,
            Response.Listener {  response ->
                val data = response.toString()
                var jArrya = JSONArray(data)
                for(i in 0..jArrya.length()-1){
                    var jObject = jArrya.getJSONObject(i)
                    var brandCar = jObject.getString("make")
                    var modelCar = jObject.getString("model")
                    var horsepower = jObject.getInt("horsepower")
                }
            }, Response.ErrorListener{  })
        queue.add(request)
    }
}