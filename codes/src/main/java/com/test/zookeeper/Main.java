package com.test.zookeeper;

import org.apache.zookeeper.*;

import java.util.List;

/**
 * Created by daiya on 2017/9/7.
 */
public class Main {
    public static void main(String[] args)throws Exception{
        String connectStr="192.168.86.128:2181";
        int sessionTimeout=50000000;
        ZooKeeper zooKeeper=new ZooKeeper(connectStr, sessionTimeout, new Watcher() {
            //监控所有被触发的事件
            public void process(WatchedEvent watchedEvent) {
                System.out.println("某事件被触发了");
            }
        });

        zooKeeper.register(new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("hello");
            }
        });

        zooKeeper.register(new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("你好");
            }
        });

        //创建/root节点，模式为长久型
        zooKeeper.create("/root","mytest-data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zooKeeper.create("/root/childone","childone".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        //取得/root下的节点名称,返回List<String>，这里的子节点仍然是路径
        List<String> childrenNames= zooKeeper.getChildren("/root",true);

        //取得/root/childone节点下的数据，返回byte[],这个是获取数据
        zooKeeper.getData("/root/childone",true,null);

        //修改节点下的数据，-1表明无视版本
        zooKeeper.setData("/root/childone","data-modified".getBytes(),-1);

        //删除节点,-1表明无视版本
        zooKeeper.delete("/root/childone",-1);

        //关闭session
        zooKeeper.close();
    }
}
