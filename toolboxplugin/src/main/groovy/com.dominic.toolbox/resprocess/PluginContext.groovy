package com.dominic.toolbox.resprocess

import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Project

class PluginContext {

    Project project
    ProjectExtentions projectExtentions
    BaseVariant variant

    PluginContext(Project project, ProjectExtentions projectExtentions, BaseVariant variant) {
        this.project = project
        this.projectExtentions = projectExtentions
        this.variant = variant
    }
}