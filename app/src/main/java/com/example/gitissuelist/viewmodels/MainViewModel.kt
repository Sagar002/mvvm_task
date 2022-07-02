package com.example.gitissuelist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitissuelist.repository.GitRepository
import com.example.gitissuelist.repository.Response
import com.example.gitissuelist.model.GitIssueItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: GitRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getGitIssue()
        }

    }

    val gitIssueItem : LiveData<Response<List<GitIssueItem>>>
    get() = repository.issues


    fun refreshData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getGitIssue()
        }
    }
}