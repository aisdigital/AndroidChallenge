package br.com.aisdigital.androidchallenge.domain

data class User (
    val name: String,
    val age: Int,
    val gender: Gender
)

enum class Gender{
    FEMALE, MALE
}