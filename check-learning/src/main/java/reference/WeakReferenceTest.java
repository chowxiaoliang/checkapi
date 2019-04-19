package reference;

import commonbeans.People;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class WeakReferenceTest {

    public static void main(String[] args) {
        People people = new People();
        Reference<People> weakReference = new WeakReference<>(people);
        people = null;
        int i = 0;
        while (true){
            People people1 = weakReference.get();
            if(people1 == null){
                System.out.println("软引用的对象已经被回收了！");
                break;
            }else {
                System.out.println("软引用的对象还没有被GC回收！i=" + i);
                i ++;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
