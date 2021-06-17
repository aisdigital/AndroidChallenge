package br.com.aisdigital.androidchallenge.model

//TODO: Make this class generic
sealed class Option {

    data class SomeTeam(val value: List<Team>) : Option ()

    data class SomeUser(val value: User) : Option ()

    data class None(val errorId: Int) : Option()
}