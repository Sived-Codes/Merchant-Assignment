import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class NetworkReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val isConnected = isOnline(context)

        val networkIntent = Intent(ACTION_NETWORK_STATUS)
        networkIntent.putExtra(EXTRA_NETWORK_STATUS, isConnected)
        context.sendBroadcast(networkIntent)
    }

    companion object {
        const val ACTION_NETWORK_STATUS = "com.prashant.merchantassignment.ACTION_NETWORK_STATUS"
        const val EXTRA_NETWORK_STATUS = "com.prashant.merchantassignment.EXTRA_NETWORK_STATUS"

        fun isOnline(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}
