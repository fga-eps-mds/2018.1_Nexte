package com.nexte.nexte.Entities.Comment
import java.util.*

class CommentAdapterSpy: CommentAdapter {

    override fun delete(identifier: String): Comment? {
        if (identifier == "1") {
            return mockComment()
        }
        else if(identifier == "108") {
            return mockComment()
        }
        else {
            return null
        }
    }

    override fun get(identifier: String): Comment? {
        if (identifier == "1") {
            return mockComment()
        } else {
            return null
        }
    }

    override fun getAll(): List<Comment> {
        val commentList: MutableList<Comment> = mutableListOf(mockComment(), mockComment(), mockComment(), mockComment())
        return commentList.toList()
    }

    override fun updateOrInsert(challenge: Comment): Comment? {
        if (challenge.id == "1") {
            return mockComment()

        } else if(challenge.id == "108") {
            return mockComment()
        }
        else {
            return null
        }
    }

    private fun mockComment(): Comment {
        val id: String? = "1"
        val userId: String? = "2"
        val comment: String? = ""
        val date: Date? = Date()

        val comments = Comment(id, userId, comment, date)

        return comments
    }
}