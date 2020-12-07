package com.bignerdranch.android.votingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.ballot_summary.*



class BallotSummary : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ballot_summary)

        //Make database connection
        val context = this
        val db = DataBaseHandler(context)

        val ballot = intent.getSerializableExtra("ElectionDetails") as Ballot?
        val user = intent.getSerializableExtra("User") as User
        //Creates an array to hold the choices made in the previously accessed ballot.
        val ballotChoices = ArrayList<String>()
        ballotChoices.add(intent.getSerializableExtra("Choice 1") as String)

        if (ballot != null)
        {
            if(ballotChoices.size == 1)
            {
                txtPosition1.text = getString(R.string.txtPosition1, "Position:", ballot.positionName)
                txtChoice1.text = getString(R.string.txtChoice1, "Choice:", ballotChoices[0])
                txtPosition2.text = getString(R.string.txtPosition2, "", "")
                txtChoice2.text = getString(R.string.txtChoice2, "", "")
                txtPosition3.text = getString(R.string.txtPosition3, "", "")
                txtChoice3.text = getString(R.string.txtChoice3, "", "")
                txtPosition4.text = getString(R.string.txtPosition4, "", "")
                txtChoice4.text = getString(R.string.txtChoice4, "", "")
            }
            if(ballotChoices.size == 2)
            {
                txtPosition1.text = getString(R.string.txtPosition1, "Position:", ballot.positionName)
                txtChoice1.text = getString(R.string.txtChoice1, "Choice:", ballotChoices[0])
                txtPosition2.text = getString(R.string.txtPosition2, "Position:", ballot.positionName)
                txtChoice2.text = getString(R.string.txtChoice2, "Choice:", ballotChoices[1])
                txtPosition3.text = getString(R.string.txtPosition3, "", "")
                txtChoice3.text = getString(R.string.txtChoice3, "", "")
                txtPosition4.text = getString(R.string.txtPosition4, "", "")
                txtChoice4.text = getString(R.string.txtChoice4, "", "")
            }
            if(ballotChoices.size == 3)
            {
                txtPosition1.text = getString(R.string.txtPosition1, "Position:", ballot.positionName)
                txtChoice1.text = getString(R.string.txtChoice1, "Choice:", ballotChoices[0])
                txtPosition2.text = getString(R.string.txtPosition2, "Position:", ballot.positionName)
                txtChoice2.text = getString(R.string.txtChoice2, "Choice:", ballotChoices[1])
                txtPosition3.text = getString(R.string.txtPosition3, "Position:", ballot.positionName)
                txtChoice3.text = getString(R.string.txtChoice3, "Choice:", ballotChoices[2])
                txtPosition4.text = getString(R.string.txtPosition4, "", "")
                txtChoice4.text = getString(R.string.txtChoice4, "", "")
            }
            if(ballotChoices.size == 4)
            {
                txtPosition1.text = getString(R.string.txtPosition1, "Position:", ballot.positionName)
                txtChoice1.text = getString(R.string.txtChoice1, "Choice:", ballotChoices[0])
                txtPosition2.text = getString(R.string.txtPosition2, "Position:", ballot.positionName)
                txtChoice2.text = getString(R.string.txtChoice2, "Choice:", ballotChoices[1])
                txtPosition3.text = getString(R.string.txtPosition3, "Position:", ballot.positionName)
                txtChoice3.text = getString(R.string.txtChoice3, "Choice:", ballotChoices[2])
                txtPosition4.text = getString(R.string.txtPosition4, "Position:", ballot.positionName)
                txtChoice4.text = getString(R.string.txtChoice4, "Choice:", ballotChoices[3])
            }

            btnBallotSummary.setOnClickListener{
                ballot.status = "Closed"
                val intent = Intent(this, BallotList::class.java)

                System.out.println("Hello There : " + ballotChoices[0])


                if(ballotChoices[0].contains("Democrat"))
                {
                    db.addVote(ballot, "Democrat")
                    db.closeVoteToUser(ballot, user)
                    System.out.println("ballot.electionName = " + ballot.electionName)
                    System.out.println("ballot.BallotID = " + ballot.ballotID)
                }
                else if(ballotChoices[0].contains("Republican"))
                {
                    db.addVote(ballot, "Republican")
                    db.closeVoteToUser(ballot, user)
                    System.out.println("ballot.electionName = " + ballot.electionName)
                    System.out.println("ballot.BallotID = " + ballot.ballotID)
                }


                intent.putExtra("User", user)//Pass the user object
                intent.putExtra("ElectionDetails", ballot)

                Toast.makeText(this, "Confirmed", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }


    }
}
