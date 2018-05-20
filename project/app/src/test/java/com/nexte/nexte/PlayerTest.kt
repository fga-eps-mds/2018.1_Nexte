package com.nexte.nexte

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class PlayerTest {

    @Before
    fun setUp() {
    }

    @Test
    fun successPlayer(){
        //prepare
        val name = "Luis Gustavo"
        val rankingPosition = 1
        val pictureAdress = ""
        val email = "luis@email.com"
        val gender = "Masculino"
        val club = "Tenis House"
        val age = 21
        val password = "123456"
        val category = "profissional"

        //call
        val player = Player(name = "Luis Gustavo", rankingPosition = 1, pictureAddress = "", email = "luis@email.com", gender = "Masculino",
                club = "Tenis House", age = 21, password = "123456", category = "profissional")

        //assert
        assertEquals(name, player.name)
        assertEquals(rankingPosition, player.rankingPosition)
        assertEquals(pictureAdress, player.pictureAddress)
        assertEquals(email, player.email)
        assertEquals(gender, player.gender)
        assertEquals(club, player.club)
        assertEquals(age, player.age)
        assertEquals(password, player.password)
        assertEquals(category, player.category)
    }

    @After
    fun tearDown() {
    }
}