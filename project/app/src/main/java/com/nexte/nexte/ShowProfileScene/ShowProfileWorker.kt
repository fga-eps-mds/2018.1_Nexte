package com.nexte.nexte.ShowProfileScene

/**
 * Created by albino on 25/03/18.
 */

class ShowProfileWorker {

    constructor() { }

    fun getUserProfile(request: ShowProfileModel.Request, completion: (ShowProfileModel.Response) -> Unit) {
        val username = request.username
        val tokenID = request.tokenID

        val empty = ShowProfileModel.Player("", -1, "", "",
                                             "", "", -1)

        val userTest = ShowProfileModel.Player("gabrielalbino", 2,
                                                "imgur.com/nudh486d4",
                                                "enggabriel@gmail.com", "masculino",
                                                "ASCAD", 19)
        var returned: ShowProfileModel.Player? = null

        if(tokenID.equals("")){
            returned = empty
        } else if(username.equals("gabrielalbino")) {
            returned = userTest
        }

        var response: ShowProfileModel.Response = ShowProfileModel.Response(returned)

        completion(response)
    }
}
