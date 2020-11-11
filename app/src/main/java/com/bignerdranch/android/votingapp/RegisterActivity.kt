package com.bignerdranch.android.votingapp

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
        val states = arrayOf<String>("Alabama")
        val spnAdapter = ArrayAdapter.createFromResource(this, R.array.state_list,android.R.layout.simple_spinner_dropdown_item)
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnState.adapter = spnAdapter

        val context = this
        //When register button is pressed
        btnRegister.setOnClickListener {
            if (editTextFName.text.toString().isNotEmpty() && editTextMName.text.toString()
                    .isNotEmpty() && editTextLName.text.toString()
                    .isNotEmpty() && editTextBDay.text.toString()
                    .isNotEmpty() && editTextZip.text.toString()
                    .isNotEmpty()
            )
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
                val db = DataBaseHandler(context)
                db.insertUser(user)
            }
            else
            {
                Toast.makeText(context, "Please Check All Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}