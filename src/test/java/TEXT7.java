import org.bouncycastle.jcajce.provider.digest.MD2;

import java.util.HashMap;
import java.util.Objects;

public class TEXT7 {
    public static void main(String[] args) {
        HashMap<ToDoa, String> m = new HashMap<>();
        ToDoa t1 = new ToDoa("mondy");
        ToDoa t2 = new ToDoa("mondy");
        ToDoa t3 = new ToDoa("tuesday");
        m.put(t1,"dolauday");
        m.put(t2,"payBills");
        m.put(t3,"cleanAttic");
        System.out.println(m.size());


    }

}
class ToDoa{
    String day;
    ToDoa(String d){day=d;}
    public boolean equals(Object o){
        return ((ToDoa)o).day == this.day;
    }

    @Override
    public int hashCode() {
        return 9;
    }
}
