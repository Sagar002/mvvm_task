package com.example.gitissuelist.repository


sealed class Response<T>(val data:T?=null,val errorMessage:String?=null) {
    class Success<T>(data:T?=null):Response<T>(data = data)
    class Error<T>(val error:String?=null):Response<T>(errorMessage = error)
    class Loading<T>:Response<T>()
}