package com.bootcamp.inviobootcampbp_sepette.retrofit

import com.bootcamp.inviobootcampbp_sepette.data.entity.CRUDCevap
import com.bootcamp.inviobootcampbp_sepette.data.entity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {
    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php
    //http://kasimadalan.pe.hu/ -> Base
    //yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yemekleriYukle(): YemeklerCevap

    @POST("yemekler/tumYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun search(@Field("yemek_adi") word:String): YemeklerCevap

    @POST("yemekler/yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addBasket(@Field("yemek_adi") yemek_adi:String,
                          @Field("yemek_resim_adi") yemek_resim_adi:String,
                          @Field("yemek_fiyat") yemek_fiyat:Int,
                          @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                          @Field("kullanici_adi") kullanici_adi:String,        ): CRUDCevap

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepetYemekleriGetir(@Field("kullanici_adi") kullanici_adi:String): CRUDCevap

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun delete(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                    @Field("kullanici_adi") kullanici_adi:String): CRUDCevap

}