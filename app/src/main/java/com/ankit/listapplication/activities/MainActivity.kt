package com.ankit.listapplication.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankit.listapplication.R
import com.ankit.listapplication.adapter.UserDataAdapter
import com.ankit.listapplication.database.UserDataBase
import com.ankit.listapplication.model.ERROR
import com.ankit.listapplication.model.LOADING
import com.ankit.listapplication.model.SUCCESS
import com.ankit.listapplication.model.UserModel
import com.ankit.listapplication.utility.DialogShow
import com.ankit.listapplication.utility.MessageShow
import com.ankit.listapplication.utility.NetworkHelper
import com.ankit.listapplication.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    lateinit var userDataAdapter: UserDataAdapter
    lateinit var userDataBase: UserDataBase
    lateinit var userList: List<UserModel>
    var pageNumbe: Int = 0;
    var pageSize: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userDataBase = UserDataBase.getInstance(applicationContext)
        rvUserList.setLayoutManager(LinearLayoutManager(this))
        rvUserList.setHasFixedSize(true)
        userDataAdapter = UserDataAdapter()

        rvUserList.setAdapter(userDataAdapter)

        userViewModel = ViewModelProviders.of(
            this,
            UserViewModel.UserViewModelFactory(
                UserRepository(
                    context = this
                )
            )
        ).get(UserViewModel::class.java);

        pullToRefresh.setOnRefreshListener {
            getUserList()
            pullToRefresh.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        getUserList()
    }

    private fun getUserList() {
        if (NetworkHelper.isOnline(this)) {
            getUsersFromAPI()
        }else {
            MessageShow.showToast(this, resources.getString(R.string.no_connection))
            getUsersFromDB()
        }
    }

    private fun getUsersFromAPI() {

        userViewModel.getUsersFromAPI(pageNumbe, pageSize).observe(this, Observer {
            when (it.status) {
                LOADING -> {
                    DialogShow.showProgressDialog(
                        this,
                        resources.getString(R.string.please_wait_server)
                    );
                }
                SUCCESS -> {
                    DialogShow.dismissProgressDialog();
                    Log.i("MainActivity", " Data : " + it.data)
                    it.data?.let { itData ->
                        // Delete all old data from local DataBase
                        userDataBase.deleteAllUsersData()
                        // insert Data into Room DataBase
                        userDataBase.insertUsers(itData)
                        getUsersFromDB()
                    };
                }
                ERROR -> {
                    DialogShow.dismissProgressDialog();
                    MessageShow.showToast(this, it.message)
                }
            }
        })
    }


    // Get Data From Local Room DataBase
    private fun getUsersFromDB() {
        userViewModel.getUsersFromDB().observe(this, Observer {
            when (it.status) {
                LOADING -> {
                    DialogShow.showProgressDialog(
                        this,
                        resources.getString(R.string.please_wait_database)
                    );
                }
                SUCCESS -> {
                    DialogShow.dismissProgressDialog();
                    Log.i("MainActivity", "DB Data : " + it.data)
                    it.data?.let { itData ->
                        userList = itData
                        userDataAdapter!!.setUsersList(userList)
                    };
                }

                ERROR -> {
                    DialogShow.dismissProgressDialog();
                    MessageShow.showToast(this, it.message)
                }
            }

        })
    }
}