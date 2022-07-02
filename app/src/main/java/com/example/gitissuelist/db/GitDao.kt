package com.example.gitissuelist.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gitissuelist.model.GitIssueItem
import com.example.gitissuelist.model.comment.Comment

@Dao
interface GitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGitIssue(quotes: List<GitIssueItem>)

    @Query("SELECT * FROM issue")
    suspend fun getGitIssueList() : List<GitIssueItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addComment(quotes: List<Comment>)

    @Query("SELECT * FROM comment where issue_number=:issueNumber")
    suspend fun getCommentList(issueNumber:Int) : List<Comment>

    @Query("SELECT * FROM issue where id=:issueId")
    suspend fun getIssue(issueId:Int) : GitIssueItem

}