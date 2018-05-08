package com.nexte.nexte.LikeListScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LikeListInteractorTest {

    private var mock: MockLikeListPresentationLogic? = null
    private var interactor: LikeListInteractor? = null
    private var mockUpdateResponseLogic: MockUpdatesResponseLogic? = null

    @Before
    fun setUp() {
        this.mock = MockLikeListPresentationLogic()
        this.mockUpdateResponseLogic = MockUpdatesResponseLogic()
        this.interactor = LikeListInteractor(presenter = mock)
        this.mockUpdateResponseLogic?.mock = mock
        this.interactor?.worker?.responseLogic = this.mockUpdateResponseLogic
    }

    @Test
    fun testFetchDataToList(){
        //prepare
        val request = LikeListModel.Request(request = "akjsbdask")

        //call
        this.interactor?.fetchDataToList(request = request)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @Test
    fun testGetUsers(){
        //prepare
        val name = "luis"
        val photo = 1
        val time = "AASC"
        val player = LikeListModel.Players(name = name, photo = photo, time = time)
        val response = LikeListModel.Response(players = mutableListOf(player))

        //call
        this.interactor?.getUsers(response = response)

        //assert
        assertEquals(this.mock?.passedHere, true)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.interactor = null
        this.mock?.passedHere = false
    }
}

private class MockLikeListPresentationLogic: LikeListPresentationLogic{

    var passedHere = false

    override fun formatLikeList(response: LikeListModel.Response) {
        this.passedHere = true
    }
}

private class MockUpdatesResponseLogic : UpdateResponseLogic{
    var mock: MockLikeListPresentationLogic? = null

    override fun getUsers(response: LikeListModel.Response) {
        this.mock?.formatLikeList(response = response)
    }
}