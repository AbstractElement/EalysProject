
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.RiskDTO;
import com.sam_solutions.ealys.entity.Risk;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting Risk class to RiskDTO class.
 */
public class RiskConverter implements Converter<Risk, RiskDTO> {

    /**
     * Converting Risk object to RiskDTO object
     * @param risk - Risk object
     */
    @Override
    public RiskDTO convert(final Risk risk) {
        RiskDTO riskDTO = new RiskDTO();
        riskDTO.setRiskId(risk.getRiskId());
        riskDTO.setAuthor(new UserConverter().convert(risk.getAuthor()));
        riskDTO.setName(risk.getName());
        riskDTO.setDangerLevel(risk.getDangerLevel());
        riskDTO.setDescription(risk.getDescription());
        return riskDTO;
    }
}
