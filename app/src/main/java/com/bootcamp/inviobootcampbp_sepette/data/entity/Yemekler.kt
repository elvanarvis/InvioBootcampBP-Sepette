package com.bootcamp.inviobootcampbp_sepette.data.entity

import java.io.Serializable

data class Yemekler(
    var yemek_id: Int,
    var yemek_adi: String,
    val yemek_resim_adi: String,
    var yemek_fiyat: Int
): Serializable {
}