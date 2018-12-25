package com.example.architamittal.soundrecorder;

public class SoundFile {
    String Filename;
    String FilePath;

    public SoundFile(String filename, String filePath) {
        Filename = filename;
        FilePath = filePath;
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }


}
