package com.bootcamp.inviobootcampbp_sepette.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.bootcamp.inviobootcampbp_sepette.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var yrepo: YemeklerRepository) : ViewModel(){

    fun addBasket(
        sepet_yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int,
        yemek_siparis_adet: Int, kullanici_adi: String
    ){
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.addBasket(sepet_yemek_id,yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)

        }
    }
}