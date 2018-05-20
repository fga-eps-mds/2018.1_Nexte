package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Player
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class EditProfileModelTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testConstructor(){
        //prepare and call
        val model = EditProfileModel()
        val modelFirstRequest = EditProfileModel.EditProfileRequest()
        val modelSecondRequest  = EditProfileModel.RecoverUserRequest()

        //assert
        assertNotNull(model)
        assertNotNull(modelFirstRequest)
        assertNotNull(modelSecondRequest)
    }

    @Test
    fun testRequests(){
        //prepare
        val username = "gabriel"
        val tokenID = "123cc123s"
        val player = Player("gabriel",
                1,
                "",
                "",
                "",
                "",
                1,
                "",
                "")

        //call
        val firstRequest = EditProfileModel.RecoverUserRequest.Request(username, tokenID)
        val secondRequest = EditProfileModel.EditProfileRequest.Request(player)

        //assert
        assertEquals(username, firstRequest.username)
        assertEquals(tokenID, firstRequest.tokenID)
        assertEquals(player, secondRequest.user)
    }

    @Test
    fun testRequestSetter(){
        //prepare
        val newUsername = "gabriel"
        val newTokenID = "123cc123s"
        val newPlayer = Player("gabriel",
                1,
                "",
                "",
                "",
                "",
                1,
                "",
                "")
        val firstRequest = EditProfileModel.RecoverUserRequest.Request("lul", "aa")
        val secondRequest = EditProfileModel.EditProfileRequest.Request(Player(
                "lul", 1, "", "", "", "", 1, "", ""
        ))
        //call
        firstRequest.tokenID = newTokenID
        firstRequest.username  = newUsername
        secondRequest.user = newPlayer

        //assert
        assertEquals(newUsername, firstRequest.username)
        assertEquals(newTokenID, firstRequest.tokenID)
        assertEquals(newPlayer, secondRequest.user)
    }

    @Test
    fun testResponses(){
        //prepare
        val newPlayer = Player("gabriel",
                1,
                "",
                "",
                "",
                "",
                1,
                "",
                "")
        val errorID = 3

        //call
        val firstResponse = EditProfileModel.EditProfileRequest.Response(errorID, newPlayer)
        val secondResponse = EditProfileModel.RecoverUserRequest.Response(newPlayer)

        //assert
        assertEquals(firstResponse.errorID, errorID)
        assertEquals(firstResponse.newUser, newPlayer)
        assertEquals(secondResponse.user, newPlayer)
    }

    @Test
    fun testResponsesSetters(){
        //prepare
        val newPlayer = Player("gabriel",
                1,
                "",
                "",
                "",
                "",
                1,
                "",
                "")
        val newErrorID = 3
        val firstResponse = EditProfileModel.EditProfileRequest.Response(2,
                Player("",0,"", "", "", "", 0, "", ""))
        val secondResponse = EditProfileModel.RecoverUserRequest.Response(
                Player("",0,"", "", "", "", 0, "",  "")
        )

        //call
        firstResponse.newUser = newPlayer
        firstResponse.errorID = newErrorID
        secondResponse.user = newPlayer

        //assert
        assertEquals(firstResponse.errorID, newErrorID)
        assertEquals(firstResponse.newUser, newPlayer)
        assertEquals(secondResponse.user, newPlayer)
    }

    @Test
    fun testFormattedPlayer(){
        //prepare
        val username = "gabriel"
        val ranking = "#1"
        val club = "Ttt"
        val age = "19"
        val email = "g@g.g"

        //call
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                username, ranking, club, age, email
        )

        //assert
        assertEquals(formattedPlayer.age, age)
        assertEquals(formattedPlayer.club, club)
        assertEquals(formattedPlayer.email, email)
        assertEquals(formattedPlayer.ranking, ranking)
        assertEquals(formattedPlayer.username, username)
    }

    @Test
    fun testFormattedPlayerSetter(){
        //prepare
        val username = "gabriel"
        val ranking = "#1"
        val club = "Ttt"
        val age = "19"
        val email = "g@g.g"
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                "", "", "", "", ""
        )

        //call
        formattedPlayer.username = username
        formattedPlayer.ranking = ranking
        formattedPlayer.email = email
        formattedPlayer.club = club
        formattedPlayer.age = age

        //assert
        assertEquals(formattedPlayer.age, age)
        assertEquals(formattedPlayer.club, club)
        assertEquals(formattedPlayer.email, email)
        assertEquals(formattedPlayer.ranking, ranking)
        assertEquals(formattedPlayer.username, username)
    }

    @Test
    fun testViewModels(){
        val username = "gabriel"
        val ranking = "#1"
        val club = "Ttt"
        val age = "19"
        val email = "g@g.g"
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                username, ranking, club, age, email
        )
        val message = "message"

        //call
        val firstViewModel = EditProfileModel.RecoverUserRequest.ViewModel(formattedPlayer)
        val secondViewModel = EditProfileModel.EditProfileRequest.ViewModel(message)

        //assert
        assertEquals(firstViewModel.playerToEdit, formattedPlayer)
        assertEquals(secondViewModel.errorMessage, message)
    }

    @Test
    fun testViewModelSetters(){
        val username = "gabriel"
        val ranking = "#1"
        val club = "Ttt"
        val age = "19"
        val email = "g@g.g"
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                username, ranking, club, age, email
        )
        val message = "message"
        val firstViewModel = EditProfileModel.RecoverUserRequest.ViewModel(
                EditProfileModel.RecoverUserRequest.FormattedPlayer(
                        "", "", "", "", ""
                )
        )
        val secondViewModel = EditProfileModel.EditProfileRequest.ViewModel("teste")

        //call
        firstViewModel.playerToEdit = formattedPlayer
        secondViewModel.errorMessage = message

        //assert
        assertEquals(firstViewModel.playerToEdit, formattedPlayer)
        assertEquals(secondViewModel.errorMessage, message)
    }
}