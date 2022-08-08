package Utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.net.URI;

public class hdfsUtil {
    static FileSystem fs=null;
    /**
     * 获取hdfs对象
     * @param uri 连接路径
     * @param user hdfs用户
     * @return 文件系统对象
     */
    public static FileSystem getFileSystem(URI uri,String user)throws Exception{
            // 1 获取文件系统
            Configuration configuration = new Configuration();
            configuration.set("dfs.client.use.datanode.hostname", "true");
            // FileSystem fs = FileSystem.get(new URI("hdfs://yun1:8020"), configuration);
            fs = FileSystem.get(uri, configuration,user);

        return fs;
    }

    /**
     * 从本地上传文件到hdfs
     * @param localPath hdfs路径
     * @param hdfsPath 本地路径
     */
    public static void copyFromLocal(Path localPath,Path hdfsPath)throws Exception{
        fs.copyFromLocalFile(localPath, hdfsPath);
    }


    /**
     * 从hdfs下载文件到本地
     * @param delSrc 是否删除源文件
     * @param hdfsPath hdfs路径
     * @param localPath 本地路径
     * @param checkout 是否校验文件
     * @throws Exception
     */
    public static void copyToLocal(boolean delSrc,Path hdfsPath,Path localPath,boolean checkout)
            throws Exception{
        fs.copyToLocalFile(delSrc, hdfsPath, localPath, checkout);
    }


    /**
     * 修改文件名
     * @param oldName 旧名
     * @param newName 新名
     * @throws Exception
     */
    public static void renameHdfs(Path oldName,Path newName) throws Exception{
        fs.rename(oldName,newName);
    }



}
