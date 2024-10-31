package com.example.travelogue

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(private val auth:  FirebaseAuth,
      private val firestore: FirebaseFirestore) {

      suspend fun signUp(
            email: String, pass: String, conpass: String,
            fullname: String
      ): Result<Boolean> =
            try {
                  auth.createUserWithEmailAndPassword(email, pass).await()
                  val user = User(fullname, email, pass, conpass)
                  saveUserToFirestore(user)
                  Result.Success(true)
            } catch(e:Exception){
                  Result.Error(e)
            }

      suspend fun login(email:String, password:String): Result<Boolean> =
            try{
                  auth.signInWithEmailAndPassword(email,password).await()
                  Result.Success(true)
            } catch(e:Exception){
                  Result.Error(e)
            }


      private suspend fun saveUserToFirestore(user: User) {
            firestore.collection("users").document(user.email).set(user).await()
            println("USER SAVED")
      }
}
