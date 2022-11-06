package com.bootcamp.inviobootcampbp_sepette.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bootcamp.inviobootcampbp_sepette.R
import com.bootcamp.inviobootcampbp_sepette.databinding.FragmentDetailBinding
import com.bootcamp.inviobootcampbp_sepette.ui.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: FragmentDetailBinding
    var num = 1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.detailFragment = this

        val bundle: DetailFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        binding.yemekListesi = gelenYemek
        binding.kullaniciAdi = "elvan"
        binding.yemekFiyat = gelenYemek.yemek_fiyat

        binding.totalText = "$num"
        binding.totalFiyatText = "${gelenYemek.yemek_fiyat.toDouble()}"
        binding.totalTextInt = num


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(requireActivity()).load(url).into(binding.yemekResim)

    }

    fun minusClick(total: String, yemekFiyat: Int) {
        num = total.toInt()
        if (num <= 1)
            Toast.makeText(requireActivity(), "Daha az seçilemez.", Toast.LENGTH_LONG)
                .show()
        else {
            num - 1
            binding.totalText = "${num - 1}"
            binding.totalTextInt = num-1
            binding.totalFiyatText = "₺${(yemekFiyat * (num - 1).toDouble())}"
        }
    }

    fun plusClick(total: String, yemekFiyat: Int) {
        num = total.toInt()
        binding.totalText = "${num + 1}"
        binding.totalTextInt = num +1
        binding.totalFiyatText = "₺${yemekFiyat * (num + 1).toDouble()}"
    }

    fun addBasket(
        sepet_yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int,
        yemek_siparis_adet: Int, kullanici_adi: String
    ) {
        Log.e("AddBasket", "$sepet_yemek_id - $yemek_adi - $yemek_resim_adi - $yemek_fiyat - $yemek_siparis_adet - $kullanici_adi")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:DetailViewModel by viewModels()
        viewModel = tempViewModel
    }
}
