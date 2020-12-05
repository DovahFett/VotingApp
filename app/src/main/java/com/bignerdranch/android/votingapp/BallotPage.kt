package com.bignerdranch.android.votingapp

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ballot_page.*

class BallotPage : AppCompatActivity()
{
    private lateinit var selectedRadioButton: RadioButton

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
            //Start database connection
            val context = this
            val db = DataBaseHandler(context)

            var ballotIDList : ArrayList<Int> = db.getBallotID(ballot)//Get ID associated with election name
            ballot.ballotID = ballotIDList[0]

            var ballotNameList : ArrayList<String> = db.getPositionName(ballot)//Get name of position on ballot
            ballot.positionName = ballotNameList[0]
            txtPositionName.text = ballot.positionName

            var candidateData : ArrayList<String> = db.getCandidates(ballot)
            if(candidateData.size != 0)
            {
                //Attach candidate info to radio buttons
                rButtonChoice1.text = candidateData[0]
                rButtonChoice2.text = candidateData[1]
            }

            btnSubmitBallot.setOnClickListener{
                val selectedRadioButtonId: Int = rGrpChoices.checkedRadioButtonId
                selectedRadioButton = findViewById(selectedRadioButtonId)
                val intent = Intent(this, BallotSummary::class.java)
                intent.putExtra("User", user)//Pass the user object
                intent.putExtra("ElectionDetails", ballot)
                intent.putExtra("Choice 1", selectedRadioButton.text)
                Toast.makeText(this, "Submission Complete", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }




        }
    }
}