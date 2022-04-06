package com.example.myfriends2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import com.example.myfriends2.R

class AddFriendActivity : AppCompatActivity() {

    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText

    private lateinit var buttonAddFriend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        initializeVariables()

        createNavigationBar()

        buttonAddFriend.setOnClickListener { btn ->
            val intent: Intent = Intent()

            intent.putExtra("firstName", editTextFirstName.text.toString())
            intent.putExtra("lastName", editTextLastName.text.toString())

            setResult(RESULT_OK, intent)
            finish()
        }

    }

    private fun initializeVariables() {
        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)

        buttonAddFriend = findViewById(R.id.buttonAddProfile)
    }

    private fun createNavigationBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.title = "Friends"
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}