package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.HelpForRealm
import com.nexte.nexte.UserSingleton
import org.junit.*
import org.junit.Assert.*

class EditProfileModelTest: HelpForRealm() {

    @Before
    fun setUp() {
        super.setUpWithUser()
    }

    @After
    fun tearDown() {
        super.tearDownRealm()
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
        val id = "gabrielalbino123456"
        val tokenID = "123cc123s"
        val user = UserSingleton.loggedUser
        val password = "senha"

        //call
        val firstRequest = EditProfileModel.RecoverUserRequest.Request(id, tokenID)
        val secondRequest = EditProfileModel.EditProfileRequest.Request(user, password)

        //assert
        assertEquals(id, firstRequest.id)
        assertEquals(tokenID, firstRequest.tokenID)
        assertEquals(user, secondRequest.user)
        assertEquals(password, secondRequest.password)
    }

    @Test
    fun testRequestSetter(){
        //prepare
        val newId = "gabriel"
        val newTokenID = "123cc123s"
        val newUser = UserSingleton.loggedUser
        val newPassword = "senha"
        val firstRequest = EditProfileModel.RecoverUserRequest.Request("lul", "aa")
        val secondRequest = EditProfileModel.EditProfileRequest.Request(newUser, newPassword)
        //call
        firstRequest.tokenID = newTokenID
        firstRequest.id  = newId
        secondRequest.user = newUser
        secondRequest.password = newPassword

        //assert
        assertEquals(newId, firstRequest.id)
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
        val phone = "130"
        val picture = 1

        //call
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                username, ranking, club, email, phone, picture)

        formattedPlayer.club = club
        formattedPlayer.email = email
        formattedPlayer.ranking = ranking
        formattedPlayer.username = username
        formattedPlayer.phone = phone
        formattedPlayer.picture = picture

        //assert
        assertEquals(formattedPlayer.club, club)
        assertEquals(formattedPlayer.email, email)
        assertEquals(formattedPlayer.ranking, ranking)
        assertEquals(formattedPlayer.username, username)
        assertEquals(formattedPlayer.phone, phone)
        assertEquals(formattedPlayer.picture, picture)
    }

    @Test
    fun testFormattedPlayerSetter(){
        //prepare
        val username = "gabriel"
        val ranking = "#1"
        val club = "Ttt"
        val email = "g@g.g"
        val phone = "130"
        val picture = 1
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                "", "", "", "", "", 0)

        //call
        formattedPlayer.username = username
        formattedPlayer.ranking = ranking
        formattedPlayer.email = email
        formattedPlayer.club = club
        formattedPlayer.phone = phone
        formattedPlayer.picture = picture

        //assert
        assertEquals(formattedPlayer.club, club)
        assertEquals(formattedPlayer.email, email)
        assertEquals(formattedPlayer.ranking, ranking)
        assertEquals(formattedPlayer.username, username)
        assertEquals(formattedPlayer.phone, phone)
        assertEquals(formattedPlayer.picture, picture)
    }

    @Test
    fun testViewModels(){
        val username = "gabriel"
        val ranking = "#1"
        val club = "Ttt"
        val email = "g@g.g"
        val phone = "130"
        val picture = 1
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                username, ranking, club, email, phone, picture)
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
        val phone = "130"
        val picture = 1
        val formattedPlayer = EditProfileModel.RecoverUserRequest.FormattedPlayer(
                username, ranking, club, email, phone, picture)
        val message = "message"
        val firstViewModel = EditProfileModel.RecoverUserRequest.ViewModel(
                EditProfileModel.RecoverUserRequest.FormattedPlayer(
                        "", "", "", "", "", -1)
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