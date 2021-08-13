package com.example.pkl_admin.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pkl_admin.data.repository.DashboardRepository
import com.example.pkl_admin.model.ModelNilaiPasien
import com.example.pkl_admin.model.ModelPasien

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    val getDataPasien: LiveData<MutableList<ModelPasien>>
    private val repository: DashboardRepository = DashboardRepository()

    init {
        getDataPasien = repository.getDataPasien()
    }

    fun getQuestionValue(uidPasien: String): LiveData<MutableList<ModelNilaiPasien>> {
        return repository.getQuestionValue(uidPasien)
    }

}