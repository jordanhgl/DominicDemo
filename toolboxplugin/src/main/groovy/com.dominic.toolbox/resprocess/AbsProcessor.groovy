package com.dominic.toolbox.resprocess

import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Project


abstract class AbsProcessor {

    abstract void init(PluginContext context);

    abstract boolean checkEnable();

    abstract void process(Project project, BaseVariant variant)
}