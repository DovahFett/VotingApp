package com.bignerdranch.android.votingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create listeners for both register buttons
        val btnNewUser = findViewById<Button>(R.id.btnNewUser)
        /*val btnNewUserHor = findViewById<Button>(R.id.btnNewUserHor)*/

        //Send user to registration page if they click the button
        btnNewUser.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
       /* btnNewUserHor.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }*/
        //Create listeners for both login buttons
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        /*val btnLoginHor = findViewById<Button>(R.id.btnLoginHor)*/

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