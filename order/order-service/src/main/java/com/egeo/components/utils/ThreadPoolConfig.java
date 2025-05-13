package com.egeo.components.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Component
@ConditionalOnResource(
        resources = {"file:./config/ThreadPoolConfig.properties"}
)
@PropertySource(
        value = {"file:./config/ThreadPoolConfig.properties"},
        ignoreResourceNotFound = false,
        encoding = "UTF-8",
        name = "ThreadPoolConfig.properties"
)
public class ThreadPoolConfig {

    /**
     * 连接池配置-最少数量,核心线程数
     */
    @Value("${thread.pool.core-size:5}")
    private int corePoolSize;

    /**
     * 连接池配置-线程的最大数量
     */
    @Value("${thread.pool.max-size:5}")
    private int maxPoolSize;

    /**
     * 连接池配置-线程池维护线程所允许的空闲时间
     */
    @Value("${thread.pool.keep-alive-time:60}")
    private int keepAliveTime;

    /**
     * 连接池配置-缓冲队列大小
     */
    @Value("${thread.pool.blocking-queue-count:10}")
    private int blockingQueueCount;



    /**
     * 子连接池配置-最少数量,核心线程数
     */
    @Value("${child.thread.pool.core-size:5}")
    private int childCorePoolSize;

    /**
     * 子连接池配置-线程的最大数量
     */
    @Value("${child.thread.pool.max-size:5}")
    private int childMaxPoolSize;

    /**
     * 子连接池配置-线程池维护线程所允许的空闲时间
     */
    @Value("${child.thread.pool.keep-alive-time:60}")
    private int childKeepAliveTime;

    /**
     * 子连接池配置-缓冲队列大小
     */
    @Value("${child.thread.pool.blocking-queue-count:10}")
    private int childBlockingQueueCount;


    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getBlockingQueueCount() {
        return blockingQueueCount;
    }

    public void setBlockingQueueCount(int blockingQueueCount) {
        this.blockingQueueCount = blockingQueueCount;
    }

    public int getChildCorePoolSize() {
        return childCorePoolSize;
    }

    public void setChildCorePoolSize(int childCorePoolSize) {
        this.childCorePoolSize = childCorePoolSize;
    }

    public int getChildMaxPoolSize() {
        return childMaxPoolSize;
    }

    public void setChildMaxPoolSize(int childMaxPoolSize) {
        this.childMaxPoolSize = childMaxPoolSize;
    }

    public int getChildKeepAliveTime() {
        return childKeepAliveTime;
    }

    public void setChildKeepAliveTime(int childKeepAliveTime) {
        this.childKeepAliveTime = childKeepAliveTime;
    }

    public int getChildBlockingQueueCount() {
        return childBlockingQueueCount;
    }

    public void setChildBlockingQueueCount(int childBlockingQueueCount) {
        this.childBlockingQueueCount = childBlockingQueueCount;
    }
}
