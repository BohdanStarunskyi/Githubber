package com.example.testtask.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.model.repository.RepositoryModel
import com.example.testtask.retrofit_service.GitHubApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gitHubApi: GitHubApi): ViewModel() {
    private val userRepositories = MutableLiveData<RepositoryModel?>()
    fun requestRepositories(userName: String){
        viewModelScope.launch {
            gitHubApi.getUsersRepos(userName).enqueue(object : Callback<RepositoryModel>{
                override fun onResponse(
                    call: Call<RepositoryModel>,
                    response: Response<RepositoryModel>
                ) {
                    val result = response.body()
                    userRepositories.postValue(result)
                }

                override fun onFailure(call: Call<RepositoryModel>, t: Throwable) {
                    userRepositories.postValue(null)
                }
            })
            RepositoryDatabaseOperations().insertRepository("dsa", "ads", 1, "ekd")
            Log.d("TAG", "requestRepositories: " + RepositoryDatabaseOperations().retrieveRepositories()[0].html_url)

        }

    }

    fun getUserRepositories():LiveData<RepositoryModel?>{
        return userRepositories
    }
}