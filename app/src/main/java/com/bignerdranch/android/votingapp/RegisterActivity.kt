package com.bignerdranch.android.votingapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.register.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class RegisterActivity : AppCompatActivity()
{
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)


        val spnState = findViewById<Spinner>(R.id.spnState)
        val spnAdapter = ArrayAdapter.createFromResource(this, R.array.state_list,android.R.layout.simple_spinner_dropdown_item)
        spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnState.adapter = spnAdapter

        val pm1 = PasswordManager()
        val pm2 = PasswordManager()
        //Get the current date
        val current = LocalDateTime.now() //Get date & time
        //val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        //val currentDate = current.format(formatter)

        val context = this

        //Toast.makeText(context, "Date: $currentDate", Toast.LENGTH_SHORT).show()
        //When register button is pressed
        btnRegister.setOnClickListener {
            if (editTextFName.text.toString().isNotEmpty())
            {
                if(editTextMName.text.toString().isNotEmpty())
                {
                    if(editTextLName.text.toString().isNotEmpty())
                    {
                        val convertedBirthDate = LocalDate.parse(editTextBDay.text.toString(), DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                        val years = ChronoUnit.YEARS.between(convertedBirthDate, current)

                        if(editTextBDay.text.toString().isNotEmpty())//Check if user is at least 18 years old
                        {
                            if(years >= 18)
                            {
                                if(editTextZip.text.toString().isNotEmpty() && editTextZip.text.toString().length == 5)
                                {
                                    //Create new user with contents of text fields

                                    val user = User(editTextFName.text.toString(),editTextMName.text.toString(),editTextLName.text.toString(),editTextBDay.text.toString(),spnState.selectedItem.toString(),Integer.parseInt(editTextZip.text.toString()))

                                    user.id = pm1.generatePassword(isWithLetters = false, isWithUppercase = false, isWithNumbers = true, isWithSpecial = false, 8).toInt()//Generate user ID
                                    user.setUserPassword(pm2.generatePassword(isWithLetters = true, isWithUppercase = true, isWithNumbers = true, isWithSpecial = true, 16))//Generate strong password for user & hash it


                                    val db = DataBaseHandler(context)
                                    db.insertUser(user)//Add user to database
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
                                Toast.makeText(context, "Must be at least 18 to register", Toast.LENGTH_SHORT).show()
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