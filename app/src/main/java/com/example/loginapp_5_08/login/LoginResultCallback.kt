package com.example.loginapp_5_08.login

interface LoginResultCallback {
    fun onSeccess(message:String)
    fun onFailure(message:String)
}