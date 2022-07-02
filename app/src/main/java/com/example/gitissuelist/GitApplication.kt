package com.example.gitissuelist

import android.app.Application
import com.example.gitissuelist.api.GitService
import com.example.gitissuelist.api.RetrofitHelper
import com.example.gitissuelist.db.GitDatabase
import com.example.gitissuelist.repository.GitRepository

class GitApplication : Application() {
    lateinit var quoteRepository: GitRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(GitService::class.java)
        val database = GitDatabase.getDatabase(applicationContext)
        quoteRepository = GitRepository(quoteService, database, applicationContext)
    }
}