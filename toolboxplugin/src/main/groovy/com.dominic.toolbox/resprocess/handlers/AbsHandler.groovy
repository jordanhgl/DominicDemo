package com.dominic.toolbox.resprocess.handlers

import com.dominic.toolbox.resprocess.LayoutAttrExtentions


abstract class AbsHandler {

    abstract void init(LayoutAttrExtentions layoutAttrExtentions)

    abstract boolean filter(File file)
    abstract void handleXml(File xmlFile)

}