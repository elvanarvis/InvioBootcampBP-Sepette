package com.bootcamp.inviobootcampbp_sepette.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bootcamp.inviobootcampbp_sepette.data.entity.Yemekler
import com.bootcamp.inviobootcampbp_sepette.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var yrepo: YemeklerRepository) : ViewModel() {


    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemekleriYukle()
    }

    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesi.value = yrepo.yemekleriYukle()
        }
    }

    fun ara(word: String){
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesi.value = yrepo.ara(word)
        }
    }


}