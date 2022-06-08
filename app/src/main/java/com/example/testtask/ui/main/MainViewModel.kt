package com.example.testtask.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.model.user.UserModel
import com.example.testtask.retrofit_service.GitHubApi
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

    fun getUsersFromApi() {
        viewModelScope.launch {
            gitHubApi.getUsers().enqueue(object : Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    val user = response.body()
                    users.postValue(user)
                    viewModelScope.launch {
                        if (userDatabaseOperations.retrieveUsers().size == 0){
                            for (i in 0 until user!!.size) {
                                userDatabaseOperations.insertUser(
                                    username = user[i].login,
                                    imageUrl = user[i].avatar_url,
                                    id = i.toString()
                                )
                            }
                        } else{
                            for (i in 0 until user!!.size) {
                                userDatabaseOperations.updateUser(
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
        userModel = userDatabaseOperations.retrieveUsers()
        users.postValue(userModel)
    }

    fun getUsers(): LiveData<UserModel?> {
        return users
    }
}