package Utils;

import java.io.*;

public class HtmlReader {
    public String readHtmlFile(String htmlFile) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(htmlFile)
                        )
                )
        );

        StringBuilder htmlContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            htmlContent.append(line).append(System.lineSeparator());
        }

        return htmlContent.toString().trim();
    }
}
