package com.codexplo.sleepingkit.domain;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * Users: Bazlur Rahman Rokon
 * Date: 2/7/13
 * Time: 3:51 PM
 */
@Entity
@NamedQueries({
        @NamedQuery( name = "Users.findAllUsers",  query =   "select u from Users u"),
        @NamedQuery(name = "Users.countUsers", query = "select count(u)  from Users u"),
        @NamedQuery(name = "Users.delete",query = "delete  from Users u where u.id =:id")
})
public class Users extends BaseEnitity {
    @NotNull
    @Size(min = 6, max = 32)
    private String userName;
    @NotNull
    @Size(min = 6, max = 32)
    private String password;
    @Transient
    private String passwordConfirmed;
    @NotNull
    @Size(max = 100)
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
