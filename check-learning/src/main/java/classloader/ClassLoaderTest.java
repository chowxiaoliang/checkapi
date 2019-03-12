package classloader;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

public class ClassLoaderTest {
    public static void main(String[] args) throws IOException {
        java.lang.ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        System.out.println(java.lang.ClassLoader.getSystemClassLoader());
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
        Enumeration<URL> urls = classLoader.getParent().getResources("bootstrap.properties");
        ArrayList result = new ArrayList();
        while(urls.hasMoreElements()) {
            URL url = urls.nextElement();
            Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
            String factoryClassNames = properties.getProperty("server.port");
            result.addAll(Arrays.asList(StringUtils.commaDelimitedListToStringArray(factoryClassNames)));
        }

        System.out.println(JSONObject.toJSONString(result));
    }
}
