package com.bignerdranch.android.votingapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

 val DATABASE_NAME = "VotingDB"
 var TABLE_NAME = "Users"
 val COL_ID = "ID"
 val COL_FNAME = "FirstName"
 val COL_MNAME = "MiddleName"
 val COL_LNAME = "LastName"
 val COL_BDAY = "DateOfBirth"
 val COL_STATE = "State"
 val COL_ZIPCODE = "ZIPCode"
 val COL_PASSWORD = "Password"


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
                "Status" + " VARCHAR(256)," +
                "DemocratVotes" + " INTEGER," +
                "RepublicanVotes" + " INTEGER," +
                "Winner" + " VARCHAR(256))"

        db?.execSQL(createBallotTable)

        //Create Candidate Details table
        val createCandidateDetailsTable = "CREATE TABLE " + "CandidateDetails" + " (" +
                COL_ID + " INTEGER NOT NULL PRIMARY KEY, " +
                "CandidateName" + " VARCHAR(256)," +
                "CandidateParty" + " VARCHAR(256)," +
                "BallotID" + " INTEGER)"

        db?.execSQL(createCandidateDetailsTable)

        //Create ballot and insert it
        var election1 = Ballot("2020 Presidential Election", "All", 0, "11/03/2020", "11/04/2020", 10)
        insertBallot(election1, db)
        //Create candidates and insert them
        var candidate1 = Candidate("Elijah Harrison", "Democrat", 10)
        insertCandidates(candidate1, db)
        var candidate2 = Candidate("Jeb Collins", "Republican", 10)
        insertCandidates(candidate2, db)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    //Insert new users
    fun insertUser(user : User)
    {
        var db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_FNAME, user.fName)
        cv.put(COL_MNAME, user.mName)
        cv.put(COL_LNAME, user.lName)
        cv.put(COL_BDAY, user.bDay)
        cv.put(COL_STATE, user.state)
        cv.put(COL_ZIPCODE, user.zipCode)
        cv.put(COL_PASSWORD, user.password)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Insert Succeeded", Toast.LENGTH_SHORT).show()
    }

    //insertBallot is called once at startup to place ballots in database.
    private fun insertBallot(election : Ballot, db: SQLiteDatabase?)
    {


        var cv = ContentValues()
        cv.put("ElectionName", election.electionName)
        cv.put(COL_STATE, election.state)
        cv.put(COL_ZIPCODE, election.zipCode)
        cv.put("StartDate", election.startDate)
        cv.put("EndDate", election.endDate)
        cv.put("BallotID", election.ballotID)
        cv.put("Status", "Open")
        cv.put("DemocratVotes", 0)
        cv.put("RepublicanVotes", 0)


        var result = db?.insert("Ballots", null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Insert Succeeded", Toast.LENGTH_SHORT).show()

    }
    //Called once to add example candidates to database
    private fun insertCandidates(candidate: Candidate, db : SQLiteDatabase?)
    {

        var cv = ContentValues()
        cv.put("CandidateName", candidate.candidateName)
        cv.put("CandidateParty", candidate.party)
        cv.put("BallotID", candidate.ballotID)

        var result = db?.insert("CandidateDetails", null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Insert Succeeded", Toast.LENGTH_SHORT).show()
    }
    //Retrieve user info
    fun readData(password : String) : MutableList<User>
    {
        var list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_PASSWORD + " = " + "'" + password + "'"//Get user with matching password
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
        {
            do{
                var user = User()
                user.id = result.getString(0).toInt()
                user.fName = result.getString(1)
                user.mName = result.getString(2)
                user.lName = result.getString(3)
                user.bDay = result.getString(4)
                user.state = result.getString(5)
                user.zipCode = result.getString(6).toInt()
                user.password = result.getString(7)
                list.add(user)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
    //Get list of all applicable ballots
    fun getBallots(zipCode : Int, state : String) : MutableList<Ballot>
    {
        var list: MutableList<Ballot> = ArrayList()
        val db = this.readableDatabase

        val query = "SELECT * FROM Ballots WHERE (ZIPCode = " + zipCode + " OR ZIPCode = 0) AND Status = 'Open' AND (State = " + "'" + state + "'" + " OR State = 'All')"

        val result = db.rawQuery(query,null)

        if(result.moveToFirst())
        {
            do
            {
               var ballot = Ballot()
               ballot.electionName = result.getString(1)
            }while(result.moveToNext())
        }
        result.close()
        db.close()
        return list

    }

    //Set user's password in database
    fun setPassword(user: User)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_PASSWORD, user.password)
        db.update("Users", cv, "FirstName=" + user.fName + " AND MiddleName= " + user.mName + " AND LastName=" + user.lName + " AND DateOfBirth=" + user.bDay + " AND State=" + user.state + " AND ZIPCode=" + user.zipCode, null)
    }
    //Get the user's ID from the database
    fun getID(user: User) : MutableList<User>
    {
        TABLE_NAME = "Users"

        var list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        //Get user with matching password
        val query = "SELECT " + COL_ID + " FROM " + TABLE_NAME + " WHERE " + COL_PASSWORD+ " = " + user.password //Get ID from row with matching password

        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
        {
            do{
                var user = User()
                //Set user object ID to ID found in column
                user.id = result.getString(0).toInt()
                list.add(user)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

}