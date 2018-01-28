
package com.sam_solutions.ealys.converters;

import com.sam_solutions.ealys.dto.RiskDTO;
import com.sam_solutions.ealys.entity.Risk;
import org.springframework.core.convert.converter.Converter;

/**
 * Converting RiskDTO class to Risk class.
 */
public class RiskDTOConverter implements Converter<RiskDTO, Risk> {

    /**
     * Converting RiskDTO object to Risk object.
     * @param riskDTO - RiskDTO object
     */
    @Override
    public Risk convert(final RiskDTO riskDTO) {
        Risk risk = new Risk();
        risk.setRiskId(riskDTO.getRiskId());
        risk.setAuthor(new UserDTOConverter().convert(riskDTO.getAuthor()));
        risk.setName(riskDTO.getName());
        risk.setDangerLevel(riskDTO.getDangerLevel());
        risk.setDescription(riskDTO.getDescription());
        return risk;
    }
}
