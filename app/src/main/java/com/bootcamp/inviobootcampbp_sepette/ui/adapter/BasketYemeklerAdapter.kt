package com.bootcamp.inviobootcampbp_sepette.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.inviobootcampbp_sepette.R
import com.bootcamp.inviobootcampbp_sepette.data.entity.SepetYemekler
import com.bootcamp.inviobootcampbp_sepette.databinding.SepetCardViewBinding
import com.bootcamp.inviobootcampbp_sepette.ui.viewmodel.BasketViewModel
import com.bootcamp.inviobootcampbp_sepette.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class BasketYemeklerAdapter(var mContext: Context, var sepetListesi: List<SepetYemekler>, var viewModel: BasketViewModel) :
    RecyclerView.Adapter<BasketYemeklerAdapter.SepetCardViewHolder>(){

    inner class SepetCardViewHolder(binding: SepetCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: SepetCardViewBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardViewHolder {
        val binding:SepetCardViewBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.sepet_card_view,parent,false)
        return SepetCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SepetCardViewHolder, position: Int) {
        val sepet = sepetListesi.get(position)
        val s = holder.binding
        s.sepetListesi = sepet

        s.sepetDelete.setOnClickListener {
            Snackbar.make(it, "${sepet.yemek_adi} Silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET"){
                    //viewModel.delete()
                }.show()
            }
    }

    private fun delete(yemek_adi:String){
    }
    override fun getItemCount() = sepetListesi.size
}

