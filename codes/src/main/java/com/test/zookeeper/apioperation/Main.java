package com.test.zookeeper.apioperation;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.security.AlgorithmParameterGenerator;
import java.security.acl.Acl;
import java.util.Iterator;
import java.util.List;

/**
 * Created by daiya on 2017/9/18.
 */
public class Main {

    /**
     *
     * @param args
    Zookeeper的API接口
    »	String create(String path, byte[] data, List<ACL> acl, CreateMode createMode)
    »	Stat exists(String path, boolean watch)
    »	void delete(String path, int version)
    »	List<String> getChildren(String path, boolean watch)
    »	Stat setData(String path, byte[] data, int version)
    »	byte[] getData(String path, boolean watch, Stat stat)
    »	void addAuthInfo(String scheme, byte[] auth)
    »	Stat setACL(String path, List<ACL> acl, int version)
    »	List<ACL> getACL(String path, Stat stat)

     */
    public static void main(String[] args) throws Exception{
        String connStr="localhost:2181";
        int timeOut=1000000;
        ZooKeeper zooKeeper=new ZooKeeper(connStr, timeOut, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("some event happen");
            }
        });

        //create
        //1. 创建子节点需要保证父节点存在
        //2. 保存的数据为byte[]类型
        zooKeeper.create("/parent","the parent".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);//创建一个非序列的临时节点
        zooKeeper.exists("/parent", new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("exists called");
            }
        });

        zooKeeper.getData("/parent", false, null);
//        zooKeeper.setACL("/parent",ZooDefs.Ids.OPEN_ACL_UNSAFE,0);
        //getChildren
        List<String> children=zooKeeper.getChildren("/zookeeper",true);
        Iterator<String> iterator= children.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        List<ACL> acls=zooKeeper.getACL("/parent",null);
        Iterator<ACL> iterator1=acls.iterator();
        while(iterator1.hasNext()){
            ACL acl=iterator1.next();
            System.out.println(acl.getId());
        }

        zooKeeper.close();
    }
}
