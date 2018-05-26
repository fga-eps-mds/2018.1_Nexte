package com.nexte.nexte.CommentsScene

import com.nexte.nexte.R
import com.nexte.nexte.UserSingleton
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class CommentsWorkerTest {

    private var worker: CommentsWorker? = null

    @Before
    fun setUp() {
        this.worker = CommentsWorker()
    }







    @After
    fun tearDown() {
        this.worker = null
    }
}