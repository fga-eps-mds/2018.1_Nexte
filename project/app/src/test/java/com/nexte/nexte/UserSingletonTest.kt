package com.nexte.nexte

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class UserSingletonTest {

    var playerInfo: Player? = null

    @Before
    fun setUp() {
        this.playerInfo = UserSingleton.getUserInformations()
    }


    @Test
    fun testUserInformationsSet(){
        //prepare
        val userInformations: Player = Player("luis",
                1,
                "imgur.com/asdnkjadsn",
                "aksjdb@gmail.com",
                "masculino123",
                "akjsdb",
                21,
                "alsjndajks")

        //call
        UserSingleton.setUserInformations(userInformations = userInformations)
        val userInfo = UserSingleton.getUserInformations()

        //assert
        assertEquals(userInformations.name, userInfo.name)
        assertEquals(userInformations.rankingPosition, userInfo.rankingPosition)
        assertEquals(userInformations.pictureAddress, userInfo.pictureAddress)
        assertEquals(userInformations.email, userInfo.email)
        assertEquals(userInformations.gender, userInfo.gender)
        assertEquals(userInformations.club, userInfo.club)
        assertEquals(userInformations.age, userInfo.age)
        assertEquals(userInformations.password, userInfo.password)
    }

    @Test
    fun testUserInformationsGet(){
        //prepare
        val userInformations: Player = this.playerInfo!!

        //call
        val userInfo = UserSingleton.getUserInformations()

        //assert
        assertEquals(userInformations.name, userInfo.name)
        assertEquals(userInformations.rankingPosition, userInfo.rankingPosition)
        assertEquals(userInformations.pictureAddress, userInfo.pictureAddress)
        assertEquals(userInformations.email, userInfo.email)
        assertEquals(userInformations.gender, userInfo.gender)
        assertEquals(userInformations.club, userInfo.club)
        assertEquals(userInformations.age, userInfo.age)
        assertEquals(userInformations.password, userInfo.password)
    }

    @After
    fun tearDown() {
        UserSingleton.setUserInformations(userInformations = this.playerInfo!!)
    }
}