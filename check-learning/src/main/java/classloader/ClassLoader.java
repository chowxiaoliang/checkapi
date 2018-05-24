package classloader;


import util.ConfigUtil;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
public class ClassLoader {

    public static void main(String[] args){

        String path1 = ClassLoader.class.getResource("").getFile();

        String path2 = ClassLoader.class.getResource("/").getFile();

        String path3 = ClassLoader.class.getCanonicalName();



        System.out.println(path1);
        System.out.println(path2);
        System.out.println(path3);


        String str = ConfigUtil.getValueByKey("es.client.transport.sniff");

        System.out.println("result is :"+ str);

    }
}
