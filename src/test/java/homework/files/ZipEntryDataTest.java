package homework.files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipEntryDataTest {

    private final ClassLoader cl = ZipEntryDataTest.class.getClassLoader();

    @Test
    @DisplayName("Check extracted from the archive CSV third line array string is as expected")
    void zipCsvFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.files/zip-test-archive.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("csv-test.csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> csvData = reader.readAll();

                    Assertions.assertEquals(5, csvData.size());
                    Assertions.assertArrayEquals(
                            new String[]{"3", "Cardinal", "Ring Binder", "Heavy Gauge Vinyl",
                                    "Barry French", "293", "46", "7", "69"},
                            csvData.get(2));
                }
            }
        }
    }

    @Test
    @DisplayName("Check author of the extracted from the archive PDF file")
    void zipPdfFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.files/zip-test-archive.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("pdf-test.pdf")) {
                    PDF pdf = new PDF(zis);

                    Assertions.assertEquals("Yukon Department of Education", pdf.author);
                    System.out.println();
                }
            }
        }
    }


    @Test
    @DisplayName("Check extracted from the archive XLSX ")
    void zipXlsxFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.files/zip-test-archive.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("xlsx-test.xlsx")) {
                    XLS xls = new XLS(zis);
                    double numericValue = xls.excel
                            .getSheetAt(0)
                            .getRow(6)
                            .getCell(7)
                            .getNumericCellValue();
                    int actualValue = (int) numericValue;

                    Assertions.assertEquals(2554, actualValue);
                }
            }
        }
    }

}