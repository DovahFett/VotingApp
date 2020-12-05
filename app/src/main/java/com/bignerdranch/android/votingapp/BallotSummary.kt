package com.bignerdranch.android.votingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ballot_summary.*


class BallotSummary : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ballot_summary)

        var ballot = intent.getSerializableExtra("ElectionDetails") as Ballot?
        //Creates an array to hold the choices made in the previously accessed ballot.
        var ballotChoices = ArrayList<String>()
        ballotChoices.add(intent.getSerializableExtra("Choice 1") as String)

        if (ballot != null)
        {
            lblChoice1.text = getString(R.string.lblChoice1, ballot.positionName, ballotChoices[0]);
        }
    }
}
