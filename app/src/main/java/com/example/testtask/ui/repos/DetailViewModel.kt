package com.example.testtask.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.model.repository.RepositoryModel
import com.example.testtask.retrofit_service.GitHubApi
import com.example.testtask.ui.repos.database.RepositoryDatabaseOperations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gitHubApi: GitHubApi): ViewModel() {
    private val userRepositories = MutableLiveData<RepositoryModel?>()
    private val repositoryDatabaseOperations = RepositoryDatabaseOperations()
    private var repositoryModel = RepositoryModel()

    init {
        requestRepositoriesFromDatabase()
    }

    fun requestRepositoriesFromApi(username: String){
        viewModelScope.launch {
            gitHubApi.getUsersRepos(username).enqueue(object : Callback<RepositoryModel>{
                override fun onResponse(
                    call: Call<RepositoryModel>,
                    response: Response<RepositoryModel>
                ) {
                    val repository = response.body()
                    userRepositories.postValue(repository)
                    viewModelScope.launch {
                        if (repositoryDatabaseOperations.retrieveRepositories().size == 0)
                            insertRepository(repository)
                        else
                            updateRepository(repository)

                        requestRepositoriesFromDatabase()
                    }
                }

                override fun onFailure(call: Call<RepositoryModel>, t: Throwable) {
                    if (repositoryDatabaseOperations.retrieveRepositories().size == 0)
                        requestRepositoriesFromApi(username)
                    else
                        requestRepositoriesFromDatabase()
                }
            })

        }
    }

    suspend fun insertRepository(repository: RepositoryModel?){
        for (i in 0 until repository!!.size) {
            repositoryDatabaseOperations.insertRepository(
                id = i.toString(),
                repositoryName = repository[i].name,
                programmingLanguage = repository[i].language,
                starCount = repository[i].stargazers_count,
                url = repository[i].html_url
            )
        }
    }

    suspend fun updateRepository(repository: RepositoryModel?){
        for (i in 0 until repository!!.size) {
            repositoryDatabaseOperations.updateRepository(
                id = i.toString(),
                repositoryName = repository[i].name,
                programmingLanguage = repository[i].language,
                starCount = repository[i].stargazers_count,
                url = repository[i].html_url
            )
        }
    }

    fun requestRepositoriesFromDatabase() {
        repositoryModel = repositoryDatabaseOperations.retrieveRepositories()
        userRepositories.postValue(repositoryModel)
    }

    fun getUserRepositories():LiveData<RepositoryModel?>{
        return userRepositories
    }
}