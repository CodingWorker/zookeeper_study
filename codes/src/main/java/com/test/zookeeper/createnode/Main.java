package com.test.zookeeper.createnode;

import org.apache.zookeeper.*;

/**
 * Created by daiya on 2017/9/7.
 */
public class Main {
    public static void main(String[] args)throws Exception{
        String zkConn="localhost:2181";
        int sessionTimeout=5000000;
        ZooKeeper zkClient=new ZooKeeper(zkConn,sessionTimeout,new MyWatcherImpl());

        //创建节点
        System.out.println(zkClient.exists("/root",true));

        //创建永久节点：/root
        zkClient.create("/root","root".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(zkClient.getData("/root",true,null));

        //创建临时节点
        zkClient.create("/temp", "temp".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(zkClient.getData("/temp",true,null));

        zkClient.close();
    }
}

class MyWatcherImpl implements Watcher{
    public void process(WatchedEvent watchedEvent) {
        System.out.println("某些事件发生触发了此事件");
    }
}
