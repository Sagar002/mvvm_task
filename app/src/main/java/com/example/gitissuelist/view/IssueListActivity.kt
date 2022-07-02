package com.example.gitissuelist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitissuelist.GitApplication
import com.example.gitissuelist.RecycleViewItemClick
import com.example.gitissuelist.repository.Response
import com.example.gitissuelist.viewmodels.MainViewModel
import com.example.gitissuelist.viewmodels.MainViewModelFactory
import com.example.gitissuelist.R
import com.example.gitissuelist.databinding.ActivityIssueListBinding
import com.example.gitissuelist.model.GitIssueItem
import com.example.gitissuelist.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class IssueListActivity : AppCompatActivity() , RecycleViewItemClick {
    lateinit var mainViewModel: MainViewModel
    lateinit var binding:ActivityIssueListBinding
    var issueList:ArrayList<GitIssueItem> =ArrayList<GitIssueItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_issue_list)
        setViewDefaultProperties()
        setData()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        title = "Issue List"
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * set data to view
     */
    private fun setData() {
        val repository = (application as GitApplication).quoteRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.gitIssueItem.observe(this, Observer {
            when(it){
                is Response.Loading ->{
                    binding.loader.show()
                }
                is Response.Success ->{
                    binding.loader.hide()
                    issueList.clear()
                    issueList.addAll(it.data!!)
                    binding.recycleView.adapter!!.notifyDataSetChanged()
                }
                is Response.Error ->{
                    binding.loader.hide()
                }
            }

        })
    }

    /**
     * initialise default properties of view
     */
    private fun setViewDefaultProperties() {
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = IssueListAdapter(issueList,this)

    }

    /**
     * to handle click Event from RecycleView
     */
    override fun onItemClick(position:Int){
        val intent = Intent(this, DetailsViewActivity::class.java)
        intent.putExtra(Constants.issueID, issueList.get(position).id)
        intent.putExtra(Constants.issueNumber, issueList.get(position).number)
        startActivity(intent)

    }
}