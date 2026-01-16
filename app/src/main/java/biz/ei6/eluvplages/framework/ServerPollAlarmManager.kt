package biz.ei6.eluvplages.framework

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ServerPollAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ServerPollAlarm", "Alarm received -> start poll")


        val updateIntent = Intent(InternalBroadcasts.ACTION_SERVER_HAS_UPDATE).apply {
            setPackage(context.packageName)

        }
        context.sendBroadcast(updateIntent)
    }
}

