package org.example;

import java.util.Date;

public class User {
    private Integer id;
    private Date birthdate;
    private String password;
    private String login;

    public User(Integer id, Date birthdate, String password, String login) {
        this.id = id;
        this.birthdate = birthdate;
        this.password = password;
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
