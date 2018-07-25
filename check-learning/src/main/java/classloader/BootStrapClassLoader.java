package classloader;

import java.net.URL;

/**
 * @author zhouliang
 * @since 2018-06-28 10:20
 **/
public class BootStrapClassLoader {
    public static void main(String[] args) {
        URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
        System.out.println(String.class.getClassLoader());
    }
}
