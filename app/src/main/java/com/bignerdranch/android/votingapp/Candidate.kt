package com.bignerdranch.android.votingapp

//Candidate object that holds candidate details
class Candidate
{
    var id : Int = 0
    var candidateName : String = ""
    var party : String = ""
    var ballotID : Int = 0

    constructor(candidateName : String, party : String, ballotID : Int)
    {
        this.candidateName = candidateName
        this.party = party
        this.ballotID = ballotID
    }

    constructor()
    {

    }



}