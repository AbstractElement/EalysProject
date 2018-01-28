package com.sam_solutions.ealys.test.dao;

import com.sam_solutions.ealys.dao.ProjectDAO;
import com.sam_solutions.ealys.dao.RiskDAO;
import com.sam_solutions.ealys.dao.UserDAO;
import com.sam_solutions.ealys.entity.Project;
import com.sam_solutions.ealys.entity.Risk;
import com.sam_solutions.ealys.entity.User;
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
public class TestRiskDAO {
    @Autowired
    private RiskDAO riskDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ProjectDAO projectDAO;

    private User user;
    private Project secondProject;
    private Project firstProject;
    private Risk firstRisk;
    private Risk secondRisk;

    @Before
    public void prepareSamples(){
        user = new User();
        secondProject = new Project();
        firstProject = new Project();
        firstRisk = new Risk();
        secondRisk = new Risk();

        userDAO.create(user);

        firstRisk.setName("First risk");
        firstRisk.setAuthor(user);
        firstRisk.setDangerLevel(9);

        secondRisk.setName("First risk");
        secondRisk.setAuthor(user);
        secondRisk.setDangerLevel(2);

        firstProject.getRisks().add(firstRisk);
        firstProject.getRisks().add(secondRisk);
        projectDAO.create(firstProject);
        projectDAO.create(secondProject);

    }

    @Test
    public void testGetRiskById(){
        riskDAO.create(firstRisk);
        Assert.assertEquals(firstRisk, riskDAO.read(Risk.class, firstRisk.getRiskId()));
    }

    @Test
    public void testDelete(){
        riskDAO.create(firstRisk);
        riskDAO.delete(firstRisk);
    }

    @Test
    public void testGetLimitRisks(){
        Assert.assertEquals(2, riskDAO.getLimitRisks(firstProject.getProjectId(), 0).size());
        Assert.assertEquals(0, riskDAO.getLimitRisks(secondProject.getProjectId(), 0).size());
    }

    @Test
    public void testGetAmountRisks(){
        Assert.assertEquals(2, riskDAO.getAmountRisks(firstProject.getProjectId()));
        Assert.assertEquals(0, riskDAO.getAmountRisks(secondProject.getProjectId()));
    }
}

