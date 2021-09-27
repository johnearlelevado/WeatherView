package com.example.challenge.ui

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    open fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")
}