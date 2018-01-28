
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.SkillDTO;
import com.sam_solutions.ealys.entity.Skill;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting SkillDTO class to Skill class.
 */
public class SkillDTOConverter implements Converter<SkillDTO, Skill> {

    /**
     * Converting SkillDTO object to Skill object
     * @param skillDTO - SkillDTO object
     */
    @Override
    public Skill convert(final SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setSkillId(skillDTO.getSkillId());
        skill.setName(skillDTO.getName());
        return skill;
    }
}
