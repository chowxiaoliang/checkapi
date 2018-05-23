package atomic;

import commonbeans.People;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhouliang
 * @since 2018-05-23 10:05
 * @desc 无锁原子更新引用类型
 **/
public class AtomicReferenceTest {

    private final static AtomicReference<People> ATOMIC_REFERENCE = new AtomicReference<>();

    public static void main(String[] args) {
        People people = new People();
        people.setSex("male");
        people.setName("zhouliang");
        people.setAge(21);
        ATOMIC_REFERENCE.set(people);
        People updatePeople = new People();
        updatePeople.setAge(23);
        updatePeople.setName("yangxiaoxiao");
        updatePeople.setSex("female");

        ATOMIC_REFERENCE.compareAndSet(people, updatePeople);
        System.out.println(ATOMIC_REFERENCE.get());
    }

}
