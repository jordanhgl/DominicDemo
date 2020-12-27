package com.dominic.toolbox.util

class FileLog {
    File logFile
    boolean isAppend = true
    FileLog(String filePath, boolean append) {
        File logF = new File(filpath)
        if (!logF.exists()) {
            logF.createNewFile()
        } else if (!append) {
            logF.delete()
            logF.createNewFile()
        }
    }

    void log2File(Object msg) {
        try {
            FileWriter fileWriter = new FileWriter(logFile, isAppend)
            fileWriter.write(String.valueof(msg))
            filewriter.write("\n")
            filewriter.flush()
        } catch (Exception e) {
            DLog.println("write msg to log file catch Exception:" + e?.getMessage())
        }
    }
}