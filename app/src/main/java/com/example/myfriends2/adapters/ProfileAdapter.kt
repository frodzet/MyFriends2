package com.example.myfriends2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfriends2.adapters.ProfileAdapter.ProfileViewHolder
import com.example.myfriends2.databinding.ProfileItemBinding
import com.example.myfriends2.entities.Profile


class ProfileAdapter(var profiles: MutableList<Profile>, private val listener: (Profile, Int) -> Unit) :
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
            textViewProfileName.text = "${profiles[position].firstName} ${profiles[position].lastName}: ${profiles[position].age} år"
            textViewProfileAddress.text = "Address: ${profiles[position].address}"
            textViewProfilePhone.text = "Phone: ${profiles[position].phone}"

            imageViewProfile.setImageURI(profiles[position].imageURL)

           buttonProfileDelete.setOnClickListener { btn ->
               profiles.remove(profile)
               holder.bindingAdapter?.notifyDataSetChanged()
           }
        }
    }

    override fun getItemCount(): Int {
        return profiles.size
    }
}

