package com.nexte.nexte.FeedScene

import com.nexte.nexte.R
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class FeedModelTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testCreateRequest(){
        //prepare // call
        val request = FeedModel.Request()

        //assert
        assertNotNull(request)
    }

    @Test
    fun testCreateResponse(){
        //preprare
        val challenger1 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 2)
        val challenged1 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val date = Date()
        val activity1 = FeedModel.FeedActivity(challenge = FeedModel.FeedChallenge(challenger = challenger1, challenged = challenged1, challengeDate = date), feedDate = date)

        //call
        val response = FeedModel.Response(feedActivities = arrayOf(activity1))

        //assert
        assertEquals(challenged1, response.feedActivities[0].challenge.challenged)
        assertEquals(challenger1, response.feedActivities[0].challenge.challenger)
        assertEquals(date, response.feedActivities[0].feedDate)
        assertEquals(response.feedActivities.size, 1)
    }

    @Test
    fun testCreateViewModel(){
        //prepare
        val challengerName = "Luis"
        val challengerPhoto = 1
        val challengerSets = "1/3"
        val challengedName = "Miguel"
        val challengedPhoto =  2
        val challengedSets = "2/3"
        val feedDate = "10/02/2018"
        val feedActivity = FeedModel.FeedActivityFormatted(challengerName = challengerName, challengerPhoto = challengerPhoto, challengerSets = challengerSets, challengedName = challengedName, challengedPhoto = challengedPhoto, challengedSets = challengedSets, feedDate = feedDate)
        val feedActivityFormated = listOf(feedActivity)

        //call
        val viewModel = FeedModel.ViewModel(feedActivities = feedActivityFormated)

        //assert
        assertEquals(challengerName, viewModel.feedActivities[0].challengerName)
        assertEquals(challengerPhoto, viewModel.feedActivities[0].challengerPhoto)
        assertEquals(challengerSets, viewModel.feedActivities[0].challengerSets)
        assertEquals(challengedName, viewModel.feedActivities[0].challengedName)
        assertEquals(challengedPhoto, viewModel.feedActivities[0].challengedPhoto)
        assertEquals(challengedSets, viewModel.feedActivities[0].challengedSets)
        assertEquals(feedDate, viewModel.feedActivities[0].feedDate)
        assertEquals(1, viewModel.feedActivities.size)
    }

    @Test
    fun testCreateFeedPlayer(){
        //prepare
        val name = "Luis"
        val photo = 1
        val set =  2

        //call
        val feedPlayer = FeedModel.FeedPlayer(name = name, photo = photo, set = set)

        //assert
        assertEquals(name, feedPlayer.name)
        assertEquals(photo, feedPlayer.photo)
        assertEquals(set, feedPlayer.set)
    }

    @Test
    fun testCreateFeedChallenge(){
       //prepare
        val challenger = FeedModel.FeedPlayer(name = "Luis", photo = 1, set = 2)
        val challenged =  FeedModel.FeedPlayer(name = "Miguel", photo = 2, set = 1)
        val challengeDate = Date()

        //call
        val feedChallenge = FeedModel.FeedChallenge(challenger = challenger, challenged = challenged, challengeDate = challengeDate)

        //assert
        assertEquals(challenger, feedChallenge.challenger)
        assertEquals(challenged, feedChallenge.challenged)
        assertEquals(challengeDate, feedChallenge.challengeDate)
    }

    @Test
    fun createFeedActivity(){
        //prepare
        val challenger = FeedModel.FeedPlayer(name = "Luis", photo = 1, set = 2)
        val challenged =  FeedModel.FeedPlayer(name = "Miguel", photo = 2, set = 1)
        val challengeDate = Date()
        val challenge =  FeedModel.FeedChallenge(challenger = challenger, challenged = challenged, challengeDate = challengeDate)
        val feedDate = Date()

        //call
        val feedActivity = FeedModel.FeedActivity(challenge = challenge, feedDate = feedDate)

        //assert
        assertEquals(challenge, feedActivity.challenge)
        assertEquals(feedDate, feedActivity.feedDate)
    }

    @Test
    fun feedActivityFormatted(){
        //prepare
        val challengerName =  "Luis"
        val challengerPhoto = 1
        val challengerSets = "1"
        val challengedName = "Miguel"
        val challengedPhoto =  2
        val challengedSets = "3"
        val feedDate =  "10/02/2018"

        //call
        val feedActivityFormatted = FeedModel.FeedActivityFormatted(challengerName = challengerName, challengerPhoto = challengerPhoto, challengerSets = challengerSets, challengedName = challengedName, challengedPhoto = challengedPhoto, challengedSets =  challengedSets, feedDate = feedDate)

        //assert
        assertEquals(challengerName, feedActivityFormatted.challengerName)
        assertEquals(challengerPhoto, feedActivityFormatted.challengerPhoto)
        assertEquals(challengerSets, feedActivityFormatted.challengerSets)
        assertEquals(challengedName, feedActivityFormatted.challengedName)
        assertEquals(challengedPhoto, feedActivityFormatted.challengedPhoto)
        assertEquals(challengedSets, feedActivityFormatted.challengedSets)
        assertEquals(feedDate, feedActivityFormatted.feedDate)
    }

    @After
    fun tearDown() {
    }
}