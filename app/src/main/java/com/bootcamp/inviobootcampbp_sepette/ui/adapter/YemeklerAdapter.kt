package com.bootcamp.inviobootcampbp_sepette.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.inviobootcampbp_sepette.R
import com.bootcamp.inviobootcampbp_sepette.data.entity.Yemekler
import com.bootcamp.inviobootcampbp_sepette.databinding.CardViewBinding
import com.bootcamp.inviobootcampbp_sepette.ui.fragment.MainFragmentDirections
import com.bootcamp.inviobootcampbp_sepette.utils.changePage
import com.bumptech.glide.Glide

class YemeklerAdapter(var mContext: Context, var yemeklerListesi: List<Yemekler>) :
    RecyclerView.Adapter<YemeklerAdapter.CardViewHolder>() {

    inner class CardViewHolder(binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: CardViewBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding: CardViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.card_view, parent, false
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val yemek = yemeklerListesi.get(position)
        val b = holder.binding
        b.yemekListesi = yemek


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(mContext).load(url).into(b.yemekImg)


        b.cardView.setOnClickListener {
            val nav = MainFragmentDirections.actionMainFragmentToDetailFragment(yemek = yemek)
            Navigation.changePage(it, nav)
        }
    }

    override fun getItemCount() = yemeklerListesi.size

}