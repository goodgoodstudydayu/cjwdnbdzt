import java.util.*;

public class text11 extends HashSet<Person> {
    public static void main(String[] args) {
        text11 g=new text11();
        g.add(new Person("huns"));
        g.add(new Person("Lotte"));
        g.add(new Person("Jans"));
        g.add(new Person("huns"));
        g.add(new Person("Jans"));
        System.out.println("Tptal: "+g.size());
    }

    public boolean add(Person o) {
        System.out.println("Adding:"+o);
        return super.add(o);

    }
}
class Person{
    private final String name;
    public Person(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return name;
    }
}