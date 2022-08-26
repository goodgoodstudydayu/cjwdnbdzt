java虚拟机面试
程序计数器-寄存器实现
记住下一条jvm指令的执行地址
程序计数器（cpu寄存器实现）-解释器-机器码-cpu
特点：
线程私有
每个线程都有自己的程序计数器
不存在内存溢出，虚拟机规定
虚拟机栈-每个线程运行时所需内存
栈帧-每个方法运行时需要的内存
方法1调用方法2
1先入栈、2后入栈
栈内存不需要垃圾回收，因为栈方法执行完栈帧会弹出，自动的回收掉不需要垃圾回收

栈内存大小默认1M，设置过大会影响线程并行数目
一个线程对应一个虚拟机栈，调用相同方法互不干扰

本地方法栈
虚拟机调用本地方法时提供的内存
不是由java代码实现的方法，java调用

堆
通过new关键字创建对象都会使用堆内存
线程共享，有垃圾回收机制
java.long.OutOfMemoryError:Java heap space
参数 -Xmx8m


方法区
线程共享
类的结构相关信息：类的成员变量，方法，
构造方法代码部分，运行时常量池

虚拟机启动时创建


常量池就是一张表，虚拟机指令根据这张表找到要执行的类名、方法名、参数类型、字面量信息
运行时常量池就是当该类被加载时，它的常量池信息就会被加载到运行时常量池中，并把里面的符号地址变为真实内存地址
