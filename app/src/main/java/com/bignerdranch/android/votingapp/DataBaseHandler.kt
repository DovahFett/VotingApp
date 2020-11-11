package com.bignerdranch.android.votingapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

 val DATABASE_NAME = "VotingDB"
 val TABLE_NAME = "Users"
 val COL_ID = "ID"
 val COL_FNAME = "FirstName"
 val COL_MNAME = "MiddleName"
 val COL_LNAME = "LastName"
 val COL_BDAY = "DateOfBirth"
 val COL_STATE = "State"
 val COL_ZIPCODE = "ZIPCode"
 val COL_FINGERPRINT = "Fingerprint"


class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1)
{
    override fun onCreate(db: SQLiteDatabase?)
    {
        //Create User table
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FNAME + " VARCHAR(256)," +
                COL_MNAME + " VARCHAR(256)," +
                COL_LNAME + " VARCHAR(256)," +
                COL_BDAY + " VARCHAR(256)," +
                COL_STATE + " VARCHAR(256)," +
                COL_ZIPCODE + " VARCHAR(256))";

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    //Insert new users
    fun insertUser(user : User)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_FNAME, user.fName)
        cv.put(COL_MNAME, user.mName)
        cv.put(COL_LNAME, user.lName)
        cv.put(COL_BDAY, user.bDay)
        cv.put(COL_STATE, user.state)
        cv.put(COL_ZIPCODE, user.zipCode)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Insert Succeeded", Toast.LENGTH_SHORT).show()
    }
    //Retrieve user info
    fun readData(user : User) : MutableList<User>
    {
        var list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ZIPCODE + " = " + user.fingerPrint//Get user with matching fingerprint
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
                list.add(user)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

}