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

class Register : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        databaseReference = FirebaseDatabase.getInstance().getReference("Gacha")

        var userReg = findViewById<EditText>(R.id.userReg).text
        var passReg = findViewById<EditText>(R.id.passReg).text
        var regBtn = findViewById<Button>(R.id.registerBtn)

        regBtn.setOnClickListener {
            var userDetails = UserInfo(passReg.toString(), userReg.toString())
            var dataList = ArrayList<String>()
            login_redirection()
            databaseReference.child("Users").child(userReg.toString()).setValue(userDetails)
                .addOnSuccessListener {
                    Toast.makeText(this, "Success - ADD", Toast.LENGTH_SHORT).show()

                }
        }
    }

        private fun login_redirection() {
            try {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("error_redirect", e.message.toString())

            }
        }
    }