package com.example.travelogue

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
      private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

      // Mutable state to store user data
      val userDataState = mutableStateOf<User?>(null)

      private fun normalizeEmail(email: String): String {
            return email.replace(".", ",")
      }

      fun fetchUserData(email: String) {
            val userId = normalizeEmail(email)
            viewModelScope.launch {
                  database.child(userId).get().addOnSuccessListener { snapshot ->
                        val user= snapshot.getValue(User::class.java)
                              userDataState.value = user
                  }.addOnFailureListener {
                        // Handle failure, e.g., show a toast or log error
                  }
            }
      }
}