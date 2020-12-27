package com.dominic.toolbox.resprocess

class ProjectExtentions {

    boolean enable                  //top-level toggle
    LayoutAttrExtentions layConf

    ProjectExtensions() {
        this.layConf = new LayoutAttrExtentions()
    }

    LayoutAttrExtentions getLayConf() {
        return layConf
    }

    void setLayConf(LayoutAttrExtentions layConf) {
        this.layConf = layConf
    }




}