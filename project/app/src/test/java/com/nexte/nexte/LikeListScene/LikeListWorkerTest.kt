package com.nexte.nexte.LikeListScene

import io.realm.Realm
import io.realm.RealmConfiguration
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class LikeListWorkerTest {

    var worker: LikeListWorker? = null
    var mock: MockUpdateResponseLogic? = null

    @Before
    fun setUp() {
        this.worker = LikeListWorker()
        this.mock = MockUpdateResponseLogic()
        this.worker?.responseLogic = mock
    }

    // TODO: Need to mock Realm.init() to can't call convertUserToUserRealm method
    @Test
    fun testGetListLikesPlayers(){
//        //prepare
//        val requestMessage = "123"
//        val request = LikeListModel.Request(request = requestMessage)
//
//        //call
//        this.worker?.getListLikesPlayers(request = request)
//        //assert
//
//        assertEquals(this.mock?.response?.players?.size, 4)
//        assertEquals(this.mock?.response?.players!![0].name, "Alexandre")
//        assertEquals(this.mock?.response?.players!![1].time, String())
    }

    @After
    fun tearDown() {
        this.worker = null
    }
}

class MockUpdateResponseLogic: UpdateResponseLogic {

    var response: LikeListModel.Response? = null

    override fun getUsers(response: LikeListModel.Response) {
        this.response = response
    }
}