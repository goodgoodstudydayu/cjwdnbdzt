import java.util.LinkedList;
import java.util.Queue;

public class text5 {
    public static void main(String[] args) {
        Queue<String> q=new LinkedList<>();
        q.add("Veronicm");
        q.add("Valla");
        q.add("Duncan");
        showAll(q);
    }
    public static void showAll(Queue q){
        q.add((new Integer(42)));
        while (!q.isEmpty()){
            System.out.print(q.remove()+" ");
        }
    }
}
