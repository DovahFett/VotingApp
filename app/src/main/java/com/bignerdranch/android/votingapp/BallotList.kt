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

        val context = this
        val db = DataBaseHandler(context)

        var user = intent.getSerializableExtra("User") as User? //Receive user from RegisterActivity.kt

        if (user != null) {
            Toast.makeText(this, "Hello " + user.fName, Toast.LENGTH_LONG).show()


            var data = db.readData(user.password)
            user = data[0]


            txtIDBallots.text = user.id.toString()
            txtFNameBallots.text = user.fName
            txtMNameBallots.text = user.mName
            txtLNameBallots.text = user.lName
            txtBDayBallots.text = user.bDay
            txtStateBallots.text = user.state
            txtZipBallots.text = user.zipCode.toString()
            txtPasswordBallots.text = user.password

        }
        else
        {
            Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
        }



    }
}