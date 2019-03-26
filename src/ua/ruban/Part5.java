package ua.ruban;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {

    private static final int TWO = 2;

    public static void main(String[] args) {

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

        int magic=0;
        List<String> keyL = new ArrayList<>();
        Pattern p = Pattern.compile(".+");
        Matcher m = p.matcher(s);
        while (m.find()){
            keyL.add(m.group());
        }
        for (int i=0; i<keyL.size();i++){
            if ("stop".equals(keyL.get(i))){
                magic = i;
            }
            if ("stop".equals(keyL.get(i).trim())){
                magic = i;
            }
            if ("stop\n".equals(keyL.get(i).trim())){
                magic = i;
            }
        }
        if (magic == 0){
            System.out.println("magic ");
        }else {
            keyL.subList(magic,keyL.size()).clear();
        }
        for (int i=0; i<magic; i++){
            String lst = keyL.get(i);
            String nam = lst.substring(lst.length()-TWO,lst.length());
            String val = lst.substring(0,lst.length()-TWO).trim();
            Locale loc = new Locale(nam);
            ResourceBundle rb =
                    ResourceBundle.getBundle("resources", loc);
            String mg1 = rb.getString(val);
            System.out.println(mg1);
        }
    }

}
