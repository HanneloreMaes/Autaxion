package com.hanneloremaes.autaxion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.hanneloremaes.autaxion.ui.home.HomeFragment

class HomeDetailCar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_detail_car)

        val btnBack: ImageButton = findViewById(R.id.backBtn)
        btnBack.setOnClickListener {
            val homeFragment= HomeFragment()
            val fragment: Fragment? = supportFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

            if (fragment !is HomeFragment){
                supportFragmentManager.beginTransaction().add(R.id.homeFragmentContainer, homeFragment, HomeFragment::class.java.simpleName).commit()
            }
            btnBack.visibility = View.GONE
        }
    }
}