package com.nexte.nexte.MatchScene

import com.fasterxml.jackson.databind.deser.DataFormatReaders
import com.nexte.nexte.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.Request


class MatchModelTest {

    @Before
    fun setUp(){

    }

    @Test
    fun testMatchModel() {
        //prepare
        //call
        var testClass = MatchModel()

        //assert
        assertNotNull(testClass)
    }

    @Test
    fun testInitScene() {
        //prepare
        //call
        var testInit = MatchModel.InitScene()

        //assert
        assertNotNull(testInit)
    }

    @Test
    fun testRequest() {
        //prepare
        var identifierMatch = "abcd1234"

        //call
        var request = MatchModel.InitScene.Request(identifierMatch)

        //assert
        assertEquals(request.matchID, identifierMatch)
    }

    @Test
    fun testResponse() {
        //prepare
        var challengerResponse = MatchModel.MatchPlayer("Fiona", R.mipmap.ic_launcher)
        var challengedResponse = MatchModel.MatchPlayer("Marquinhos", R.mipmap.ic_launcher)
        var matchDataResponse = MatchModel.MatchData(challengedResponse, challengerResponse)
        //call
        var matchTest = MatchModel.InitScene.Response(matchDataResponse)

        //assert
        assertEquals(challengedResponse.name, matchTest.match.challenged.name )
        assertEquals(challengedResponse.photo, matchTest.match.challenged.photo)
        assertEquals(challengerResponse.name, matchTest.match.challenger.name)
        assertEquals(challengerResponse.photo, matchTest.match.challenger.photo)
    }

    @Test
    fun sucessMatchData() {
        //prepare
        var challenger = MatchModel.MatchPlayer(name = "Letícia", photo = R.mipmap.ic_launcher)
        var challenged = MatchModel.MatchPlayer(name = "Alexandre", photo = R.mipmap.ic_launcher)
        var enumSet = MatchModel.SetsNumber.Three

        //call
        var matchData = MatchModel.MatchData(challenger = challenger, challenged = challenged)

        //asserts

        assertEquals(challenger, matchData.challenger)
        assertEquals(challenged, matchData.challenged)

    }

    @Test
    fun formattedMatchData() {
        //prepare
        var challengerName = "Anduin"
        var challengerPhoto = 1
        var challengedName = "Jaina"
        var challengedPhoto = 2
        var numberSet = MatchModel.SetsNumber.One

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