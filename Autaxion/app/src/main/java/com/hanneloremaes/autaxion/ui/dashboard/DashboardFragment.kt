package com.hanneloremaes.autaxion.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hanneloremaes.autaxion.CarAdapter
import com.hanneloremaes.autaxion.databinding.FragmentDashboardBinding
import com.hanneloremaes.autaxion.models.Car
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        val cars = mutableListOf<Car>()
        for (i in 0..100){
            cars.add(Car("Ford"))
        }

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = CarAdapter(cars)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}