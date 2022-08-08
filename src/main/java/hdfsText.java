import Utils.hdfsUtil;
import org.apache.hadoop.fs.*;

import java.net.URI;
import java.util.Arrays;

public class hdfsText {
    public static void main(String[] args) throws Exception{

        FileSystem fs = hdfsUtil.getFileSystem(new URI("hdfs://hadoop101:8020"), "hdfs");


        // 2 创建目录
        fs.mkdirs(new Path("/text/textt"));


        // 3 上传文件到集群
        hdfsUtil.copyFromLocal(new Path("C:\\Users\\FuHai\\Desktop\\hdfsTextss.txt"),
                new Path("/"));


        // 4 下载文件到本地
//        hdfsUtil.copyToLocal(false,
//                new Path("/topology.py"),
//                new Path("C:\\Users\\FuHai\\Desktop"),
//                true);


        // 5 修改文件名
        hdfsUtil.renameHdfs(
                new Path("/topology.py"),
                new Path("/newName.py"));


        // 6 文件删除
        //fs.delete(new Path("/text/textt/hdfsTextss.txt"),true);


        // 7 获取文件信息
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();

            System.out.println("========" + fileStatus.getPath() + "=========");
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getGroup());
            System.out.println(fileStatus.getLen());
            System.out.println(fileStatus.getModificationTime());
            System.out.println(fileStatus.getReplication());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPath().getName());

            // 获取块信息
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println(Arrays.toString(blockLocations));
        }


        // 8 判断是文件还是文件夹
        FileStatus[] listStatus = fs.listStatus(new Path("/"));

        for (FileStatus fileStatus : listStatus) {

            // 如果是文件
            if (fileStatus.isFile()) {
                System.out.println("f:"+fileStatus.getPath().getName());
            }else {
                System.out.println("d:"+fileStatus.getPath().getName());
            }
        }
        fs.close();
    }
}
