package com.nexte.nexte.Entities.Story

import com.nexte.nexte.HelpForRealm

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class StoryAdapterRealmTest: HelpForRealm() {


    var storyAdapterRealm: StoryAdapterRealm? = null

    @Before
    fun setUp() {
        setUpWithStory()
        storyAdapterRealm = StoryAdapterRealm()
    }

    @Test
    fun testConvertStoryToStoryRealm(){
        val id = "1"
        val winnerId = "2"
        val winnerSetResult = 3
        val winner = StoryPlayer(winnerId, winnerSetResult)
        val loserId = "3"
        val loserSetResult = 1
        val date = Date()
        val loser = StoryPlayer(loserId, loserSetResult)
        val commentsIds = listOf("1", "2")
        val likesIds = listOf("1", "2")
        val story = Story(id, winner, loser, date, commentsIds, likesIds)

        val storyRealm = this.storyAdapterRealm?.convertStoryToStoryRealm(story)

        assertEquals(id, storyRealm?.id)
        assertEquals(winnerId, storyRealm?.winnerId)
        assertEquals(winnerSetResult, storyRealm?.winnerSetResult)
        assertEquals(loserId, storyRealm?.loserId)
        assertEquals(loserSetResult, storyRealm?.loserSetResult)
        assertEquals(date, storyRealm?.date)
    }

    @Test
    fun testGet(){
        Story()
        StoryRealm()
        StoryPlayer()
        this.storyAdapterRealm?.realm
        val story = this.storyAdapterRealm?.get("9")
        assertEquals(story?.id, "9")
        assertEquals(story?.winner?.setResult, 3)
        assertEquals(story?.loser?.setResult, 1)
    }


    @Test
    fun testConvertStoryRealmToStory(){
        val id = "1"
        val winnerId = "2"
        val winnerSetResult = 3
        val loserId = "3"
        val loserSetResult = 1
        val date = Date()
        val storyRealm = StoryRealm(id, winnerId, winnerSetResult,  loserId, loserSetResult ,date)

        val story = this.storyAdapterRealm?.convertStoryRealmToStory(storyRealm)

        assertEquals(id, story?.id)
        assertEquals(winnerId, story?.winner?.userId)
        assertEquals(winnerSetResult, story?.winner?.setResult)
        assertEquals(loserId, story?.loser?.userId)
        assertEquals(loserSetResult, story?.loser?.setResult)
        assertEquals(date, storyRealm?.date)
    }

    @Test
    fun testConvertStoryRealmListToStoryList(){
        val id = "1"
        val winnerId = "2"
        val winnerSetResult = 3
        val loserId = "3"
        val loserSetResult = 1
        val date = Date()
        val storyRealm = StoryRealm(id, winnerId, winnerSetResult,  loserId, loserSetResult ,date)

        val storiesRealm = listOf(storyRealm, storyRealm)

        val stories = this.storyAdapterRealm?.convertStoryRealmListToStoryList(storiesRealm)

        assertEquals(stories?.size, 2)
        assertEquals(id, stories!![0].id)
        assertEquals(winnerId, stories[0].winner?.userId)
        assertEquals(winnerSetResult, stories[0].winner?.setResult)
        assertEquals(loserId, stories[0].loser?.userId)
        assertEquals(loserSetResult, stories[0].loser?.setResult)
        assertEquals(date, stories[0].date)
    }


//
//    @Test
//    fun testConvertStoryRealmListToStoryList(){
//        //prepare
//        val id = "1"
//        val winnerId = "1"
//        val winnerSetResult  = 5
//        val loserId = "2"
//        val loserSetResult = 3
//        val date = Date()
//        val commentsId = RealmList<String>()
//        val likesId = RealmList<String>()
//
//        //call
//        val storyRealm = StoryRealm(id, winnerId, winnerSetResult, loserId, loserSetResult, date, commentsId, likesId)
//        val storyRealmList = listOf(storyRealm)
//        val storyList = storyAdapterRealm?.convertStoryRealmListToStoryList(storyRealmList)
//
//        //assert
//        assertEquals(storyList?.size, 1)
//        assertEquals(storyList is List<Story>, true)
//        assertEquals(storyList!![0].id, "1")
//        assertEquals(storyList!![0].winner?.userId, "1")
//        assertEquals(storyList!![0].winner?.setResult, 5)
//        assertEquals(storyList!![0].loser?.userId, "2")
//        assertEquals(storyList!![0].loser?.setResult, 3)
//        assertEquals(storyList!![0].date, date)
//        assertEquals(storyList!![0].commentsId.size, 0)
//        assertEquals(storyList!![0].likesId?.size, 0)
//    }
//
//    @Test
//    fun testConvertStoryToStoryRealm(){
//
//    }


    @After
    fun tearDown() {
    }
}