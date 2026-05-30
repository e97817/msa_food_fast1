package bo.gob.food_fast.atencion.service.service;

import bo.gob.food_fast.atencion.dto.dto.response.ReporteResponse;
import bo.gob.food_fast.atencion.entity.entities.CelularJPA;
import bo.gob.food_fast.atencion.repository.repository.CelularDao;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestScoped
public class ReporteServicioImpl implements ReporteServicio {

    @Inject
    CelularDao celularDao;

    @Override
    public ReporteResponse generarReporteCelulares(Map<String, Object> filtros) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.LETTER);
            PdfWriter.getInstance(document, out);

            document.open();

            // Fuentes
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 9);

            // Título
            Paragraph title = new Paragraph("CELULARES CONFIGURADOS", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Fecha de impresión
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Paragraph date = new Paragraph("Fecha de impresión: " + sdf.format(new Date()), fontBody);
            document.add(date);

            // Fechas de filtros (ejemplo)
            String fechaDesde = filtros.getOrDefault("fechaDesde", "N/A").toString();
            String fechaHasta = filtros.getOrDefault("fechaHasta", "N/A").toString();
            Paragraph filters = new Paragraph("Fecha Desde: " + fechaDesde + " - Fecha Hasta: " + fechaHasta, fontBody);
            filters.setSpacingAfter(20);
            document.add(filters);

            // Tabla
            float[] widths = {8f, 35f, 20f, 20f, 17f};
            PdfPTable table = new PdfPTable(widths);
            table.setWidthPercentage(100);

            // Cabeceras
            String[] headers = {"NRO.", "NSEC REG ADUANA", "FECHA CREACIÓN", "OBSERVACIONES", "ESTADO"};
            for (String headerText : headers) {
                PdfPCell header = new PdfPCell(new Phrase(headerText, fontHeader));
                header.setBackgroundColor(new Color(238, 238, 238)); // #eeeeee
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setPadding(5);
                table.addCell(header);
            }

            // Datos
            List<CelularJPA> celulares = celularDao.findAll().list();
            int count = 1;
            for (CelularJPA cel : celulares) {
                table.addCell(createCell(String.valueOf(count++), fontBody, Element.ALIGN_RIGHT));
                table.addCell(createCell(cel.nsecVeRegAduana != null ? cel.nsecVeRegAduana.toString() : "", fontBody, Element.ALIGN_LEFT));
                table.addCell(createCell(cel.fechaCreacion != null ? sdf.format(cel.fechaCreacion) : "", fontBody, Element.ALIGN_CENTER));
                table.addCell(createCell(cel.observaciones != null ? cel.observaciones : "", fontBody, Element.ALIGN_LEFT));
                table.addCell(createCell(cel.estado, fontBody, Element.ALIGN_LEFT));
            }

            document.add(table);
            document.close();

            String base64Content = Base64.getEncoder().encodeToString(out.toByteArray());

            return ReporteResponse.builder()
                    .contentBase64(base64Content)
                    .fileName("ReporteCelulares.pdf")
                    .contentType("application/pdf")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el reporte PDF", e);
        }
    }

    private PdfPCell createCell(String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(alignment);
        cell.setPadding(5);
        return cell;
    }
}
