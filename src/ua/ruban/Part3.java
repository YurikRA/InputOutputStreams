package ua.ruban;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static List<String> readFile;

    public static void main(String[] args)  {

        readFile = new ArrayList<>();

        try(BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream("part3.txt"), "Cp1251"))){
            String line;
            while ((line = in.readLine()) != null) {
                StringBuilder sbl = new StringBuilder();
                readFile.add(String.valueOf(sbl.append(" ").append(line).append(" ")));
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        String ch = "(?im)(\\s[A-zЁ-ї]\\b)";
        String str = "(?im)[A-zЁ-ї]{2,}";
        String into = "(?im)\\s[\\d]+\\s";
        String doubl = "(?im)[\\d]*[.][\\d]*";

        StringBuilder sb = new StringBuilder();
        String s = null;
        try(BufferedInputStream bis = new BufferedInputStream(System.in)){
            int c;
            while((c=bis.read())!=-1){
                sb.append(String.valueOf((char)c));
            }
            s = String.valueOf(sb);
        }catch (IOException e){
            e.printStackTrace();
        }

        Matcher m = Pattern.compile("\\w+\\b").matcher(s);
        List<String> input = new ArrayList<>();
        while (m.find()){
            input.add(m.group().trim());
        }
        for (int i=0; i<input.size()-1; i++){
            String strInp = input.get(i);
            switch (strInp){
                case "char":
                    sortToList(ch);
                    break;
                case "String":
                    sortToList(str);
                    break;
                case "int":
                    sortToList(into);
                    break;
                case "double":
                    sortToList(doubl);
                    break;
                default :
                    break;
            }
        }
    }

    public static void sortToList(String regex){
        Pattern p = Pattern.compile(regex);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<readFile.size();i++){
            Matcher m = p.matcher(readFile.get(i));
            while (m.find()){
                sb.append(m.group().trim()).append(" ");
            }
        }
        sb.delete(sb.length()-1,sb.length());
        System.out.println(sb);
    }
}
