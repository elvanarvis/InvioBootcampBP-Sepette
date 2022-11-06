package com.bootcamp.inviobootcampbp_sepette.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bootcamp.inviobootcampbp_sepette.R
import com.bootcamp.inviobootcampbp_sepette.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment(R.layout.fragment_payment) {
    private lateinit var binding: FragmentPaymentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.paymentFragment = this

        binding.apply {
            paymentButton.setOnClickListener {
                if (cardNo.text.isNullOrEmpty() && cardMonth.text.isNullOrEmpty() && cardYear.text.isNullOrEmpty()){

                }else{
                    if(binding.checkBox.isChecked)
                        paymentSuccess.visibility = View.VISIBLE
                }

            }
        }

    }
}
