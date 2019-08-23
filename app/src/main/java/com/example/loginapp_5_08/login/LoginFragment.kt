package com.example.loginapp_5_08.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.loginapp_5_08.homeScreen.HomeScreen
import com.example.loginapp_5_08.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.layout_current_status.view.*
import java.util.regex.Pattern

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_login,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load("https://s3.amazonaws.com/appsdeveloperblog/Micky.jpg").into(imageView2)

        button.setOnClickListener {showDialOk()}
    }

    companion object{
        fun newInstance() = LoginFragment()
    }

    private fun showDialOk(){

        val email = editText1.editableText.toString()
        val pass = editText2.editableText.toString()

        if (!isEmailValid(email)) {
            editText1.error = "Invalid Email"
        }
        else if (!isValidPassword(pass)) {
                editText2.error = "Invalid Password"
        }

        else {


            val clickintent = Intent(activity, HomeScreen::class.java)
            startActivity(clickintent)

            activity?.finish()
        }
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