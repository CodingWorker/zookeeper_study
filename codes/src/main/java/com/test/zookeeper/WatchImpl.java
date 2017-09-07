package com.test.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Created by daiya on 2017/9/7.
 */
public class WatchImpl implements Watcher {
    private static CountDownLatch countDownLatch=new CountDownLatch(0);

    public void process(WatchedEvent watchedEvent) {
        System.out.println("received watched event: "+watchedEvent);
        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args)throws Exception{
        String zkConn="192.168.86.128:2181";
        int sessionTimeout=5000000;
        ZooKeeper zooKeeper=new ZooKeeper(zkConn,sessionTimeout,new WatchImpl());
        System.out.println(zooKeeper.getState());
        try{
            countDownLatch.await();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        System.out.println("zookeeper session established");
        String value=new String(zooKeeper.getData("/t",true, null));
        System.out.println(value);
    }



}
