package com.bootcamp.inviobootcampbp_sepette.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.inviobootcampbp_sepette.R
import com.bootcamp.inviobootcampbp_sepette.data.entity.Yemekler
import com.bootcamp.inviobootcampbp_sepette.databinding.FragmentMainBinding
import com.bootcamp.inviobootcampbp_sepette.ui.adapter.YemeklerAdapter
import com.bootcamp.inviobootcampbp_sepette.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.mainFragment = this

        (activity as AppCompatActivity).setSupportActionBar(binding.searchToolbar)

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                val adapter = YemeklerAdapter(requireContext(),it)
                binding.yemeklerAdapter = adapter
            }
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu,menu)

                val item = menu.findItem(R.id.action_search)
                val search = item.actionView as SearchView
                search.setOnQueryTextListener(this@MainFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        search(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        search(newText!!)
        return true
    }

    fun search(word:String){
        viewModel.ara(word)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempModel:MainViewModel by viewModels()
        viewModel = tempModel
    }
}

