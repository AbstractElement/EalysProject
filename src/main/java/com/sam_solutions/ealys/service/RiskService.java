
package com.sam_solutions.ealys.service;

import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.RiskDTO;

import java.util.List;

/**
 * Interface for working with projects risks.
 */
public interface RiskService {
    /**
     * Retrieves risk by id
     * @param id - risk id
     */
    RiskDTO getRiskById(Long id);

    /**
     * Retrieves risks from the interval
     * @param id - risk id
     * @param start - position of first result
     */
    List<RiskDTO> getLimitRisks(Long id, int start);

    /**
     * Deleting risk
     * @param risk - risk object
     */
    void deleteRisk(RiskDTO risk);

    /**
     * Updating risk
     * @param risk - risk object
     * @param projectDTO - project object
     */
    void updateRisk(RiskDTO risk, ProjectDTO projectDTO);

    /**
     * Groups risks by danger level.
     * @param riskDTOList -project risks
     * @return - grouped risks
     */
    int[] counterByDangerLevel(List<RiskDTO> riskDTOList);

    /**
     * Retrieves amount project risks.
     * @param projectId - project id
     */
    long getAmountProjectRisks(Long projectId);
}
