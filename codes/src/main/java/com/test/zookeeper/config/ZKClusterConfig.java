package com.test.zookeeper.config;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ZKClusterConfig {
    private static Map<String,ZKConfig> nodeConfigs;
    private ZKClusterConfig(){
        nodeConfigs=new HashMap<String, ZKConfig>();
    }

    public static Map<String, ZKConfig> getNodeConfigs() {
        return nodeConfigs;
    }
}
