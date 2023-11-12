package com.example.yelpmefind

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BusinessAdapter(private val context: Context, private val businessList: List<Business>) :
RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return BusinessViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        val business = businessList[position]

        // Load business image using Glide (you need to add the Glide library to your dependencies)
        Glide.with(context)
            .load(business.imageUrl)
            .into(holder.imageView)

        holder.businessNameTextView.text = business.name
        holder.ratingTextView.text = business.rating.toString()
        holder.addressTextView.text = business.location.address1 + ", " + business.location.city
    }



    override fun getItemCount(): Int {
        return businessList.size
    }

    inner class BusinessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val businessNameTextView: TextView = itemView.findViewById(R.id.businessNameTV)
        val ratingTextView: TextView = itemView.findViewById(R.id.textView)
        val addressTextView: TextView = itemView.findViewById(R.id.addressTV)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val selectedBusiness = businessList[position]
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("business", selectedBusiness)
                }
                context.startActivity(intent)
            }
        }

    }




}