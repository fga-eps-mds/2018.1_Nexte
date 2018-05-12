package com.nexte.nexte.EditProfileScene

import org.junit.Before
import org.junit.After

/**
 * Created by miguelpimentel on 10/05/18.
 */

class EditProfileWorkerTest {

    private var worker: EditProfileWorker? = null

    @Before
    fun setUp() {
        this.worker = EditProfileWorker()
    }

    @After
    fun tearDown() {
         this.worker = null
    }
}
