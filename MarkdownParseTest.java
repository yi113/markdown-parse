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
            assertEquals(List.of("https://something.com","some-page.html"),
            MarkdownParse.getLinks(Files.readString(Path.of("test-file.md"))));
            assertEquals(List.of(),
            MarkdownParse.getLinks(Files.readString(Path.of("breaking_test_2.md"))));
            assertEquals(List.of(),
            MarkdownParse.getLinks(Files.readString(Path.of("breaking_test_3.md"))));
            assertEquals(List.of("https:/https://ucsd-cse15l-w22.github.io/week/week3"),
            MarkdownParse.getLinks(Files.readString(Path.of("breaking_test.md"))));
            assertEquals(List.of(),
            MarkdownParse.getLinks(Files.readString(Path.of("test-file6.md"))));
            assertEquals(List.of(),
            MarkdownParse.getLinks(Files.readString(Path.of("test4.md"))));
            //assertEquals(List.of(),
           // MarkdownParse.getLinks(Files.readString(Path.of("test-file8.md"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSnippet1() throws IOException{
        assertEquals(List.of("`google.com", "google.com", "ucsd.edu"),
        MarkdownParse.getLinks(Files.readString(Path.of("snippet1.md"))));
    }

    @Test
    public void testSnippet2() throws IOException{
        assertEquals(List.of("a.com", "a.com(())", "example.com"),
        MarkdownParse.getLinks(Files.readString(Path.of("snippet2.md"))));
    }

    @Test
    public void testSnippet3() throws IOException{
        assertEquals(List.of("https://www.twitter.com", "https://ucsd-cse15l-w22.github.io/", "https://cse.ucsd.edu/"),
        MarkdownParse.getLinks(Files.readString(Path.of("snippet3.md"))));
    }
}