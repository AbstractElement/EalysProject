
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.SkillDTO;
import com.sam_solutions.ealys.entity.Skill;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting Skill class to SkillDTO class.
 */
public class SkillConverter implements Converter<Skill, SkillDTO>{

    /**
     * Converting Skill object to SkillDTO object.
     * @param skill - Skill object
     */
    @Override
    public SkillDTO convert(final Skill skill) {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setSkillId(skill.getSkillId());
        skillDTO.setName(skill.getName());
        return skillDTO;
    }
}
