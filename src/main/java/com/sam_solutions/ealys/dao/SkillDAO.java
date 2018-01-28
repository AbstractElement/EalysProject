
package com.sam_solutions.ealys.dao;

import com.sam_solutions.ealys.entity.Skill;

import java.util.List;

/**
 * Interface for working with user skills table.
 */
public interface SkillDAO extends GenericDAO<Skill, Long> {
    /**
     * Retrieves all skills.
     */
    List<Skill> getAllSkills();

    /**
     * Retrieves skill by skill name.
     * @param name - skill name.
     */
    Skill getSkillByName(String name);
}
