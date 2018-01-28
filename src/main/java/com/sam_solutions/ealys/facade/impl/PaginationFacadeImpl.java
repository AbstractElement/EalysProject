
package com.sam_solutions.ealys.facade.impl;

import com.sam_solutions.ealys.facade.PaginationFacade;
import com.sam_solutions.ealys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Class for working with pagination.
 */
@Component
public class PaginationFacadeImpl implements PaginationFacade {

    /**
     * Service for working with pagination.
     */
    private final PaginationService paginationService;

    /**
     * Service for working with users
     */
    private final UserService userService;

    /**
     * Service for working with risks
     */
    private final RiskService riskService;

    /**
     * Service for working with projects
     */
    private final ProjectService projectService;

    /**
     * Amount users on page (Property value: util.properties)
     */
    @Value("${users.on.page}")
    private int amountUsersOnPage;

    /**
     * Amount projects on page (Property value: util.properties)
     */
    @Value("${projects.on.page}")
    private int amountProjectsOnPage;

    /**
     * Amount risks on page (Property value: util.properties)
     */
    @Value("${risks.on.page}")
    private int amountRisksOnPage;

    @Autowired
    public PaginationFacadeImpl(final PaginationService paginationService, final UserService userService,
                                final RiskService riskService, final ProjectService projectService) {
        this.paginationService = paginationService;
        this.userService = userService;
        this.riskService = riskService;
        this.projectService = projectService;
    }

    /**
     * @see PaginationFacade#amountUsersWithSkillPages(String)
     */
    @Override
    public int amountUsersWithSkillPages(final String skill) {
        long amountUsers = userService.getAmountUsersWithSkill(skill);
        return filterOnePage(amountUsers, amountUsersOnPage);
    }

    /**
     * @see PaginationFacade#amountUsersPages()
     */
    @Override
    public int amountUsersPages() {
        long amountUsers = userService.getAmountUsers();
        return filterOnePage(amountUsers, amountUsersOnPage);
    }

    /**
     * @see PaginationFacade#amountProjectsPages(Long)
     */
    @Override
    public int amountProjectsPages(final Long userId) {
        long amountProjects = projectService.getAmountProjects(userId);
        return filterOnePage(amountProjects, amountProjectsOnPage);
    }

    /**
     * @see PaginationFacade#amountRisksPages(Long)
     */
    @Override
    public int amountRisksPages(final Long projectId) {
        long amountRisks = riskService.getAmountProjectRisks(projectId);
        return filterOnePage(amountRisks, amountRisksOnPage);
    }

    /**
     * @see PaginationFacade#filterOnePage(long, int)
     */
    @Override
    public int filterOnePage(final long amount, final int amountItemsOnPage) {
        int amountPages = paginationService.getAmountPages(amount, amountItemsOnPage);
        if (amountPages == 1)
            return 0;
        else return amountPages;
    }
}
