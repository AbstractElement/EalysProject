
package com.sam_solutions.ealys.service;

import com.sam_solutions.ealys.dto.SkillDTO;

import java.util.List;

/**
 * Interface for working with user skills.
 */
public interface SkillService {
    /**
     * Retirves skill by id
     * @param id - skill id
     */
    SkillDTO getSkillById(Long id);

    /**
     * Retrieve skill by name
     * @param name - skill name
     */
    SkillDTO getSkillByName(String name);

    /**
     * Retirves all skills
     */
    List<SkillDTO> getAllSkill();
}
