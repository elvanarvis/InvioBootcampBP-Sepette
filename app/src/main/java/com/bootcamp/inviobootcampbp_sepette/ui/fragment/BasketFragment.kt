package com.bootcamp.inviobootcampbp_sepette.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.inviobootcampbp_sepette.R
import com.bootcamp.inviobootcampbp_sepette.data.entity.SepetYemekler
import com.bootcamp.inviobootcampbp_sepette.databinding.FragmentBasketBinding
import com.bootcamp.inviobootcampbp_sepette.ui.adapter.BasketYemeklerAdapter
import com.bootcamp.inviobootcampbp_sepette.ui.adapter.YemeklerAdapter
import com.bootcamp.inviobootcampbp_sepette.ui.viewmodel.BasketViewModel
import com.bootcamp.inviobootcampbp_sepette.utils.changePage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment(R.layout.fragment_basket) {

    private lateinit var binding: FragmentBasketBinding
    private lateinit var vievModel: BasketViewModel
    var fiyat = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.basketFragment = this

        vievModel.sepetListesi.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                val adapter = BasketYemeklerAdapter(requireContext(),it,vievModel)
                binding.sepetAdapter = adapter
                for (s in it){
                    fiyat += s.yemek_fiyat * s.yemek_siparis_adet
                }
                binding.totalFiyat ="₺${fiyat.toDouble()}"
            }
        }
        binding.sepetAddBasketButton.setOnClickListener { gecis(it) }
    }

    fun gecis(view: View){
        Navigation.changePage(view,R.id.action_basketFragment_to_paymentFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempModel:BasketViewModel by viewModels()
        vievModel = tempModel

    }

    override fun onResume() {
        super.onResume()
        //vievModel.sepetiYukle()
    }

}

