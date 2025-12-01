package com.hiennv.flutter_callkit_incoming

import android.app.Activity
import android.app.KeyguardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import kotlin.math.abs

class DeliveryCallActivity : Activity() {

    companion object {
        private const val ACTION_ENDED_CALL_INCOMING =
            "com.hiennv.flutter_callkit_incoming.ACTION_ENDED_CALL_INCOMING"

        fun getIntent(context: Context, data: Bundle) =
            Intent(context, DeliveryCallActivity::class.java).apply {
                putExtra(CallkitConstants.EXTRA_CALLKIT_INCOMING_DATA, data)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

        fun getIntentEnded(context: Context, isAccepted: Boolean): Intent {
            val intent = Intent("${context.packageName}.${ACTION_ENDED_CALL_INCOMING}")
            intent.putExtra("ACCEPTED", isAccepted)
            return intent
        }
    }

    inner class EndedCallkitIncomingBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (!isFinishing) {
                val isAccepted = intent.getBooleanExtra("ACCEPTED", false)
                if (isAccepted) {
                    finishDelayed()
                } else {
                    finishTask()
                }
            }
        }
    }

    private var endedCallkitIncomingBroadcastReceiver = EndedCallkitIncomingBroadcastReceiver()

    private lateinit var tvAppName: TextView
    private lateinit var tvPickupAddress: TextView
    private lateinit var tvDeliveryAddress: TextView
    private lateinit var tvEstimatedTime: TextView
    private lateinit var btnAccept: Button
    private lateinit var btnDecline: Button

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            setTurnScreenOn(true)
            setShowWhenLocked(true)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
            window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
            window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        }
        
        transparentStatusAndNavigation()
        setContentView(R.layout.activity_delivery_call)
        
        initView()
        incomingData(intent)
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                endedCallkitIncomingBroadcastReceiver,
                IntentFilter("${packageName}.${ACTION_ENDED_CALL_INCOMING}"),
                Context.RECEIVER_EXPORTED,
            )
        } else {
            registerReceiver(
                endedCallkitIncomingBroadcastReceiver,
                IntentFilter("${packageName}.${ACTION_ENDED_CALL_INCOMING}")
            )
        }
    }

    private fun transparentStatusAndNavigation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setWindowFlag(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, true
            )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setWindowFlag(
                (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION), false
            )
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win: Window = window
        val winParams: WindowManager.LayoutParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    private fun incomingData(intent: Intent) {
        val data = intent.extras?.getBundle(CallkitConstants.EXTRA_CALLKIT_INCOMING_DATA)
        if (data == null) {
            android.util.Log.d("DeliveryCallActivity", "Data is NULL!")
            finish()
            return
        }

        val extra = data.getSerializable(CallkitConstants.EXTRA_CALLKIT_EXTRA) as? HashMap<String, Any?>
        android.util.Log.d("DeliveryCallActivity", "Extra data: $extra")

        val appName = data.getString(CallkitConstants.EXTRA_CALLKIT_APP_NAME, "App")
        tvAppName.text = appName
        android.util.Log.d("DeliveryCallActivity", "App name: $appName")

        val pickupAddress = extra?.get("pickupAddress") as? String ?: "Endereço não informado"
        tvPickupAddress.text = pickupAddress
        android.util.Log.d("DeliveryCallActivity", "Pickup: $pickupAddress")

        val deliveryAddress = extra?.get("deliveryAddress") as? String ?: "Endereço não informado"
        tvDeliveryAddress.text = deliveryAddress
        android.util.Log.d("DeliveryCallActivity", "Delivery: $deliveryAddress")

        val estimatedTime = extra?.get("estimatedTime") as? String ?: "Não informado"
        tvEstimatedTime.text = estimatedTime
        android.util.Log.d("DeliveryCallActivity", "Time: $estimatedTime")

        val acceptText = extra?.get("acceptText") as? String ?: "Aceitar"
        btnAccept.text = acceptText
        android.util.Log.d("DeliveryCallActivity", "Accept text: $acceptText")

        val declineText = extra?.get("declineText") as? String ?: "Rejeitar"
        btnDecline.text = declineText
        android.util.Log.d("DeliveryCallActivity", "Decline text: $declineText")

        val duration = data.getLong(CallkitConstants.EXTRA_CALLKIT_DURATION, 30000L)
        finishTimeout(data, duration)
    }

    private fun finishTimeout(data: Bundle?, duration: Long) {
        val currentSystemTime = System.currentTimeMillis()
        val timeStartCall =
            data?.getLong(CallkitNotificationManager.EXTRA_TIME_START_CALL, currentSystemTime)
                ?: currentSystemTime

        val timeOut = duration - abs(currentSystemTime - timeStartCall)
        Handler(Looper.getMainLooper()).postDelayed({
            if (!isFinishing) {
                finishTask()
            }
        }, timeOut)
    }

    private fun initView() {
        tvAppName = findViewById(R.id.tvAppName)
        tvPickupAddress = findViewById(R.id.tvPickupAddress)
        tvDeliveryAddress = findViewById(R.id.tvDeliveryAddress)
        tvEstimatedTime = findViewById(R.id.tvEstimatedTime)
        btnAccept = findViewById(R.id.btnAccept)
        btnDecline = findViewById(R.id.btnDecline)

        btnAccept.setOnClickListener {
            onAcceptClick()
        }
        
        btnDecline.setOnClickListener {
            onDeclineClick()
        }
    }

    private fun onAcceptClick() {
        val data = intent.extras?.getBundle(CallkitConstants.EXTRA_CALLKIT_INCOMING_DATA)

        CallkitNotificationService.startServiceWithAction(
            this@DeliveryCallActivity,
            CallkitConstants.ACTION_CALL_ACCEPT,
            data
        )

        val acceptIntent =
            TransparentActivity.getIntent(this, CallkitConstants.ACTION_CALL_ACCEPT, data)
        startActivity(acceptIntent)

        dismissKeyguard()
        finish()
    }

    private fun dismissKeyguard() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        }
    }

    private fun onDeclineClick() {
        val data = intent.extras?.getBundle(CallkitConstants.EXTRA_CALLKIT_INCOMING_DATA)

        val intent =
            CallkitIncomingBroadcastReceiver.getIntentDecline(this@DeliveryCallActivity, data)
        sendBroadcast(intent)
        finishTask()
    }

    private fun finishDelayed() {
        Handler(Looper.getMainLooper()).postDelayed({
            finishTask()
        }, 1000)
    }

    private fun finishTask() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask()
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        unregisterReceiver(endedCallkitIncomingBroadcastReceiver)
        super.onDestroy()
    }

    override fun onBackPressed() {}
}

