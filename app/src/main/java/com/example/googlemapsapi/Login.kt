package com.example.googlemapsapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*



class Login : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn: Button = findViewById(R.id.loginBtn)
        val registerBtn : Button = findViewById(R.id.registerBtn)
        val userInput : EditText = findViewById(R.id.userInput)
        val passInput : EditText = findViewById(R.id.passInput)

        var dataList = arrayListOf<UserInfo>()

        databaseReference = FirebaseDatabase.getInstance().getReference("FirebaseDatabase")

        databaseReference.child("Users").get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                for (e in snapshot.children) {
                    var getPassword = e.child("password").getValue(String::class.java)
                    var getUsername = e.child("username").getValue(String::class.java)

                    var dc = UserInfo(
                        getPassword.toString(),
                        getUsername.toString(),
                    )
                    dataList.add(dc)
                }

                val passwd = snapshot.child("password").getValue(String::class.java)
                val userName = snapshot.child("username").getValue(String::class.java)

                Log.e("username", userName.toString())
            }
        }


        loginBtn.setOnClickListener {

        }
        registerBtn.setOnClickListener {
            register_redirection()
        }
    }


    private fun login_redirection() {
        try {
            val intent = Intent(this, Welcome::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("error_redirect", e.message.toString())

        }
    }

    private fun register_redirection() {
        try {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("error_redirect", e.message.toString())

        }
    }

    private fun showToast(message : String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}