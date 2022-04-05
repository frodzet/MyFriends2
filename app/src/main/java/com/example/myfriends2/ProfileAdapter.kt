package com.example.myfriends2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfriends2.ProfileAdapter.ProfileViewHolder
import com.example.myfriends2.databinding.ProfileItemBinding


class ProfileAdapter(var profiles: List<Profile>, private val listener: (Profile, Int) -> Unit) :
    RecyclerView.Adapter<ProfileViewHolder>() {

    inner class ProfileViewHolder(val binding: ProfileItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        override fun onClick(view: View?) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProfileItemBinding.inflate(layoutInflater, parent, false)

        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profiles[position]
        holder.itemView.setOnClickListener { listener(profile, position) }

        holder.binding.apply {
            profileNameTextView.text = "${profiles[position].firstName} ${profiles[position].lastName}: ${profiles[position].age}"
            profileAddressTextView.text = "Address: ${profiles[position].address}"
            profilePhoneTextView.text = "Phone: ${profiles[position].phone}"

            profileImageView.setImageURI(profiles[position].imageURL)
        }
    }

    override fun getItemCount(): Int {
        return profiles.size
    }
}

