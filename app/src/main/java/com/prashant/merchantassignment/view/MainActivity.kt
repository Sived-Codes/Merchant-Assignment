package com.prashant.merchantassignment.view

import NetworkReceiver
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.prashant.merchantassignment.R
import com.prashant.merchantassignment.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding
    private val mNetworkReceiver: NetworkReceiver = NetworkReceiver()
    private val networkStatusReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == NetworkReceiver.ACTION_NETWORK_STATUS) {
                val isOnline = intent.getBooleanExtra(NetworkReceiver.EXTRA_NETWORK_STATUS, false)
                if (isOnline) {
                    bind.networkStatus.visibility=View.VISIBLE

                    bind.networkStatus.text = "Getting Online Data"
                    bind.networkStatus.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.green))
                    hideStatus()
                } else {
                    bind.networkStatus.visibility=View.VISIBLE

                    bind.networkStatus.text = "Getting Offline Data"
                    bind.networkStatus.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.red))
                    hideStatus()

                }

            }
        }
    }

    private fun hideStatus() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            bind.networkStatus.visibility=View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
    }

    override fun onPause() {
        super.onPause()
        try {

            unregisterReceiver(mNetworkReceiver)
            unregisterReceiver(networkStatusReceiver)
        } catch (e:Exception){}

    }

    override fun onResume() {
        super.onResume()
        try {
            val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            registerReceiver(mNetworkReceiver, filter)
            val statusFilter = IntentFilter(NetworkReceiver.ACTION_NETWORK_STATUS)
            registerReceiver(networkStatusReceiver, statusFilter)
        } catch (e:Exception){}


    }


}
