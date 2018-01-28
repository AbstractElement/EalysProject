
package com.sam_solutions.ealys.service.impl;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.sam_solutions.ealys.dto.ImageDTO;
import com.sam_solutions.ealys.dto.ProjectDTO;
import com.sam_solutions.ealys.dto.RiskDTO;
import com.sam_solutions.ealys.dto.RoleOnProjectDTO;
import com.sam_solutions.ealys.service.PDFGeneratorService;
import com.sam_solutions.ealys.service.ProjectService;
import com.sam_solutions.ealys.service.RiskService;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Class for working with PDF files.
 */
@Service
public class PDFGeneratorServiceImpl implements PDFGeneratorService {
    /**
     * Logging
     */
    private static final Logger LOGGER = Logger.getLogger(PDFGeneratorServiceImpl.class);

    /**
     * Getting server context
     */
    private final ServletContext servletContext;

    /**
     * Service fow working with projects
     */
    private final ProjectService projectService;

    /**
     * Service for working with risks
     */
    private final RiskService riskService;

    /**
     * Retrieves property values
     */
    private ResourceBundle resourceBundle;

    /**
     * Path to fonts (Property value: util.properties)
     */
    @Value("${path.pdf.fonts}")
    private String pathToFonts;

    /**
     * Path to project PDF
     */
    @Value("${path.pdf.projects}")
    private String pathToProjectsPDF;

    /**
     * Path to project photos
     */
    @Value("${path.projects.photos}")
    private String pathToProjectPhotos;

    @Autowired
    public PDFGeneratorServiceImpl(final ServletContext servletContext, final ProjectService projectService,
                                   final RiskService riskService) {
        this.servletContext = servletContext;
        this.projectService = projectService;
        this.riskService = riskService;
    }

    /**
     * @see PDFGeneratorService#getProjectPDF(Long, String)
     */
    @Override
    public String getProjectPDF(final Long projectId, final String lang) throws IOException, DocumentException {
        try {
            if (lang.equals("ru"))
                resourceBundle = ResourceBundle.getBundle("messages_ru", new UTF8ControlService());
            else if (lang.equals("en"))
                resourceBundle = ResourceBundle.getBundle("messages_en", new UTF8ControlService());
            ProjectDTO projectDTO = projectService.getProjectById(projectId);
            String pathFont = servletContext.getRealPath("") + pathToFonts;

            Document document = new Document(PageSize.A4, 25, 15, 25, 25);
            String pathPdf = servletContext.getRealPath("") + pathToProjectsPDF + "/" + projectDTO.getName()
                    + projectDTO.getProjectId() + ".pdf";
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pathPdf));
            document.open();

            Font font = FontFactory.getFont(pathFont, "Cp1251", BaseFont.EMBEDDED);
            Font smallFont = FontFactory.getFont(pathFont, "Cp1251", 11);

            Paragraph paragraph = new Paragraph();
            Chunk chunk = new Chunk("HAZOP", FontFactory.getFont(pathFont, BaseFont.IDENTITY_H, BaseFont.SUPERSCRIPT_SIZE));
            paragraph.add(chunk);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            Paragraph projectNameParagraph = new Paragraph(resourceBundle.getString("project.mes.projectName")
                    + ": " + projectDTO.getName(), font);
            projectNameParagraph.setSpacingBefore(10f);
            document.add(projectNameParagraph);

            Paragraph projectTargetParagraph = new Paragraph(resourceBundle.getString("project.mes.target")
                    + ": " + projectDTO.getTarget(), font);
            projectTargetParagraph.setSpacingBefore(5f);
            document.add(projectTargetParagraph);

            Paragraph projectDescriptionParagraph = new Paragraph(resourceBundle.getString("project.mes.description")
                    + ": " + projectDTO.getDescription(), font);
            projectDescriptionParagraph.setSpacingBefore(5f);
            document.add(projectDescriptionParagraph);

            Paragraph projectGroupParagraph = new Paragraph(resourceBundle.getString("nav-bar.mes.projectGroup"), font);
            projectGroupParagraph.setSpacingBefore(10f);
            document.add(projectGroupParagraph);

            int counter = 1;
            for (RoleOnProjectDTO user : projectDTO.getUsers()){
                Paragraph author = new Paragraph(counter + ". " + user.getUser().getFirstName() + " " + user.getUser().getLastName(), font);
                author.setSpacingBefore(2f);
                document.add(author);
                counter++;
            }

            document.newPage();

            if (projectDTO.getRisks().size() != 0) {
                Paragraph titleRisks = new Paragraph(resourceBundle.getString("pdf.mes.risks"), font);
                titleRisks.setSpacingBefore(10f);
                titleRisks.setAlignment(Element.ALIGN_CENTER);
                document.add(titleRisks);
                document.add(generateProjectRisksTable(smallFont, projectDTO.getRisks()));
                document.newPage();
                getProjectRisksChart(writer, projectId);
            }
            if (projectDTO.getImages().size() != 0){
                for (ImageDTO image : projectDTO.getImages()){
                    Image img = Image.getInstance(servletContext.getRealPath("") + "/" + pathToProjectPhotos + "/" + image.getImageName());
                    document.setPageSize(img);
                    img.setAbsolutePosition(0, 0);
                    document.newPage();
                    document.add(img);
                }
            }
            document.close();
            return pathPdf;
        }
        catch (JDBCException | DocumentException | IOException ex){
            LOGGER.error("Error creating pdf document", ex);
            throw ex;
        }
    }

    /**
     * Getting project risks chart for pdf file.
     * @param writer - for writting chart in pdf file
     * @param projectId - project id
     */
    private void getProjectRisksChart(final PdfWriter writer, final Long projectId){
        PdfContentByte contentByte = writer.getDirectContent();
        PdfTemplate template = contentByte.createTemplate(550, 800);
        Graphics2D graphics2d = template.createGraphics(550, 800, new DefaultFontMapper());
        Rectangle2D rectangle2d = new Rectangle2D.Double(5, 0, 550,800);
        generateRisksBarChart(projectId).draw(graphics2d, rectangle2d);
        graphics2d.dispose();
        contentByte.addTemplate(template, 5, 0);
    }

    /**
     * Generating project risks bar chart
     * @param projectId - project id
     */
    private JFreeChart generateRisksBarChart(final Long projectId) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        List<RiskDTO> risks = projectService.getProjectById(projectId).getRisks();
        int[] amountRisksByDangerLevel = riskService.counterByDangerLevel(risks);
        for (int i = 0; i < amountRisksByDangerLevel.length; i++)
            dataSet.setValue(amountRisksByDangerLevel[i], resourceBundle.getString("pdf.mes.legend"), String.valueOf(i + 1));
        return ChartFactory.createBarChart(resourceBundle.getString("pdf.mes.chart.title"),
                    resourceBundle.getString("pdf.med.chart.X"), resourceBundle.getString("pdf.mes.chart.Y"),
                    dataSet, PlotOrientation.VERTICAL, false, true, false);
    }

    /**
     * Generating project risks table for pdf file.
     * @param font - text font
     * @param risks - project risks
     * @throws DocumentException
     */
    private PdfPTable generateProjectRisksTable(final Font font, final List<RiskDTO> risks) throws DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setTotalWidth(new float[]{150, 250, 100, 50});
        table.setLockedWidth(true);
        table.setSpacingBefore(5f);
        table.addCell(new Paragraph(resourceBundle.getString("risk.mes.name"), font));
        table.addCell(new Paragraph(resourceBundle.getString("risk.mes.description"), font));
        table.addCell(new Paragraph(resourceBundle.getString("risk.mes.author"), font));
        table.addCell(new Paragraph(resourceBundle.getString("risk.mes.dangerLevel"), font));
        for (RiskDTO risk : risks) {
            table.addCell(new Paragraph(risk.getName(), font));
            table.addCell(new Paragraph(risk.getDescription(), font));
            table.addCell(new Paragraph(risk.getAuthor().getFirstName() + " " + risk.getAuthor().getLastName(), font));
            table.addCell(new Paragraph(risk.getDangerLevel().toString(), font));
        }

        return table;
    }

}
