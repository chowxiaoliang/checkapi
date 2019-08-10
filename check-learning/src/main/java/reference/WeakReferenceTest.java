package reference;

import junit.framework.TestCase;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 * 强引用，软引用，弱引用，虚引用
 * 通常我们通过new来创建一个新对象时返回的引用就是一个强引用，若一个对象通过一系列强引用可到达，它就是强可达的(strongly reachable)，那么它就不被回收
 * 软引用（Soft Reference）：软引用和弱引用的区别在于，若一个对象是弱引用可达，无论当前内存是否充足它都会被回收，而软引用可达的对象在内存不充足时才会被回收，因此软引用要比弱引用“强”一些
 * 虚引用（Phantom Reference）：虚引用是Java中最弱的引用，那么它弱到什么程度呢？它是如此脆弱以至于我们通过虚引用甚至无法获取到被引用的对象，虚引用存在的唯一作用就是当它指向的对象被回收后，虚引用本身会被加入到引用队列中，用作记录它指向的对象已被销毁。
 *
 * WeakReference=>WeakHashMap=>ThreadLocal(https://majiaji.coding.me/2017/03/27/threadLocal-WeakReference%E5%92%8C%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E7%9A%84%E6%80%9D%E8%80%83/)
 * @author zhouliang
 */
public class WeakReferenceTest extends TestCase {

    public void testTwo(){
        A a = new A();
        C c = new C(a);
        a = null;
        System.gc();
        System.out.println(c.getA());
    }


    /**
     * A对象的引用a置空了，a不再指向对象A的地址，我们都知道当一个对象不再被其他对象引用的时候，是会被GC回收的，很显然及时a=null，那么A对象也是不可能被回收的，因为B依然依赖与A，在这个时候，造成了内存泄漏！
     */
    public void testOne(){
        A a = new A();
        B b = new B(a);
        a = null;
        System.gc();
        System.out.println(a);
    }

    class A{}

    class B{
        A a;
        B(A a){
            this.a = a;
        }
    }

    class C{
        WeakReference<A> weakReference;
        C(A a){
            weakReference = new WeakReference<>(a);
        }
        public A getA(){
            return weakReference.get();
        }
    }


}
