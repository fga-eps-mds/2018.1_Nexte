package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.Entities.User.User
import com.nexte.nexte.Player
import com.nexte.nexte.R.id.password
import com.nexte.nexte.UserSingleton
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
        val user = UserSingleton.loggedUser
        val password = "senha"


        //call
        val firstRequest = EditProfileModel.RecoverUserRequest.Request(username, tokenID)
        val secondRequest = EditProfileModel.EditProfileRequest.Request(user, password)

        firstRequest.tokenID = tokenID
        firstRequest.username = username
        secondRequest.user = UserSingleton.loggedUser
        secondRequest.password = password
        //assert
        assertEquals(username, firstRequest.username)
        assertEquals(tokenID, firstRequest.tokenID)
        assertEquals(user, secondRequest.user)
        assertEquals(password, secondRequest.password)
    }

    @Test
    fun testRequestSetter(){
        //prepare
        val newUsername = "gabriel"
        val newTokenID = "123cc123s"
        val newUser = UserSingleton.loggedUser
        val newPassword = "senha"
        val firstRequest = EditProfileModel.RecoverUserRequest.Request("lul", "aa")
        val secondRequest = EditProfileModel.EditProfileRequest.Request(newUser, newPassword)
        //call
        firstRequest.tokenID = newTokenID
        firstRequest.username  = newUsername
        secondRequest.user = newUser
        secondRequest.password = newPassword

        //assert
        assertEquals(newUsername, firstRequest.username)
        assertEquals(newTokenID, firstRequest.tokenID)
        assertEquals(newUser, secondRequest.user)
        assertEquals(newPassword, secondRequest.password)
    }

    @Test
    fun testResponses(){
        //prepare
        val newUser = UserSingleton.loggedUser
        val errorID = 3

        //call
        val firstResponse = EditProfileModel.EditProfileRequest.Response(errorID, newUser)
        val secondResponse = EditProfileModel.RecoverUserRequest.Response(newUser)

        firstResponse.errorID = errorID
        firstResponse.newUser = newUser
        secondResponse.user = newUser

        //assert
        assertEquals(firstResponse.errorID, errorID)
        assertEquals(firstResponse.newUser, newUser)
        assertEquals(secondResponse.user, newUser)
    }

    @Test
    fun testResponsesSetters(){
        //prepare
        val newUser = UserSingleton.loggedUser
        val newErrorID = 3
        val firstResponse = EditProfileModel.EditProfileRequest.Response(newErrorID, newUser)
        val secondResponse = EditProfileModel.RecoverUserRequest.Response(newUser)

        //call
        firstResponse.newUser = newUser
        firstResponse.errorID = newErrorID
        secondResponse.user = newUser

        //assert
        assertEquals(firstResponse.errorID, newErrorID)
        assertEquals(firstResponse.newUser, newUser)
        assertEquals(secondResponse.user, newUser)
    }

    @Test
    fun testFormattedPlayer(){
        //prepare
        val username = "gabriel"
        val ranking = "#1"
        val club = "Ttt"
        val email = "g@g.g"

        //call
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                username, ranking, club, email)

        formattedPlayer.club = club
        formattedPlayer.email = email
        formattedPlayer.ranking = ranking
        formattedPlayer.username = username

        //assert
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
        val email = "g@g.g"
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                "", "", "", "")

        //call
        formattedPlayer.username = username
        formattedPlayer.ranking = ranking
        formattedPlayer.email = email
        formattedPlayer.club = club

        //assert
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
        val email = "g@g.g"
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                username, ranking, club, email)
        val message = "message"

        //call
        val firstViewModel = EditProfileModel.RecoverUserRequest.ViewModel(formattedPlayer)
        val secondViewModel = EditProfileModel.EditProfileRequest.ViewModel(message)

        firstViewModel.playerToEdit = formattedPlayer
        secondViewModel.errorMessage = message
        //assert
        assertEquals(firstViewModel.playerToEdit, formattedPlayer)
        assertEquals(secondViewModel.errorMessage, message)
    }

    @Test
    fun testViewModelSetters(){
        val username = "gabriel"
        val ranking = "#1"
        val club = "Ttt"
        val email = "g@g.g"
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                username, ranking, club, email)
        val message = "message"
        val firstViewModel = EditProfileModel.RecoverUserRequest.ViewModel(
                EditProfileModel.RecoverUserRequest.FormattedPlayer(
                        "", "", "", "")
        )
        val secondViewModel = EditProfileModel.EditProfileRequest.ViewModel("teste")

        //call
        firstViewModel.playerToEdit = formattedPlayer
        secondViewModel.errorMessage = message

        //assert
        assertEquals(firstViewModel.playerToEdit, formattedPlayer)
        assertEquals(secondViewModel.errorMessage, message)
    }

    @Test
    fun successEditProfileModel(){
        //prepare
        //call
        val model = EditProfileModel()

        //assert
        assertNotNull(model)
    }
}