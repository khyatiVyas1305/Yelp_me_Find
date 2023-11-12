package com.example.yelpmefind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yelpmefind.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var about : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        about = findViewById(R.id.aboutApp)

        about.setOnClickListener {
            val intent = Intent(this, AboutApp::class.java)
            startActivity(intent)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    fetchBusinesses(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }


    private fun fetchBusinesses(keyword: String) {
        val call = RetrofitClient.yelpApiService.searchBusinesses(
            "Bearer BtHCnt_R2fLUgVUdQiWH8VM_tB8xGzh1m6gcE6-0Q2l5BSfPOYqyGeRtLJvJvqquNAk-rrByi6lO9vGGTaJ3lk4r4czizzjMNNLkfHS8F8I6qVnyRo3wamOHekJQZXYx",
            keyword
        )

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val businesses = response.body()?.businesses
                    businesses?.let {
                        val adapter = BusinessAdapter(this@MainActivity, it)
                        binding.recyclerView.adapter = adapter
                        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Error: "+ response.errorBody(), LENGTH_LONG).show()

                    Log.e("API Error", "Response: ${response.raw().toString()}")
                    Log.d("API Error",  response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Network Error", LENGTH_LONG).show()
            }
        })
    }

    private fun filterList(newText: String?) {
        //Filter the suggestions and searchview
    }
}