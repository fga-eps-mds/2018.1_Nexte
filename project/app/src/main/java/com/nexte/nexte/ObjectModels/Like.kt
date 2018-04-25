package com.nexte.nexte.ObjectModels

import java.util.*

class Like(val id: String,
           val userId: String,
           val date: Date) {

    enum class ServerRequest(val request: Map<String, String>) {
        LIKES(hashMapOf("route" to "likes", "method" to "get")),
        LIKE(hashMapOf("route" to "like", "method" to "post")),
        UNLIKE(hashMapOf("route" to "like", "method" to "delete"))
    }
}