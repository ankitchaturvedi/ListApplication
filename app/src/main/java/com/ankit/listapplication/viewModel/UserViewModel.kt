package com.ankit.listapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ankit.listapplication.activities.UserRepository
import com.ankit.listapplication.model.Resource
import com.ankit.listapplication.model.UserModel

class UserViewModel(val userRepository: UserRepository) : ViewModel() {

    internal class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UserViewModel(userRepository) as T
        }
    }

    fun getUsersFromAPI(page:Int, page_size:Int): MutableLiveData<Resource<UserModel>> {
        return userRepository!!.getUserData(page, page_size)
    }

    fun getUsersFromDB(): MutableLiveData<Resource<UserModel>> {
        return userRepository!!.getDBUsers()
    }
}