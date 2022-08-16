package com.furkanekiz.coroutinesdemo

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class UserDataManager {

    //Unstructured Concurrency
    suspend fun getTotalUserCount(): Int{
        var count = 0

        CoroutineScope(IO).launch {
            delay(1000)
            count = 50
        }

        val deferred = CoroutineScope(IO).async {
            delay(3000)
            return@async 70
        }

        return count + deferred.await()
    }
}