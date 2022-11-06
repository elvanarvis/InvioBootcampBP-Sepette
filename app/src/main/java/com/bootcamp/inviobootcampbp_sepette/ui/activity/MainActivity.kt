package com.bootcamp.inviobootcampbp_sepette.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bootcamp.inviobootcampbp_sepette.R
import com.bootcamp.inviobootcampbp_sepette.databinding.ActivityMainBinding
import com.bootcamp.inviobootcampbp_sepette.ui.fragment.BasketFragment
import com.bootcamp.inviobootcampbp_sepette.ui.fragment.MainFragment
import com.bootcamp.inviobootcampbp_sepette.ui.fragment.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        NavigationUI.setupWithNavController(binding.bottomNav,navHostFragment.navController)
    }
}