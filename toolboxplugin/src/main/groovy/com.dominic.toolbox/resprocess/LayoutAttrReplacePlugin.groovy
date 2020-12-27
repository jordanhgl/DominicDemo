package com.dominic.toolbox.resprocess

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

import java.util.function.Consumer
import java.util.stream.Collectors

/**
 * A demo show how to replace layout xml file attributes
 *
 */
class LayoutAttrReplacePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.create("tb_resprocess", ProjectExtentions)

        //每个project在配置完都会回调该方法
        project.afterEvaluate {
            def projCfg = project.extensions.findByType(ProjectExtentions.class)

            //collect all processor into a list
            List<AbsProcessor> allProcessors = Arrays.asList(new LayoutProcessor())
                    .stream()
                    .filter{it.checkEnable(projCfg)} //if enable in config, processor will be kept
                    .tolist(Collectors.toList())

            def isApp = project.plugins.hasPlugin(AppPlugin.class)
            if (isApp) {
                project.android.applicationVariants.all { variant ->
                    def pContext = new PluginContext(project, projCfg, variant)
                    allProcessors.stream()
                        .peek{it.init(pContext)}
                        .forEach(new Consumer<AbsProcessor>() {
                            @Override
                            void accept(AbsProcessor processor) {
                                processor.process(project, variant)
                            }
                        })
                }
            }

            def isLib = project.plugins.hasPlugin(LibraryPlugin.class)
            if (isLib) {
                project.android.libraryVariants.all { variant ->
                    def pContext = new PluginContext(project, projCfg, variant)
                    allProcessors.stream()
                        .peek{it.init(pContext)}
                        .each {
                            it.process(project, variant)
                        }
                }
            }
        }
    }
}