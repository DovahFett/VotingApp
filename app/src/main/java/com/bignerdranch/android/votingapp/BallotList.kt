package com.bignerdranch.android.votingapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ballotlist.*

class BallotList : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ballotlist)

        val spnBallots = findViewById<Spinner>(R.id.spnBallots)


        val context = this
        val db = DataBaseHandler(context)

        var user = intent.getSerializableExtra("User") as User? //Receive user from RegisterActivity.kt

        if (user != null)
        {
            //Toast.makeText(this, "Hello " + user.fName, Toast.LENGTH_LONG).show()


            var userData = db.readData(user.password)
            //If the user's data has been successfully retrieved, display it.
            if(userData.size != 0)
            {
                user = userData[0]

                //Load user info into text fields
                txtIDBallots.text = user.id.toString()
                txtFNameBallots.text = user.fName
                txtMNameBallots.text = user.mName
                txtLNameBallots.text = user.lName
                txtBDayBallots.text = user.bDay
                txtStateBallots.text = user.state
                txtZipBallots.text = user.zipCode.toString()
                txtPasswordBallots.text = user.password

                var ballotData : ArrayList<String> = db.getBallotNames(user.zipCode, user.state)//get list of ballot objects
                if(ballotData.size != 0)
                {
                    val spnAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, ballotData)

                    spnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spnBallots.adapter = spnAdapter
                }
                else
                {
                    Toast.makeText(this, "Error retrieving ballots", Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(this, "Error getting user data", Toast.LENGTH_LONG).show()
            }
        }
        else
        {
            Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}