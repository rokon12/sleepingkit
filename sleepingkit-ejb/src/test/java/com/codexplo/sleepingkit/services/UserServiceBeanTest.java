package com.codexplo.sleepingkit.services;

import com.codexplo.sleepingkit.bean.UserBean;
import com.codexplo.sleepingkit.domain.Users;
import com.codexplo.sleepingkit.utils.DummyData;
import junit.framework.Assert;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Bazlur Rahman Rokon
 * Date: 2/13/13
 * Time: 12:08 PM
 */
@RunWith(Arquillian.class)
public class UserServiceBeanTest {

    @Inject
    private UserServiceBean userBean;

    private List<Users> usersList;

    @Before
    public void setUp(){
        usersList = new ArrayList<Users>();
        usersList.addAll(DummyData.getUserses(5));
    }

    @Deployment
    public static Archive<?> createDevelopment() {
        return ShrinkWrap.create(JavaArchive.class, "UserBeanService.jar").addClasses(UserServiceBean.class, Users.class)
                .addAsManifestResource("test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }

    @Test
    public void testSaveUser() throws Exception {
        for (Users u: usersList){
            userBean.saveUser(u);
        }
        //Assert.assertEquals(5, userBean.countUser());
    }
    @Test
    public void testCountUsers(){
        Assert.assertEquals(5, userBean.countUser());
    }


    @Test
    public void testFindUser() throws Exception {
        Users user = userBean.findUser((long)1);
        Assert.assertNotNull(user);
    }


    @Test
    public void testUpdateUser() throws Exception {
        Users user = userBean.findUser((long)1);
        user.setUserName("rokon");
        userBean.updateUser(user);

    }

    @Test
    public void testDeleteUser() throws Exception {

    }

    @Test
    public void testFindUsers() throws Exception {

    }

    @After
    public void tearDown(){

    }
}
