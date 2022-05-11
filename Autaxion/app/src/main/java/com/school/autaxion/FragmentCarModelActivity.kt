package com.school.autaxion


import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.school.autaxion.databinding.FragmentDashboardBinding
import com.school.autaxion.models.CarAdapter
import com.school.autaxion.models.CarModel
import org.json.JSONException


class FragmentCarModelActivity : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!


    private var courseRV: RecyclerView? = null
    private var adapter: CarAdapter? = null
    private var courseModalArrayList: ArrayList<CarModel>? = null

    var url = "https://private-anon-a867bc34bb-carsapi1.apiary-mock.com/cars"
    private var progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private val data:
            // in this case the data we are getting is in the form
            // of array so we are making a json array request.
            // below is the line where we are making an json array
            // request and then extracting data from each json object.
            Unit
        private get() {
            // creating a new variable for our request queue
            val queue = Volley.newRequestQueue(this@FragmentCarModelActivity)
            // in this case the data we are getting is in the form
            // of array so we are making a json array request.
            // below is the line where we are making an json array
            // request and then extracting data from each json object.
            val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET, url, null,
                { response ->
                    progressBar!!.visibility = View.GONE
                    courseRV!!.visibility = View.VISIBLE
                    for (i in 0 until response.length()) {
                        try {
                            // we are getting each json object.
                            val responseObj = response.getJSONObject(i)

                            val carBrandName = responseObj.getString("make")
                            val carBrandImage = responseObj.getString("img_url")
                            courseModalArrayList!!.add(
                                CarModel(
                                    carBrandName,
                                    carBrandImage
                                )
                            )
                            buildRecyclerView()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }
            ) {
                Toast.makeText(this@FragmentCarModelActivity, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
            queue.add(jsonArrayRequest)
        }

    private fun buildRecyclerView() {

        // initializing our adapter class.
        adapter = CarAdapter(courseModalArrayList, this@FragmentCarModelActivity)

        // adding layout manager
        // to our recycler view.
        val manager = LinearLayoutManager(this)
        courseRV!!.setHasFixedSize(true)

        // setting layout manager
        // to our recycler view.
        courseRV!!.layoutManager = manager

        // setting adapter to
        // our recycler view.
        courseRV!!.adapter = adapter
    }
}