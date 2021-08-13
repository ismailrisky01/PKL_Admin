package com.example.pkl_admin.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pkl_admin.model.ModelNilaiPasien
import com.example.pkl_admin.model.ModelPasien
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DashboardRepository {
    var firestore = FirebaseFirestore.getInstance()

    fun getDataPasien(): LiveData<MutableList<ModelPasien>> {
        val mutableList = MutableLiveData<MutableList<ModelPasien>>()

        firestore.collection("PKL/Data/Pasien").get().addOnSuccessListener { result ->

            val data = mutableListOf<ModelPasien>()
            data.clear()
            result.forEach { document ->
                val it = document.toObject(ModelPasien::class.java)
                data.add(it)
            }
            mutableList.value = data
        }
        return mutableList
    }

    fun getQuestionValue(uidPasien:String):LiveData<MutableList<ModelNilaiPasien>>{
        val mutableList = MutableLiveData<MutableList<ModelNilaiPasien>>()

        firestore.collection("PKL/Question/$uidPasien").get().addOnSuccessListener { result ->
            val data = mutableListOf<ModelNilaiPasien>()

                result.forEach { document ->
                    val it = document.toObject(ModelNilaiPasien::class.java)
                    data.add(it)
            }

            mutableList.value = data
        }
        return mutableList
    }
}