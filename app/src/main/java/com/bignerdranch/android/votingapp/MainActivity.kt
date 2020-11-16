package com.bignerdranch.android.votingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this
        val db = DataBaseHandler(context)
        //Create listeners for both register buttons
        val btnNewUser = findViewById<Button>(R.id.btnNewUser)

        //Send user to registration page if they click the button
        btnNewUser.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        //Create listeners for both login buttons
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        //Validate login field data
        btnLogin.setOnClickListener{
            if(editTextEnterID.text.isNotEmpty())
            {
                //if(editTextEnterPassword.text.isNotEmpty() && editTextEnterPassword.text.contains())
                Toast.makeText(this, "Valid Data Detected", Toast.LENGTH_SHORT).show()
            }
            else if(editTextEnterID.text.isEmpty())
            {
                Toast.makeText(this, "No Data Detected", Toast.LENGTH_SHORT).show()
            }
        }


    }
}