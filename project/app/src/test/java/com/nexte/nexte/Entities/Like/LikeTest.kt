package com.nexte.nexte.Entities.Like
import io.realm.RealmList
import org.junit.Assert
import org.junit.Before
import java.util.*

class LikeTest {

    var likes: List<String> = RealmList()

    @Before
    fun setup(){
        likes = listOf("1", "2", "3")
    }

    fun successStoryConstructorTest() {

        // Prepare
        val id = "1234567890"
        val userId = "0987654321"
        val date = Date()

        // Call
        val like = Like(id, userId, date)

        // Asserts
        Assert.assertEquals("Id of like is incorrect!", id, like.id)
        Assert.assertEquals("Id of user is incorrect!", userId, like.userId)
    }

}
