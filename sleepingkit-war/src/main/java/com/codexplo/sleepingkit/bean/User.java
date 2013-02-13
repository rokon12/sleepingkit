package com.codexplo.sleepingkit.bean;

import javax.faces.bean.ManagedBean;

/**
 * Created with IntelliJ IDEA.
 * Users: Bazlur Rahman Rokon
 * Date: 2/10/13
 * Time: 1:21 PM
 */
@ManagedBean
public class User {
    private String login;
    private String password;
    private String passwordConfirmed;
    private String emailAddress;
    private int age;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmed() {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(String passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
