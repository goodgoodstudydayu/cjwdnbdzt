
import java.util.List;

public class text14 {
    public static void main(String[] args) {
        List<Number> input=null;
        List<? super Number> output=null;
        output = process(input);
    }
    public static <E extends Number> List<? super  E> process(List<E> some){
        List<? super  E> aa=some;
        return aa;
    }
}


