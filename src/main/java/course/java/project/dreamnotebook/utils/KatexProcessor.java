package course.java.project.dreamnotebook.utils;

public class KatexProcessor {
    static private String prefixHtml = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "  <meta charset=\"UTF-8\">\n" +
            "  <title>KaTeX Example</title>\n" +
            "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.11.1/katex.min.css\">\n" +
            "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.11.1/katex.min.js\"></script>\n" +
            "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.11.1/contrib/auto-render.min.js\"></script>\n" +
            "  <script>\n" +
            "    document.addEventListener(\"DOMContentLoaded\", function() {\n" +
            "      renderMathInElement(document.body, {\n" +
            "        delimiters: [\n" +
            "          {left: \"$$\", right: \"$$\", display: true},\n" +
            "          {left: \"\\\\[\", right: \"\\\\]\", display: true},\n" +
            "          {left: \"$\", right: \"$\", display: false},\n" +
            "          {left: \"\\\\(\", right: \"\\\\)\", display: false}\n" +
            "        ]\n" +
            "      });\n" +
            "    });\n" +
            "  </script>\n" +
            "</head>\n" +
            "<body>";
    static private String postfixHtml = "</body>\n" +
            "</html>\n";

    static public String process(String content){
        return prefixHtml + content + postfixHtml;
    }

}
