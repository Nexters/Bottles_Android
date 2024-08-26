package com.team.bottles

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.messaging.FirebaseMessaging
import com.team.bottles.core.designsystem.theme.BottlesTheme
import com.team.bottles.core.domain.auth.repository.AuthRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
  
    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        createNotificationChannel()
        initializeFcm()

        setContent {
            BottlesTheme {
                BottlesApp()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.IO) {
            authRepository.updateFcmTokenToServer()
        }
    }

    private fun createNotificationChannel() {
        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channelId = getString(R.string.fcm_channel_id)
        val channelName = getString(R.string.fcm_channel_name)
        val notificationChannel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(notificationChannel)
    }

    private fun initializeFcm() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Timber.tag("FCM").d("Fail To Get FCM Instance")
                    return@OnCompleteListener
                }
                val result = task.result
                lifecycleScope.launch(Dispatchers.IO) {
                    val savedLocalToken = authRepository.getSavedLocalFcmToken()
                    if (savedLocalToken.isEmpty() || savedLocalToken != result) {
                        authRepository.updateLocalFcmToken(fcmToken = result)
                    }
                }
                Timber.tag("FCM").d("Success To Get FCM Instance >> $result")
            }
        )
    }

}
