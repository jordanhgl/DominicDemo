package com.dominic.toolbox.util

import com.android.build.gradle.AppPlugin
import org.gradle.api.Project

class PluginUtil {


    static boolean isAppModule(Project project) {
        if (project.plugins.hasPlugin(AppPlugin.class)) {
            return true
        }
        return false
    }



}