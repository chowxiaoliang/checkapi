package classloader;

import java.lang.ClassLoader;

/**
 * @author zhouliang
 * @since 2018-06-28 11:38
 * @desc Java虚拟机可以安装多个类加载器，系统默认三个主要的类加载器，每个加载器负责加载特定位置的类：
         BootStrap，ExtClassLoader，AppClassLoader。
        当Java虚拟机要加载一个类时，一般来说，一个加载请求由AppClassLoader发起，但是AppClassLoader并不会尝试去加载该类，
        而是把这个任务委托给他的父类，即ExtClassLoader，而ExtClassLoader也不会去加载，而是又委托给他的父类，即BootStrap，
        BootStrap已经是终极大boss了，他不能委托给其他人，只好自己去寻找class文件，如果他找到了class文件，
        则将之加载，当他找不到时，又把任务推给儿子，也就是ExtClassLoader，ExtClassLoader这时才会去寻找，如果他找到了class文件，
        则将之加载，当他找不到时，又把任务推给儿子，也就是AppClassLoader，如果他找到了class文件，则将之加载，当他找不到时，
        则抛出一个ClassNotFoundException，加载请求的发起者不会再去寻找自己的儿子让他加载。
 **/
public class ClassLoaderTestTwo {
    public static void main(String[] args) {
        // 拿到一个类加载器
        ClassLoader classLoader = ClassLoaderTestTwo.class.getClassLoader();
        // 循环依次遍历loader的父类
        while (classLoader != null){
            System.out.println("current loader is => " + classLoader);
            classLoader = classLoader.getParent();
        }
        // 最顶层的loader
        System.out.println("top loader is => " + classLoader);
    }
}
