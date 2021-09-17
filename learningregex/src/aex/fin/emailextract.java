package aex.fin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

public class emailextract {


    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("([a-z-._]+)@softwire.com", Pattern.CASE_INSENSITIVE);

        try {
            FileReader reader = new FileReader("/Users/ftarthur/Desktop/Training/emailextraction/learningregex/src/sample.txt");
            BufferedReader buff = new BufferedReader(reader);

            String line;

            int count = 0;

            while ((line = buff.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    System.out.println(matcher.group());
                    count++;
                }
            }
            reader.close();
            System.out.println(count);
        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }

}
