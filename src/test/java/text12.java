import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class text12 {
    public static void main(String[] args) {
        String[] array= {"123","12345678","l","12","1234567890"};
        List<String> list = Arrays.asList(array);
        Collection<String> resultList=getLongWords(list);

    }
    static public<E extends CharSequence> Collection<E> getLongWords(Collection<E> coll){
        Collection<E> longWords = new ArrayList<E>();
        for(E word:coll){
            if (word.length()>6){
                longWords.add(word);
            }

        }
        return longWords;
    }


}
