package com.example.yelpmefind

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yelpmefind.databinding.ActivityDetailsBinding
import com.example.yelpmefind.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    lateinit var phoneTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        phoneTextView = findViewById(R.id.detailContact)

        val business = intent.getParcelableExtra<Business>("business")

        business?.let {

            Glide.with(this)
                .load(it.imageUrl)
                .into(binding.detailImg)

            binding.detailBusinessName.text = it.name
            binding.detailBusinessType.text = it.categories[0].title
            binding.detailAddress.text = it.location.address1 + ", " + it.location.city
            binding.detailRating.text = it.rating.toString()
            binding.detailContact.text = it.phone

            val phoneNo  = it.phone

            phoneTextView.setOnClickListener(){
                dialPhoneNumber(phoneNo)
            }

        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }
}