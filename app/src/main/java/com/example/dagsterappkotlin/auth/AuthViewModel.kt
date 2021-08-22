package com.example.dagsterappkotlin.auth
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dagsterappkotlin.model.Post
import com.example.dagsterappkotlin.repository.Repository


import kotlinx.coroutines.launch

class AuthViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Post> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
}