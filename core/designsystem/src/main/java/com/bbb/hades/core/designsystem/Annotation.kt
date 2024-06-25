package com.bbb.hades.core.designsystem

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.compose.ui.tooling.preview.Preview

@Preview(uiMode = UI_MODE_NIGHT_NO, apiLevel = Build.VERSION_CODES.TIRAMISU, showBackground = true, name = "Light theme", widthDp = 500, heightDp = 1000)
@Preview(uiMode = UI_MODE_NIGHT_YES, apiLevel = Build.VERSION_CODES.TIRAMISU, showBackground = true, name = "Dark theme", widthDp = 500, heightDp = 1000)
annotation class ThemePreviews