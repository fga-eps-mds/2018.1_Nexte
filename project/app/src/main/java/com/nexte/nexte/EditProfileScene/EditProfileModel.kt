package com.nexte.nexte.EditProfileScene

import com.nexte.nexte.*

/**
 * Created by albino on 27/03/18.
 */

class EditProfileModel {

    class FirstRequest {

        class Response(var user: Player)

        class Request(var username: String, var tokenID: String)

        class ViewModel(var username: String,
                        var ranking: String,
                        var club: String,
                        var age: String,
                        var email: String
                        )
    }

    class SecondRequest {

        class Response {}

        class Request {}

        class ViewModel {}
    }
}

