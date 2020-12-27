import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction

class GeneratorTask extends DefaultTask {

    File sourceDirectory

    @InputDirectory   //指定该变量用来标记输入目录
    public File getSourceDirectory() {
        return sourceDirectory
    }

    void setSourceDirectory(File sourceDirectory) {
        this.sourceDirectory = sourceDirectory
    }


    @TaskAction
    void execute() {
        println('execute() annotated with @TaskAction')

    }
}