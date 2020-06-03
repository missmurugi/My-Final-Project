package com.example.myfinalproject

class UserSignUp {
    var fullname:String = ""
    var username:String = ""
    var email: String = ""
    var password:String = ""
    var confirmpassword:String = ""

    constructor(fullname:String,username:String,email:String,password:String,confirmpassword:String){
        this.fullname = fullname
        this.username = username
        this.email = email
        this.password = password
        this.confirmpassword = confirmpassword
    }

    constructor(){}

}