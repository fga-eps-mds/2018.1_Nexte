//package com.nexte.nexte.FeedScene
//
//import com.nexte.nexte.R
//import org.junit.After
//import org.junit.Before
//
//import org.junit.Assert.*
//import org.junit.Test
//import java.util.*
//
//class FeedWorkerTest {
//
//    var worker: FeedWorker? = null
//
//    @Before
//    fun setUp() {
//        this.worker = FeedWorker()
//    }
//
//    @Test
//    fun testFetchData(){
//        //prepare
//        val challenger2Name = "Letícia"
//        val challenged3Set = 0
//        val challenger5Name = "Larissa"
//        val request = FeedModel.Request()
//
//        //call
//        this.worker?.fetchFeedData(request = request, completion = {response ->
//            //assert
//            assertNotNull(response)
//            assertEquals(response.feedActivities.size, 8)
//            assertEquals(response.feedActivities[1].challenge.challenger.name, challenger2Name)
//            assertEquals(response.feedActivities[2].challenge.challenged.set, challenged3Set)
//            assertEquals(response.feedActivities[4].challenge.challenger.name, challenger5Name)
//        })
//    }
//
//    @After
//    fun tearDown() {
//        this.worker = null
//    }
//}