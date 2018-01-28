
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.dao.SkillDAO;
import com.sam_solutions.ealys.dto.SkillDTO;
import com.sam_solutions.ealys.entity.Skill;
import com.sam_solutions.ealys.service.SkillService;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for working with user skills.
 */
@Service
public class SkillServiceImpl implements SkillService {
    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(Skill.class);

    /**
     * For working with skills table
     */
    private final SkillDAO skillDAO;

    /**
     * For converting objects
     */
    private final ConversionService conversionService;

    @Autowired
    public SkillServiceImpl(final SkillDAO skillDAO, final ConversionService conversionService) {
        this.skillDAO = skillDAO;
        this.conversionService = conversionService;
    }

    /**
     * @see SkillService#getSkillById(Long)
     * @param id - skill id
     */
    @Override
    @Transactional
    public SkillDTO getSkillById(final Long id) {
        try {
            LOGGER.info("Getting skill by id, " + id);
            return conversionService.convert(skillDAO.read(Skill.class, id), SkillDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting skill by id, " + id, ex);
            throw ex;
        }
    }

    /**
     * @see SkillService#getSkillByName(String)
     * @param name - skill name
     */
    @Override
    @Transactional
    public SkillDTO getSkillByName(final String name) {
        try {
            LOGGER.info("Getting skill by name " + name);
            return conversionService.convert(skillDAO.getSkillByName(name), SkillDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting skill by name " + name, ex);
            throw ex;
        }
    }

    /**
     * @see SkillService#getAllSkill()
     */
    @Override
    @Transactional
    public List<SkillDTO> getAllSkill() {
        try {
            LOGGER.info("Getting all skills");
            List<Skill> skills = skillDAO.getAllSkills();
            List<SkillDTO> skillDTOList = new ArrayList<>();
            for (Skill skill : skills)
                skillDTOList.add(conversionService.convert(skill, SkillDTO.class));
            return skillDTOList;
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting all skills", ex);
            throw ex;
        }
    }
}
