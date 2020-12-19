package com.dominic.toolbox

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.plugins.JavaLibraryPlugin

class ToolboxPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        //a small demo for extension
        project.extensions.create("simple_config", SimpleExtendsion)
        def config = project.extensions.findByType(SimpleExtendsion.class)
        println("simple config name " + config.name)

//        project.extensions.add("simple_config", )
        //create file or file tree

        def srcFile = project.file(project.projectDir.parent + "/src")
        def sourceFileTree = project.fileTree(project.projectDir.path + "/src/main")


        def userTask = project.tasks.create('headerprinter')
        userTask.group = 'toolbox'
        userTask.description = 'hello from headerprinter task'
        userTask.doFirst {
            println("doFirst")
        }
        userTask.doLast(new Action<Task>() {
            @Override
            void execute(Task task) {
                println("doLast()")
            }
        })

        //获取project的所有变体 variant
        def isApp = project.plugins.hasPlugin(ApplicationPlugin.class)
        if (isApp) {
            project.android.applicationVariants.all { variant ->
                println("application variant: " + variant)
            }
        }

        def isJavaLib = project.plugins.hasPlugin(JavaLibraryPlugin.class)
        if (isJavaLib) {
            project.android.libraryVariants.all { variant ->
                println("library variant: " + variant)

            }
        }

    }
}