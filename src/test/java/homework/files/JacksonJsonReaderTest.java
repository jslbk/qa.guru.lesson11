package homework.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import homework.files.model.SolarJsonModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JacksonJsonReaderTest {

    private final ClassLoader cl = JacksonJsonReaderTest.class.getClassLoader();
    ObjectMapper om = new ObjectMapper();

    @Test
    @DisplayName("Проверка данных JSON")
    void checkOptionsForFirstQuestion() throws Exception {
        try (InputStream is = cl.getResourceAsStream("json-solar-test.json")) {
            assert is != null;
            try (InputStreamReader isr = new InputStreamReader(is)) {
                SolarJsonModel data = om.readValue(isr, SolarJsonModel.class);

                Assertions.assertEquals("3CX-500", data.getModel());
                Assertions.assertEquals("Huston Rocket", data.getName());
                Assertions.assertEquals(199.99, data.getPrice());
                Assertions.assertEquals(25, data.getWarranty());
                Assertions.assertEquals(List.of(
                        "Black",
                        "Red",
                        "Blue"), data.getColor());
            }
        }
    }
}
