package com.ankit.listapplication.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ankit.listapplication.model.UserModel
import io.reactivex.Maybe

@Dao
 interface UserDAO {
    @Query("select * from user")
    open fun getUsersList():Maybe<List<UserModel>>

    @Query("DELETE FROM user")
    open fun clearUsersDetails()

    @Insert
    open fun insertUser( userModel:UserModel)
}