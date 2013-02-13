package com.codexplo.sleepingkit.services;

import com.codexplo.sleepingkit.bean.UserBean;
import com.codexplo.sleepingkit.domain.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Users: Bazlur Rahman Rokon
 * Date: 2/7/13
 * Time: 4:06 PM
 */
@Stateless
@LocalBean
public class UserServiceBean implements UserBean {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceBean.class);

    @PersistenceContext (unitName = "persistDB")
    private EntityManager em;

    @Override
    public Users findUser(Long id) {
        return em.find(Users.class, id);
    }
    @Override
    public void saveUser(Users users) {
        logger.debug("about to persist users..");
        em.persist(users);
        logger.debug("users persisted...");
    }

    @Override
    public void updateUser(Users users) {
        logger.debug("about to users users...");
        em.merge(users);
        logger.debug("users persisted...");
    }

    @Override
    public void deleteUser(Long id) {
       em.createNamedQuery("Users.delete").setParameter("id", id).executeUpdate();
    }

    @Override
    public List<Users> findUsers() {
        Query query = em.createNamedQuery("Users.findAllUsers");
        return query.getResultList();
    }

    @Override
    public long countUser() {
        TypedQuery query =em.createNamedQuery("Users.countUsers",Long.class);
        return ((Number)query.getSingleResult()).longValue();
    }
}
