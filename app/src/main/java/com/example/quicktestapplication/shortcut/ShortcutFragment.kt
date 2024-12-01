package com.example.quicktestapplication.shortcut

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.quicktestapplication.presentation.common.BaseFragment
import com.example.quicktestapplication.presentation.common.BaseFragmentContent
import com.example.quicktestapplication.presentation.common.setComposeViewContent
import com.example.quicktestapplication.utils.ToastWrapper.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShortcutFragment : BaseFragment() {
    private val shortcutHelper = ShortcutHelper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setComposeViewContent(composeView) {
            ShortcutFragmentContent(lifecycleScope, requireActivity(), shortcutHelper)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ShortcutFragmentContent(
    lifecycleScope: LifecycleCoroutineScope,
    activity: Activity,
    shortcutHelper: ShortcutHelper
) {
    val context = LocalContext.current
    BaseFragmentContent("Test shortcut") { topPadding ->
        FlowRow(
            Modifier.padding(top = topPadding)
        ) {
            Button(
                onClick = {
                    lifecycleScope.launch {
                        var granted = false
                        try {
                            val clazz = Class.forName("android.content.pm.LauncherApps")
                            val method =
                                clazz.getMethod("canManagePinnedShortcuts", String::class.java)
                            val launcherApps =
                                context.getSystemService(Context.LAUNCHER_APPS_SERVICE)
                            granted = method.invoke(launcherApps, context.packageName) as Boolean
                            showToast(context, "Granted shortcut permission = $granted")
                        } catch (e: Exception) {
                            granted = false
                            showToast(context, "Granted shortcut permission = $granted\n$e")
                        }
                    }
                }
            ) {
                Text(text = "Check Xiaomi shortcut permission 1")
            }

            Button(
                onClick = {
                    shortcutHelper.checkXiaomiShortcutPermission2(context)
                }
            ) {
                Text(text = "Check Xiaomi shortcut permission 2")
            }

            Button(
                onClick = {
                    shortcutHelper.checkXiaomiShortcutPermission3(context)
                }
            ) {
                Text(text = "Check Xiaomi shortcut permission 3")
            }

            Button(
                onClick = {
                    lifecycleScope.launch(Dispatchers.IO) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                            val pinedShortcuts = getSystemService(
                                context.applicationContext,
                                ShortcutManager::class.java
                            )?.pinnedShortcuts
                            showToast(context, "pinedShortcuts = $pinedShortcuts")
                        } else {
                            showToast(context, "Cannot get pinned shortcut for API < 25")
                        }
                    }
                }
            ) {
                Text(text = "Get pinned shortcut")
            }

            Button(
                onClick = {
                    if (Build.MANUFACTURER.lowercase() == "xiaomi") {
                        val localIntent = Intent("miui.intent.action.APP_PERM_EDITOR");
                        localIntent.setClassName(
                            "com.miui.securitycenter",
                            "com.miui.permcenter.permissions.PermissionsEditorActivity"
                        )
                        localIntent.putExtra("extra_pkgname", activity.packageName);
                        activity.startActivity(localIntent)
                    } else {
                        showToast(context, "This is not a Xiaomi phone")
                    }
                }
            ) {
                Text(text = "Open setting Xiaomi screen")
            }

            Button(
                onClick = {
                    shortcutHelper.createPinnedShortcut(context)
                }
            ) {
                Text(text = "Pin a shortcut")
            }

            Button(
                onClick = {
                    shortcutHelper.updateShortcut(context)
                }
            ) {
                Text(text = "Update shortcut")
            }

            Button(
                onClick = {
                    shortcutHelper.disableShortcut(context)
                }
            ) {
                Text(text = "Disable shortcut")
            }
        }
    }
}