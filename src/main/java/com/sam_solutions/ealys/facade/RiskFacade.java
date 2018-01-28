
package com.sam_solutions.ealys.facade;

import com.sam_solutions.ealys.dto.RiskDTO;

import java.util.List;

/**
 * Interface for working with project risks.
 */
public interface RiskFacade {
    /**
     * Save risk project
     * @param riskDTO - risk object
     * @param projectId - project id
     */
    RiskDTO saveRisk(RiskDTO riskDTO, Long projectId);

    /**
     * Retrieves project risk by ID
     * @param id - risk id
     */
    RiskDTO findRiskByRiskId(Long id);

    /**
     * Retrieves project risks from the interval
     * @param id - project id
     * @param numberPage - number of selected page
     */
    List<RiskDTO> findLimitRisks(Long id, int numberPage);

    /**
     * Updating risk
     * @param riskDTO - object risk
     * @param projectId - project id
     */
    void updateRisk(RiskDTO riskDTO, Long projectId);

    /**
     * Deleting risk
     * @param riskId - risk id
     */
    void deleteRisk(Long riskId);

    /**
     * Retrieves values for project risks bar chart by danger level
     * @param projectId - project id
     */
    int[] valuesForChart(Long projectId);
}
