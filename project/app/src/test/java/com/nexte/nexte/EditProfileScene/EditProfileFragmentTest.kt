//package com.nexte.nexte.EditProfileScene
//
//import com.nexte.nexte.HelpForRealm
//import com.nexte.nexte.UserSingleton
//import org.junit.After
//import org.junit.Before
//import org.junit.Assert.*
//import org.junit.Test
//
//
//class EditProfileViewTest: HelpForRealm() {
//
//    var fragment: EditProfileFragment? = null
//
//    @Before
//    fun setUp() {
//
//        super.setUpWithUser()
//
//        this.fragment = EditProfileFragment()
//    }
//
//    @Test
//    fun testSetupEditProfileScene(){
//        //prepare  //call
//        this.fragment?.setupEditProfileScene()
//
//        //assert
//        assertNotNull(this.fragment?.getUserInformationInteractor)
//        assertNotNull(this.fragment?.editUserInformationInteractor)
//    }
//
//    @Test
//    fun testPasswordWatcher(){
//        //prepare
//        val passwordWatcher = EditProfileFragment.PasswordWatcher(fragment = this.fragment!!)
//
//        //call
//        passwordWatcher.afterTextChanged(s = null)
//        passwordWatcher.beforeTextChanged(s = null, start = 0, count = 0, after = 0)
//
//        //assert
//        assertNotNull(passwordWatcher)
//    }
//
//    @Test
//    fun testOnTextChanged(){
//        //prepare
//        this.fragment?.passwordConfirmationTextEdit?.text?.trim().toString()
//        this.fragment?.passwordTextEdit?.text?.trim().toString()
//
//        //call
//        this.testPasswordWatcher()
//
//        // assert
//        assertEquals(this.fragment?.passwordConfirmationTextEdit?.text?.trim().toString(),this.fragment?.passwordTextEdit?.text?.trim().toString())
//    }
//
//    @Test
//    fun testGetProfileCreateRequest(){
//        //prepare
//        this.fragment?.setupEditProfileScene()
//        val mock = MockGetProfileToEditBusinessLogic()
//        this.fragment?.getUserInformationInteractor = mock
//
//        //call
//        this.fragment?.createGetProfileRequest()
//
//        //assert
//        mock.hasBeenHere = true
//    }
//
//    @Test
//    fun testEditProfileCreateRequest(){
//        //prepare
//        this.fragment?.setupEditProfileScene()
//        val mock = MockEditProfileBusinessLogic()
//        this.fragment?.editUserInformationInteractor = mock
//
//        //call
//
//        this.fragment?.createEditProfileRequest(user = UserSingleton.loggedUser, password = "senha")
//
//        //assert
//        mock.hasBeenHere = true
//    }
//
//    @Test
//    fun testGetInstance() {
//
//        //prepare
//        val editProfileFragment = EditProfileFragment()
//
//        //call
//        this.fragment?.getInstance()
//
//        //assert
//        assertNotNull(editProfileFragment)
//    }
//
//    @Test
//    fun testOnCreateView() {
//
//    }
//
//    @After
//    fun tearDown() {
//        super.tearDownRealm()
//    }
//}
//
//private class MockGetProfileToEditBusinessLogic: GetProfileToEditBusinessLogic{
//    var hasBeenHere = false
//
//    override fun getProfileToEdit(request: EditProfileModel.RecoverUserRequest.Request) {
//        this.hasBeenHere = true
//    }
//}
//
//private class MockEditProfileBusinessLogic: EditProfileBusinessLogic{
//    var hasBeenHere = false
//
//    override fun setEditedProfile(request: EditProfileModel.EditProfileRequest.Request) {
//        this.hasBeenHere = true
//    }
//
//}