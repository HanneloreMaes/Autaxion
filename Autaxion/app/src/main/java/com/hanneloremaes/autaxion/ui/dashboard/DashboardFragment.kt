package com.hanneloremaes.autaxion.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.hanneloremaes.autaxion.R
import com.hanneloremaes.autaxion.databinding.FragmentDashboardBinding
import com.hanneloremaes.autaxion.model.Car
import com.hanneloremaes.autaxion.model.CarAdapter
import com.hanneloremaes.autaxion.onClickFragments.ModelCarsFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment(), CarAdapter.OnItemClickListener {

    var carsBrandsList: MutableList<Car> = mutableListOf()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 begin*/
        val queue = Volley.newRequestQueue(this.context)
        val url = "https://private-anon-a867bc34bb-carsapi1.apiary-mock.com/manufacturers"

        val carRequest = JsonArrayRequest(
            Request.Method.GET, url, null, { response ->
                for (car in 0..39){
                    val objRes = response.getJSONObject(car)
                    Log.d("Hannelore", "CarString: ${objRes}")

                    val carBrandName = objRes.getString("name")

                    carsBrandsList.add(Car(carBrandName))
                }

                val recyclerView: RecyclerView = binding.recyclerView
                recyclerView.layoutManager = LinearLayoutManager(this.context)
                recyclerView.adapter = CarAdapter(carsBrandsList, this)
            }, { Log.d("Gebruiker", "Something went wrong") })

        queue.add(carRequest)
        /*https://www.youtube.com/watch?v=e3MDW87mbR8 By SmallAcademy Pt. 1-3 eind*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this.context, "Item $position clicked", Toast.LENGTH_SHORT).show()

        val fragment: Fragment = ModelCarsFragment()
        val fragmentManager : FragmentManager = childFragmentManager
        fragmentManager.beginTransaction().replace(R.id.recDashboard, fragment).commit()

//        val clickedItem: Car = carsBrandsList[position]
//        clickedItem.name = "Clicked"
//        recyclerView.adapter?.notifyItemChanged(position)
    }
}