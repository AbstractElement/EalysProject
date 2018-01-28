package com.sam_solutions.ealys.test.dao;

import com.sam_solutions.ealys.dao.ResetTokenDAO;
import com.sam_solutions.ealys.entity.ResetToken;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:spring-mvc-servlet-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class TestResetTokenDAO {

    @Autowired
    private ResetTokenDAO resetTokenDAO;

    private ResetToken resetToken;

    @Before
    public void prepareSamples(){
        resetToken = new ResetToken();
        resetToken.setToken("token");
        resetTokenDAO.create(resetToken);
    }

    @Test
    public void testUpdateResetToken(){
        resetToken.setToken("updateToken");
        resetTokenDAO.update(resetToken);
        Assert.assertEquals(resetToken, resetTokenDAO.getByToken("updateToken"));
    }

    @Test
    public void testGetByToken(){
        Assert.assertEquals(resetToken, resetTokenDAO.getByToken("token"));
    }
}


