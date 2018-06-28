package com.nexte.nexte.Entities.Story

import io.realm.Realm
import io.realm.RealmList
import io.realm.kotlin.where

class StoryAdapterRealm: StoryAdapter{

    var realm: Realm = Realm.getDefaultInstance()

    // Adapter methods
    override fun delete(identifier: String): Story? {
        val storyRealm = realm.where<StoryRealm>().equalTo("id", identifier).findAll()
        realm.beginTransaction()
        val story = convertStoryRealmToStory(storyRealm.first())
        storyRealm.deleteAllFromRealm()
        realm.commitTransaction()
        return story
    }

    override fun get(identifier: String): Story? {

        val storyRealm = realm.where<StoryRealm>().equalTo("id", identifier).findFirst()
        return if (storyRealm == null) {
            null
        }else{
            convertStoryRealmToStory(storyRealm)
        }
    }

    override fun getAll(): List<Story> {
        val storyRealmResults = realm.where<StoryRealm>().findAll()
        return convertStoryRealmListToStoryList(storyRealmResults)
    }

    override fun updateOrInsert(story: Story): Story? {
        convertStoryToStoryRealm(story).let {
            realm.beginTransaction()
            realm.insertOrUpdate(it)
            realm.commitTransaction()
            return story
        }
    }

    override fun addComment(story: Story, commentId: String): Story? {
        convertStoryToStoryRealm(story).let {
            val mutableComments = story.commentsId.toMutableList()
            mutableComments.add(commentId)
            story.commentsId = mutableComments.toList()
            return updateOrInsert(story)
        }
    }

    override fun removeComment(story: Story, commentPos: Int): Story? {

        convertStoryToStoryRealm(story).let {

            val mutableComments = story.commentsId.toMutableList()
            mutableComments.removeAt(commentPos)
            story.commentsId = mutableComments.toList()
            return updateOrInsert(story)

        }
    }

    override fun updateLikes(story: Story, userId: String): Story? {
        //verifies if the user is on list
        convertStoryToStoryRealm(story).let {
            val storyIndex = story.likesId.contains(userId)
            val mutableLikes = story.likesId.toMutableList()

            if(storyIndex){
                mutableLikes.remove(userId)
            }
            else{
                mutableLikes.add(userId)
            }

            story.likesId = mutableLikes.toList()
            return updateOrInsert(story)
        }
    }

    fun convertStoryRealmToStory(storyRealm: StoryRealm?): Story? {

        var story: Story? = null

        storyRealm?.let {
            val storyId = it.id
            val winner = StoryPlayer(userId = it.winnerId!!, setResult = it.winnerSetResult!!)
            val loser = StoryPlayer(userId = it.loserId!!, setResult = it.loserSetResult!!)
            val date = it.date
            val commentsId = it.commentsId
            val likesId = it.likesId

            story = Story(id = storyId, winner = winner, loser = loser, commentsId = commentsId, date = date, likesId = likesId)
        }
        return story
    }

    fun convertStoryToStoryRealm(story: Story?): StoryRealm? {

        var storyRealm: StoryRealm? = null
        story?.let {
            val id = it.id

            val winnerId = it.winner?.userId
            val winnerSetResult = it.winner?.setResult

            val loserId = it.loser?.userId
            val loserSetResult = it.loser?.setResult

            val date = it.date

            val commentsIdRealmList = RealmList<String>()
            val comments = it.commentsId.toList()
            for (comment in comments){
                commentsIdRealmList.add(comment)
            }

            val likesIdRealmList = RealmList<String>()
            val likes = it.likesId.toList()
            for (like in likes){
                likesIdRealmList.add(like)
            }

            storyRealm = StoryRealm(id = id, winnerId = winnerId,
                     winnerSetResult = winnerSetResult, loserId = loserId,
                     loserSetResult = loserSetResult, date = date,
                     commentsId = commentsIdRealmList,
                     likesId = likesIdRealmList)
        }

        return storyRealm
    }

    fun convertStoryRealmListToStoryList(storiesRealm: List<StoryRealm>): List<Story>{
        val storiesMutable = mutableListOf<Story>()

        if (storiesRealm.size > 0) {
            for (storyRealm in storiesRealm) {
                convertStoryRealmToStory(storyRealm)?.let {
                    storiesMutable.add(it)
                }
            }
        }

        return storiesMutable.toList()
    }

}