package com.bootcamp.inviobootcampbp_sepette.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bootcamp.inviobootcampbp_sepette.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {

            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        },3000)
    }
}