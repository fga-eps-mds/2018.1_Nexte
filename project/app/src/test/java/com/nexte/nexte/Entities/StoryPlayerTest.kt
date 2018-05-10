package com.nexte.nexte.Entities

import com.nexte.nexte.Entities.Story.StoryPlayer
import org.junit.Assert
import org.junit.Test

class StoryPlayerTest {

    @Test
    fun successStoryPlayerConstructorTest() {

        // Prepare
        val userId  = ""
        val setResult = 1

        // Call
        val storyPlayer = StoryPlayer(userId, setResult)

        // Asserts
        Assert.assertEquals("Id of player is incorrect!", userId, storyPlayer.userId)
        Assert.assertEquals("Result Set is incorrect!", setResult, storyPlayer.setResult)
    }

    @Test
    fun successStoryPlayerEmptyConstructorTest() {
        // Call
        val storyPlayer = StoryPlayer()

        // Asserts
        Assert.assertNotNull(storyPlayer)
    }

    @Test
    fun successStoryPlayerSetMethodsTest() {

        // Prepare
        val userId = "123"
        val setResult = 1

        // Call
        val storyPlayer = StoryPlayer().apply {
            this.userId = userId
            this.setResult = setResult
        }

        // Asserts
        Assert.assertNotNull(storyPlayer)
        Assert.assertEquals("Id of player is incorrect!", userId, storyPlayer.userId)
        Assert.assertEquals("Result Set is incorrect!", setResult, storyPlayer.setResult)
    }
}