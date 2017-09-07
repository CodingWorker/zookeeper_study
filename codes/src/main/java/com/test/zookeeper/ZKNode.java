package com.test.zookeeper;

import com.test.zookeeper.config.ZKConfig;

/**
 * Created by daiya on 2017/9/7.
 */
public class ZKNode {
    private ZKConfig zkConfig;
    public ZKNode(ZKConfig zkConfig){
        this.zkConfig=zkConfig;
    }

    public ZKNode(){}

    public ZKConfig getZkConfig() {
        return zkConfig;
    }

    public void setZkConfig(ZKConfig zkConfig) {
        this.zkConfig = zkConfig;
    }
}
