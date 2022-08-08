

import java.util.ArrayList;
import java.util.List;

public class listText {
    public static void main(String[] args) {
        List<? extends Animal> list=new ArrayList<dog>();
        //list.add(new dog());
        List<? super Animal> aa=new ArrayList<>();
        aa.add(new dog());
        aa.add(new cat());

    }
    public static List<? extends Animal> test(List<? extends Animal> param) {return null;}
}
