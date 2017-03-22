package com.cd.wzjkj.canyi.tools;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by liuzheng on 2016/12/7.
 */

public class MyAuthenticator extends Authenticator {
    private String username;
    private String password;
    public MyAuthenticator(){

    }

    public MyAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username,password);
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
