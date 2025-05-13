package com.egeo.components.product.scheduler;

import com.egeo.utils.cache.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@ConditionalOnResource(resources= {"file:./productScheduler.properties"})
@PropertySource(value = {"file:./productScheduler.properties"},ignoreResourceNotFound = false, encoding = "UTF-8", name = "productScheduler.properties")
public class ProductSchedulerJob {

    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Value("${jd.syncSellState:6}")
    public Integer syncSellState;
    @Resource
    private JedisUtil jedisUtil;
    @Resource
    private StandardUnitCombinationSuSchedulerFacade standardUnitCombinationSuSchedulerFacade;

    @Resource
    private JdProductSchedulerFacade jdProductSchedulerFacade;

    @Scheduled(cron = "${scheduled.cron.refreshCombinationSuSellStatus}")
    public void refreshCombinationSuSellStatus(){
        logger.info("定时任务:处理同步商品组合京东可售状态");
        Date endDate=new Date(System.currentTimeMillis()-syncSellState*60*60*1000);
        standardUnitCombinationSuSchedulerFacade.refreshCombinationSuSellStatus(endDate);
    }

//    @Scheduled(cron = "${scheduled.cron.syncJdCategory}")
//    public void syncJdCategory(){
//        logger.info("定时任务:同步京东类目===开始");
//        String lockKey="ProductSchedulerJob_syncJdCategory";
//        boolean lock=false;
//        try {
//            lock=jedisUtil.lockWithParam(lockKey,"1", JedisUtil.ORDER_CANCEL_LOCK_EXPIRE_TIME);
//            if (!lock){
//                return;
//            }
//            jdProductSchedulerFacade.syncJdCategory();
//        }catch (Exception e){
//            logger.error("定时任务:同步京东类目异常"+e.getMessage());
//        }finally {
//            if (lock){
//                jedisUtil.delLock(lockKey);
//            }
//        }
//        logger.info("定时任务:同步京东类目===完成");
//    }
}
