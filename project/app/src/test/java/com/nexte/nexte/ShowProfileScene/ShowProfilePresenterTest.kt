package com.nexte.nexte.ShowProfileScene

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ShowProfilePresenterTest {

    private var mock: MockShowProfileDisplayLogic? = null
    private var presenter: ShowProfilePresenter? = null

    @Before
    fun setUp() {
        this.mock = MockShowProfileDisplayLogic()
        this.presenter = ShowProfilePresenter()
        this.presenter?.viewScene = mock
    }

    @Test
//    fun successPresentUserProfile(){
//        //prepare
//        val response = ShowProfileModel.Response(user = ShowProfileModel.Player(name = "gabrielalbino", rankingPosition = 2, pictureAdress = "imgur.com/nudh486d4", email = "enggabriel@gmail.com", gender = "masculino", club = "ASCAD", age = 19))
//        val name = "gabrielalbino"
//        val rank = "#2"
//        val club = "ASCAD"
//        val email = "enggabriel@gmail.com"
//        val age = "19"
//
//        //call
//        this.presenter?.presentUserProfile(response = response)
//
//        //assert
//        assertEquals(name, this.mock?.name)
//        assertEquals(rank, this.mock?.rank)
//        assertEquals(club, this.mock?.club)
//        assertEquals(email, this.mock?.email)
//        assertEquals(age, this.mock?.age)
//    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockShowProfileDisplayLogic: ShowProfileDisplayLogic{
    var name = " "
    var rank = " "
    var club = " "
    var email = " "
    var age = " "

    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {
//        this.name = viewModel.name!!
//        this.rank = viewModel.rank!!
//        this.club = viewModel.club!!
//        this.email = viewModel.email!!
//        this.age = viewModel.age!!

    }
}