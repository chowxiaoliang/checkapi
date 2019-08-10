package reference;

import junit.framework.TestCase;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * 虚引用
 * 最弱的引用关系
 * PhantomReference的get方法总是返回null，因此无法访问对应的引用对象；其意义在于说明一个对象已经进入finalization阶段，可以被gc回收，用来实现比finalization机制更灵活的回收操作。
 * PhantomReference的应用并不多，其中一个应用是sun.misc.Cleaner类
 *
 * 当JVM将虚引用插入到引用队列的时候，虚引用执行的对象内存还是存在的
 */
public class PhantomReferenceTest extends TestCase {

    public void testOne() throws InterruptedException {
        Object object = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);

        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        object = null;
        TimeUnit.SECONDS.sleep(20);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

    }

}
