package com.test.zookeeper;

import com.test.zookeeper.config.ZKConfig;

/**
 *
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
