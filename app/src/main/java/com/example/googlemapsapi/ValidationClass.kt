package com.example.googlemapsapi

import android.util.Log
import java.util.regex.Pattern

public class ValidationClass {
    public fun ValidatePassword(password : String) : Boolean{
        if (!password.isNullOrBlank()) {
            if (password.length <= 8) {
                var char_of_string = password.toCharArray()
                val (upperCases, notUppercases) = char_of_string.partition { it.isUpperCase() }
                if (upperCases.isEmpty() && !notUppercases.isEmpty()) {
                    val (digitCase, notDigitCase) = char_of_string.partition { it.isDigit() }
                    if (!digitCase.isEmpty()) {
                        val special: Pattern =
                            Pattern.compile("[!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~]]")
                        if (special.matcher(password).find()) {
                            return true
                        }else {
                            Log.i("PW", "Special Char Issue")}
                    }else {
                        Log.i("PW", "Digit Issue")}
                } else {
                    Log.i("PW", "Uppercase Issue")}
            } else {
                Log.i("PW", "More than 8")}
        }
        return false
    }
}