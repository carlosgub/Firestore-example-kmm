package com.myapplication

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.initialize(this)
        setContent {
            MainView()
        }
    }
}