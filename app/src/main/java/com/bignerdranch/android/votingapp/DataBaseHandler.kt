package com.bignerdranch.android.votingapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

const val DATABASE_NAME = "VotingDB"
 var TABLE_NAME = "Users"
 const val COL_ID = "ID"
 const val COL_FNAME = "FirstName"
 const val COL_MNAME = "MiddleName"
 const val COL_LNAME = "LastName"
 const val COL_BDAY = "DateOfBirth"
 const val COL_STATE = "State"
 const val COL_ZIPCODE = "ZIPCode"
 const val COL_PASSWORD = "Password"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1)
{
    override fun onCreate(db: SQLiteDatabase?)
    {
        //Create User table
        val createUserTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                COL_FNAME + " VARCHAR(256)," +
                COL_MNAME + " VARCHAR(256)," +
                COL_LNAME + " VARCHAR(256)," +
                COL_BDAY + " VARCHAR(256)," +
                COL_STATE + " VARCHAR(256)," +
                COL_ZIPCODE + " VARCHAR(256)," +
                COL_PASSWORD + " VARCHAR(256))"


        db?.execSQL(createUserTable)

        //Create Ballot table
        val createBallotTable = "CREATE TABLE " + "Ballots" + " (" +
                COL_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                "ElectionName" + " VARCHAR(256)," +
                COL_STATE + " VARCHAR(256)," +
                COL_ZIPCODE + " VARCHAR(256)," +
                "StartDate" + " VARCHAR(256)," +
                "EndDate" + " VARCHAR(256)," +
                "BallotID" + " INTEGER," +
                "PositionName" + " VARCHAR(256)," +
                "Status" + " VARCHAR(256)," +
                "DemocratVotes" + " INTEGER," +
                "RepublicanVotes" + " INTEGER," +
                "Winner" + " VARCHAR(256) DEFAULT 'None')"

        db?.execSQL(createBallotTable)

        //Create Candidate Details table
        val createCandidateDetailsTable = "CREATE TABLE " + "CandidateDetails" + " (" +
                COL_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                "CandidateName" + " VARCHAR(256)," +
                "CandidateParty" + " VARCHAR(256)," +
                "BallotID" + " INTEGER)"

        db?.execSQL(createCandidateDetailsTable)

        val createVotesTakenTable = "CREATE TABLE " + "SubmittedVotes" + " (" +
                COL_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                "VoterID" + " INTEGER, " +
                "BallotID" + " INTEGER," +
                "BallotStatus" + " VARCHAR(256))"

        db?.execSQL(createVotesTakenTable)

        //Create ballot and insert it
        val election1 = Ballot("2020 Presidential Election", "All", 0, "11/03/2020", "11/04/2020", 10, "President of the United States")
        insertBallot(election1, db)
        //Create candidates and insert them
        val candidate1 = Candidate("Elijah Harrison", "Democrat", 10)
        insertCandidates(candidate1, db)
        val candidate2 = Candidate("Jeb Collins", "Republican", 10)
        insertCandidates(candidate2, db)

        val election2 = Ballot("Test Election", "All", 0, "02/28/2021", "03/07/2021", 11, "House Representative")
        insertBallot(election2, db)
        val candidate3 = Candidate("Sarah Maxson", "Democrat", 11)
        insertCandidates(candidate3, db)
        val candidate4 = Candidate("James Doran", "Republican", 11)
        insertCandidates(candidate4, db)
        val candidate5 = Candidate("Frank Jorgan", "Libertarian", 11)
        insertCandidates(candidate5, db)
        val candidate6 = Candidate("Sarah Maxson", "Democrat", 11)
        insertCandidates(candidate6, db)
        val candidate7 = Candidate("James Doran", "Republican", 11)
        insertCandidates(candidate7, db)
        val candidate8 = Candidate("Frank Jorgan", "Libertarian", 11)
        insertCandidates(candidate8, db)
        val candidate9 = Candidate("Sarah Maxson", "Democrat", 11)
        insertCandidates(candidate9, db)
        val candidate10 = Candidate("James Doran", "Republican", 11)
        insertCandidates(candidate10, db)
        val candidate11 = Candidate("Frank Jorgan", "Libertarian", 11)
        insertCandidates(candidate11, db)
        val candidate12 = Candidate("Sarah Maxson", "Democrat", 11)
        insertCandidates(candidate12, db)
        val candidate13 = Candidate("James Doran", "Republican", 11)
        insertCandidates(candidate13, db)
        val candidate14 = Candidate("Frank Jorgan", "Libertarian", 11)
        insertCandidates(candidate14, db)
        val candidate15 = Candidate("Sarah Maxson", "Democrat", 11)
        insertCandidates(candidate15, db)
        val candidate16 = Candidate("James Doran", "Republican", 11)
        insertCandidates(candidate16, db)
        val candidate17 = Candidate("Frank Jorgan", "Libertarian", 11)
        insertCandidates(candidate17, db)
        val candidate18 = Candidate("Sarah Maxson", "Democrat", 11)
        insertCandidates(candidate18, db)
        val candidate19 = Candidate("James Doran", "Republican", 11)
        insertCandidates(candidate19, db)
        val candidate20 = Candidate("Frank Jorgan", "Libertarian", 11)
        insertCandidates(candidate20, db)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    //Insert new users
    fun insertUser(user : User)
    {
        val db = this.writableDatabase
        val encryptedPassword = AESEncryption.encrypt(user.password) //encrypt password
        val cv = ContentValues()
        cv.put(COL_FNAME, user.fName)
        cv.put(COL_MNAME, user.mName)
        cv.put(COL_LNAME, user.lName)
        cv.put(COL_BDAY, user.bDay)
        cv.put(COL_STATE, user.state)
        cv.put(COL_ZIPCODE, user.zipCode)
        cv.put(COL_PASSWORD, encryptedPassword)
        //cv.put(COL_PASSWORD, user.passwordHash)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Insert Succeeded", Toast.LENGTH_SHORT).show()
    }

    //insertBallot is called once at startup to place ballots in database.
    private fun insertBallot(election : Ballot, db: SQLiteDatabase?)
    {
        val cv = ContentValues()
        cv.put("ElectionName", election.electionName)
        cv.put(COL_STATE, election.state)
        cv.put(COL_ZIPCODE, election.zipCode)
        cv.put("StartDate", election.startDate)
        cv.put("EndDate", election.endDate)
        cv.put("BallotID", election.ballotID)
        cv.put("PositionName", election.positionName)
        cv.put("Status", "Open")
        cv.put("DemocratVotes", 0)
        cv.put("RepublicanVotes", 0)

        val result = db?.insert("Ballots", null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Insert Succeeded", Toast.LENGTH_SHORT).show()
    }

    //Called once to add example candidates to database
    private fun insertCandidates(candidate: Candidate, db : SQLiteDatabase?)
    {

        val cv = ContentValues()
        cv.put("CandidateName", candidate.candidateName)
        cv.put("CandidateParty", candidate.party)
        cv.put("BallotID", candidate.ballotID)

        val result = db?.insert("CandidateDetails", null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Insert Succeeded", Toast.LENGTH_SHORT).show()
    }
    //Retrieve user info
    fun readData(password : String): MutableList<User> {
        val list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val returnedUser = User()
        val decryptedPassword = AESEncryption.encrypt(password)
        val query =
            "SELECT * FROM $TABLE_NAME WHERE $COL_PASSWORD = '$decryptedPassword'"//Get user with matching password
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
        {
            do{

                returnedUser.id = result.getString(0).toInt()
                returnedUser.fName = result.getString(1)
                returnedUser.mName = result.getString(2)
                returnedUser.lName = result.getString(3)
                returnedUser.bDay = result.getString(4)
                returnedUser.state = result.getString(5)
                returnedUser.zipCode = result.getString(6).toInt()
                returnedUser.password = AESEncryption.decrypt(result.getString(7)).toString()
                list.add(returnedUser)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun getBallotNames(zipCode : Int, state : String, user : User) : ArrayList<String>
    {
        val userID = user.id
        val ballotNames = ArrayList<String>()
        val listOfTakenBallots = ArrayList<String>()
        val db = this.readableDatabase


        val query = "SELECT * FROM Ballots LEFT JOIN SubmittedVotes ON SubmittedVotes.BallotID = Ballots.BallotID WHERE (ZIPCode = $zipCode OR ZIPCode = 0) AND Status = 'Open' AND (State = '$state' OR State = 'All') AND SubmittedVotes.VoterID = $userID"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
        {
            do{
                val nameOfTakenBallot = result.getString(1) //Get names of all ballots that have been taken
                listOfTakenBallots.add(nameOfTakenBallot)
            }while(result.moveToNext())
        }
        result.close()

        val query2 = "SELECT * FROM Ballots WHERE (ZIPCode = $zipCode OR ZIPCode = 0) AND Status = 'Open' AND (State = '$state' OR State = 'All')"
        val result2 = db.rawQuery(query2, null)
        if(result2.moveToFirst())
        {
            do{
                val nameOfBallot = result2.getString(1) //Get list of all ballot names
                ballotNames.add(nameOfBallot)
            }while(result2.moveToNext())
        }
        result2.close()

        for(i in listOfTakenBallots)
        {
            if(ballotNames.contains(i))
            {
                ballotNames.remove(i)
            }
        }
        db.close()
        return ballotNames
    }

    //Submits a voter's ballot
    fun addVote(ballot: Ballot, voterChoice: String)
    {
        val db = this.writableDatabase
        if(voterChoice == "Democrat")
        {
            //Increment vote count by 1
            val query = "UPDATE Ballots SET DemocratVotes = (DemocratVotes + 1) WHERE ElectionName = " + "'" + ballot.electionName + "'"
            db.execSQL(query)
        }
        else if(voterChoice == "Republican")
        {
            val query = "UPDATE Ballots SET RepublicanVotes = (RepublicanVotes + 1) WHERE ElectionName = " + "'" + ballot.electionName + "'"
            db.execSQL(query)
        }

        db.close()
    }

    //Prevents voter from voting in same election more than once
    fun closeVoteToUser(ballot: Ballot, user: User)
    {
        val db = this.writableDatabase

        //Insert the VoterID, BallotID, and status of the ballot into the table
        val query = "INSERT INTO SubmittedVotes (VoterID, BallotID, BallotStatus) VALUES (" + user.id + ", " + ballot.ballotID + ", " + "'" + ballot.status + "')"
        db.execSQL(query)
        db.close()
    }


    //Get the ID associated with the election
    fun getBallotID(ballot : Ballot) : ArrayList<Int>
    {
        val ballotIDArray = ArrayList<Int>()
        val db = this.readableDatabase
        val query = "SELECT BallotID FROM Ballots WHERE ElectionName = " + "'" + ballot.electionName + "'"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
        {
            do
            {
                val ballotIDResult = result.getString(0).toInt()
                ballotIDArray.add(ballotIDResult)
            }while(result.moveToNext())
        }
        result.close()
        db.close()
        return ballotIDArray

    }

    //Get the name of the position the election is for
    fun getPositionName(ballot : Ballot) : ArrayList<String>
    {
        val electionNameArray = ArrayList<String>()
        val db = this.readableDatabase
        val query = "SELECT PositionName FROM Ballots WHERE ElectionName = " + "'" + ballot.electionName + "'"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
        {
            do
            {
                val electionNameResult = result.getString(0)
                electionNameArray.add(electionNameResult)
            }while(result.moveToNext())
        }
        result.close()
        db.close()
        return electionNameArray

    }

    fun getCandidates(ballot: Ballot) : ArrayList<String>
    {
        val candidateDetails = ArrayList<String>()
        val db = this.readableDatabase
        val query = "SELECT * FROM CandidateDetails WHERE BallotID = " + ballot.ballotID
        val result = db.rawQuery(query,null)
        if(result.moveToFirst())
        {
            do {

                val candidateInfo = result.getString(1) + " - " + result.getString(2)//Append name and part into one string
                candidateDetails.add(candidateInfo)

            }while(result.moveToNext())
        }
        result.close()
        db.close()
        return candidateDetails
    }

}