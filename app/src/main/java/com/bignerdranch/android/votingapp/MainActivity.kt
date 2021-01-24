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
            if(editTextEnterID.text.isNotEmpty())//If ID field is filled
            {
                if(editTextEnterPassword.text.isNotEmpty())//If password is filled
                {
                    val password = (editTextEnterPassword.text).toString()
                    val result = db.readData(password)//Get data of user with matching password
                    val user: User//Create user object
                    if(result.size != 0)
                    {
                        user = result[0]//Set object equal to result of query

                        //Go to authentication page and bring the user object.
                        val intent = Intent(this, FingerprintAuthentication::class.java)
                        intent.putExtra("User", user)//Pass the user object
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(this, "Account not found", Toast.LENGTH_LONG).show()
                    }
                }
            }
            else if(editTextEnterID.text.isEmpty() || editTextEnterPassword.text.isEmpty())
            {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        //View credits page
        btnCredits.setOnClickListener {
            val intent = Intent(this, Credits::class.java)
            startActivity(intent)
        }


    }
}