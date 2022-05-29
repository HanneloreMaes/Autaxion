package com.hanneloremaes.autaxion

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hanneloremaes.autaxion.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_home_detail_car.*

class HomeDetailCar : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_detail_car)

        setSupportActionBar(findViewById(R.id.detail_toolbar))

        /*https://www.youtube.com/watch?v=fp-b9o4HgTg&list=PLhHQ6SXLVn4vNSg0QtLPxj0Q2uj0hvKQ1&index=5 By MS Pengejar begin*/

        val textName: TextView = findViewById(R.id.nameFragment)
        textName.text = "Home"

        backBtnActivity.setOnClickListener {
            val homeFragment= HomeFragment()
            val fragment: Fragment? = supportFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

            if (fragment !is HomeFragment){
                supportFragmentManager.beginTransaction().add(R.id.homeFragmentContainer, homeFragment, HomeFragment::class.java.simpleName).commit()
            }
            backBtnActivity.visibility = View.GONE
        }
        /*https://www.youtube.com/watch?v=fp-b9o4HgTg&list=PLhHQ6SXLVn4vNSg0QtLPxj0Q2uj0hvKQ1&index=5 By MS Pengejar eind*/
    }
}
