package com.prashant.merchantassignment.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.prashant.merchantassignment.R
import com.prashant.merchantassignment.core.MyApplication
import com.prashant.merchantassignment.databinding.ActivityMainBinding
import com.prashant.merchantassignment.viewmodel.RoomViewModel
import com.prashant.merchantassignment.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    private val viewModel: RoomViewModel by viewModels {
        UserViewModelFactory((application as MyApplication).userRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)


    }
}