package com.example.quicktestapplication.shortcut

import android.app.AppOpsManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import com.example.quicktestapplication.MainActivity
import com.example.quicktestapplication.R
import com.example.quicktestapplication.utils.ToastWrapper.showToast

class ShortcutHelper {
    private val shortcutId = "shortcutId"
    
    fun createPinnedShortcut(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = context.getSystemService(ShortcutManager::class.java)
            if (shortcutManager.isRequestPinShortcutSupported) {
                // Define the intent for the shortcut
                val shortcutIntent = Intent(context, MainActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    putExtra("source", "Shortcut")
                }

                // Create a shortcut
                val shortcut = ShortcutInfo.Builder(context, shortcutId)
                    .setShortLabel("My Shortcut")
                    .setLongLabel("Open My Shortcut")
                    .setIcon(Icon.createWithResource(context, R.drawable.baseline_adb_24))
                    .setIntent(shortcutIntent)
                    .build()

                // Request to pin the shortcut
                val successCallback = PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent(context, ShortcutPinnedReceiver::class.java),
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )
                val success =
                    shortcutManager.requestPinShortcut(shortcut, successCallback.intentSender)
                showToast(context, "Support pin shortcut? $success")

            } else {
                showToast(context, "Launcher does not support pin shortcut")
            }
        }
    }

    fun updateShortcut(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = context.getSystemService(ShortcutManager::class.java)
            val shortcutIntent = Intent(context, MainActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                putExtra("source", "Shortcut")
            }
            val shortcut = ShortcutInfo.Builder(context, shortcutId)
                .setShortLabel("Updated Short Label")
                .setLongLabel("Updated Long Label")
                .setIcon(Icon.createWithResource(context, R.drawable.baseline_adb_24))
                .setIntent(shortcutIntent)
                .build()
            val updated = shortcutManager.updateShortcuts(listOf(shortcut))
            if (updated) {
                showToast(context, "Updated shortcut")
            } else {
                showToast(context, "Cannot update shortcut")
            }
        }
    }
    
    fun disableShortcut(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val shortcutManager = context.getSystemService(ShortcutManager::class.java)
            shortcutManager.disableShortcuts(listOf(shortcutId))
        }
    }

    fun checkXiaomiShortcutPermission2(context: Context) {
        val appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        try {
            val method = AppOpsManager::class.java.getMethod(
                "checkOpNoThrow",
                String::class.java,
                Int::class.java,
                String::class.java
            )
            val result = method.invoke(
                appOpsManager,
                "OP_CREATE_SHORTCUT", // MIUI-specific shortcut operation
                android.os.Process.myUid(),
                context.packageName
            ) as Int
            result == AppOpsManager.MODE_ALLOWED
            showToast(context, "Had shortcut permission: $result")
        } catch (e: Exception) {
            false // Default to false if reflection fails
            showToast(context, "Had shortcut permission: false\n$e")
        }
    }

    fun checkXiaomiShortcutPermission3(context: Context) {
        val appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        try {
            // Use reflection to access the noteOpNoThrow method
            val method = AppOpsManager::class.java.getMethod(
                "noteOpNoThrow",
                String::class.java,
                Int::class.java,
                String::class.java
            )
            // Xiaomi-specific permission for shortcut creation
            val opCode = "android:launcher_permission" // Adjust if you know the actual MIUI op string
            val result = method.invoke(
                appOpsManager,
                opCode,
                android.os.Process.myUid(),
                context.packageName
            ) as Int
            result == AppOpsManager.MODE_ALLOWED
            showToast(context, "Had shortcut permission: $result")
        } catch (e: Exception) {
            // Handle exceptions or unsupported devices
            false
            showToast(context, "Had shortcut permission: false\n$e")
        }
    }
}

class ShortcutPinnedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Check if the broadcast indicates a successful pin
        showToast(context, "Pinned shortcut")
    }

}