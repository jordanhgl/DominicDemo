package com.dominic.toolbox

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

import java.util.stream.Collectors

class ToolboxPlugin implements Plugin<Project> {
    def TASK_GROUP = 'dominic_toolbox'

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
        def filterFileList = sourceFileTree.toList().stream()
            .filter {!it.isDirectory() && it.path.endsWith(".java")}  //保留jva
            .peek { //注意这里要用{}!!!
                println("after filter file" + it.name + "keeped!")
            }
            .collect(Collectors.toList())
        println("we got " + filterFileList?.size() + " java file in Total!")


        //配置阶段完成后 各个prject的afterEvaluate 会被回调
        project.afterEvaluate {
            println("project: " + project.name + " afterEvaluate{} phase...")
            project.task('CommonPathPrinter') {
                group = TASK_GROUP
                doFirst {

                    //def javaCompileClassPath =
                }
            }
        }

        def userTask = project.tasks.create('headerprinter')
        userTask.group = 'toolbox'
        userTask.description = 'Description defined in headerprinter task'
        userTask.doFirst {
            println("doFirst")
        }
        userTask.doLast(new Action<Task>() {
            @Override
            void execute(Task task) {
                println("doLast111()")
            }
        })

        userTask.doLast {
            println("doLast() for real")

            //获取project的所有变体 variant
            def isApp = project.plugins.hasPlugin(com.android.build.gradle.AppPlugin.class)
            if (isApp) {
                project.android.applicationVariants.all { variant ->
                    println("application variant: " + variant)
                    def compileClzPath = variant.javaCompile.classpath
                    def destDir = variant.javaCompile.destinationDir

                    println("java compileClzPath: " + compileClzPath )
                    println("java compile destDir = " + destDir)

                    def bootPath = project.android.bootClasspath
                    println("bootPath = " + bootPath)
                }
            }

            def isJavaLib = project.plugins.hasPlugin(com.android.build.gradle.LibraryPlugin.class)
            if (isJavaLib) {
                project.android.libraryVariants.all { variant ->
                    println("library variant: " + variant)

                }
            }
        }
    }
}