package qltc.BussinessLogicLayer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import qltc.Entity.buySP;

public class Bill {
    private int hd;
    private int maxsl = 0;
    private int maxprice = 0;
    public void create_cell(Phrase phrase, PdfPTable table, int align) {
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(align);
        table.addCell(cell);
    }

    public String Day() {
        // Create a formatter for the date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Get the current date
        LocalDate now = LocalDate.now();
        // Format the current date according to the formatter
        String formattedDate = dtf.format(now);
        return formattedDate;
    }

    public void createBill(List<buySP> data) throws Exception {
        for (buySP buySP : data) {
            hd = buySP.getHd();
            maxsl += buySP.getSoluong();
            maxprice += buySP.getTotal();
        }
        // Convert mm to points
        String formattedDate = Day();
        float width = Utilities.millimetersToPoints(74);
        float height = Utilities.millimetersToPoints(300);

        // Create a rectangle with the size of A7
        Rectangle pageSize = new Rectangle(width, height);

        // Create a new document with the A7 page size
        Document document = new Document(pageSize);
        try {
            // Create a PdfWriter instance with the specified file name
            PdfWriter.getInstance(document,
                    new FileOutputStream("src\\main\\java\\qltc\\PresentationLayer\\HoaDon.pdf"));
            // Open the document
            document.setMargins(10, 10, 15, 2);
            document.open();
            String tahoma = "D:\\java project\\New folder\\PetShop\\quanlithucung\\src\\main\\java\\qltc\\Resource\\FONT\\TAHOMAB0.TTF";
            BaseFont bf = BaseFont.createFont(tahoma, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            BaseFont bf2 = BaseFont.createFont(
                    "D:\\java project\\New folder\\PetShop\\quanlithucung\\src\\main\\java\\qltc\\Resource\\FONT\\tahoma.TTF",
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED);
            Font font1 = new Font(bf, 12);
            Font font2 = new Font(bf, 13);
            Font font3 = new Font(bf, 10);
            Font font4 = new Font(bf, 9);
            Font font5 = new Font(bf2, 9);
            Font font6 = new Font(bf2, 9, Font.ITALIC);
            ULocale locale = new ULocale("vi_VN");
            RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(locale, RuleBasedNumberFormat.SPELLOUT);
            String result = formatter.format(maxprice);
            // Add content to the document
            Paragraph p1 = new Paragraph("Pet Shop Management Application\n\n", font1);
            Paragraph p2 = new Paragraph("HÓA ĐƠN BÁN HÀNG", font2);
            Paragraph p3 = new Paragraph("Ngày: " + formattedDate, font3);
            Paragraph p4 = new Paragraph("Số HĐ: " + hd + "\r\n" + "Thu ngân: Admin\r\n" , font3);
            Paragraph p5 = new Paragraph("");
            Paragraph p6 = new Paragraph("(" + result + ")", font4);
            Paragraph p7 = new Paragraph("Cảm ơn Quý khách. Hẹn gặp lại !", font6);
            Phrase phrase1 = new Phrase("Mặt hàng", font4);
            Phrase phrase2 = new Phrase("SL", font4);
            Phrase phrase3 = new Phrase("Đơn giá", font4);
            Phrase phrase4 = new Phrase("Số tiền", font4);

            p1.setAlignment(Element.ALIGN_CENTER);
            p2.setAlignment(Element.ALIGN_CENTER);
            p2.setSpacingAfter(10);
            p3.setAlignment(Element.ALIGN_CENTER);
            p5.setSpacingAfter(20);
            p6.setAlignment(Element.ALIGN_CENTER);
            p7.setAlignment(Element.ALIGN_CENTER);
            p7.setSpacingBefore(30);
            document.add(p1);
            document.add(p2);
            document.add(p3);
            document.add(p4);

            DottedLineSeparator dottedline = new DottedLineSeparator();

            // Set the line color
            dottedline.setLineColor(new BaseColor(0, 0, 0)); // Black color

            // Add the dotted line separator to the document
            document.add(new Chunk(dottedline));
            document.add(p5);
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            float[] columnWidths = new float[] { 47f, 10f, 21f, 21f };
            table.setWidths(columnWidths);
            create_cell(phrase1, table, 0);
            create_cell(phrase2, table, 1);
            create_cell(phrase3, table, 1);
            create_cell(phrase4, table, 2);
            // for (int n = 0; n < data.length; n++) {
            // Phrase phrase = new Phrase(data[n] +"", font5);
            // create_cell(phrase, table);}
            for (buySP d : data) {
                Phrase phrase = new Phrase(d.getName() + "", font5);
                create_cell(phrase, table, 0);
                phrase = new Phrase(d.getSoluong() + "", font5);
                create_cell(phrase, table, 1);
                phrase = new Phrase(d.getDongia() + "", font5);
                create_cell(phrase, table, 1);
                phrase = new Phrase(d.getTotal() + "", font5);
                create_cell(phrase, table, 2);
            }
            document.add(table);
            PdfPTable table1 = new PdfPTable(3);
            table1.setWidthPercentage(100);
            float[] columnWidths1 = new float[] { 47f, 10f, 42f };
            table1.setWidths(columnWidths1);
            Phrase phrase5 = new Phrase("Tổng", font4);
            Phrase phrase6 = new Phrase(String.valueOf(maxsl), font4);
            Phrase phrase7 = new Phrase(String.valueOf(maxprice), font4);
            create_cell(phrase5, table1, 0);
            create_cell(phrase6, table1, 1);
            create_cell(phrase7, table1, 2);
            document.add(new Paragraph("\n"));
            document.add(table1);
            document.add(new Chunk(dottedline));
            PdfPTable table2 = new PdfPTable(2);
            table2.setWidthPercentage(100);
            float[] columnWidths2 = new float[] { 50f, 50f };
            table2.setWidths(columnWidths2);
            Phrase phrase8 = new Phrase("Tổng cộng", font3);
            Phrase phrase9 = new Phrase(String.valueOf(maxprice), font3);
            create_cell(phrase8, table2, 0);
            create_cell(phrase9, table2, 2);
            document.add(table2);
            document.add(new Paragraph("\n"));
            document.add(p6);
            test testObj = new test();
            testObj.getQR(maxprice);
            Image image = Image.getInstance("src\\\\main\\\\java\\\\qltc\\\\2.png");
            image.setAlignment(Image.MIDDLE);
            image.scaleAbsolute(150, 150);
            document.add(image);
            document.add(new Chunk(dottedline));
            document.add(p7);
            // Close the document
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
