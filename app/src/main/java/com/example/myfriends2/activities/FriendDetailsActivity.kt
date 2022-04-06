package com.example.myfriends2.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.myfriends2.R

class FriendDetailsActivity : AppCompatActivity() {

    lateinit var editTextFirstName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_details)

        initializeVariables()

        createNavigationBar()
    }

    private fun initializeVariables() {
        editTextFirstName = findViewById(R.id.editTextFirstName)
    }

    private fun createNavigationBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Friends"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent: Intent = Intent()
        intent.putExtra("firstName", editTextFirstName.text.toString())
        setResult(RESULT_OK, intent)
        finish()
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}