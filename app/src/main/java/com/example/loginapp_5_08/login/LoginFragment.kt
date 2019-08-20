package com.example.loginapp_5_08.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.loginapp_5_08.homeScreen.HomeScreen
import com.example.loginapp_5_08.R
import kotlinx.android.synthetic.main.login_fragment.*

import java.util.regex.Pattern


class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.login_fragment,
            container, false
        )

        val button: Button = view.findViewById(R.id.button)

        button.setOnClickListener {showDialOk()}

        //val PasswordInputLayout = view.findViewById<TextInputEditText>(R.id.editText2)
        //PasswordInputLayout.error = "8+ characters and at least one uppercase letter, a number, and a special character (\$, #, !)"

        return view
    }

    private fun showDialOk(){

        val email = editText1.editableText.toString()
        val pass = editText2.editableText.toString()

        Toast.makeText(activity, "Logging", Toast.LENGTH_SHORT).show()

        if (isEmailValid(email))
        {
            //view.findViewById<EditText>(R.id.editText1).setError( "First name is required!" )
            if (isValidPassword(pass))
            {
                val clickintent = Intent(activity, HomeScreen::class.java)
                startActivity(clickintent)

                activity?.finish()

            }
            else
            {
                editText2.error = "Invalid Password"
                //Toast.makeText(activity, " In valid Password", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            editText1.error = "Invalid Email"
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(str: String): Boolean {

        var valid = true

        // Password policy check

        //Valid Length
        if (!validLength(str.length))
        {
            valid = false
        }

        //atleast one number
        if (!validNumber(str))
        {
            valid = false
        }

        //atleast one capital
        if (!validCapital(str))
        {
            valid = false
        }

        //atleast one small
        if (!validSmall(str))
        {
            valid = false
        }

        //atleast one special
        if (!validSpecial(str))
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