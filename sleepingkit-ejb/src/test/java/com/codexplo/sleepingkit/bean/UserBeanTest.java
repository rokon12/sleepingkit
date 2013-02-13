package com.codexplo.sleepingkit.bean;

import com.codexplo.sleepingkit.domain.Users;
import com.codexplo.sleepingkit.services.UserServiceBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Users: Bazlur Rahman Rokon
 * Date: 2/7/13
 * Time: 5:06 PM
 */
@RunWith(Arquillian.class)
public class UserBeanTest {

    @Inject
    UserServiceBean userServiceBean;

    @Deployment
    public static JavaArchive createTestableDeployment() {
        final JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "sleepingkit.jar")
                .addClasses(Users.class, UserServiceBean.class)
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                        // Enable CDI
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
        return jar;
    }

    @Test
    public void testSaveUser() throws Exception {
        Users users = new Users();
        users.setPassword("rokonoid12");
        users.setUserName("rokonoid");
        users.setEmail("anm_brr@yahoo.com");
        userServiceBean.saveUser(users);
    }

    // @Test
    public void testUpdate() throws Exception {

    }

    //  @Test
    public void testFindAllUser() throws Exception {

    }
}
