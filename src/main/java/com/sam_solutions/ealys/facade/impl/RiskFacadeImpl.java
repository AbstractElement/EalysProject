
package com.sam_solutions.ealys.facade.impl;

import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.RiskDTO;
import com.sam_solutions.ealys.facade.RiskFacade;
import com.sam_solutions.ealys.service.ProjectService;
import com.sam_solutions.ealys.service.RiskService;
import com.sam_solutions.ealys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class for working with project risks
 */
@Component
public class RiskFacadeImpl implements RiskFacade {
    /**
     * @see ProjectService
     */
    private final ProjectService projectService;

    /**
     * @see UserService
     */
    private final UserService userService;

    /**
     * @see RiskService
     */
    private final RiskService riskService;

    /**
     * Amount project risks on page (Property value)
     */
    @Value("${risks.on.page}")
    private int amountRisksOnPage;

    @Autowired
    public RiskFacadeImpl(final ProjectService projectService, final UserService userService, final RiskService riskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.riskService = riskService;
    }

    /**
     * @see RiskFacade#saveRisk(RiskDTO, Long)
     * @param newRisk - risk object
     * @param projectId - project id
     */
    @Override
    public RiskDTO saveRisk(final RiskDTO newRisk, final Long projectId) {
        newRisk.setAuthor(userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        ProjectDTO projectDTO = projectService.getProjectById(projectId);
        projectDTO.getRisks().add(newRisk);
        projectService.updateProject(projectDTO);
        return newRisk;
    }

    /**
     * @see RiskFacade#deleteRisk(Long)
     * @param id - risk id
     */
    @Override
    public void deleteRisk(final Long id) {
        riskService.deleteRisk(riskService.getRiskById(id));
    }

    /**
     * @see RiskFacade#valuesForChart(Long)
     * @param projectId - project id
     */
    @Override
    public int[] valuesForChart(final Long projectId) {
        List<RiskDTO> riskList = projectService.getProjectById(projectId).getRisks();
        return riskService.counterByDangerLevel(riskList);
    }

    /**
     * @see RiskFacade#findRiskByRiskId(Long)
     * @param id - risk id
     */
    @Override
    public RiskDTO findRiskByRiskId(final Long id) {
        return riskService.getRiskById(id);
    }

    /**
     * @see RiskFacade#findLimitRisks(Long, int)
     * @param id - project id
     * @param pageNumber - number of selected page
     */
    @Override
    public List<RiskDTO> findLimitRisks(final Long id, final int pageNumber) {
        int start = pageNumber * amountRisksOnPage - amountRisksOnPage;
        return riskService.getLimitRisks(id, start);
    }

    /**
     * @see RiskFacade#updateRisk(RiskDTO, Long)
     * @param riskDTO - object risk
     * @param projectId - project id
     */
    @Override
    public void updateRisk(final RiskDTO riskDTO, final Long projectId) {
        riskService.updateRisk(riskDTO, projectService.getProjectById(projectId));
    }

}
