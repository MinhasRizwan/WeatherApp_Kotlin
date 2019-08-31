package com.example.loginapp_5_08.login

import androidx.databinding.BaseObservable
import java.util.regex.Pattern

class User(private var email:String, private var password:String) : BaseObservable() {

    val isDataValid:Boolean
    get() = (isEmailValid(getEmail()) && isValidPassword(getPassword()))


    fun setEmail(ema :String)
    {
        this.email = ema
    }

    fun setPassword(pass :String)
    {
        this.password = pass
    }


    fun getEmail():String
    {
        return email
    }

    fun getPassword():String
    {
        return password
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(str: String): Boolean {

        var valid = true

        //Password policy check
        //Valid Length
        if (!validLength(str.length) || !validNumber(str) || !validCapital(str) || !validSmall(str) || !validSpecial(str))
        {
            valid = false
        }

        return valid
    }

    private fun validLength(len : Int) : Boolean
    {
        // Password should be minimum minimum 8 characters long
        if (len < 6) {
            return false
        }
        return true
    }

    private fun validNumber(str: String) : Boolean
    {
        // Password should contain at least one number
        val exp = ".*[0-9].*"
        val pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(str)

        if (!matcher.matches()) {
            return false
        }
        return true
    }

    private fun validCapital(str: String) : Boolean
    {
        // Password should contain at least one capital letter
        val exp = ".*[A-Z].*"
        val pattern = Pattern.compile(exp)
        val matcher = pattern.matcher(str)

        if (!matcher.matches()) {
            return false
        }
        return true
    }

    private fun validSmall(str: String) : Boolean
    {
        // Password should contain at least one small letter
        val exp = ".*[a-z].*"
        val pattern = Pattern.compile(exp)
        val matcher = pattern.matcher(str)

        if (!matcher.matches()) {
            return false
        }
        return true
    }

    private fun validSpecial(str: String) : Boolean
    {
        // Password should contain at least one special character
        // Allowed special characters : "~!@#$%^&*()-_=+|/,."';:{}[]<>?"
        val exp = ".*[~!@#\$%^&*()\\-_=+|\\[{\\]};:'\",<.>/?].*"
        val pattern = Pattern.compile(exp)
        val matcher = pattern.matcher(str)

        if (!matcher.matches()) {
            return false
        }
        return true
    }

}