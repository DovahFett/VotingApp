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

        val user = intent.getSerializableExtra("User") as User? //Receive user from RegisterActivity.kt

        if (user != null) {
            Toast.makeText(this, "Hello " + user.fName, Toast.LENGTH_LONG).show()


            var data = db.readData(user)


            txtIDBallots.text = data[0].toString()//user.id.toString()
            txtFNameBallots.text = data[1].toString()//user.fName
            txtMNameBallots.text = data[2].toString()//user.mName
            txtLNameBallots.text = data[3].toString()//user.lName
            txtBDayBallots.text = data[4].toString()//user.bDay
            txtStateBallots.text = data[5].toString()//user.state
            txtZipBallots.text = data[6].toString()//user.zipCode.toString()
            txtPasswordBallots.text = data[7].toString()//user.password

        }
        else
        {
            Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
        }



    }
}