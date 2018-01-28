
package com.sam_solutions.ealys.service.impl;

import com.sam_solutions.ealys.service.PaginationService;
import com.sam_solutions.ealys.service.UserService;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class for working with pagination.
 */
@Service
public class PaginationServiceImpl implements PaginationService {

    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    /**
     * @see PaginationService#getAmountPages(long, int)
     */
    @Override
    @Transactional
    public int getAmountPages(final long amountElements, final int amountElementsOnPage) {
        try{
            return (int) Math.ceil((float) amountElements / (float) amountElementsOnPage);
        }
        catch (JDBCException ex){
            LOGGER.error("Risks pages count error");
            throw ex;
        }
    }
}
