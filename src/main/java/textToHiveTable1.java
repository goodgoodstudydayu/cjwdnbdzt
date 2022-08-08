import Utils.hdfsUtil;
import Utils.hiveUtil;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;
public class textToHiveTable1 {
    public static void main(String[] args) throws Exception{
        FileSystem fs = hdfsUtil.getFileSystem(new URI("hdfs://hadoop101:8020"), "hdfs");

        fs.copyFromLocalFile(new Path("C:\\Users\\FuHai\\Desktop\\hive练习表\\airline.json"),
                new Path("/lixian/airline"));
        fs.copyFromLocalFile(new Path("C:\\Users\\FuHai\\Desktop\\hive练习表\\book.json"),
                new Path("/lixian/book"));
        fs.copyFromLocalFile(new Path("C:\\Users\\FuHai\\Desktop\\hive练习表\\flt.json"),
                new Path("/lixian/flt"));
        fs.copyFromLocalFile(new Path("C:\\Users\\FuHai\\Desktop\\hive练习表\\distance.json"),
                new Path("/lixian/distance"));

        fs.close();
        hiveUtil.closeConnect();
    }
}
