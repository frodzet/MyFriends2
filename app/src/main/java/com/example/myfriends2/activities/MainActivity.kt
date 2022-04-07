@file:Suppress("DEPRECATION")

package com.example.myfriends2.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfriends2.entities.Profile
import com.example.myfriends2.adapters.ProfileAdapter
import com.example.myfriends2.R


class MainActivity : AppCompatActivity() {

    // RequestCodes
    private val GET_PROFILE_DATA = 100
    private val SELECT_PICTURE = 200

    private lateinit var profilesList: MutableList<Profile>
    private lateinit var profileRecyclerView: RecyclerView
    private lateinit var profileAdapter: ProfileAdapter

    private lateinit var lastClickedProfile: Profile
    var lastClickedIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title = "My Friends 2.0"

        initializeVariables()
        initializeEvents()
    }

    private fun initializeVariables() {
        // List of profiles (With mock data)
        profilesList = mutableListOf(
            Profile(firstName = "Simon", lastName = "Birkedal", age = 25),
            Profile(firstName = "Mikkel", lastName = "Larsen", age = 33),
            Profile(firstName = "Niels", lastName = "Jakobsen", age = 25),
            Profile(firstName = "Mike", lastName = "Anders", age = 83),
            Profile(firstName = "Anders", lastName = "Svendsen", age = 58),
            Profile(firstName = "Janus", lastName = "Nielsen", age = 43),
            Profile(firstName = "Mikkel", lastName = "Jensen", age = 27),
            Profile(firstName = "Nadja", lastName = "Iversen", age = 20),
        )

        // Profile Adapter
        profileAdapter = ProfileAdapter(profilesList, listener = { profile: Profile, position: Int ->
            lastClickedProfile = profile
            lastClickedIndex = position
            imageChooser()
        })


        // Profile View (RecyclerView)
        profileRecyclerView = findViewById(R.id.profilesRecyclerView)
        profileRecyclerView.adapter = profileAdapter
        profileRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initializeEvents() {

    }

    private fun imageChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data or return if URL is null
                val selectedImageUri: Uri = data?.data ?: return

                profilesList[lastClickedIndex].imageURL = selectedImageUri;
                profileAdapter.notifyItemChanged(lastClickedIndex)

                profileRecyclerView.requestLayout()
            }

            if (requestCode == GET_PROFILE_DATA) {
                addProfile(data)
            }
        }
    }

    private fun openAddFriend() {
        val intent = Intent(this@MainActivity, AddFriendActivity::class.java)
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, GET_PROFILE_DATA)
    }

    private fun addProfile(data: Intent?) {
        val firstName: String? = data?.getStringExtra("firstName")
        val lastName: String? = data?.getStringExtra("lastName")
        val age: Int? = data?.getIntExtra("age", 0)

        profilesList.add(Profile(firstName = firstName, lastName = lastName, age = age))

        profileAdapter.notifyItemInserted(profilesList.size)

        profileRecyclerView.scrollToPosition(profilesList.size - 1)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menuItemAddFriend -> {
                openAddFriend()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
