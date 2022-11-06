package model;

import java.time.LocalDate;

public abstract class User {
    private String nickName;
    private String id;
    private LocalDate linkDate;
    
    public User(String nickName, String id) {
        this.nickName = nickName;
        this.id = id;
        this.linkDate= LocalDate.now();
    }

    public String getNickName() {
        return nickName;
    }

    public String getId() {
        return id;
    }

    public LocalDate getLinkDate() {
        return linkDate;
    }
    
}