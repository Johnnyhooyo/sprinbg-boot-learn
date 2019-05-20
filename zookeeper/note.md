### acl权限控制
（官方文档的翻译）：   
Zookeeper通过ACLs管理znodes的权限，类似于UNIX的文件权限。ACLs只针对当前node不会针对其子node，通过ids。  
ZooKeeper supports the following permissions:
- CREATE: you can create a child node
- READ: you can get data from a node and list its children.
- WRITE: you can set data for a node
- DELETE: you can delete a child node
- ADMIN: you can set permissions    
ZooKeeper has the following built in schemes:
- world has a single id, anyone, that represents anyone. 代表所有连接
- auth doesn't use any id, represents any authenticated user.  代表认证了的连接
- digest uses a username:password string to generate MD5 hash which is then used as an ACL ID identity. Authentication is done by sending the username:password in clear text. When used in the ACL the expression will be the username:base64 encoded SHA1 password digest.
    用户名：密码
- ip uses the client host IP as an ACL ID identity. The ACL expression is of the form addr/bits where the most significant bits of addr are matched against the most significant bits of the client host IP.
    IP地址    

API中提供了集中权限选项，再ZooDefs.java类中。
- ZOO_OPEN_ACL_UNSAFE 开放所有权限给所有连接
- ZOO_READ_ACL_UNSAFE 开放给所有连接只读权限
- CREATE_ALL_ACL 只开放给node的创建者所有权限，前提是创建这个node的时候带有认证信息   

AuthenticationProvider接口的使用  待完善

### Zookeeper-JAVA Binding
Zookeeper的java客户端主要是Zookeeper类，其创建的同时还有两个线程被创建：IO线程和事件线程；IO使用NIO，包括想连接，心跳检测等都是IO线程的事；事件线程用于回调。  

### 事务
public static Collection<CuratorTransactionResult> transaction(CuratorFramework client) throws Exception {
        // this example shows how to use ZooKeeper's new transactions
        Collection<CuratorTransactionResult> results = client.inTransaction().create().forPath("/a/path", "some data".getBytes())
                .and().setData().forPath("/another/path", "other data".getBytes())
                .and().delete().forPath("/yet/another/path")
                .and().commit(); // IMPORTANT!
                                                                                                                                // called
        for (CuratorTransactionResult result : results) {
            System.out.println(result.getForPath() + " - " + result.getType());
        }
        return results;
    }

### 锁
在org.apache.curator.framework.recipes.locks;包下 
InterProcessMutex 可重入锁
InterProcessMultiLock 一组锁的组合  有一个失败全部释放
InterProcessReadWriteLock 可重入读写锁  可分别获取读锁和写锁 读锁不互斥 写锁互斥 读写页互斥
InterProcessSemaphoreMutex  不可重入锁
InterProcessSemaphoreV2  信号量的锁   InterProcessSemaphoreV2实现了一个跨jvm的信号量,主要工作原理是:acquire时创建一个临时顺序节点,如果创建成功且临时节点数小于等于maxLeases则说明信号量获取成功,否则wait等待,等待目录发生变化或计数改变时唤醒。整个acquire过程持InterProcessMutex互斥的,而因为其中带有等待，性能不会太高。实际使用时等待时间最好不要太长。
另外zk里面的通知可能会漏掉,所以有一定的可靠性的问题。
如果只传maxLeases进去，实际上计数不是共享的，可以使用ShardCountReader进行共享。    
- Lease 租约
- SharedCountReader 计数器 记录最大的租约数   一个用户一次可拿多个租约 比如10个租约 一次拿3个 就只能拿3次    
信号量是有两个节点，一个lock，一个lease。比如SharedCountReader是3，则lease节点可以有四个子节点，三个可用节点一个等待节点
线程先进入lock，在判断lease有没有空余位置，有的话进入lease，删除lock；实现了限流等功能。 

都是公平的锁


### 注意
使用ConnectionStateListener监控连接的状态

### 链接
https://github.com/twitter/commons  twitter的zookeeper锁
http://zookeeper.apache.org/doc/r3.4.6/zookeeperProgrammers.html#ch_zkDataModel 官方文档
http://www.dba.cn/book/zookeeper/ZOOKEEPERZhongWenShouCe/ZOOKEEPERAPI.html 中文手册