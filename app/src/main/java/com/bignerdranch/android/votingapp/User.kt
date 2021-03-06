package com.bignerdranch.android.votingapp

import org.mindrot.jbcrypt.BCrypt
import java.io.Serializable

//User object that is used to hold user information
class User : Serializable //Serializable object that can be passed between activities
{
    var id: Int = 0
    var fName : String = ""
    var mName : String = ""
    var lName : String = ""
    var bDay : String = ""
    var state : String = ""
    var zipCode : Int = 0
    var password : String = ""
    var passwordHash : String = ""


    constructor(fName : String, mName : String, lName : String, bDay : String, state : String, zipCode : Int)
    {
        this.fName = fName
        this.mName = mName
        this.lName = lName
        this.bDay = bDay
        this.state = state
        this.zipCode = zipCode

    }

    constructor()

    fun setUserPassword(password: String)
    {
        this.password = password
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(10))
    }


}