package com.nexte.nexte.ShowProfileScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ShowProfileViewTest {

    var view: ShowProfileFragment? = null

    @Before
    fun setUp() {
        this.view = ShowProfileFragment()
    }

    @Test
    fun testCompanionObject(){
        //prepare
        //call

        val testComp = ShowProfileFragment.newInstance()

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

    @Test
    fun testSetYAxisValuesRanking(){
        //prepare//call
        val yValsRanking = this.view?.setYAxisValuesRanking()

        //assert
        assertNotNull(yValsRanking)
        assertEquals(6, yValsRanking?.size)
        assertEquals(yValsRanking?.get(0)!!.x, 0f)
        assertEquals(yValsRanking[0].y, 3f)
        assertEquals(yValsRanking[5].x, 5f)
        assertEquals(yValsRanking[5].y, 4f)
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