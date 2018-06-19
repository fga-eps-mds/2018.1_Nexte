package com.nexte.nexte.Entities.Challenge

interface ChallengeAdapter {
    fun getAll(): List<Challenge>
    fun get(identifier: String): Challenge?
    fun updateOrInsert(challenge: Challenge): Challenge?
    fun delete(identifier: String): Challenge?
    fun getLastFiveChallenges(userdId: String): List<Challenge>
    fun getUserPlayedGames(userdId: String): List<Challenge>
}