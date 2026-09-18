package com.zzztie.careerhub.domain;


public abstract class BaseEntity {
    private Long id;

    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id=id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void printInfo() {
        System.out.println("id=" + id);
    }

    public abstract String getEntityType();

}
