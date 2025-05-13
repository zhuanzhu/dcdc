package com.egeo.components.order.business.thread;

import com.egeo.components.utils.ThreadPoolConfig;
import com.egeo.utils.SpringContextTool;
import lombok.extern.log4j.Log4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description 线程池
 * @Author lsl
 * @Date 2023/12/28 15:27
 * @Version V1.0
 **/
@Log4j
public class CommonThreadPoolExecutor {

    private static volatile ThreadPoolExecutor futureThreadPool; // 并发共用线程
    public static ThreadPoolExecutor getInstance() {
        if (null == futureThreadPool) {
            synchronized (CommonThreadPoolExecutor.class) {
                if (futureThreadPool == null) {
                    ThreadPoolConfig threadPoolConfig = SpringContextTool.getBean(ThreadPoolConfig.class);
                    //线程池维护线程的最少数量,核心线程数
                    final int PARTNER_COREPOOLSIZE = threadPoolConfig.getCorePoolSize();
                    //线程池维护线程的最大数量
                    final int PARTNER_MAXINUMPOOLSIZE = threadPoolConfig.getMaxPoolSize();
                    //线程池维护线程所允许的空闲时间
                    final long PARTNER_KEEPALIVETIME = threadPoolConfig.getKeepAliveTime();
                    //线程池维护线程所允许的空闲时间的单位
                    final TimeUnit PARTNER_UNIT = TimeUnit.SECONDS;
                    //线程池所使用的缓冲队列,这里队列大小为1000
                    final BlockingQueue<Runnable> PARTNER_WORKQUEUE = new ArrayBlockingQueue<Runnable>(threadPoolConfig.getBlockingQueueCount());

                    //线程池对拒绝任务的处理策略：AbortPolicy为抛出异常；CallerRunsPolicy为重试添加当前的任务，他会自动重复调用execute()方法；DiscardOldestPolicy为抛弃旧的任务，DiscardPolicy为抛弃当前的任务
                    final ThreadPoolExecutor.CallerRunsPolicy PARTNER_HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();
                    futureThreadPool = new ThreadPoolExecutor(PARTNER_COREPOOLSIZE, PARTNER_MAXINUMPOOLSIZE, PARTNER_KEEPALIVETIME, PARTNER_UNIT, PARTNER_WORKQUEUE, PARTNER_HANDLER);

                }
            }
        }
        return futureThreadPool;
    }
}
