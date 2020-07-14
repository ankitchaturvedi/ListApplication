package com.ankit.listapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ankit.listapplication.interfaces.UserDAO
import com.ankit.listapplication.model.UserModel
import kotlinx.coroutines.*

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDAO(): UserDAO?
    var modelList: List<UserModel>? = null

    fun insertUsers(modelList: List<UserModel?>?) {
        GlobalScope.async {
            if (modelList != null){
                for (i in modelList.indices){
                    var userModel:UserModel? = modelList!!.get(i);
                    if (userModel != null){
                        userDAO()!!.insertUser(userModel);
                    }
                }
            }
        }
        runBlocking {
            delay(1000L)  // ... while we delay for 1 seconds to keep JVM alive So Coroutine thread complete task
        }
    }

    fun deleteAllUsersData(){
        GlobalScope.launch {
            userDAO()!!.clearUsersDetails();
        }
    }

    companion object {
        const val DB_NAME = "User_db"
        @Volatile private var instance: UserDataBase? = null
        @Synchronized
        fun getInstance(context: Context): UserDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as UserDataBase
        }
    }
}