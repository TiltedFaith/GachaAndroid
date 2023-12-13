package com.example.googlemapsapi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import kotlin.random.Random

class PullScreen : AppCompatActivity() {
    private lateinit var buttonRandomize: Button
    private lateinit var buttonRandomizeTen: Button
    private lateinit var textResult: TextView
    private lateinit var listView: ListView

    private val items5Star =
        arrayOf("5 STAR \n" + "Ganyu",
            "5 STAR \n" + "Hu Tao") // Add your 5-star items here
    private val items4Star =
        arrayOf("4 STAR \n" + "Xingqiu",
            "4 STAR \n" + "Xiangling",
            "4 STAR \n" + "Bennett") // Add your 4-star items here
    private val items3Star = arrayOf(
        "3 STAR \n" + " Dull Blade",
        "3 STAR \n" + " Thrilling Tales",
        "3 STAR \n" + " Cool Steel",
        "3 STAR \n" + " Debate Club") // Add your 3-star items here

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_screen)

        buttonRandomize = findViewById(R.id.buttonRandomize)
        buttonRandomizeTen = findViewById(R.id.buttonRandomizeTen)
        textResult = findViewById(R.id.textResult)
        val addBtn : Button = findViewById(R.id.addCharacter)
        val editBtn : Button = findViewById(R.id.editCharacter)

        buttonRandomize.setOnClickListener{
            randomizeSelection()
        }

        buttonRandomizeTen.setOnClickListener{
            randomizeSelectionTen()
        }

        addBtn.setOnClickListener{
            addRedirection()
        }

        editBtn.setOnClickListener{
            editRedirection()
        }

    }

    private fun addRedirection() {
        try {
            val intent = Intent(this, AddCharacter::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("error_redirect", e.message.toString())

        }
    }

    private fun editRedirection() {
        try {
            val intent = Intent(this, EditCharacter::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e("error_redirect", e.message.toString())

        }
    }

    private fun randomizeSelection() {
        val randomValue = Random.nextDouble(0.0, 1.0)

        val result = when {
            randomValue < 0.006 -> items5Star.random() // 0.6% chance for 5-star
            randomValue < 0.156 -> items4Star.random() // 15% chance for 4-star
            else -> items3Star.random() // 15% chance for 3-star
        }

        textResult.text = "YOU GOT: \n$result"
    }

    private fun randomizeSelectionTen() {
        val stringBuilder = StringBuilder()

        repeat(10) {
            val randomValue = Random.nextDouble(0.0, 1.0)

            val result = when {
                randomValue < 0.006 -> items5Star.random() // 0.6% chance for 5-star
                randomValue < 0.156 -> items4Star.random() // 15% chance for 4-star
                else -> items3Star.random() // 15% chance for 3-star
            }

            stringBuilder.append("YOU GOT:$result  ")
        }

        textResult.text = stringBuilder.toString()
    }
}