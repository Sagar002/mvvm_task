package com.example.gitissuelist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gitissuelist.repository.GitRepository
import com.example.gitissuelist.repository.Response
import com.example.gitissuelist.model.GitIssueItem
import com.example.gitissuelist.model.comment.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: GitRepository,private val issueId:Int,private val issueNumber:Int) : ViewModel() {


    private val issueModel = MutableLiveData<GitIssueItem>()

    val comments : LiveData<Response<List<Comment>>>
        get() = repository.comments

    val issue:LiveData<GitIssueItem>
        get() = repository.issue


    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getComments(issueNumber,issueId)
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getIssue(issueId)
        }
    }

}