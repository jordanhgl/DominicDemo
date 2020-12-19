package com.dominic.toolbox

import org.gradle.api.Plugin
import org.gradle.api.Project


class DemoPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("Hello this is plugin demo output...")

        def sssTask = project.task("simpletask")
        sssTask.group = "democenter"
        sssTask.doFirst {
            println("dofirst action ")
        }

    }
}