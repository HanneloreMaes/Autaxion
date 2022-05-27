package com.hanneloremaes.autaxion.onClickFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanneloremaes.autaxion.R
import com.hanneloremaes.autaxion.databinding.FragmentDashboardBinding
import com.hanneloremaes.autaxion.databinding.FragmentModelCarsBinding

class ModelCarsFragment : Fragment() {

    private var _binding: FragmentModelCarsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentModelCarsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

}