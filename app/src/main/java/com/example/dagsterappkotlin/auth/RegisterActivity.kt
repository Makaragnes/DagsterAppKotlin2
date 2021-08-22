package com.example.dagsterappkotlin.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dagsterappkotlin.MainActivity
import com.example.dagsterappkotlin.R
import com.example.dagsterappkotlin.model.Destination
import com.example.dagsterappkotlin.model.userModel.UserModel
import com.example.dagsterappkotlin.service.DestinationService
import com.example.dagsterappkotlin.service.ServiceBuilder
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback


class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    private lateinit var login: EditText
    private lateinit var password: EditText
    private lateinit var compainName:EditText
    private lateinit var signUpButton: Button
    private lateinit var signInButton: Button

    //private lateinit var intentMainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val intentAuthActivity = Intent(this, AuthActivity::class.java)
        val intentMainActivity = Intent(this, MainActivity::class.java)

        login = findViewById(R.id.loginField)
        password = findViewById(R.id.passwordField)
        compainName = findViewById(R.id.compainField)
        signInButton = findViewById(R.id.signIn)
        signUpButton = findViewById(R.id.signUp)

        signInButton.setOnClickListener{
            startActivity(intentAuthActivity)
        }

        signUpButton.setOnClickListener{
            val newDestination = Destination()

            //newDestination.id = 1
            newDestination.mail = login.text.toString()
            newDestination.password = password.text.toString()
            newDestination.name = compainName.text.toString()

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall = destinationService.addDestination(newDestination)

            requestCall.enqueue(object: Callback<Destination> {

                override fun onResponse(call: Call<Destination>, response: retrofit2.Response<Destination>) {
                    if (response.isSuccessful) {
                        finish() // Move back to DestinationListActivity
                        var newlyCreatedDestination = response.body() // Use it or ignore it
                        //Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show()
                    } else {
                        //Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    //Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
                }
            })

            startActivity(intentMainActivity)
        }


//        val repository = Repository()
//        val viewModelFactory = RegisterViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
//        viewModel.getPost()
//        viewModel.myResponse.observe(this, Observer { responce->
//            Log.d("Response", responce.url.toString())
//
//        })


    }


}