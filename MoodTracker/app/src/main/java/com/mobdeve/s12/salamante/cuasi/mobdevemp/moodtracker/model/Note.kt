package com.mobdeve.s12.salamante.cuasi.mobdevemp.moodtracker.model

class Note(
    var mood: String?,
    var reason: String?,
    var location: String?,
    var date: String?,
    var id: Int
){
    constructor() : this("", "", "", "", -1)

    constructor(mood: String, reason: String, date: String,  id: Int)
            : this(mood, reason, "", date, id)

//    constructor(id: Int, fullName:String, password:String)
//            : this("", "", password, fullName, id)
}