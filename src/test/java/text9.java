public class text9 {

}
interface Hungry<E> {
    void munch(E x);
}
interface Carnivore<E extends Animals> extends Hungry<E>{}
interface Hmrbivore<E extends Plant> extends Hungry<E>{}
abstract class Plant{}
class Grass extends Plant{}
abstract class Animals{}
//class Sheep extends Animals implements Hmrbivore<Sheep>{
//    @Override
//    public void munch(Sheep x) {
//
//    }
//}
class Wolf extends Animals implements Hmrbivore<Grass>{
    @Override
    public void munch(Grass x) {

    }
}