// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkdownParse {
    static int findCloseParen(String markdown, int openParen) {
        int closeParen = openParen + 1;
        int openParenCount = 1;
        while (openParenCount > 0 && closeParen < markdown.length()) {
            if (markdown.charAt(closeParen) == '(') {
                openParenCount++;
            } else if (markdown.charAt(closeParen) == ')') {
                openParenCount--;
            }
            closeParen++;
        }
        if(openParenCount == 0) {
          return closeParen - 1;
        }
        else {
          return -1;
        }

    }

    public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        if(dirOrFile.isDirectory()) {
            for(File f: dirOrFile.listFiles()) {
                result.putAll(getLinks(f));
	    }
            return result;
        }
        else {
            Path p = dirOrFile.toPath();
            int lastDot = p.toString().lastIndexOf(".");
            if(lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
                return result;
            }
            ArrayList<String> links = getLinks(Files.readString(p));
            result.put(dirOrFile.getPath(), links);
            System.out.println(links);
            return result;
        }
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next

        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if(nextOpenBracket == -1)
                break;
            if(nextOpenBracket != 0 && markdown.charAt(nextOpenBracket -1) == '!'){
                currentIndex = nextOpenBracket+1;
                continue;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if(nextCloseBracket - 1 == nextOpenBracket)
                break;
            int openParen = markdown.indexOf("(", nextCloseBracket);
            if(openParen == -1)
                break;
            int closeParen = markdown.indexOf(")", openParen);

            if(nextOpenBracket >= 0 &&
               nextCloseBracket >= 0 &&
               openParen == nextCloseBracket+1 &&
               closeParen >= 0){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
            } else {
                currentIndex += 1;
            }
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
	    String contents = fileName.toString();
	    File file = new File(contents);
        Map<String, List<String>> links = getLinks(file);
       	//System.out.println(links);
    }
}
