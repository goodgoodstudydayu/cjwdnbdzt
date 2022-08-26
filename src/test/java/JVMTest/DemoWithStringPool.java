package JVMTest;

public class DemoWithStringPool {
    public static void main(String[] args) {
        String s1="a";//懒加载，执行到此处才将a字符加载到字符串池中，再加载到局部变量
        String s2="b";
        String s3="ab";

        String s4=s1+s2;//new StringBuilder().append(s1).append(s2).toString() new String("ab")
        //s4 == s3   false
        String s5="a"+"b";//在程序编译时会将a+b直接合并为ab，由于之前代码ab字符串已经加载到常量池中所以不再加载
        //s5 == s3   true

        String s6 =new String("abc");
        String intern = s6.intern();  //将在堆中的s6放入到串池中，返回串池中的对象
    }
}
