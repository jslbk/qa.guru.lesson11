package qa.guru.lesson.files;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelenideDownloadFilesTest {

    @BeforeEach
    void setUp() {
        open("https://github.com/junit-team/junit5/blob/main/README.md/");
    }

    @Test
    void downloadFileTest() throws Exception {
//        $("[data-testid=download-raw-button]").click();
        File downloaded =
                $(".react-blob-header-edit-and-raw-actions [href*='raw/main/README.md'")
                        .download();

//        try (InputStream io = new FileInputStream(downloaded)) {
//            byte[] data = io.readAllBytes();
//            String dataAsString = new String(data, StandardCharsets.UTF_8);
//            assertTrue(dataAsString.contains("This repository is the home of _JUnit 5_."));
//        }

        String dataAsString = FileUtils.readFileToString(downloaded, StandardCharsets.UTF_8);
        assertTrue(dataAsString.contains("This repository is the home of _JUnit 5_."));
    }

}
