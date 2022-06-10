package com.example.testtask.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.model.user.UserModel
import com.example.testtask.retrofit_service.GitHubApi
import com.example.testtask.ui.main.database.UserDatabaseOperations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val gitHubApi: GitHubApi) : ViewModel() {
    private val users = MutableLiveData<UserModel?>()
    private val userDatabaseOperations = UserDatabaseOperations()
    private var userModel = UserModel()

    init {
        requestUsersFromDatabase()
    }

    fun requestUsersFromApi() {
        viewModelScope.launch {
            gitHubApi.getUsers().enqueue(object : Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    val user = response.body()
                    viewModelScope.launch {
                        kotlin.runCatching {
                            for (i in 0 until user!!.size) {
                                userDatabaseOperations.updateOrCreateUser(
                                    username = user[i].login,
                                    imageUrl = user[i].avatar_url,
                                    id = i.toString()
                                )
                            }
                        }
                        requestUsersFromDatabase()
                    }
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    requestUsersFromDatabase()
                }
            })
        }
    }

    fun requestUsersFromDatabase() {
        //we have to create new instance of UserDatabaseOperations() to update data after a message received
        userModel = UserDatabaseOperations().retrieveUsers()
        users.postValue(userModel)
    }

    fun getUsers(): LiveData<UserModel?> {
        return users
    }
}