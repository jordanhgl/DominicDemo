package com.dominic.toolbox.resprocess

import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.tasks.MergeResources
import com.android.ide.common.resources.ResourceSet
import com.dominic.toolbox.util.DLog
import org.gradle.api.Project

class LayoutProcessor extends AbsProcessor {

    ProjectExtentions projConf
    @Override
    void init(PluginContext context) {

    }

    @Override
    boolean checkEnable() {
        if (projConf.enable && projConf?.layConf.enable) {
            return true
        }
        return false
     }

    @Override
    void process(Project project, BaseVariant variant) {
        def lpTask = project.tasks.create("layoutProcessFor" + variant.name)
        lpTask.group = "domtoolbox"

        MergeResources mergeResources = variant.mergeResources
        def variantMergeResName = mergeResources.getVariantName()
        DLog.println("process() in project: "+project.name+" variantMergeResName = " + variantMergeResName)

        lpTask.doLast {
            List<ResourceSet> resSet = mergeResources.resourcesComputer.compute()
            resSet.each {
                it.sourceFiles?.each { File sourceFile ->
                    DLog.println("sourceFile = " + sourceFile.name)
                }
            }
        }

        variant.assemble.dependsOn(lpTask)
    }
}