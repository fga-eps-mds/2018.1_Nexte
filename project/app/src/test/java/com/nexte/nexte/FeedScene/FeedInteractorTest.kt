//package com.nexte.nexte.FeedScene
//
//import org.junit.After
//import org.junit.Before
//
//import org.junit.Assert.*
//import org.junit.Test
//
//class FeedInteractorTest {
//
//    private var mock: MockFeedPresentationLogic? = null
//    private var interactor: FeedInteractor? = null
//
//    @Before
//    fun setUp() {
//        this.mock = MockFeedPresentationLogic()
//        this.interactor = FeedInteractor(presenter = mock)
//    }
//
//    @Test
//    fun testFetchFeed(){
//        //prepare
//        val request = FeedModel.Request()
//
//        //call
//        this.interactor?.fetchFeed(request = request)
//
//        //assert
//        assertEquals(this.mock?.passedHere, true)
//    }
//
//    @After
//    fun tearDown() {
//        this.mock = null
//        this.interactor = null
//    }
//}
//
//private class MockFeedPresentationLogic: FeedPresentationLogic{
//    var passedHere = false
//
//    override fun formatFeed(response: FeedModel.Response) {
//        this.passedHere = true
//    }
//}