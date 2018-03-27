package com.nexte.nexte.EditProfileScene

/**
 * Created by lorrany on 27/03/18.
 */
class EditProfileWorker {

    constructor() { }

    fun editUserProfile(request: EditProfileModel.Request, completion:
    (EditProfileModel.Response) -> Unit) {
        var username: String = request.username

        var name: String = ""
        var rank: Int = -1
        var profilePictureURL: String = ""
        var club: String = ""
        var age: Int = -1
        var email: String = ""
        var sex: Char = 'U'
        var facebookUsername: String = ""
        if (username == "lorranyfreire"){
            name = "Lorrany Freire"
            rank = 1
            profilePictureURL = "https://www.google.com.br/amaisgatadodf/eumesma.jpg"
            club = "Minas Tenis Club"
            age = 19
            email = "lorranyfreire@hotmail.com"
            sex = 'F'
            facebookUsername = "lorrany.freire"
        }

        var response:EditProfileModel.Response= EditProfileModel.Response(name, rank,
                profilePictureURL, club, age, email, sex, facebookUsername)

        completion(response)
    }