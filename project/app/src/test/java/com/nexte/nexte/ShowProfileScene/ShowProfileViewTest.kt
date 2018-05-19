package com.nexte.nexte.ShowProfileScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ShowProfileViewTest {

    var view: ShowProfileView? = null

    @Before
    fun setUp() {
        this.view = ShowProfileView()
    }

    @Test
    fun testCompanionObject(){
        //prepare
        //call

        val testComp = ShowProfileView.newInstance()

        //assert
        assertNotNull(testComp)
    }

    @Test
    fun testSetupShowProfileScene(){
        //prepare
        this.view?.setupShowProfileScene()

        //call

        //assert
        assertNotNull(this.view?.showProfileInteractor)
    }

    @Test
    fun testCreateShowProfileRequest(){
        //prepare
        this.view?.setupShowProfileScene()
        val mock = MockShowProfileBusinessLogic()
        this.view?.showProfileInteractor = mock

        //call
        this.view?.createShowProfileRequest()

        //assert
        assertNotNull(mock.request)
    }

    @Test
    fun testSetXAxisValues(){
        //prepare //call
        val xVals = this.view?.setXAxisValues()

        //assert
        assertNotNull(xVals)
        assertEquals(0, xVals?.size)
    }

    @Test
    fun testSetYAxisValues(){
        //prepare //call
        val yVals = this.view?.setYAxisValues()

        //assert
        assertNotNull(yVals)
        assertEquals(6, yVals?.size)
        assertEquals(yVals?.get(0)!!.x, 0f)
        assertEquals(yVals[0].y, 2f)
        assertEquals(yVals[5].x, 5f)
        assertEquals(yVals[5].y, 5f)
    }

    @After
    fun tearDown() {
    }
}

private class MockShowProfileBusinessLogic: ShowProfileBusinessLogic{
    var request: ShowProfileModel.Request? = null

    override fun showProfile(request: ShowProfileModel.Request) {
        this.request = request
    }
}