package com.bignerdranch.android.votingapp

import java.io.Serializable
//Ballot object that hold information about ballots
class Ballot : Serializable
{
    var id : Int = 0
    var electionName : String = ""
    var positionName : String = ""
    var state : String = ""
    var zipCode : Int = 0
    var startDate : String = ""
    var endDate : String = ""
    var ballotID : Int = 0
    var status : String = ""
    var democratVotes : Int = 0
    var republicanVotes : Int = 0
    var winner : String = ""



    constructor(electionName : String, state : String, zipCode : Int, startDate : String, endDate : String, ballotID : Int, positionName : String)
    {
        this.electionName = electionName
        this.positionName = positionName
        this.state = state
        this.zipCode = zipCode
        this.startDate = startDate
        this.endDate = endDate
        this.ballotID = ballotID

    }

    constructor()
    {

    }



}