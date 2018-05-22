package com.nexte.nexte.Entities.Challenge.Helper

import com.nexte.nexte.Entities.Challenge.Challenge
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

class CancelledRealmTest {

    @Before
    fun setUp() {}

    @Test
    fun successCancelledRealmTest() {

        // Prepare
        val cancelledRealm = CancelledRealm("Any Reason", Challenge.UserType.CHALLENGER.name, Date())

        // Asserts
        Assert.assertEquals("Reason are incorrect!", "Any Reason", cancelledRealm.reason)
        Assert.assertEquals("User type that cancelled is incorrect!", Challenge.UserType.CHALLENGER.name, cancelledRealm.userType)
        Assert.assertNotNull("Date is null!", cancelledRealm.date)
    }

    @Test
    fun successSetMethodsTest() {

        // Prepare
        val cancelledRealm = CancelledRealm().apply {
            this.reason = "Any Reason"
            this.userType = Challenge.UserType.CHALLENGER.name
            this.date = Date()
        }

        // Asserts
        Assert.assertEquals("Reason are incorrect!", "Any Reason", cancelledRealm.reason)
        Assert.assertEquals("User type that cancelled is incorrect!", Challenge.UserType.CHALLENGER.name, cancelledRealm.userType)
        Assert.assertNotNull("Date is null!", cancelledRealm.date)
    }

    @Test
    fun successSecondConstructorTest() {

        // Prepare
        val canceled = Challenge.Stage.Canceled("Any Reason", Challenge.UserType.CHALLENGER, Date())

        // Call
        val cancelledRealm = CancelledRealm(canceled)

        // Asserts
        Assert.assertEquals("Reason are incorrect!", "Any Reason", cancelledRealm.reason)
        Assert.assertEquals("User type that cancelled is incorrect!", Challenge.UserType.CHALLENGER.name, cancelledRealm.userType)
        Assert.assertNotNull("Date is null!", cancelledRealm.date)
    }

    @After
    fun tearDown() {}
}