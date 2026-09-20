package com.zzztie.careerhub.domain;


import java.time.LocalDateTime;

public abstract class BaseEntity {
    private Long id;
    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

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
