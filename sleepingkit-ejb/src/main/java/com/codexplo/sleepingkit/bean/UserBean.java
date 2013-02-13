package com.codexplo.sleepingkit.bean;

import com.codexplo.sleepingkit.domain.Users;

import javax.ejb.Local;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * Users: Bazlur Rahman Rokon
 * Date: 2/11/13
 * Time: 2:42 PM
 */
//@Local
public interface UserBean {
    public Users findUser(Long id);
    public void saveUser(Users users);
    public void updateUser(Users users);
    public void deleteUser(Long id);
    public List<Users> findUsers();
    public long countUser();
}
