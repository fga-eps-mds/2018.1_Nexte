package com.nexte.nexte.ObjectModels

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ResponseErrorTest {
    @Before
    fun setUp() {}

    @Test
    fun successResponseErrorConstructorTest() {

        // Prepare
        val responseErrorType = ResponseError.ResponseErrorType.REQUEST_ERROR
        val title = "Database Error"
        val detail = "This Database Error is just a test!"

        // Call
        val responseError = ResponseError(responseErrorType, title, detail)

        // Asserts
        Assert.assertEquals(responseErrorType, responseError.type)
        Assert.assertEquals(title, responseError.title)
        Assert.assertEquals(detail, responseError.detail)
    }

    @After
    fun tearDown() {}
}