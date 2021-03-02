package com.dominic.toolbox.resprocess

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Action

class ProjectExtentions {

    boolean enable                  //top-level toggle
    LayoutAttrExtentions layConf

    ProjectExtentions() {
        this.layConf = new LayoutAttrExtentions()
    }

    LayoutAttrExtentions getLayConf() {
        return layConf
    }

    void setLayConf(LayoutAttrExtentions layConf) {
        this.layConf = layConf
    }

    void layConf(Action<? super LayoutAttrExtentions> action) {
        action.execute(layConf)
    }

}