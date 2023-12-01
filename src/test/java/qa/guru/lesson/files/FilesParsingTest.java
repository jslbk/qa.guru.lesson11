package qa.guru.lesson.files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qa.guru.lesson.files.model.JsonModel;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.*;

public class FilesParsingTest {

    private ClassLoader cl = FilesParsingTest.class.getClassLoader();
    private static final Gson gson = new Gson();

    @Test
    void pdfFileParsingTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded = $("[href*='junit-user-guide-5.10.1.pdf'").download();

        PDF pdf = new PDF(downloaded);
        Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein", pdf.author);
    }

    @Test
    void xlsFileParsingTest() {
        open("https://sample-videos.com/download-sample-xls.php");
        File downloaded = $("[href*='xls/Sample-Spreadsheet-10-rows.xls']").download();

        XLS xls = new XLS(downloaded);
        String actualValue = xls.excel.getSheetAt(0).getRow(9).getCell(1).getStringCellValue();

        Assertions.assertEquals("Xerox 198", actualValue);
    }

    @Test
    void csvFileParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.files/csv-test.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {

            List<String[]> csvData = csvReader.readAll();
            Assertions.assertEquals(5, csvData.size());
            Assertions.assertArrayEquals(
                    new String[]{"3", "Cardinal", "Ring Binder", "Heavy Gauge Vinyl", "Barry French", "293", "46", "7", "69"},
                    csvData.get(2)
            );
            Assertions.assertArrayEquals(
                    new String[]{"5", "Holmes", "HEPA Air Purifier", "Carlos Soltero", "515", "94", "21", "7", "Nunavut Appliances"},
                    csvData.get(4)
            );
        }
    }

    @Test
    void zipFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.files/zip-test-archive.zip")
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                Assertions.assertEquals("csv-archived-test", entry.getName().contains(".csv"));
                Assertions.assertEquals("pdf-archived-test", entry.getName().contains(".pdf"));
                Assertions.assertEquals("xlsx-archived-test", entry.getName().contains(".xlsx"));
            }
        }
    }

    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("test.files/json-test.json")
        )) {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);

            Assertions.assertEquals("json-test-example", actual.get("title").getAsString());
            Assertions.assertEquals(1, actual.get("ID").getAsInt());

            JsonObject inner = actual.get("data").getAsJsonObject();

            Assertions.assertEquals("Jeanette", inner.get("first_name").getAsString());
            Assertions.assertEquals("Female", inner.get("gender").getAsString());
        }
    }

    @Test
    void jsonFileParsingImprovedTest() throws Exception {
        try (Reader reader = new InputStreamReader(
                cl.getResourceAsStream("test.files/json-test.json")
        )) {
            JsonModel actual = gson.fromJson(reader, JsonModel.class);

            Assertions.assertEquals("json-test-example", actual.getTitle());
            Assertions.assertEquals(1, actual.getId());
            Assertions.assertEquals("Jeanette", actual.getData().getFirstName());
            Assertions.assertEquals("Female", actual.getData().getGender());
        }
    }

}