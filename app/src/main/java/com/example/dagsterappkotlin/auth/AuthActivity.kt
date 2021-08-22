package com.example.dagsterappkotlin.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.dagsterappkotlin.MainActivity
import com.example.dagsterappkotlin.R
import com.example.dagsterappkotlin.model.Destination
import com.example.dagsterappkotlin.model.authDestination
import com.example.dagsterappkotlin.service.DestinationService
import com.example.dagsterappkotlin.service.ServiceBuilder
import com.example.dagsterappkotlin.utilits.saveSharedPrefString
import retrofit2.Call
import retrofit2.Callback

class AuthActivity : AppCompatActivity() {

    private lateinit var signUpButton: Button
    private lateinit var signInButton: Button
    private lateinit var email: EditText
    private lateinit var password: EditText

    private val fileSharedPreferences = "data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val intentRegisterActivity = Intent(this, RegisterActivity::class.java)
        val intentMainActivity = Intent(this, MainActivity::class.java)

        email = findViewById(R.id.loginField)
        password = findViewById(R.id.passwordField)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(fileSharedPreferences, Context.MODE_PRIVATE)

        saveSharedPrefString("email", email.toString(), sharedPreferences)
        saveSharedPrefString("password", password.toString(), sharedPreferences)

        signUpButton = findViewById(R.id.signUp)
        signInButton = findViewById(R.id.signIn)

        signUpButton.setOnClickListener{
            startActivity(intentRegisterActivity)
        }

        signInButton.setOnClickListener{
            var newAuthDestination = authDestination()

            newAuthDestination.mail = email.text.toString()
            newAuthDestination.password = password.text.toString()

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall = destinationService.authDestination(newAuthDestination)

            requestCall.enqueue(object: Callback<authDestination> {

                override fun onResponse(call: Call<authDestination>, response: retrofit2.Response<authDestination>) {
                    if (response.isSuccessful) {
                        finish() // Move back to DestinationListActivity
                        var newlyCreatedDestination = response.body() // Use it or ignore it
                        //saveSharedPrefString("uuid", response.uuid.toString(), sharedPreferences)
                        //Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show()
                    } else {
                        //Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<authDestination>, t: Throwable) {
                    //Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
                }
            })

            startActivity(intentMainActivity)
        }
    }
}