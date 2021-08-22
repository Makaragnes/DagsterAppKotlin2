package com.example.dagsterappkotlin.auth
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dagsterappkotlin.repository.Repository

class RegisterViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
        return RegisterViewModel(repository) as T
    }
}