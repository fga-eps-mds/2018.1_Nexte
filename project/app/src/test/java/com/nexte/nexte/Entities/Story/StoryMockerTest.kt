package com.nexte.nexte.Entities.Story

import com.nexte.nexte.Entities.Comment.CommentAdapterSpy
import com.nexte.nexte.Entities.Comment.CommentMocker
import com.nexte.nexte.Entities.Like.LikeAdapterSpy
import com.nexte.nexte.Entities.Like.LikeMocker
import com.nexte.nexte.Entities.User.UserAdapterSpy
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class StoryMockerTest {

    @Before
    fun setUp() {
        StoryMocker.likeAdapter = LikeAdapterSpy()
        StoryMocker.userAdapter = UserAdapterSpy()
        StoryMocker.commentAdapter = CommentAdapterSpy()
        CommentMocker.userAdapter = UserAdapterSpy()
        LikeMocker.userAdapter = UserAdapterSpy()
    }

    @Test
    fun testGenerateRandomStories(){
        val userAdapterSpy = UserAdapterSpy()
        StoryMocker.userAdapter = userAdapterSpy
        val randomStories = StoryMocker.generateRandomStories()
        assertTrue("Random Stories have between 0 and 20 stories", randomStories.size >= 0 && randomStories.size <= 20 )

        for (story in randomStories){

            assertTrue("Each story has between 0 and 9 comments", story.commentsId.size >= 0 && story.commentsId.size <= 9)
            assertTrue("Each story has between 0 and 6 likes", story.likesId.size >= 0 && story.likesId.size <= 6)
        }
    }

    @After
    fun tearDown() {
    }
}