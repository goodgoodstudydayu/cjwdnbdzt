import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.net.URI;

public class HdfsClient {

    @Test
    public void testMkdirs() throws Exception {

        // 1 获取文件系统
        Configuration configuration = new Configuration();

        // FileSystem fs = FileSystem.get(new URI("hdfs://yun1:8020"), configuration);
        FileSystem fs = FileSystem.get(new URI("hdfs://yun1:8020"), configuration,"admin");

        // 2 创建目录
        fs.mkdirs(new Path("/text/textt"));

        // 3 关闭资源
        fs.close();
    }
}