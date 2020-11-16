package com.bignerdranch.android.votingapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ballotlist.*

class BallotList : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ballotlist)

        val user = intent.getSerializableExtra("User") as User? //Receive user from RegisterActivity.kt

        if (user != null) {
            Toast.makeText(this, "Hello " + user.fName, Toast.LENGTH_LONG).show()

            lblFNameBallots.text = user.fName
            lblMNameBallots.text = user.mName
            lblLNameBallots.text = user.lName
            lblBDayBallots.text = user.bDay
            lblStateBallots.text = user.state
            lblZipBallots.text = user.zipCode.toString()
        }
        else
        {
            Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
        }



    }
}