
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.dao.RiskDAO;
import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.RiskDTO;
import com.sam_solutions.ealys.entity.Project;
import com.sam_solutions.ealys.entity.Risk;
import com.sam_solutions.ealys.service.RiskService;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Class for working with .
 */
@Service
public class RiskServiceImpl implements RiskService {

    /**
     * Amount danger levels (Property value)
     */
    @Value("${amount.levels}")
    private int amountDangerLevels;

    /**
     * For working with risks table DB
     */
    private final RiskDAO riskDAO;

    /**
     * For converting object
     */
    private final ConversionService conversionService;

    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(Risk.class);

    @Autowired
    public RiskServiceImpl(final RiskDAO riskDAO, final ConversionService conversionService) {
        this.riskDAO = riskDAO;
        this.conversionService = conversionService;
    }

    /**
     * @see RiskService#getRiskById(Long)
     * @param id - risk id
     */
    @Override
    @Transactional
    public RiskDTO getRiskById(final Long id){
        try{
            LOGGER.info("Retrieve risk by id: " + id);
            return conversionService.convert(riskDAO.read(Risk.class, id), RiskDTO.class);
        }
        catch (JDBCException ex){
            LOGGER.error("Error retrieving risk by id", ex);
            throw ex;
        }
    }

    /**
     * @see RiskService#getLimitRisks(Long, int)
     * @param id - risk id
     * @param start - position of first result
     */
    @Override
    @Transactional
    public List<RiskDTO> getLimitRisks(final Long id, final int start) {
        try{
            LOGGER.info("Retrieve risks by project id");
            List<Risk> risks = riskDAO.getLimitRisks(id, start);
            List<RiskDTO> riskDTOList = new ArrayList<>();
            for (Risk risk : risks)
                riskDTOList.add(conversionService.convert(risk, RiskDTO.class));
            return riskDTOList;
        }
        catch (JDBCException ex){
            LOGGER.error("Error retrieving risks by project id from interval", ex);
            throw ex;
        }
    }

    /**
     * @see RiskService#deleteRisk(RiskDTO)
     * @param risk - risk object
     */
    @Override
    @Transactional
    public void deleteRisk(final RiskDTO risk){
        try {
            riskDAO.delete(conversionService.convert(risk, Risk.class));
            LOGGER.info("Risk was deleted, id: " + risk.getRiskId());
        }
        catch (JDBCException ex){
            LOGGER.error("Error deleting risk, id: " + risk.getRiskId(), ex);
            throw ex;
        }
    }

    /**
     * @see RiskService#updateRisk(RiskDTO, ProjectDTO)
     * @param riskDTO - updated risk
     * @param projectDTO - project object
     */
    @Override
    @Transactional
    public void updateRisk(final RiskDTO riskDTO, final ProjectDTO projectDTO){
        try {
            Risk risk = conversionService.convert(riskDTO, Risk.class);
            risk.setProject(conversionService.convert(projectDTO, Project.class));
            riskDAO.update(risk);
            LOGGER.info("Risk was updated, id: " + riskDTO.getRiskId());
        }
        catch (JDBCException ex){
            LOGGER.error("Error updating risk, id: " + riskDTO.getRiskId(), ex);
            throw ex;
        }
    }

    /**
     * @see RiskService#counterByDangerLevel(List)
     * @param riskList - project risks collection
     */
    @Override
    public int[] counterByDangerLevel(final List<RiskDTO> riskList) {
        int[] counteredValues = new int[amountDangerLevels];
        for (RiskDTO riskDTO : riskList)
            counteredValues[riskDTO.getDangerLevel() - 1]++;
        return counteredValues;
    }

    /**
     * @see RiskService#getAmountProjectRisks(Long)
     * @param projectId - project id
     */
    @Override
    @Transactional
    public long getAmountProjectRisks(final Long projectId) {
        try {
            LOGGER.info("Get amount project risks, project id: " + projectId);
            return riskDAO.getAmountRisks(projectId);
        }
        catch (JDBCException ex){
            LOGGER.error("Error getting project risks, project id: " + projectId, ex);
            throw ex;
        }
    }
}
