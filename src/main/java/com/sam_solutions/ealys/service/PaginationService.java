
package com.sam_solutions.ealys.service;

/**
 * Interface for working with pagination.
 */
public interface PaginationService {
    /**
     * getting amount pages
     * @param amountElements - amount elements
     * @param amountElementsOnPage - amount elements on one page
     * @return - amount pages
     */
    int getAmountPages(long amountElements, int amountElementsOnPage);
}
