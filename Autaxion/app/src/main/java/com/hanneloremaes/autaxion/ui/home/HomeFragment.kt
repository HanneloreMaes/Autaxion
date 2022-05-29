package com.hanneloremaes.autaxion.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hanneloremaes.autaxion.HomeDetailCar
import com.hanneloremaes.autaxion.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*https://www.youtube.com/watch?v=fp-b9o4HgTg&list=PLhHQ6SXLVn4vNSg0QtLPxj0Q2uj0hvKQ1&index=5 By MS Pengejar begin*/
        binding.btnHomeDetail.setOnClickListener {
            val intent = Intent(this@HomeFragment.requireContext(), HomeDetailCar::class.java)
            startActivity(intent)
        }
        /*https://www.youtube.com/watch?v=fp-b9o4HgTg&list=PLhHQ6SXLVn4vNSg0QtLPxj0Q2uj0hvKQ1&index=5 By MS Pengejar eind*/
        
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}