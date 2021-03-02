package com.dominic.toolbox.resprocess.transf

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import javassist.NotFoundException
import org.apache.commons.io.FileUtils


class FirstTransform extends Transform {

    @Override
    String getName() {
        return "DomFirstTransform"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)

        for(TransformInput inputItem : transformInvocation.getInputs()) {
            inputItem.getJarInputs().parallelStream().each { JarInput  jarInput ->
                //对jar文件不操作，需要拷贝到out目录
                File src = jarInput.getFile()
                println("jarinput src = " + src.getPath())
                File des = transformInvocation.getOutputProvider().getContentLocation(
                            jarInput.getName(), jarInput.getContentTypes(), jarInput.getScopes(), Format.JAR)
                try {
                    FileUtils.copyFile(src, des)
                } catch (Exception e){
                    println("catch exception: " + e.getMessage())
                }

            }

            inputItem.getDirectoryInputs().parallelStream().each { DirectoryInput dircInput ->
                File src = dircInput.getFile()
                println("dirinput src = " + src.getPath())
                File des = transInvocation.getOutputProvider().getContentLocation(
                        direInput.getName(), direInput.ContentType(),
                        direInput.getScopes(), Format.DIRECTORY)

                try {
                    doInsertMarker(src.getAbsolutePath())
                    FileUtils.copyDirectory(src, des)
                } catch(Exception e) {
                    println("catch directory exception: " + e.getMessage())
                }

            }
        }
    } //end of transform


    void doInsertMarker(String path) {
        ClassPool classPool = ClassPool.getDefault()
        classPool.appendClassPath(path)
        CtClass ctClass = classPool.getCtClass("com.dominic.demos.MainActivity")
        if (ctClass == null) {
            println("search target class failed, RETURN!")
            return
        }

        if (ctClass.isfrozen()) {
            ctClass.defrost()
        }

        try {
            CtMethod ctMethod = ctClass.getDeclaredMethod("onCreate")
            ctMethod.insertAfter("Log.d(\"dom_tag\", \"well this is added by transform\")")
            ctClass..writeFile()
        } catch (NotFoundException exception ) {
            System.err.println("onCreate method not found!!!")
        }

        ctClass.detach()





    }

}
