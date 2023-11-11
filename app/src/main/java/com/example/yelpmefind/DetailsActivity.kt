package com.example.yelpmefind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yelpmefind.databinding.ActivityDetailsBinding
import com.example.yelpmefind.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}