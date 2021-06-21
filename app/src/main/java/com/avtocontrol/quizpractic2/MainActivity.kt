package com.avtocontrol.quizpractic2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.avtocontrol.quizpractic2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
             val finalHost = NavHostFragment.create(R.navigation.nav_graph)
                 supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, finalHost)
                .setPrimaryNavigationFragment(finalHost)
                .commit()
            }
     }


}


