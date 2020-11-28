package com.bignerdranch.android.votingapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BallotPage : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ballot_page)

        var user = intent.getSerializableExtra("User") as User? //Receive user from RegisterActivity.kt
        var ballot = intent.getSerializableExtra("Ballot") as Ballot?

        if (ballot != null)
        {
            Toast.makeText(this, ballot.electionName, Toast.LENGTH_LONG).show()
        }
    }
}