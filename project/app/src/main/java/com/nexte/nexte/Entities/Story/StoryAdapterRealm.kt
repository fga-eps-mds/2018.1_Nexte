package com.nexte.nexte.Entities.Story

import com.nexte.nexte.Entities.Comment.Comment
import com.nexte.nexte.Entities.Like.Like
import io.realm.Realm
import io.realm.RealmList
import io.realm.kotlin.where

class StoryAdapterRealm: StoryAdapter{

    var realm: Realm = Realm.getDefaultInstance()

    // Adapter methods
    override fun delete(identifier: String): Story? {
        val storyRealm = realm.where<StoryRealm>().equalTo("id", identifier).findAll()
        realm.beginTransaction()
        val story = convertStoryRealmToStory(storyRealm.first()!!)
        storyRealm.deleteAllFromRealm()
        realm.commitTransaction()
        return story
    }

    override fun get(identifier: String): Story? {
        val storyRealm = realm.where<StoryRealm>().equalTo("id", identifier).findFirst()
        return convertStoryRealmToStory(storyRealm!!)
    }

    override fun getAll(): List<Story> {
        val storyRealmResults = realm.where<StoryRealm>().findAll()
        return convertStoryRealmListToStoryList(storyRealmResults)
    }

    override fun updateOrInsert(story: Story): Story? {
        convertStoryToStoryRealm(story)?.let {
            realm.beginTransaction()
            realm.insertOrUpdate(it)
            realm.commitTransaction()
            return story
        }
        return null
    }

    private fun convertStoryRealmToStory(storyRealm: StoryRealm): Story{
        //Id
        val storyId = storyRealm.id

        //winner
        val winner = StoryPlayer(userId = storyRealm.winnerId!!, setResult = storyRealm.winnerSetResult!!)

        //loser
        val loser = StoryPlayer(userId = storyRealm.loserId!!, setResult = storyRealm.loserSetResult!!)

        //Date
        val date = storyRealm.date

        //Comments
        val commentsId = storyRealm.commentsId

        //Likes
        val likesId = storyRealm.likesId


        val story = Story(id = storyId, winner = winner, loser = loser, commentsId = commentsId, date = date, likesId = likesId)
        return story
    }

    private fun convertStoryRealmListToStoryList(storiesRealm: List<StoryRealm>): List<Story>{
        val storiesRealmMutable = storiesRealm.toMutableList()
        val storiesMutable = mutableListOf<Story>()

        for (storyRealm in storiesRealmMutable){
            val story = convertStoryRealmToStory(storyRealm)
            storiesMutable.add(story)
        }

        val stories = storiesMutable.toList()
        return stories
    }

    private fun convertStoryToStoryRealm(story: Story): StoryRealm{
        //Id
        val id = story.id

        //Winner
        val winnerId = story.winner?.userId
        val winnerSetResult = story.winner?.setResult

        //Loser
        val loserId = story.loser?.userId
        val loserSetResult = story.loser?.setResult

        //Date
        val date = story.date

        //Comments
//        val commentsRealmList = RealmList<Comment>()
//        val comments = story.comments.toList()
//        for (comment in comments){
//            commentsRealmList.add(comment)
//        }
        val commentsIdRealmList = RealmList<String>()
        val comments = story.commentsId.toList()
        for (comment in comments){
            commentsIdRealmList.add(comment)
        }

        //Likes
//        val likesRealmList = RealmList<Like>()
//        val likes = story.likes
//        for (like in likes){
//            likesRealmList.add(like)
//        }
        val likesIdRealmList = RealmList<String>()
        val likes = story.likesId.toList()
        for (like in likes){
            likesIdRealmList.add(like)
        }

        val storyRealm = StoryRealm(id = id, winnerId = winnerId, winnerSetResult = winnerSetResult, loserId = loserId, loserSetResult = loserSetResult, date = date, commentsId = commentsIdRealmList, likesId = likesIdRealmList)
        return storyRealm
    }

}