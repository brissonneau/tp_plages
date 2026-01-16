package biz.ei6.eluvplages.framework

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class ServerPollWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    val TAG = "ServerPollWorker"

    override suspend fun doWork(): Result {

        applicationContext.sendBroadcast(Intent(InternalBroadcasts.ACTION_SERVER_HAS_UPDATE))

        Log.d(TAG, "ServerPollWorker.doWork")

        return Result.success()
    }
}
