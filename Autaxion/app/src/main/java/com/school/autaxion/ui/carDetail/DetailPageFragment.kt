package com.school.autaxion.ui.carDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.school.autaxion.R

class DetailPageFragment : Fragment() {

    companion object {
        fun newInstance() = DetailPageFragment()
    }

    private lateinit var viewModel: DetailPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailPageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}