package com.sainjeeapps.smokenameart.models;

import java.io.File;

public class SavedCreationsModel {
    String created_date;
    private File file;
    String file_size;
    private boolean isPhoto;
    private String name;
    private String path;

    public SavedCreationsModel(File file2, String str, String str2, String str3, String str4) {
        this.file = file2;
        this.name = str;
        this.path = str2;
        this.created_date = str3;
        this.file_size = str4;
        this.isPhoto = file2.getName().endsWith(".mp4");
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file2) {
        this.file = file2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public boolean isPhoto() {
        return this.isPhoto;
    }

    public void setPhoto(boolean z) {
        this.isPhoto = z;
    }

    public String getCreated_date() {
        return this.created_date;
    }

    public void setCreated_date(String str) {
        this.created_date = str;
    }

    public String getFile_size() {
        return this.file_size;
    }

    public void setFile_size(String str) {
        this.file_size = str;
    }
}
