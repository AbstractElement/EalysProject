
package com.sam_solutions.ealys.service;

import com.itextpdf.text.DocumentException;
import org.jfree.chart.JFreeChart;

import java.io.IOException;

/**
 * Interface for working with PDF files.
 */
public interface PDFGeneratorService {
    /**
     * Getting project PDF file
     * @param projectId - project id
     * @param lang - current language
     * @return - path to pdf file
     * @throws IOException
     * @throws DocumentException
     */
    String getProjectPDF(Long projectId, String lang) throws IOException, DocumentException;
}
