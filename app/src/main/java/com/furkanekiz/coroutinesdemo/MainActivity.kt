package com.furkanekiz.coroutinesdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }

        btnDownloadUserData.setOnClickListener {
            CoroutineScope(Main/*Dispatchers.IO*/).launch {
                //downloadUserData()

                //Unstructured Concurrency
                //tvUserMessage.text = UserDataManager().getTotalUserCount().toString()

                //Structured Concurrency
                tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Main) {
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
        }
    }
}