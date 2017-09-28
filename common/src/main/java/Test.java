import com.ehl.lxw.common.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by 雷晓武 on 2017/8/14.
 */
public class Test {
    public static void main(String[] args) throws IOException {
//        System.out.println("hello");
//        PropertiesLoaderUtils.loadAllProperties("test.conf");
        Path path = Paths.get("../");
        System.out.println(path.toString());
    }
}
