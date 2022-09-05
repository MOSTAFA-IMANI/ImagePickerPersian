package com.github.mostafa_imani.imagepickerpersian.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import java.util.*

object ResourceHelper {
    fun getLocalizedResources(context: Context, desiredLocale: Locale?): Resources {
        var conf: Configuration = context.resources.configuration
        conf = Configuration(conf)
        conf.setLocale(desiredLocale)
        val localizedContext = context.createConfigurationContext(conf)
        return localizedContext.resources
    }
}