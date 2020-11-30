package com.bignerdranch.android.votingapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ballot_page.*

class BallotPage : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ballot_page)

        var user = intent.getSerializableExtra("User") as User? //Receive user from RegisterActivity.kt
        var ballot = Ballot()
        ballot.electionName = intent.getSerializableExtra("Ballot") as String

        if (ballot != null)
        {
            Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show()
            txtElectionTitle.text = ballot.electionName
            txtPositionName.text = ballot.positionName

            val context = this
            val db = DataBaseHandler(context)

            db.getBallotID(ballot)//Get ID associated with election name


        }
    }
}