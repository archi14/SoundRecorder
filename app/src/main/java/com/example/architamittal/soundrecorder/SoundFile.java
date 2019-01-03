package com.example.architamittal.soundrecorder;

public class SoundFile {
    String Filename;
    String FilePath;
    String DateTime;
    public SoundFile()
    {

    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public SoundFile(String filename, String filePath, String DateTime) {
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
