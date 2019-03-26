package ua.ruban;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part4  implements Iterable {

    private static List<Object> listP;
    private static int k;
    private int firstIndex =-1;

    public static void main(String[] args) {
        k=0;
        Part4 part4 = new Part4();
        StringBuilder sbl = new StringBuilder();
        listP = new ArrayList<>();
        Pattern p = Pattern.compile("(?m)([Ё-ЯA-Z].*?[!?.])");
        try(BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream("part4.txt"), "Cp1251"))){
            String line;
            while ((line = in.readLine()) != null) {
                sbl.append(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        Matcher m = p.matcher(sbl);
        while (m.find()) {
            listP.add(m.group());
            k++;
        }

        Iterator<Object> it = part4.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Override
    public Iterator iterator() {
        return new IteratorImpl() ;
    }

    class IteratorImpl implements Iterator<Object> {

        @Override
        public boolean hasNext() {
            boolean result = true;
            if (firstIndex == k-1){
                firstIndex = -1;
                result = false;
            }
            return result;
        }

        @Override
        public Object next() {
            if (listP.isEmpty()){
                throw new NoSuchElementException();
            }
            firstIndex++;
            if (firstIndex == listP.size()){
                firstIndex = listP.size()-1;
            }
            return listP.get(firstIndex);
        }
    }

}
