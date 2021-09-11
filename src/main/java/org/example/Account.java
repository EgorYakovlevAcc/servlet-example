package org.example;

import java.util.Date;
import java.util.Objects;

//org.example.Account
public class Account {
    private Integer id;
    private Date birthdate;
    private String password;
    private String login;

    public Account() {
    }

    public Account(Integer id, Date birthdate, String password, String login) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", birthdate=" + birthdate +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
