package com.sam_solutions.ealys.test.dao;

import com.sam_solutions.ealys.dao.UserDAO;
import com.sam_solutions.ealys.entity.Project;
import com.sam_solutions.ealys.entity.Skill;
import com.sam_solutions.ealys.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(locations = "classpath:spring-mvc-servlet-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class TestUserDAO {
    private final static Logger logger = Logger.getLogger(Project.class);

    @Autowired
    private UserDAO userDAO;

    private User firstUser;
    private User secondUser;
    private User thirdUser;
    private Skill skill;

    @Before
    public void prepareSamples(){
        firstUser = new User();
        secondUser = new User();
        thirdUser = new User();

        skill = new Skill();
        skill.setName("Spring MVC");
        firstUser.getSkills().add(skill);
        firstUser.setUsername("user1");
        firstUser.setEmail("user1@gmail.com");
        secondUser.setUsername("user2");
        secondUser.setEmail("user2@gmail.com");
        secondUser.getSkills().add(skill);
        thirdUser.getSkills().add(skill);
    }

    @Test
    public void testAddUser(){
        try {
            userDAO.create(firstUser);
        }
        catch (AssertionError ex){
            logger.error("Error function testAddUser(). Arguments not equals in function assertEquals()", ex);
        }
        catch (JDBCException ex){
            logger.error(ex);
        }
    }

    @Test
    public void testUpdateUser(){
        userDAO.create(firstUser);
        firstUser.setUsername("changeName");
        userDAO.update(firstUser);
        Assert.assertEquals(firstUser.getUsername(), userDAO.getUserByName(firstUser.getUsername()).getUsername());
    }

    @Test
    public void testGetUserById(){
        userDAO.create(firstUser);
        Assert.assertEquals(firstUser, userDAO.read(User.class, firstUser.getUserId()));
    }

    @Test
    public void testGetUserByUsername(){
        userDAO.create(firstUser);
        Assert.assertEquals(firstUser, userDAO.getUserByName(firstUser.getUsername()));
    }

    @Test
    public void testGetUserByEmail(){
        userDAO.create(firstUser);
        Assert.assertEquals(firstUser, userDAO.getUserByEmail(firstUser.getEmail()));
    }

    @Test
    public void testDeleteUser(){
        userDAO.create(firstUser);
        userDAO.delete(firstUser);
    }

    @Test
    public void testGetAmountUsers(){
        userDAO.create(firstUser);
        userDAO.create(secondUser);
        Assert.assertEquals(2, userDAO.getAmountUsers());
    }

    @Test
    public void testGetAmountUsersWithSkill(){
        userDAO.create(firstUser);
        Assert.assertEquals(1, userDAO.getAmountUsersWithSkill("Spring MVC"));
    }

    @Test
    public void testFindUsersWithSkill(){
        userDAO.create(firstUser);
        userDAO.create(secondUser);
        userDAO.create(thirdUser);
        Assert.assertEquals(2, userDAO.findUsersWithSkill("Spring MVC", 0).size());
        Assert.assertEquals(1, userDAO.findUsersWithSkill("Spring MVC", 2).size());
    }
}
