package com.example.lotusaddictionapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotusaddictionapp.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.Date

class LotusViewModel : ViewModel() {

    var userDao = MainApplication.appDatabase.getUserDao()

    val allUsers: LiveData<List<User>> = userDao.getAll()
    //val allGames: LiveData<List<GameEntity>> = gameDao.getAll()

    // ##### USERS #####
    fun addUser(first: String, last: String, email: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(
                User(
                    firstName = first,
                    lastName = last,
                    email = email,
                    passHash = pass,
                    createdDate = Date.from(Instant.now())
                )
            )
        }
    }

    fun createDefaultUser() {
        val defaultEmail = "default@email.com"
        viewModelScope.launch(Dispatchers.IO) {
            if (userDao.getUserByEmail(defaultEmail) == null){
                userDao.insert(
                    User(
                        firstName = "defaultfirst",
                        lastName = "defaultlast",
                        email = defaultEmail,
                        passHash = "default",
                        createdDate = Date.from(Instant.now())
                    )
                )
            }
        }
    }
    fun getUserByEmail(email: String) : User {
        var user: User = User("null", "null", "null", "null", Date.from(Instant.now()))
        viewModelScope.launch(Dispatchers.IO) {
            user = userDao.getUserByEmail(email)
        }
        while(user.email == "null"){}
        return user
    }

    fun getUserById(id: Int) : User {
        val user = userDao.getUserById(id)
        if (user == null){
            return(User("null", "null", "null", "null", Date.from(Instant.now())))
        }
        else{
            return user
        }
    }


    fun validateUser(email: String, password: String): Pair<Boolean, String> {
        if (email == "" || password == "") return Pair(false, "")

        var usercheckEmail: String? = "email-not-set"
        var usercheckPass: String? = "pass-not-set"
        var usercheckName: String = "name-not-set"
        viewModelScope.launch(Dispatchers.IO) {
            val usercheck = userDao.getUserByEmail(email)
            if (usercheck == null){
                usercheckEmail="null"
                usercheckPass="null"
                usercheckName="null"
            }
            else{
                usercheckEmail = usercheck.email
                usercheckPass = usercheck.passHash
                usercheckName = usercheck.firstName.toString()
            }

        }
        while(usercheckEmail == "email-not-set" || usercheckPass == "pass-not-set" ){
        }
        return (Pair<Boolean, String>(email == usercheckEmail && password == usercheckPass, usercheckName))
    }

    fun deleteUserById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.delete(userDao.getUserById(id))   // TODO implement delete
        }
    }
}