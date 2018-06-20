package com.nexte.nexte.LoginScene

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import com.nexte.nexte.UserSingleton
import com.nexte.nexte.UserType
import io.realm.Realm
import io.realm.RealmConfiguration
import org.json.JSONObject
import android.util.Log

/**
 * Class responsible to manager request provided from interactor to response
 * and request user authentication for nexte server
 */

class LoginWorker {

    /**
     * Method that realize request for user authentication
     * pass request for the Worker and send response to the Presenter
     *
     * @param request request provided from Interactor
     * @completion task to be completed at Interactor
     */
    fun authenticateUser(request: LoginModel.Authentication.Request,
                         completion: (LoginModel.Authentication.Response) -> Unit) {

        val authentication = "http://192.168.100.3:3000/sessions" // Local url for auth
        val headers = mapOf("Content-Type" to "application/json",
                                    "Accept-Version" to "1.0.0")
        val json = JSONObject()

        Log.i("response name", request.userName)
        Log.i("response password", request.password)
        json.put("username",  request.userName) // Expected ramires
        json.put("password",  request.password) // Expected test-nexte-ramires


        Fuel.post(authentication).header(headers).body(json.toString()).responseJson { _, _, result ->

            result.success {
                val json = result.get().obj()
                println(json)
//                val user = User.createUserFromJsonObject(json)
//                println(user)
                this.updateUserLoggedStatus("1")
                val status = LoginModel.Authentication.StatusCode.AUTHORIZED
                val response = LoginModel.Authentication.Response("3445", status)
                println(UserSingleton.loggedUser)

                completion(response)
            }

            result.failure {
                val token = ""
                val status = LoginModel.Authentication.StatusCode.UNAUTHORIZED
                val response = LoginModel.Authentication.Response(token, status)
                completion(response)
            }
        }
    }

    /**
     * Method that realize request for user authentication
     * pass request for the Worker and send response to the Presenter
     *
     * @param request request provided from Interactor
     * @completion task to be completed at Interactor
     */
    fun requestForAuth(request: LoginModel.AccountKit.Request,
                        completion: (LoginModel.AccountKit.Response) -> Unit) {

        val authentication = "http://192.168.100.7:3000/auth/login" // Local route for auth
        val headers = mapOf("Content-Type" to "application/json",
                "Accept-Version" to "1.0.0")
        val body = defineBodyForAccountKitAuth(request.phone, request.email)

        Fuel.post(authentication).header(headers).body(body).responseJson { _, _, result ->

            result.success {

                //TODO: Add auth with token in NEXTE main server
                val player = UserSingleton.loggedUser
                UserSingleton.setLoggedUser(player.id)
                val response = LoginModel.AccountKit.Response(LoginModel.AccountKit.StatusCode.SUCESSED)
                completion(response)
            }

            result.failure {
                val response = LoginModel.AccountKit.Response(LoginModel.AccountKit.StatusCode.ERROR)
                completion(response)
            }
        }
    }

    /**
     * Define body to authenticate user with Nexte main server
     * @param phone Phone from a user - used in Account Kit auth
     * @param phone Email from a user - used in Account Kit auth
     */
    private fun defineBodyForAccountKitAuth(phone: String?, email: String?): String {
        val json = JSONObject()

        if(phone != null) {
            json.put("phone",  phone) // Expected test-nexte-ramires
            json.put("password",  "test-nexte-ramires")  // Expected ramires

        } else {
            json.put("email",  email)
            json.put("password",  "test-nexte-ramires")  // Expected ramires
        }

        return json.toString()
    }

    private fun updateUserLoggedStatus(userId: String) {
        UserSingleton.setLoggedUser(userId, UserType.REAL)   // User Singleton

        // Realm instance
        val config =  RealmConfiguration.Builder().name("realRealm.realm").build()
        Realm.setDefaultConfiguration(config)
    }
}


