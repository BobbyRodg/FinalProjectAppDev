package com.example.woof

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.woof.data.GameEntity
import com.example.woof.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class RetroViewModel : ViewModel() {

    var userDao = MainApplication.appDatabase.getUserDao()
    var gameDao = MainApplication.appDatabase.getGameDao()

    val allUsers: LiveData<List<User>> = userDao.getAll()
    //val allGames: LiveData<List<GameEntity>> = gameDao.getAll()

    // ##### USERS #####
    fun addUser(first: String, last: String, username: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(
                User(
                    firstName = first,
                    lastName = last,
                    username = username,
                    passHash = pass,
                    createdDate = Date.from(Instant.now())
                )
            )
        }
    }

    fun validateUser(username: String, password: String): Boolean {
        if (username == "" || password == "") return false

        var usercheckName: String? = "default"
        var usercheckPass: String? = "default"
        viewModelScope.launch(Dispatchers.IO) {
            val usercheck = userDao.getUserByUsername(username)
            if (usercheck == null){
                usercheckName="null"
                usercheckPass="null"
            }
            else{
                usercheckName = usercheck.username
                usercheckPass = usercheck.passHash
            }

        }
        while(usercheckName == "default" || usercheckPass == "default" ){
        }
        return (username == usercheckName && password == usercheckPass)
    }

    /*fun addUser(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(User(
                id = 1,
                firstName = "Bobbyyyy",
                lastName = "Rodgers",
                username = "brodgers",
                passHash = "password",
                createdDate =  Date.from(Instant.now())))
            userDao.insert(User(
                id = 2,
                firstName = "Simon",
                lastName = "Cowell",
                username = "scow",
                passHash = "password123",
                createdDate =  Date.from(Instant.now())))
        }
    }*/

    fun deleteUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.delete(userDao.getUserByID(1))   // TODO implement delete
        }
    }

    // ##### GAMES #####

    fun getAllGames(): LiveData<List<GameEntity>> {
        return gameDao.getAll()
    }

    fun getSelectedGame(): GameEntity {
        var selectedGame: GameEntity? = null
        viewModelScope.launch(Dispatchers.IO) {
            selectedGame = gameDao.getSelectedGame()
        }
        while(selectedGame == null){}
        return selectedGame!!
    }

    fun addGame(title: String, imageResourceId: Int, condition: Int, releaseYear: Int, console: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            gameDao.insert(
                GameEntity(
                    title = title,
                    imageResourceId = imageResourceId,
                    condition = condition,
                    releaseYear = releaseYear,
                    console = console,
                    description = description
                )
            )
        }
    }

    fun deleteGame(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            gameDao.delete(gameDao.getGameByID(1))   // TODO implement delete
        }
    }

    fun updateSelectedById(id: Int, selectionStatus: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            gameDao.updateGameSelection(id, selectionStatus)
        }
    }


}