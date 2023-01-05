package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {

    @Test
    void testGenerate() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/file2.json");
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateWithOneEmptyFile() throws Exception {
        String actual = Differ.generate("src/test/resources/file1.json", "src/test/resources/emptyFile.json");
        String expected = """
                {
                  - follow: false
                  - host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                }""";
        assertEquals(expected, actual);

    }

    @Test
    void testGenerateWithWrongFile() {
        assertThrows(Exception.class, () -> Differ.generate("src/test/resources/file1.json",
                "src/test/resources/wrongFile.json"));
    }

    @Test
    void testGenerateWithWrongFilePath() {
        assertThrows(Exception.class, () -> Differ.generate("src/test/resources/file1.json",
                "src/test/resources/file10.json"));
    }
}
