package com.nexte.nexte.LikeListScene

import com.nexte.nexte.Entities.User.UserAdapterSpy
import com.nexte.nexte.Entities.User.UserManager
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LikeListInteractorTest {

    private var mock: MockLikeListPresentationLogic? = null
    private var interactor: LikeListInteractor? = null
    private var mockUpdateResponseLogic: MockWorkerUpdateLogic? = null

    @Before
    fun setUp() {
        this.mock = MockLikeListPresentationLogic()
        this.mockUpdateResponseLogic = MockWorkerUpdateLogic()
        this.interactor = LikeListInteractor(presenter = mock)
        this.mockUpdateResponseLogic?.mock = mock
        this.interactor?.worker?.updateLogic = this.mockUpdateResponseLogic
        this.interactor?.worker?.userManager = UserManager(userAdapter = UserAdapterSpy())
    }

    @Test
    fun testFetchDataToList(){
        //prepare
        val request = LikeListModel.Request(request = "1")

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
        this.interactor?.updateUsers(response = response)

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

private class MockWorkerUpdateLogic : WorkerUpdateLogic{
    var mock: MockLikeListPresentationLogic? = null

    override fun updateUsers(response: LikeListModel.Response) {
        this.mock?.formatLikeList(response = response)
    }
}