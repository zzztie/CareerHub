package com.zzztie.careerhub.domain;

public class User extends BaseEntity{
    private String username;
    private String email;

    public User(){ //无参构造器
    }

    public User(Long id,String username,String email){
        super(id);
        this.username=username;
        this.email=email;
    }

    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("username="+username);
        System.out.println("email="+email);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void updateProfile(String username) {
        this.username = username;
    }

    public void updateProfile(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
