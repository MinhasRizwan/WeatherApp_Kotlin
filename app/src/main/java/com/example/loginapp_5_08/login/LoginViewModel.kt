package com.example.loginapp_5_08.login

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.accessibility.AccessibilityManager
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class LoginViewModel(private var listener: LoginResultCallback) : ViewModel() {

    private val user:User

    init {
        this.user = User("" , "")
    }

    val emailTextWatcher:TextWatcher
    get() = object:TextWatcher{
        override fun afterTextChanged(p0: Editable?) {
            user.setEmail(p0.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    }

    val passwordTextWatcher:TextWatcher
        get() = object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                user.setPassword(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        }

    fun onLoginClicked(v: View){
        if (user.isDataValid){
            listener.onSeccess("Login Successfull")
        }
        else{
            listener.onFailure("Login Failed")
        }
    }

}