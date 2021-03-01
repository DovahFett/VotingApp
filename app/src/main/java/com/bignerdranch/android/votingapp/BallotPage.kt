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

        val user = intent.getSerializableExtra("User") as User? //Receive user from RegisterActivity.kt
        val ballot = Ballot()
        ballot.electionName = intent.getSerializableExtra("Ballot") as String

        Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show()
        txtElectionTitle.text = ballot.electionName
        //Start database connection
        val context = this
        val db = DataBaseHandler(context)

        val ballotIDList : ArrayList<Int> = db.getBallotID(ballot)//Get ID associated with election name
        ballot.ballotID = ballotIDList[0]

        val ballotNameList : ArrayList<String> = db.getPositionName(ballot)//Get name of position on ballot
        ballot.positionName = ballotNameList[0]
        txtPositionName.text = ballot.positionName

        val candidateData : ArrayList<String> = db.getCandidates(ballot)

        //Create radio buttons
        for (i in 0 until candidateData.size)
        {
            val radioButton = RadioButton(this)
            radioButton.text = candidateData[i]
            radioButton.id = i
            rGrpChoices.addView(radioButton)
        }

        //set listener to radio button group
        /*rGrpChoices.setOnCheckedChangeListener { _, _ ->
            val checkedRadioButtonId: Int = rGrpChoices.checkedRadioButtonId
            val radioBtn = findViewById<View>(checkedRadioButtonId) as RadioButton
            Toast.makeText(this, radioBtn.text, Toast.LENGTH_SHORT).show()
        }*/

        /*if(candidateData.size != 0)
        {
            //Attach candidate info to radio buttons
            rButtonChoice1.text = candidateData[0]
            rButtonChoice2.text = candidateData[1]
        }*/



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