package com.example.travelogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class AuthViewModel: ViewModel() {
      private val userRepository:UserRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
      )

      private val _authResult= MutableLiveData<Result<Boolean>>()
      val authResult: LiveData<Result<Boolean>> get()=_authResult

      fun signUp(email:String, fullName:String,pass:String,conPass:String){
            viewModelScope.launch{
                  _authResult.value=userRepository.signUp(email,pass,conPass,fullName)
            }
      }

      fun login(email:String,pass:String){
            viewModelScope.launch {
                  _authResult.value=userRepository.login(email,pass)
            }
      }

}