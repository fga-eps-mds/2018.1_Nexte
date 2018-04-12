package com.nexte.nexte.ShowProfileScene


import android.os.Bundle
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ShowProfileActivityTest {

    private var view: ShowProfileActivity? = null

    @Before
    fun setUp() {
        this.view = ShowProfileActivity()
    }

    @Test
    fun successSetupShowProfileScene(){
        this.view?.setupShowProfileScene()
        assertNotNull(this.view?.showProfileInteractor)
    }


    @After
    fun tearDown() {
        this.view = null
    }
}

