package com.nexte.nexte.Entities.Like

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class LikeRealm (@PrimaryKey var id: String? = null,
                var userId: String? = null,
                var date: Date? = null): RealmObject() {

    enum class ServerRequest(val request: Map<String, String>) {
//        LIKES(hashMapOf("route" to "likes", "method" to "get")),
        LIKE(hashMapOf("route" to "like", "method" to "post")),
        UNLIKE(hashMapOf("route" to "like", "method" to "delete"))
    }
}