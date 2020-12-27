package com.dominic.toolbox.resprocess

import java.util.function.Function

/**
 *
 */
class ConfigLines {
    Set<String> enableLines = new HashSet<String>()
    Set<String> disableLines = new HashSet<String>()

    /**
     * 加载配置文件
     * @param confFile
     */
    void loadCfg(File confFile) {
        if (confFile.exists()) {
            confFile.readLines().stream()
                .map(new Function<String, String>() {
                    @Override
                    String apply(String line) {
                        return line?.trim()
                    }
                })
                .filter{it?.length() > 0 && !it.startsWith("#")}
                .each {
                    if (!it.trim().endsWith(".xml")) {
                        return
                    }
                    if (it.trim().startWith("!")) {
                        disableLines.add(it)
                    } else {
                        enableLines.add(it)
                    }
                }
        }
    }
}