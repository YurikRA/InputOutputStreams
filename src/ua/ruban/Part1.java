package ua.ruban;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final String EOL = System.lineSeparator();
    private static final int LENGTH = 3;

    public static void main(String[] args){
        System.out.print(word());
    }

    private static String word(){

        StringBuilder sb = new StringBuilder();
        StringBuilder sbLine = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream("part1.txt"), "Cp1251"))){
            String line;
            while ((line = in.readLine()) != null) {
                sbLine.append(line).append(EOL);
            }
            Pattern p = Pattern.compile("(?im)(\\w+)|(\\p{Punct})|([Ё-ї]+)|(\\s+)");
            Matcher m = p.matcher(sbLine);
            while (m.find()) {
                if (m.group(0).length()>LENGTH){
                    sb.append(m.group(0).toUpperCase());
                } else {
                    sb.append(m.group(0));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(sb);
    }
}
