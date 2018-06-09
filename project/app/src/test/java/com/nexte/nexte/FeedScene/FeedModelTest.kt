package com.nexte.nexte.FeedScene

import com.nexte.nexte.Entities.Story.Story
import com.nexte.nexte.Entities.Story.StoryPlayer
import com.nexte.nexte.FeedScene.FeedModel.GetFeedActivities.ViewModel
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
    fun testCreateRequestGetFeedActivities(){
        //prepare // call
        val request = FeedModel.GetFeedActivities.Request()

        //assert
        assertNotNull(request)
    }

    @Test
    fun testCreateResponseGetFeedActivities(){
        // Prepare
        val id = "1234567890"
        val winner = StoryPlayer("1", 0)
        val loser = StoryPlayer("2", 1)
        val date = Date()
        val likes = listOf("1", "2", "3")
        val comments = listOf("1", "2", "3")

        // Call
        val story = Story(id, winner, loser, date, comments, likes)
        val response = FeedModel.GetFeedActivities.Response(listOf(story))
        response.feedActivities = listOf((story))

        //assert
        assertEquals(id, response.feedActivities[0].id)
        assertEquals(winner, response.feedActivities[0].winner)
        assertEquals(date, response.feedActivities[0].date)
        assertEquals(loser, response.feedActivities[0].loser)
        assertEquals(likes, response.feedActivities[0].likesId)
        assertEquals(1, response.feedActivities.size)
        assertEquals(comments, response.feedActivities[0].commentsId)
    }

    @Test
    fun testCreateViewModelGetFeedActivities(){
        //prepare
        val identifier = "id"
        val challengerName = "Luis"
        val challengerPhoto = 1
        val challengerSets = "1/3"
        val challengedName = "Miguel"
        val challengedPhoto =  2
        val challengedSets = "2/3"
        val feedDate = "10/02/2018"
        val numberOfLikes = "1"
        val currentUserLiked = false
        val feedActivityFormated = FeedModel.FeedActivityFormatted(challengerName = challengerName,
                challengerPhoto = challengerPhoto, challengerSets = challengerSets, challengedName = challengedName,
                challengedPhoto = challengedPhoto, challengedSets = challengedSets, feedDate = feedDate,
                identifier = identifier, numberOfLikes = numberOfLikes, currentUserLiked = currentUserLiked)

        //call
        val viewModel = ViewModel(feedActivities = mutableListOf(feedActivityFormated))
        viewModel.feedActivities = mutableListOf(feedActivityFormated)

        //assert
        assertEquals(challengerName, viewModel.feedActivities[0].challengerName)
        assertEquals(challengerPhoto, viewModel.feedActivities[0].challengerPhoto)
        assertEquals(challengerSets, viewModel.feedActivities[0].challengerSets)
        assertEquals(challengedName, viewModel.feedActivities[0].challengedName)
        assertEquals(challengedPhoto, viewModel.feedActivities[0].challengedPhoto)
        assertEquals(challengedSets, viewModel.feedActivities[0].challengedSets)
        assertEquals(feedDate, viewModel.feedActivities[0].feedDate)
        assertEquals(identifier, viewModel.feedActivities[0].identifier)
        assertEquals(numberOfLikes, viewModel.feedActivities[0].numberOfLikes)
        assertEquals(1, viewModel.feedActivities.size)
        assertEquals(currentUserLiked, viewModel.feedActivities[0].currentUserLiked)
    }

    @Test
    fun testCreateFeedPlayer(){
        //prepare
        val name = "Luis"
        val photo = 1
        val set =  2

        //call
        val feedPlayer = FeedModel.FeedPlayer(name = name, photo = photo, set = set)
        feedPlayer.photo = photo
        feedPlayer.set = set
        feedPlayer.name = name
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
        val feedChallenge = FeedModel.FeedChallenge(challenger = challenger,
                challenged = challenged, challengeDate = challengeDate)
        feedChallenge.challengeDate = challengeDate
        feedChallenge.challenged = challenged
        feedChallenge.challenger = challenger

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
        val identifier = "id"
        val likes = mutableListOf(challenger)

        //call
        val feedActivity = FeedModel.FeedActivity(challenge = challenge, feedDate = feedDate, identifier = identifier, likes = likes)
        feedActivity.challenge = challenge
        feedActivity.feedDate = feedDate
        feedActivity.identifier = identifier
        feedActivity.likes = likes

        //assert
        assertEquals(challenge, feedActivity.challenge)
        assertEquals(feedDate, feedActivity.feedDate)
        assertEquals(identifier, feedActivity.identifier)
        assertEquals(likes, feedActivity.likes)
    }

    @Test
    fun feedActivityFormatted(){
        //prepare
        val identifier = "id"
        val challengerName =  "Luis"
        val challengerPhoto = 1
        val challengerSets = "1"
        val challengedName = "Miguel"
        val challengedPhoto =  2
        val challengedSets = "3"
        val feedDate =  "10/02/2018"
        val numberOfLikes = "1"
        val currentUserLiked = false

        //call
        val feedActivityFormatted = FeedModel.FeedActivityFormatted(challengerName = challengerName,
                challengerPhoto = challengerPhoto, challengerSets = challengerSets, challengedName = challengedName,
                challengedPhoto = challengedPhoto, challengedSets =  challengedSets, feedDate = feedDate,
                identifier = identifier, numberOfLikes = numberOfLikes, currentUserLiked = currentUserLiked)

        feedActivityFormatted.challengedName = challengedName
        feedActivityFormatted.challengedPhoto = challengedPhoto
        feedActivityFormatted.challengedSets = challengedSets
        feedActivityFormatted.challengerName = challengerName
        feedActivityFormatted.challengerPhoto = challengerPhoto
        feedActivityFormatted.challengerSets = challengerSets
        feedActivityFormatted.feedDate = feedDate
        feedActivityFormatted.identifier = identifier
        feedActivityFormatted.numberOfLikes = numberOfLikes
        feedActivityFormatted.currentUserLiked = currentUserLiked

        //assert
        assertEquals(challengerName, feedActivityFormatted.challengerName)
        assertEquals(challengerPhoto, feedActivityFormatted.challengerPhoto)
        assertEquals(challengerSets, feedActivityFormatted.challengerSets)
        assertEquals(challengedName, feedActivityFormatted.challengedName)
        assertEquals(challengedPhoto, feedActivityFormatted.challengedPhoto)
        assertEquals(challengedSets, feedActivityFormatted.challengedSets)
        assertEquals(feedDate, feedActivityFormatted.feedDate)
        assertEquals(identifier, feedActivityFormatted.identifier)
        assertEquals(numberOfLikes, feedActivityFormatted.numberOfLikes)
        assertEquals(currentUserLiked, feedActivityFormatted.currentUserLiked)
    }

    @Test
    fun successCreateRequestLikeAndUnlike(){
        //prepare
        val identifier = "id"

        // call
        val request = FeedModel.LikeAndUnlike.Request(identifier = identifier)
        request.identifier = identifier

        //assert
        assertEquals(identifier, request.identifier)
        assertNotNull(request)
    }

    @Test
    fun successCreateResponseLikeAndUnlike(){
        //prepare
        val challenger1 = FeedModel.FeedPlayer("Helena", R.mipmap.ic_launcher, 2)
        val challenged1 = FeedModel.FeedPlayer("Gabriel", R.mipmap.ic_launcher, 3)
        val date = Date()
        val identifier = "id"
        val challenge = FeedModel.FeedChallenge(challenger = challenger1, challenged = challenged1, challengeDate = date)
        val likes = mutableListOf(challenger1)
        val activity1 = FeedModel.FeedActivity(challenge = challenge, feedDate = date, identifier = identifier, likes = likes)

        //call
        val response = FeedModel.LikeAndUnlike.Response(activity1)
       response.likedActivity  = activity1


        //assert
        assertEquals(activity1, response.likedActivity)
        assertEquals(challenge, response.likedActivity.challenge)
        assertEquals(identifier, response.likedActivity.identifier)
        assertEquals(date, response.likedActivity.feedDate)
        assertEquals(likes, response.likedActivity.likes)

    }

    @Test
    fun successCreateViewModelLikeAndUnlike(){
        //prepare
        val testLikeUnlike = FeedModel.LikeAndUnlike()
        //call
        assertNotNull(testLikeUnlike)

    }

    @Test
    fun successFeedModel() {
        //prepare

        //call
        val model = FeedModel()

        //assert
        assertNotNull(model)
    }

    @Test
    fun successGetFeedActivities(){
        //prepare and call
        val getFeed = FeedModel.GetFeedActivities()
        //assert
        assertNotNull(getFeed)
    }

    @After
    fun tearDown() {
    }
}
