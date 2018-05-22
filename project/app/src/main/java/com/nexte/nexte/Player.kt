package com.nexte.nexte

/**
 * Class responsible to hold player informations
 *
 * @property name Name of the logged user
 * @property rankingPosition Ranking of the logged user
 * @property pictureAddress Profile picture of the logged user
 * @property email Email of the logged user
 * @property gender Gender of the logged user
 * @property club Club of the logged user
 * @property age Age of the logged user
 * @property password Password of the logged user
 */
class Player(
        var name: String,
        var rankingPosition: Int,
        var pictureAddress: String,
        var email: String,
        var gender: String,
        var club: String,
        var age: Int,
        var password: String,
        var category: String)
