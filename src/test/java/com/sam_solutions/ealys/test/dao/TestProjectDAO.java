package com.sam_solutions.ealys.test.dao;

import com.sam_solutions.ealys.dao.ProjectDAO;
import com.sam_solutions.ealys.dao.UserDAO;
import com.sam_solutions.ealys.entity.Image;
import com.sam_solutions.ealys.entity.Project;
import com.sam_solutions.ealys.entity.RoleOnProject;
import com.sam_solutions.ealys.entity.User;
import org.hibernate.SessionFactory;
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

@ContextConfiguration(locations = "classpath:spring-mvc-servlet-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class TestProjectDAO {

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SessionFactory sessionFactory;

    private Project project;
    private User user;
    private RoleOnProject roleOnProject;
    private Image image;

    @Before
    public void prepareSamples(){
        project = new Project();
        user = new User();
        userDAO.create(user);
        roleOnProject = new RoleOnProject();
        roleOnProject.setUser(user);
        roleOnProject.setProject(project);
        roleOnProject.setRoleOnProject("OWNER");
        project.getUsers().add(roleOnProject);
        projectDAO.create(project);

        image = new Image();
        image.setImageName("Test.jpeg");
    }

    @Test
    public void testGetProjectById(){
        Assert.assertEquals(project, projectDAO.read(Project.class, project.getProjectId()));
    }

    @Test
    public void testUpdateProject(){
        project.getImages().add(image);
        project.setName("TestProject");
        projectDAO.update(project);
        Assert.assertEquals(project, projectDAO.read(Project.class, project.getProjectId()));
    }

    @Test
    public void testCascadeAddingImage(){
        project.getImages().add(image);
        projectDAO.update(project);
        Image image = (Image)sessionFactory.getCurrentSession().createQuery("from Image").list().get(0);
        Assert.assertEquals(project.getImages().get(0), image);
    }

    @Test
    public void testCascadeDeletingImages(){
        project.getImages().add(image);
        projectDAO.update(project);
        project.getImages().remove(0);
        projectDAO.update(project);
        int amountImages = sessionFactory.getCurrentSession().createQuery("from Image").list().size();
        Assert.assertEquals(project.getImages().size(), amountImages);
    }

    @Test
    public void testFindUserProject(){
        Assert.assertEquals(roleOnProject, projectDAO.findUserProject(user.getUserId(), project.getProjectId()));
    }

}
