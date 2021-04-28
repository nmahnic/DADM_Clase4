package com.nicomahnic.dadm.clase4.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.nicomahnic.dadm.clase4.R

class SecondActivity : AppCompatActivity() {

    private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val userName = intent.getStringExtra(Intent.EXTRA_TEXT)

        userName?.let {
            User.name = userName
        }

        Log.d("NM", "ARGS: ${userName}")
        supportActionBar?.hide()

    }

    object User{
        var name = "John"

        init {
            println("Singleton invoked")
        }

        fun printName() = println(name)
    }
}

