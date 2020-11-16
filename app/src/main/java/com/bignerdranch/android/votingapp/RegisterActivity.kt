package com.bignerdranch.android.votingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.register.*

class RegisterActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)


        val spnState = findViewById<Spinner>(R.id.spnState)
        val spnAdapter = ArrayAdapter.createFromResource(this, R.array.state_list,android.R.layout.simple_spinner_dropdown_item)
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnState.adapter = spnAdapter

        val context = this
        val db = DataBaseHandler(context)
        //When register button is pressed
        btnRegister.setOnClickListener {
            if (editTextFName.text.toString().isNotEmpty())
            {
                if(editTextMName.text.toString().isNotEmpty())
                {
                    if(editTextLName.text.toString().isNotEmpty())
                    {
                        if(editTextBDay.text.toString().isNotEmpty())
                        {
                            if(editTextZip.text.toString().isNotEmpty() && editTextZip.text.toString().length == 5)
                            {
                                //Create new user with contents of text fields
                                val user = User(
                                    editTextFName.text.toString(),
                                    editTextMName.text.toString(),
                                    editTextLName.text.toString(),
                                    editTextBDay.text.toString(),
                                    spnState.selectedItem.toString(),
                                    Integer.parseInt(editTextZip?.text.toString())
                                )
                                db.insertUser(user)
                                val intent = Intent(this, FingerprintAuthentication::class.java)
                                intent.putExtra("User", user)//Pass the user object
                                startActivity(intent)
                            }
                            else
                            {
                                Toast.makeText(context, "ZIP # must be 5 characters long", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else
                        {
                            Toast.makeText(context, "Date must follow format MM/DD/YYYY", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else
                    {
                        Toast.makeText(context, "Name must be less than 20 characters and have no spaces", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(context, "Name must be less than 20 characters and have no spaces", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(context, "Name must be less than 20 characters and have no spaces", Toast.LENGTH_SHORT).show()
            }
        }
    }
}