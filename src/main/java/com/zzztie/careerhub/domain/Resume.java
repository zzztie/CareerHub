package com.zzztie.careerhub.domain;

public class Resume {
    private Long id;
    private Long userId;
    private String fileName;
    private String filePath;

    public Resume() {
    }

    public Resume(String filepath, String filename, Long userId, Long id) {
        this.filePath = filepath;
        this.fileName = filename;
        this.userId = userId;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFilename() {
        return fileName;
    }

    public void setFilename(String filename) {
        this.fileName = filename;
    }

    public String getFilepath() {
        return filePath;
    }

    public void setFilepath(String filepath) {
        this.filePath = filepath;
    }

    public void printInfo(){
        System.out.println("id="+id);
        System.out.println("userId="+userId);
        System.out.println("filename="+fileName);
        System.out.println("filepath="+filePath);
    }
}
