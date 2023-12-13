package com.example.googlemapsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddCharacter : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_character)

        val charName = findViewById<EditText>(R.id.characterName).text

        val addBtn : Button = findViewById(R.id.addBtn)

        // Spinner Declaration
        var accessSpinner = findViewById<Spinner>(R.id.starSpinner)

        // Populating the Spinner
        val access = resources.getStringArray(R.array.accesses)
        val adapter = ArrayAdapter(this, R.layout.activity_spinner, R.id.spinnerText, access)
        accessSpinner.adapter = adapter

        databaseReference = FirebaseDatabase.getInstance().getReference("Gacha")
        //var dataKey = databaseReference.push().getKey()

        addBtn.setOnClickListener{
            var char_name = charName.toString()
            var drop_rate = accessSpinner.selectedItem.toString()
            var details = gachaCharacter(drop_rate, char_name)

            if (char_name.isNullOrBlank()){
                Toast.makeText(this, "Character Name Cannot be Empty!", Toast.LENGTH_SHORT).show()
            }
            else {
                databaseReference.child("Characters").child(char_name).setValue(details)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Success - ADD", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}