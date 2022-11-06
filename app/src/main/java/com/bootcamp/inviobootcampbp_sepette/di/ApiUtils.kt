package com.bootcamp.inviobootcampbp_sepette.di

import com.bootcamp.inviobootcampbp_sepette.retrofit.RetrofitClient
import com.bootcamp.inviobootcampbp_sepette.retrofit.YemeklerDao

class ApiUtils {
    companion object{
        var BASE_URL = "http://kasimadalan.pe.hu/"

        fun getYemeklerDao(): YemeklerDao{
            return RetrofitClient.getClient(BASE_URL).create(YemeklerDao::class.java)
        }
    }
}