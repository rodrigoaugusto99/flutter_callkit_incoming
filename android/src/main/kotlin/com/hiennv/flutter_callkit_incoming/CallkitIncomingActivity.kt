package com.hiennv.flutter_callkit_incoming

import android.app.Activity
import android.app.KeyguardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Button
import com.hiennv.flutter_callkit_incoming.widgets.RippleRelativeLayout
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.abs
import android.view.ViewGroup.MarginLayoutParams
import android.os.PowerManager
import android.text.TextUtils
import android.util.Log

class CallkitIncomingActivity : Activity() {

    companion object {

        private const val ACTION_ENDED_CALL_INCOMING =
            "com.hiennv.flutter_callkit_incoming.ACTION_ENDED_CALL_INCOMING"

        fun getIntent(context: Context, data: Bundle) =
            Intent(CallkitConstants.ACTION_CALL_INCOMING).apply {
                action = "${context.packageName}.${CallkitConstants.ACTION_CALL_INCOMING}"
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

    private lateinit var ivBackground: ImageView
    private lateinit var llBackgroundAnimation: RippleRelativeLayout

    private lateinit var tvNameCaller: TextView
    private lateinit var tvNumber: TextView
    private lateinit var tvPickupAddress: TextView
    private lateinit var tvDeliveryAddress: TextView
    private lateinit var tvEstimatedTime: TextView
    private lateinit var ivLogo: ImageView
    private lateinit var ivAvatar: CircleImageView

    private lateinit var llAction: LinearLayout
    private lateinit var btnAccept: Button
    private lateinit var btnDecline: Button

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = if (!Utils.isTablet(this@CallkitIncomingActivity)) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
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
        setContentView(R.layout.activity_callkit_incoming)
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

    private fun wakeLockRequest(duration: Long) {

        val pm = applicationContext.getSystemService(POWER_SERVICE) as PowerManager
        val wakeLock = pm.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP,
            "Callkit:PowerManager"
        )
        wakeLock.acquire(duration)
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
        if (data == null) finish()

        val isShowFullLockedScreen =
            data?.getBoolean(CallkitConstants.EXTRA_CALLKIT_IS_SHOW_FULL_LOCKED_SCREEN, true)
        if (isShowFullLockedScreen == true) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                setShowWhenLocked(true)
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
            }
        }

        val textColor = data?.getString(CallkitConstants.EXTRA_CALLKIT_TEXT_COLOR, "#ffffff")
        val isShowCallID = data?.getBoolean(CallkitConstants.EXTRA_CALLKIT_IS_SHOW_CALL_ID, false)
        tvNameCaller.text = data?.getString(CallkitConstants.EXTRA_CALLKIT_NAME_CALLER, "")
        tvNumber.text = data?.getString(CallkitConstants.EXTRA_CALLKIT_HANDLE, "")
        tvNumber.visibility = if (isShowCallID == true) View.VISIBLE else View.INVISIBLE

        try {
            tvNameCaller.setTextColor(Color.parseColor(textColor))
            tvNumber.setTextColor(Color.parseColor(textColor))
        } catch (error: Exception) {
        }

        val extra = data?.getSerializable(CallkitConstants.EXTRA_CALLKIT_EXTRA) as? HashMap<String, Any?>
        
        val pickupAddress = extra?.get("pickupAddress") as? String
        if (!pickupAddress.isNullOrEmpty()) {
            tvPickupAddress.text = "Coleta: $pickupAddress"
            tvPickupAddress.visibility = View.VISIBLE
        }

        val deliveryAddress = extra?.get("deliveryAddress") as? String
        if (!deliveryAddress.isNullOrEmpty()) {
            tvDeliveryAddress.text = "Entrega: $deliveryAddress"
            tvDeliveryAddress.visibility = View.VISIBLE
        }

        val estimatedTime = extra?.get("estimatedTime") as? String
        if (!estimatedTime.isNullOrEmpty()) {
            tvEstimatedTime.text = "Tempo estimado: $estimatedTime"
            tvEstimatedTime.visibility = View.VISIBLE
        }

        val isShowLogo = data?.getBoolean(CallkitConstants.EXTRA_CALLKIT_IS_SHOW_LOGO, false)
        ivLogo.visibility = if (isShowLogo == true) View.VISIBLE else View.INVISIBLE
        var logoUrl = data?.getString(CallkitConstants.EXTRA_CALLKIT_LOGO_URL, "")
        if (!logoUrl.isNullOrEmpty()) {
            if (!logoUrl.startsWith("http://", true) && !logoUrl.startsWith("https://", true)) {
                logoUrl = String.format("file:///android_asset/flutter_assets/%s", logoUrl)
            }
            val headers =
                data?.getSerializable(CallkitConstants.EXTRA_CALLKIT_HEADERS) as HashMap<String, Any?>
            ImageLoaderProvider.loadImage(this@CallkitIncomingActivity, logoUrl, headers, R.drawable.transparent, ivLogo)
        }

        var avatarUrl = data?.getString(CallkitConstants.EXTRA_CALLKIT_AVATAR, "")
        if (!avatarUrl.isNullOrEmpty()) {
            ivAvatar.visibility = View.VISIBLE
            if (!avatarUrl.startsWith("http://", true) && !avatarUrl.startsWith("https://", true)) {
                avatarUrl = String.format("file:///android_asset/flutter_assets/%s", avatarUrl)
            }
            val headers =
                data?.getSerializable(CallkitConstants.EXTRA_CALLKIT_HEADERS) as HashMap<String, Any?>
            ImageLoaderProvider.loadImage(this@CallkitIncomingActivity, avatarUrl, headers, R.drawable.ic_default_avatar, ivAvatar)
        }

        val duration = data?.getLong(CallkitConstants.EXTRA_CALLKIT_DURATION, 0L) ?: 0L
        wakeLockRequest(duration)

        finishTimeout(data, duration)

        val backgroundColor =
            data?.getString(CallkitConstants.EXTRA_CALLKIT_BACKGROUND_COLOR, "#0955fa")
        try {
            ivBackground.setBackgroundColor(Color.parseColor(backgroundColor))
        } catch (error: Exception) {
        }
        var backgroundUrl = data?.getString(CallkitConstants.EXTRA_CALLKIT_BACKGROUND_URL, "")
        if (!backgroundUrl.isNullOrEmpty()) {
            if (!backgroundUrl.startsWith("http://", true) && !backgroundUrl.startsWith(
                    "https://",
                    true
                )
            ) {
                backgroundUrl =
                    String.format("file:///android_asset/flutter_assets/%s", backgroundUrl)
            }
            val headers =
                data?.getSerializable(CallkitConstants.EXTRA_CALLKIT_HEADERS) as HashMap<String, Any?>
            ImageLoaderProvider.loadImage(this@CallkitIncomingActivity, backgroundUrl, headers, R.drawable.transparent, ivBackground)
        }
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
        ivBackground = findViewById(R.id.ivBackground)
        llBackgroundAnimation = findViewById(R.id.llBackgroundAnimation)
        llBackgroundAnimation.layoutParams.height =
            Utils.getScreenWidth() + Utils.getStatusBarHeight(this@CallkitIncomingActivity)
        llBackgroundAnimation.startRippleAnimation()

        tvNameCaller = findViewById(R.id.tvNameCaller)
        tvNumber = findViewById(R.id.tvNumber)
        tvPickupAddress = findViewById(R.id.tvPickupAddress)
        tvDeliveryAddress = findViewById(R.id.tvDeliveryAddress)
        tvEstimatedTime = findViewById(R.id.tvEstimatedTime)
        ivLogo = findViewById(R.id.ivLogo)
        ivAvatar = findViewById(R.id.ivAvatar)

        llAction = findViewById(R.id.llAction)

        val params = llAction.layoutParams as MarginLayoutParams
        params.setMargins(0, 0, 0, Utils.getNavigationBarHeight(this@CallkitIncomingActivity))
        llAction.layoutParams = params

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
        // Log.d("CallkitIncomingActivity", "[CALLKIT] 📱 onAcceptClick")
        val data = intent.extras?.getBundle(CallkitConstants.EXTRA_CALLKIT_INCOMING_DATA)


        CallkitNotificationService.startServiceWithAction(
            this@CallkitIncomingActivity,
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
        // Log.d("CallkitIncomingActivity", "[CALLKIT] 📱 onDeclineClick")
        val data = intent.extras?.getBundle(CallkitConstants.EXTRA_CALLKIT_INCOMING_DATA)

        val intent =
            CallkitIncomingBroadcastReceiver.getIntentDecline(this@CallkitIncomingActivity, data)
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
