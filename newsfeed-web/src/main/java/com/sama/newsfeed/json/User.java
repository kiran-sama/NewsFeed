package com.sama.newsfeed.json;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ksama on 4/3/16.
 */
@XmlRootElement
public class User {
    String username;
    String email;

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
}
