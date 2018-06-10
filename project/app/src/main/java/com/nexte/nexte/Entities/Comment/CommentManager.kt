package com.nexte.nexte.Entities.Comment

/**
 * Class define according to scene requests which kind of response will be displayed:
 * data from remote repository or from data storage.
 * @param commentAdapter hold information from CommentAdapterRealm
 * */
class CommentManager(private val commentAdapter: CommentAdapter = CommentAdapterRealm()) {
    fun get(identifier: String): Comment? {
        return commentAdapter.get(identifier)
    }

    fun getAll(): List<Comment> {
        return commentAdapter.getAll()
    }

    fun update(comment: Comment): Comment? {
        return commentAdapter.updateOrInsert(comment)
    }

    fun updateMany(comments: List<Comment>): List<Comment> {
        val newComments: MutableList<Comment> = mutableListOf()
        for (comment in comments) {
            val updatedComment = commentAdapter.updateOrInsert(comment)
            updatedComment?.let {
                newComments.add(it)
            }
        }
        return newComments.toList()
    }

    fun delete(identifier: String): Comment? {
        return commentAdapter.delete(identifier)
    }

    fun createInitialMocker(): List<Comment> {

        val commentsInMocker = CommentMocker.allComments
        val insertedComments: MutableList<Comment> = mutableListOf()

        for (comment in commentsInMocker) {

            val insertedComment = commentAdapter.updateOrInsert(comment)
            insertedComment?.let {
                insertedComments.add(it)
            }
        }
        return insertedComments.toList()
    }

    fun createCommentsIds(amount: Int): List<String>{
        return CommentMocker.generateComments(amount = amount)
    }

    fun getCommentsFromStory(commentsIds: List<String>) : List<Comment> {
        return commentAdapter.getCommentsFromStory(commentsIds)!!
    }



}