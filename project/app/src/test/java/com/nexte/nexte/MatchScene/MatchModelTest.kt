package com.nexte.nexte.MatchScene

import com.nexte.nexte.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MatchModelTest {

    @Before
    fun setUp(){
    }

    @Test
    fun testMatchModel() {
        //prepare
        //call
        val testClass = MatchModel()

        //assert
        assertNotNull(testClass)
    }

    @Test
    fun testInitScene() {
        //prepare
        //call
        val testInit = MatchModel.InitScene()

        //assert
        assertNotNull(testInit)
    }

    @Test
    fun testRequest() {
        //prepare
        val matchData  = MatchModel.MatchData(
        MatchModel.MatchPlayer("larissa", 1),
        MatchModel.MatchPlayer("larissa2", 1))

        //call
        val request = MatchModel.InitScene.Request(matchData)

        //assert
        assertEquals(request.match, matchData)
    }

    @Test
    fun testResponse() {
        //prepare
        val challengerResponse = MatchModel.MatchPlayer("Fiona", R.mipmap.ic_launcher)
        val challengedResponse = MatchModel.MatchPlayer("Marquinhos", R.mipmap.ic_launcher)
        val matchDataResponse = MatchModel.MatchData(challengedResponse, challengerResponse)
        //call
        val matchTest = MatchModel.InitScene.Response(matchDataResponse)

        //assert
        assertEquals(challengedResponse.name, matchTest.match.challenged.name )
        assertEquals(challengedResponse.photo, matchTest.match.challenged.photo)
        assertEquals(challengerResponse.name, matchTest.match.challenger.name)
        assertEquals(challengerResponse.photo, matchTest.match.challenger.photo)
    }

    @Test
    fun sucessMatchData() {
        //prepare
        val challenger = MatchModel.MatchPlayer(name = "Letícia", photo = R.mipmap.ic_launcher)
        val challenged = MatchModel.MatchPlayer(name = "Alexandre", photo = R.mipmap.ic_launcher)

        //call
        val matchData = MatchModel.MatchData(challenger = challenger, challenged = challenged)

        //asserts

        assertEquals(challenger, matchData.challenger)
        assertEquals(challenged, matchData.challenged)
    }

    @Test
    fun formattedMatchData() {
        //prepare
        val challengerName = "Anduin"
        val challengerPhoto = 1
        val challengedName = "Jaina"
        val challengedPhoto = 2

        //call
        val formattedMatchData = MatchModel.FormattedMatchData(challengerName = challengerName, challengerPhoto = challengerPhoto,
                                                                challengedName = challengedName, challengedPhoto = challengedPhoto)

        //asserts
        assertEquals(challengerName,formattedMatchData.challengerName)
        assertEquals(challengerPhoto, formattedMatchData.challengerPhoto)
        assertEquals(challengedName, formattedMatchData.challengedName)
        assertEquals(challengedPhoto, formattedMatchData.challengedPhoto)
    }

    @Test
    fun testMatchPlayer() {
        //prepare
        val name = "Rexxar"
        val photo = 3

        //call
        val testMatchPlayer = MatchModel.MatchPlayer(name =  "Rexxar", photo = 3)

        //asserts
        assertEquals(name, testMatchPlayer.name)
        assertEquals(photo, testMatchPlayer.photo)
    }
}