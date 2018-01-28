
package com.sam_solutions.ealys.facade;

/**
 * Interface for working with pagination.
 */
public interface PaginationFacade {
    /**
     * @return - amount page with users
     */
    int amountUsersPages();

    /**
     * @param skill - skill name
     * @return - amount pages with users who have selected skill
     */
    int amountUsersWithSkillPages(String skill);

    /**
     * @param userId - user id
     * @return - amount pages with user projects
     */
    int amountProjectsPages(Long userId);

    /**
     * @param projectId - project id
     * @return - amount pages with risks project
     */
    int amountRisksPages(Long projectId);

    /**
     * @param amount - amount items
     * @param amountItemsOnPage - amount items on page
     * @return - amount pages, if amount == 1 - return 0
     */
    int filterOnePage(long amount, int amountItemsOnPage);
}
