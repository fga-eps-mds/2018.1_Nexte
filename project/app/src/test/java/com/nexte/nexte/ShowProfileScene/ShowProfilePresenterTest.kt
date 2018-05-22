package com.nexte.nexte.ShowProfileScene

import com.nexte.nexte.Player
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
    fun successPresentUserProfile(){
        //prepare
        val response = ShowProfileModel.Response(user = Player(name = "gabrielalbino", rankingPosition = 2,
                pictureAddress = "imgur.com/nudh486d4", email = "enggabriel@gmail.com", gender = "masculino", club = "ASCAD",
                age = 19, password = "123456", category = "profissional"))
        val formattedPlayer = ShowProfileModel.FormattedPlayer(name = "gabrielalbino", rank = "#2",
                club = "ASCAD", email = "enggabriel@gmail.com", age = "19")

        //call
        this.presenter?.presentUserProfile(response = response)

        //assert
        assertEquals(formattedPlayer.name, this.mock?.formatedPlayer?.name)
        assertEquals(formattedPlayer.rank, this.mock?.formatedPlayer?.rank)
        assertEquals(formattedPlayer.club, this.mock?.formatedPlayer?.club)
        assertEquals(formattedPlayer.email, this.mock?.formatedPlayer?.email)
        assertEquals(formattedPlayer.age, this.mock?.formatedPlayer?.age)
    }

    @After
    fun tearDown() {
        this.mock = null
        this.presenter = null
    }
}

private class MockShowProfileDisplayLogic: ShowProfileDisplayLogic{
    var formatedPlayer: ShowProfileModel.FormattedPlayer? = null

    override fun displayProfile(viewModel: ShowProfileModel.ViewModel) {
        this.formatedPlayer = viewModel.playerInfo
    }
}