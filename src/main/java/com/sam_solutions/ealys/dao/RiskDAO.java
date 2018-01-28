
package com.sam_solutions.ealys.dao;

import com.sam_solutions.ealys.entity.Risk;

import java.util.List;

/**
 * Interface for working with project risks table.
 */
public interface RiskDAO extends GenericDAO<Risk, Long>{
    /**
     * Retrieves risks from the interval (start, stop) by project id.
     * @param projectId - project id.
     * @param start - position of first result
     */
    List<Risk> getLimitRisks(Long projectId, int start);

    /**
     * Retrieves amount project risks
     * @param projectId - project id.
     */
    long getAmountRisks(Long projectId);
}
