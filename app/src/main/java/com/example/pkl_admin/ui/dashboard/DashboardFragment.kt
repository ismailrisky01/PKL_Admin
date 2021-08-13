package com.example.pkl_admin.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pkl_admin.data.viewmodel.DashboardViewModel
import com.example.pkl_admin.databinding.FragmentDashboardBinding
import com.example.pkl_admin.model.ModelPasien


class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val mDashboardViewModel by lazy {
        ViewModelProvider(this).get(DashboardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DashboardAdapter()
//        val data = ArrayList<ModelPasien>()
//        data.add(ModelPasien("a", "a", "a", "a", "a"))
//        data.add(ModelPasien("a", "a", "a", "a", "a"))
//        data.add(ModelPasien("a", "a", "a", "a", "a"))
//        data.add(ModelPasien("a", "a", "a", "a", "a"))


        mDashboardViewModel.getDataPasien.observe(viewLifecycleOwner, Observer {
            binding.IDDashboardRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            binding.IDDashboardRecyclerview.adapter = adapter
            adapter.setData(it)
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}