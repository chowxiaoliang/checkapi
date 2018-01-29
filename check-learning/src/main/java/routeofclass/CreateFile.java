package routeofclass;

import java.io.File;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
public class CreateFile {

    public static void main(String[] args){

        File file = new File("abs");
        String path = file.getAbsolutePath();
        System.out.println(path);
    }
}
