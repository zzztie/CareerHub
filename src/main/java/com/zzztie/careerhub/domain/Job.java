package com.zzztie.careerhub.domain;

public class Job {
    private Long id;
    private String title;
    private String company;
    private String description;

    public Job() {
    }

    public Job(Long id, String title, String company, String description) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printInfo(){
        System.out.println("id="+id);
        System.out.println("title="+title);
        System.out.println("company="+company);
        System.out.println("description="+description);
    }
}
