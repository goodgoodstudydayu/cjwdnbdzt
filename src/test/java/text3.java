import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class text3 {
    public static void main(String[] args) {
        List<String> x=new ArrayList<String>();
        x.add(" x");
        x.add("xx");
        x.add("Xx");
        Collections.sort(x);
//        Collections.reverse()
//        Collections.sort(x,c);
        for (String i:x){
            System.out.println(i);
        }
    }
}
