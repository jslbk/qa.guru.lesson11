package qa.guru.lesson.files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelenideUploadFilesTest {

    @BeforeEach
    void setUp() {
        open("https://qaplayground.dev/apps/upload/");
    }

    @Test
    void uploadFileTest() {
        String testImage = "test.files/png-test.png";
        $("input[type=file]").uploadFromClasspath(testImage);
        assertTrue($("figcaption").getText().contains(testImage));
    }
}
