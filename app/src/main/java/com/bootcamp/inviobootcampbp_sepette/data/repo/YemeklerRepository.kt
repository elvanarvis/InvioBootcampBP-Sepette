package com.bootcamp.inviobootcampbp_sepette.data.repo

import com.bootcamp.inviobootcampbp_sepette.data.datasource.YemeklerDataSource
import com.bootcamp.inviobootcampbp_sepette.data.entity.SepetYemekler
import com.bootcamp.inviobootcampbp_sepette.data.entity.Yemekler

class YemeklerRepository(var yds: YemeklerDataSource){


    suspend fun addBasket(
        sepet_yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int,
        yemek_siparis_adet: Int, kullanici_adi: String
    ) = yds.addBasket(
        sepet_yemek_id,
        yemek_adi,
        yemek_resim_adi,
        yemek_fiyat,
        yemek_siparis_adet,
        kullanici_adi
    )

    suspend fun yemekleriYukle(): List<Yemekler> = yds.yemekleriYukle()

    suspend fun sepetYemekleriYukle(): List<SepetYemekler> = yds.sepetYemekleriYukle()

    suspend fun ara(word: String): List<Yemekler> = yds.ara(word)

    suspend fun delete(sepet_yemek_id:Int,kullanici_adi:String) = yds.delete(sepet_yemek_id,kullanici_adi)

}