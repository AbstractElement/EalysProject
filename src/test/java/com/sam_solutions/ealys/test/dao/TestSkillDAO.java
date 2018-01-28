package com.sam_solutions.ealys.test.dao;

import com.sam_solutions.ealys.dao.SkillDAO;
import com.sam_solutions.ealys.entity.Skill;
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
public class TestSkillDAO {

    @Autowired
    private SkillDAO skillDAO;

    private static final String SKILL_NAME_FIRST = "Spring MVC";
    private static final String SKILL_NAME_SECOND = "Hibernate";
    private static final String SKILL_NAME_THIRD = "AngularJS";

    private Skill firstSkill;
    private Skill secondSkill;

    @Before
    public void prepareSamples(){
        firstSkill = new Skill();
        firstSkill.setName("Spring MVC");
        secondSkill = new Skill();
        secondSkill.setName("Hibernate");
        skillDAO.create(firstSkill);
        skillDAO.create(secondSkill);
    }

    @Test
    public void testGetAllSkills(){
        Assert.assertEquals(2, skillDAO.getAllSkills().size());
    }

    @Test
    public void testSkillById(){
        Assert.assertEquals(firstSkill, skillDAO.read(Skill.class, firstSkill.getSkillId()));
        Assert.assertEquals(secondSkill, skillDAO.read(Skill.class, secondSkill.getSkillId()));
    }

    @Test
    public void testSkillByName(){
        Assert.assertEquals(firstSkill, skillDAO.getSkillByName(SKILL_NAME_FIRST));
        Assert.assertEquals(secondSkill, skillDAO.getSkillByName(SKILL_NAME_SECOND));
    }

    @Test
    public void deleteSkill(){
        skillDAO.delete(firstSkill);
        Assert.assertEquals(1, skillDAO.getAllSkills().size());
    }

    @Test
    public void updateSkill(){
        firstSkill.setName(SKILL_NAME_THIRD);
        skillDAO.update(firstSkill);
        Assert.assertEquals(SKILL_NAME_THIRD, skillDAO.getSkillByName(SKILL_NAME_THIRD).getName());
    }
}
