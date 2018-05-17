package com.nexte.nexte.Entities.Comment
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class CommentRealm(@PrimaryKey var id: String? = null,
                        var userId: String? = null,
                        var comment: String? = null,
                        var date: Date? = null): RealmObject() {

    enum class ServerRequest(val request: Map<String, String>) {
        COMMENTS(hashMapOf("route" to "comments", "method" to "get")),
        ADD_COMMENT(hashMapOf("route" to "comment", "method" to "post")),
        DELETE_COMMENT(hashMapOf("route" to "comment", "method" to "delete")),
        REPORT_COMMENT(hashMapOf("route" to "report-comment", "method" to "post"))
    }
}