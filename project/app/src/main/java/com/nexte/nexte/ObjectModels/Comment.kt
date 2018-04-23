package com.nexte.nexte.ObjectModels

import java.util.*

data class Comment(val id: String,
                   val userId: String,
                   val comment: String,
                   val date: Date)