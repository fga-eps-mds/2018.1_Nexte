package com.nexte.nexte.Entities.Story

import io.realm.RealmList
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class StoryAdapterRealmTest {

    // TODO: Need to mock Realm.init() to can't call convertUserToUserRealm method
//    var storyAdapterRealm: StoryAdapterRealm? = null
//
//    @Before
//    fun setUp() {
//        storyAdapterRealm = StoryAdapterRealm()
//    }
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