package com.dominic.toolbox.resprocess.handlers

import com.dominic.toolbox.resprocess.ConfigLines
import com.dominic.toolbox.resprocess.LayoutAttrExtentions

import java.util.regex.Matcher
import java.util.regex.Pattern

class TextViewHandler extends AbsHandler {

    String matchRegex = "\\s<TextView\\s*[^>]+>"
    Pattern workPattern
    ConfigLines fileFilter
    boolean defaultEnable
    @Override
    void init(LayoutAttrExtentions layoutAttrExt) {
        defaultEnable = layoutAttrExt.enable
        fileFilter = new ConfigLines(layoutAttrExt?.confFile)
        workPattern = Pattern.compile(matchregex)
    }

    @Override
    boolean filter(File file) {

        if (fileFilter?.enableLines.any {file.name.equals(it)}) {
            return true
        }
        if (fileFilter?.disableLines.any {file.name.equals(it)}) {
            return false
        }
        return defaultEnable
    }

    @Override
    void handleXml(File xmlFile) {
        def text = xmlFile.text

        def replacedText = text
        Matcher matcher = workPattern.matcher(text)
        while (matcher.find()) {
            def group0 = matcher.group()

        }
    }
}