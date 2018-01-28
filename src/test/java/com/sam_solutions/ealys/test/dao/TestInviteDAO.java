package com.sam_solutions.ealys.test.dao;

import com.sam_solutions.ealys.dao.InviteDAO;
import com.sam_solutions.ealys.dao.ProjectDAO;
import com.sam_solutions.ealys.dao.UserDAO;
import com.sam_solutions.ealys.entity.Invite;
import com.sam_solutions.ealys.entity.Project;
import com.sam_solutions.ealys.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContextConfiguration(locations = "classpath:spring-mvc-servlet-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class TestInviteDAO {
    @Autowired
    private InviteDAO inviteDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private UserDAO userDAO;

    @Value("${lifetime.invite}")
    private Long lifetime;

    private Project project;
    private User user;
    private List<Invite> inviteList;
    private Invite firstInvite;
    private Invite secondInvite;
    private final static String TEST_KEY = "123456";
    private final static String TEST_EMAIL = "test@gmail.xom";

    @Before
    public void prepareSamples(){
        project = new Project();
        projectDAO.create(project);

        user =  new User();
        userDAO.create(user);

        firstInvite = new Invite();
        firstInvite.setToken(TEST_KEY);
        firstInvite.setEmail(TEST_EMAIL);
        firstInvite.setSender(user);
        firstInvite.setProject(project);
        firstInvite.setDateOfDeactivating(new Date(new Date().getTime() + lifetime));

        secondInvite = new Invite();
        secondInvite.setSender(user);
        secondInvite.setProject(project);

        inviteList = new ArrayList<>();

        inviteList.add(firstInvite);
        inviteList.add(secondInvite);

        inviteDAO.create(firstInvite);
        inviteDAO.create(secondInvite);
    }

    @Test
    public void testFindInvitesByProjectId(){
        List invites = inviteDAO.findInvitesByProjectId(project.getProjectId());
        Assert.assertEquals(inviteList, invites);
    }

    @Test
    public void testCheckInvite(){
        Invite invite = inviteDAO.checkInvite(TEST_EMAIL, TEST_KEY);
        Assert.assertEquals(invite, firstInvite);
    }

    @Test
    public void testInviteIsExist(){
        Invite invite = inviteDAO.checkInvite(project.getProjectId(), TEST_EMAIL);
        Assert.assertEquals(invite, firstInvite);
    }
}
