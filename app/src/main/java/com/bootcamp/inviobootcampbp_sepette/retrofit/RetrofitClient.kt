package com.bootcamp.inviobootcampbp_sepette.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        fun getClient(baeUrl: String): Retrofit {
            return Retrofit.Builder().baseUrl(baeUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }

}