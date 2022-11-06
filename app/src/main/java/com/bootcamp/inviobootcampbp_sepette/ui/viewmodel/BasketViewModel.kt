package com.bootcamp.inviobootcampbp_sepette.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.inviobootcampbp_sepette.data.entity.SepetYemekler
import com.bootcamp.inviobootcampbp_sepette.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var yrepo: YemeklerRepository): ViewModel() {

    var sepetListesi = MutableLiveData<List<SepetYemekler>>()

    init {
        sepetiYukle()
    }

    fun sepetiYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            sepetListesi.value = yrepo.sepetYemekleriYukle()
        }
    }

    fun delete(sepet_yemek_id:Int,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.delete(sepet_yemek_id,kullanici_adi)
            //sepetiYukle()
        }
    }
}