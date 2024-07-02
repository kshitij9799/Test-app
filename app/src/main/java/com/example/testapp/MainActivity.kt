package com.example.testapp

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.testapp.db.TestApp
import com.example.testapp.fragment.OfflineFragment
import com.example.testapp.fragment.OnlineFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModels()

    init {
        lifecycleScope.launch {
            delay(2500)
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, OnlineFragment())
            }
            findViewById<LinearLayout>(R.id.bottom_bar).visibility = VISIBLE
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val onlineOperation = findViewById<ImageView>(R.id.online_optn)
        val offlineOperation = findViewById<ImageView>(R.id.offline_optn)

        val grayColor = ColorStateList.valueOf(resources.getColor(R.color.gray))
        val blackColor = ColorStateList.valueOf(resources.getColor(R.color.black))

        onlineOperation.imageTintList = blackColor
        offlineOperation.imageTintList = grayColor

        onlineOperation.setOnClickListener {
            onlineOperation.imageTintList = blackColor
            offlineOperation.imageTintList = grayColor
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, OnlineFragment())
            }
        }

        offlineOperation.setOnClickListener {
            onlineOperation.imageTintList = grayColor
            offlineOperation.imageTintList = blackColor
            supportFragmentManager.commit {
                replace(R.id.fragment_container_view, OfflineFragment())
            }
        }

    }
}