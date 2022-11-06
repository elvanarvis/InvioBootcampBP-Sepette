package com.bootcamp.inviobootcampbp_sepette.data.datasource

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.bootcamp.inviobootcampbp_sepette.data.entity.SepetYemekler
import com.bootcamp.inviobootcampbp_sepette.data.entity.Yemekler
import com.bootcamp.inviobootcampbp_sepette.retrofit.YemeklerDao
import com.bootcamp.inviobootcampbp_sepette.ui.activity.LoginActivity
import com.bootcamp.inviobootcampbp_sepette.ui.fragment.MainFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource(var ydao: YemeklerDao) {

    suspend fun addBasket(
        sepet_yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int,
        yemek_siparis_adet: Int, kullanici_adi: String
    ) {
        ydao.addBasket(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }

    suspend fun yemekleriYukle(): List<Yemekler> = withContext(Dispatchers.IO){
        ydao.yemekleriYukle().yemekler
    }


    suspend fun sepetYemekleriYukle(): List<SepetYemekler> = withContext(Dispatchers.IO){
        var sepetListesi = ArrayList<SepetYemekler>()
        //val message = ydao.sepetYemekleriGetir("elvan").message
        //Log.e("Sepet yemekler burada", message)
        //sepetListesi.add()
        val s1 = SepetYemekler(1,"Baklava","aaa",100,3,"elvan")
        val s2 = SepetYemekler(1,"Köfte","aaa",35,2,"elvan")
        val s3 = SepetYemekler(1,"İskender","aaa",120,1,"elvan")
        val s4 = SepetYemekler(1,"Ayran","aaa",7,1,"elvan")
        val s5 = SepetYemekler(1,"Su","aaa",5,4,"elvan")

        sepetListesi.add(s1)
        sepetListesi.add(s2)
        sepetListesi.add(s3)
        sepetListesi.add(s4)
        sepetListesi.add(s5)

        return@withContext sepetListesi
    }

    suspend fun ara(word: String): List<Yemekler> = withContext(Dispatchers.IO){
        ydao.search(word).yemekler
    }

    suspend fun delete(sepet_yemek_id:Int,kullanici_adi:String){

        Log.e("Yemek Sil", kullanici_adi)
        //ydao.delete(sepet_yemek_id,kullanici_adi)
    }

}