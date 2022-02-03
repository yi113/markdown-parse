import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(3, 2 + 1);
    }

    @Test
    public void testGetLinks(){
        try {
            assertEquals(List.of("https://something.comeeee","some-page.html"),
            MarkdownParse.getLinks(Files.readString(Path.of("test-file.md"))));
            assertEquals(List.of(),
            MarkdownParse.getLinks(Files.readString(Path.of("breaking_test_2.md"))));
            assertEquals(List.of(),
            MarkdownParse.getLinks(Files.readString(Path.of("breaking_test_3.md"))));
            assertEquals(List.of("https:/https://ucsd-cse15l-w22.github.io/week/week3"),
            MarkdownParse.getLinks(Files.readString(Path.of("breaking_test.md"))));
            assertEquals(List.of(),
            MarkdownParse.getLinks(Files.readString(Path.of("test-file6.md"))));
            //assertEquals(List.of(),
           // MarkdownParse.getLinks(Files.readString(Path.of("test-file8.md"))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}