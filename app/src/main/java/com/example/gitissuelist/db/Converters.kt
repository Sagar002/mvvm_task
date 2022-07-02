package com.example.gitissuelist.db

import androidx.room.TypeConverter
import com.example.gitissuelist.model.User
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromJsonToUser(value: String?): User? {
        return value?.let { Gson().fromJson(it, User::class.java) }
    }

    @TypeConverter
    fun userToJson(user: User?): String? {
        return Gson().toJson(user)
    }
}