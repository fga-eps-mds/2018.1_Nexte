package com.nexte.nexte.Entities.Story

import org.junit.After
import org.junit.Before
import java.util.*
import org.junit.Assert.*
import org.junit.Test
import io.realm.RealmList

class StoryRealmTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testInstatiateStoryRealm(){
        //prepare
        val id = "1"
        val winnerId = "1"
        val winnerSetResult  = 5
        val loserId = "2"
        val loserSetResult = 3
        val date = Date()
        val commentsId = RealmList<String>()
        val likesId = RealmList<String>()

        //call
        val storyRealm = StoryRealm(id, winnerId, winnerSetResult, loserId, loserSetResult, date, commentsId, likesId)

        //assert
        assertEquals(id, storyRealm.id)
        assertEquals(winnerId, storyRealm.winnerId)
        assertEquals(winnerSetResult, storyRealm.winnerSetResult)
        assertEquals(loserId, storyRealm.loserId)
        assertEquals(loserSetResult, storyRealm.loserSetResult)
        assertEquals(date, storyRealm.date)
        assertEquals(commentsId, storyRealm.commentsId)
        assertEquals(likesId, storyRealm.likesId)
    }

    @Test
    fun successRequestsEnumTest() {

        // Prepare
        val storiesRealmRequest = StoryRealm.ServerRequest.STORIES.request

        // Asserts
        assertEquals("Stories request is incorrect!", storiesRealmRequest.keys, setOf("route", "method"))
    }

    @Test
    fun testStoryRealmSet(){
        //prepare
        val id = "1"
        val winnerId = "1"
        val winnerSetResult  = 5
        val loserId = "2"
        val loserSetResult = 3
        val date = Date()
        val commentsId = RealmList<String>()
        val likesId = RealmList<String>()

        //call
        val storyRealm = StoryRealm()
        storyRealm.winnerId = winnerId
        storyRealm.winnerSetResult = winnerSetResult
        storyRealm.id = id
        storyRealm.loserId = loserId
        storyRealm.loserSetResult = loserSetResult
        storyRealm.date = date
        storyRealm.commentsId = commentsId
        storyRealm.likesId = likesId

        //assert
        assertEquals(id, storyRealm.id)
        assertEquals(winnerId, storyRealm.winnerId)
        assertEquals(winnerSetResult, storyRealm.winnerSetResult)
        assertEquals(loserId, storyRealm.loserId)
        assertEquals(loserSetResult, storyRealm.loserSetResult)
        assertEquals(date, storyRealm.date)
        assertEquals(commentsId, storyRealm.commentsId)
        assertEquals(likesId, storyRealm.likesId)
    }

    @After
    fun tearDown() {
    }
}