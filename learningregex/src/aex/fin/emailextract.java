package aex.fin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.regex.*;
import java.util.Map;
import java.util.HashMap;
public class emailextract {


    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("[a-z-._]+@([a-z0-9_.-]+[a-z])", Pattern.CASE_INSENSITIVE);
        String emailTextFile = "/Users/ftarthur/Desktop/Training/emailextraction/learningregex/src/sample.txt";

        Map<String, Integer> emails = FindFrequencyOfEmails(pattern, emailTextFile);

        PrintMostUsedDomain(emails);

    }

    private static void PrintMostUsedDomain(Map<String, Integer> emails) {

        Map<String, Integer> sortedEmails = new LinkedHashMap<>();
        emails.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedEmails.put(x.getKey(), x.getValue()));

        System.out.println(sortedEmails);


        Integer count  = 0;
        for (String key : sortedEmails.keySet()) {
            count++;
            if (count <= 10) {
                System.out.println(key + " domain appears " + sortedEmails.get(key) + " time(s)");
            }
        }

    }

    private static Map<String, Integer> FindFrequencyOfEmails(Pattern pattern, String input) {

        Map<String, Integer> emails = new HashMap<>();

        try {
            FileReader reader = new FileReader(input);
            BufferedReader buff = new BufferedReader(reader);

            String line;

            int count = 0;

            while ((line = buff.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String domain = matcher.group(1);
                    count++;
                    emails.putIfAbsent(domain, 0);
                    emails.put(domain, emails.get(domain) + 1);
                }
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return emails;
    }

}
