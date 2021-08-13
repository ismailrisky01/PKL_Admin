package com.example.pkl_admin.model

class ModelPasien(
    val idPasien: String,
    val namaPaien: String,
    val umurPasien: String,
    val beratPasien: String,
    val tinggiPasien: String
) {
    constructor() : this("", "", "", "", "")
}

class ModelAktifitasPasein(
    val idPasien: String,
    val dudukPasien: Double,
    val berdiriPasien: Double,
    val tidurPasien: Double
) {
    constructor() : this("", 0.0, 0.0, 0.0)
}

class ModelNilaiPasien(val uidNilai: String, val tanggalNilai: String, val nilai: Int) {
    constructor():this("","",0)
}