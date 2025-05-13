package com.egeo.components.stock.service.write.impl.Thread;

import com.egeo.components.stock.manage.write.CommodityProductUnitWarehouseStockWriteManage;
import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;
import com.egeo.utils.cache.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 0.0 on 2019/4/10.
 */
@Component
public class SaveListFactory implements Runnable {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private JedisUtil jedisUtil;

    public void setJedisUtil(JedisUtil jedisUtil) {
        this.jedisUtil = jedisUtil;
    }

    public void setCommodityProductUnitWarehouseStockWriteManage(CommodityProductUnitWarehouseStockWriteManage commodityProductUnitWarehouseStockWriteManage) {
        this.commodityProductUnitWarehouseStockWriteManage = commodityProductUnitWarehouseStockWriteManage;
    }

    private CommodityProductUnitWarehouseStockWriteManage commodityProductUnitWarehouseStockWriteManage;
    private CountDownLatch latch;
    private List<CommodityProductUnitWarehouseStockPO> commodityProductUnitWarehouseStockPOList;
    private String saveType;

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public void setCommodityProductUnitWarehouseStockPOList(List<CommodityProductUnitWarehouseStockPO> commodityProductUnitWarehouseStockPOList) {
        this.commodityProductUnitWarehouseStockPOList = commodityProductUnitWarehouseStockPOList;
    }

    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.info("CommodityProductUnitWarehouseStock异步处理等待异常"+e.getMessage());
            e.printStackTrace();
        }
        if(saveType.equals("CommodityProductUnitWarehouseStock")){
            logger.info("CommodityProductUnitWarehouseStock异步处理开始");
            commodityProductUnitWarehouseStockWriteManage.saveCommodityProductUnitWarehouseStock(commodityProductUnitWarehouseStockPOList);
        }
    }
}
