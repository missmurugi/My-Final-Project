package com.example.myfinalproject

class UserContact {

    var name:String = ""
    var email:String = ""
    var number: String = ""
    var message:String = ""


    constructor(name:String,email:String,number:String,message:String){
        this.name = name
        this.email = email
        this.number = number
        this.message = message

    }

    constructor(){}
}