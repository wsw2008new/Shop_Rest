package com.it.train.shop.rest.test;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 55 on 2016/5/17.
 */
public class TestJedisCluster {

    @Test
    public void testJedisCluster(){
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(30);
        // 最大连接空闲数
        config.setMaxIdle(2);

        //集群结点
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("192.168.152.131", 7001));
        jedisClusterNode.add(new HostAndPort("192.168.152.131", 7002));
        jedisClusterNode.add(new HostAndPort("192.168.152.131", 7003));
        jedisClusterNode.add(new HostAndPort("192.168.152.131", 7004));
        jedisClusterNode.add(new HostAndPort("192.168.152.131", 7005));
        jedisClusterNode.add(new HostAndPort("192.168.152.131", 7006));
        JedisCluster jc = new JedisCluster(jedisClusterNode, config);

        JedisCluster jcd = new JedisCluster(jedisClusterNode);
        jcd.set("name", "zhangsan");
        String value = jcd.get("name");
        System.out.println(value);
    }
}
