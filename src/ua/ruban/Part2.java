package ua.ruban;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part2 {

    private static final String PART2TXT = "part2.txt";
    private static final String PART2SORT = "part2_sorted.txt";
    private static int [] massInt;
    private static final int AMT = 10;
    private static final int RAND = 50;
    private static final int BYTELENGTH = 50;

    public static void main(String[] args) {

        try (FileOutputStream out = new FileOutputStream(PART2TXT)){
            int n = AMT;
            int [] mass = new int[n];
            SecureRandom random = new SecureRandom();
            StringBuilder strRandom = new StringBuilder();
            for (int i=0; i<n;i++){
                mass[i] = random.nextInt(RAND);
                strRandom.append(mass[i]).append(" ");
            }
            String strTime = String.valueOf(strRandom).trim();
            byte[] bW = new byte[strTime.length()];
            for (int i=0; i<strTime.length();i++){
                bW[i] = (byte)strTime.charAt(i);
            }
            out.write(bW);
        }catch (IOException e){
            e.printStackTrace();
        }

        readNumber();

        for (int i=0; i<massInt.length;i++){
            for (int j=0; j<massInt.length-1; j++){
                if(massInt[j]>massInt[j+1]){
                    int time = massInt[j];
                    massInt[j] = massInt[j+1];
                    massInt[j+1]= time;
                }
            }
        }

        try (FileOutputStream ou = new FileOutputStream(PART2SORT)){
            byte[] bF = new byte[BYTELENGTH];
            StringBuilder sbO = new StringBuilder();
            for (int i=0; i<AMT;i++){
                sbO.append(massInt[i]).append(" ");
            }
            String bfTime = String.valueOf(sbO);
            for (int i=0; i<bfTime.length();i++){
                bF[i] = (byte)bfTime.charAt(i);
            }
            ou.write(bF);
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("input  ==> "+readFiles(PART2TXT));
        System.out.println("output ==> "+readFiles(PART2SORT));
    }

    private static void readNumber(){
        String sbN = readFiles(PART2TXT).trim();
        int k = 0;
        massInt = new int[AMT];
        Pattern p = Pattern.compile("(\\S+){1,2}");
        Matcher m = p.matcher(sbN);
        while (m.find()) {
            int res = Integer.parseInt(m.group(0));
            massInt[k]= res;
            k++;
        }
    }

    private static String readFiles(String fileName){
        StringBuilder sb = new StringBuilder();
        try(FileInputStream in = new FileInputStream(fileName)){
            int c=-1;
            while((c=in.read())!=-1){
                sb.append(String.valueOf((char)c));
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(sb).trim();
    }
}
