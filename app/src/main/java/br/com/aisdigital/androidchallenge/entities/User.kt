package br.com.aisdigital.androidchallenge.entities

class User(
    val name: String,
    val age: Int,
    val gender: String,
    var email: String,
    var token: String?
)