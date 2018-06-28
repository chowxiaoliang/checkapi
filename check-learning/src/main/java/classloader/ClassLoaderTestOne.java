package classloader;

/**
 * @author zhouliang
 * @since 2018-06-28 11:32
 **/
public class ClassLoaderTestOne {
    public static void main(String[] args) {
        // 先得到类加载器，再拿到类加载器的字节码，最后根据字节码拿到类加载器的名称
        String loaderName = ClassLoaderTestOne.class.getClassLoader().getClass().getName();
        System.out.println("current class loader is => " + loaderName);
        // System的类加载器为null，说明System的类加载器是BootStrap
        System.out.println(System.class.getClassLoader());
        // String的类加载器是null，说明String的类加载器是BootStrap
        System.out.println(String.class.getClassLoader());
    }
}
