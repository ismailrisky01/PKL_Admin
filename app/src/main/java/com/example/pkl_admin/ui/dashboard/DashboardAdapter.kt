package com.example.pkl_admin.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pkl_admin.R
import com.example.pkl_admin.databinding.ItemPasienBinding
import com.example.pkl_admin.model.ModelPasien

class DashboardAdapter() : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
    companion object{
        val keyIdPasien="keyIdPasien"
        val keyNamaPasien ="keyNamaPasien"
        val keyUmurPasien="keyUmurPasien"
        val keyBeratPasien="keyBeratPasien"
        val keyTinggiPasien="keyTinggiPasien"
    }
    private var dataAdapter = mutableListOf<ModelPasien>()
    fun setData(data: MutableList<ModelPasien>) {
        dataAdapter = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPasienBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataAdapter[position]
        holder.initData(data)
    }

    class ViewHolder(val binding: ItemPasienBinding) : RecyclerView.ViewHolder(binding.root) {
        fun initData(modelPasien: ModelPasien) {
            binding.IDItemPasienNamaPasien.text = modelPasien.namaPaien
            binding.IDItemPasienCard.setOnClickListener {
                val bundle=Bundle()
                bundle.putString(keyIdPasien,modelPasien.idPasien)
                bundle.putString(keyNamaPasien,modelPasien.namaPaien)
                bundle.putString(keyUmurPasien,modelPasien.umurPasien)
                bundle.putString(keyBeratPasien,modelPasien.beratPasien)
                bundle.putString(keyTinggiPasien,modelPasien.tinggiPasien)
                it.findNavController().navigate(R.id.action_dashboardFragment_to_detailFragment,bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataAdapter.size
    }
}