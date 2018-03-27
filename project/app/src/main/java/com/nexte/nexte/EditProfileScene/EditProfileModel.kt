package com.nexte.nexte.EditProfileScene

/**
 * Created by albino on 27/03/18.
 */
class EditProfileModel {
    class Response {
        var name: String = ""
        var rank: Int = -1
        var profilePictureURL: String = ""
        var club: String = ""
        var age: Int = -1
        var email: String = ""
        var sex: Char = 'U'
        var facebookUsername: String = ""

        constructor(name: String, rank: Int, profilePictureURL: String, club: String, age: Int,
                    email: String, sex: Char, facebookUsername: String){
            this.name = name
            this.rank= rank
            this.profilePictureURL= profilePictureURL
            this.club= club
            this.age= age
            this.email= email
            this.sex= sex
            this.facebookUsername= facebookUsername

        }
    }
    class Request {
        var username: String = ""

        constructor(username: String) {
            this.username= username
        }

    }
    class ViewModel {
        var message: String= ""

        constructor(message: String){
            this.message= message
        }
    }
}