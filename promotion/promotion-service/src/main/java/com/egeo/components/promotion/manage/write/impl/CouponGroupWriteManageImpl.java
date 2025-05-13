package com.egeo.components.promotion.manage.write.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.read.CouponGroupStoreReadDAO;
import com.egeo.components.promotion.dao.write.CouponGroupRelWriteDAO;
import com.egeo.components.promotion.dao.write.CouponGroupStoreWriteDAO;
import com.egeo.components.promotion.dao.write.CouponGroupWriteDAO;
import com.egeo.components.promotion.manage.write.CouponGroupWriteManage;
import com.egeo.components.promotion.po.CouponGroupPO;
import com.egeo.components.promotion.po.CouponGroupRelPO;
import com.egeo.components.promotion.po.CouponGroupStorePO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class CouponGroupWriteManageImpl implements CouponGroupWriteManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CouponGroupWriteDAO couponGroupWriteDAO;

    @Autowired
    private CouponGroupRelWriteDAO couponGroupRelWriteDAO;

    @Autowired
    private CouponGroupStoreWriteDAO couponGroupStoreWriteDAO;

    @Autowired
    private CouponGroupStoreReadDAO couponGroupStoreReadDAO;

    @Override
    public Long insertCouponGroupWithTx(List<Long> storeList, CouponGroupPO po) {

        int i;
        try {
            i = couponGroupWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }

        // 新建关系
        for (Long couponId : po.getCouponList()) {
            CouponGroupRelPO couponGroupRelPO = new CouponGroupRelPO();
            couponGroupRelPO.setCouponId(couponId);
            couponGroupRelPO.setCouponGroupId(po.getId());
            i = couponGroupRelWriteDAO.insert(couponGroupRelPO);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        }
        // 优惠券组关联门店
        if (EmptyUtil.isNotEmpty(storeList)) {
            for (Long storeId : storeList) {
                CouponGroupStorePO couponStorePO = new CouponGroupStorePO();
                couponStorePO.setCouponGroupId(po.getId());
                couponStorePO.setStoreId(storeId);
                couponStorePO.setCreateTime(new Date());
                int flag = couponGroupStoreWriteDAO.insert(couponStorePO);
                if (flag == 0) {
                    throw new BusinessException("未能成功插入数据!");
                }
            }
        }
        return po.getId();
    }

    @Override
    public int updateCouponGroupWithTx(List<Long> storeList, CouponGroupPO po) {
        int i;
        i = couponGroupWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");

        // 删除关系
        CouponGroupRelPO couponGroupRelPO_ = new CouponGroupRelPO();
        couponGroupRelPO_.setCouponGroupId(po.getId());
        i = couponGroupRelWriteDAO.deleteByPara(couponGroupRelPO_);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");

        // 新建关系
        for (Long couponId : po.getCouponList()) {
            CouponGroupRelPO couponGroupRelPO = new CouponGroupRelPO();
            couponGroupRelPO.setCouponId(couponId);
            couponGroupRelPO.setCouponGroupId(po.getId());
            i = couponGroupRelWriteDAO.insert(couponGroupRelPO);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        }
        // 优惠券组关联门店
        if (EmptyUtil.isNotEmpty(storeList)) {
            Map<String, Object> req = new HashMap<>();
            req.put("couponGroupId", po.getId());
            //req.put("storeIds", storeList);
            List<CouponGroupStorePO> list = couponGroupStoreReadDAO.findCouponGroupStoreByKey(req,null);
            //无关门店优惠券组合关系，直接新增
            if (EmptyUtil.isEmpty(list)) {
                for (Long stortId : storeList) {
                    CouponGroupStorePO obj = new CouponGroupStorePO();
                    obj.setStoreId(stortId);
                    obj.setCouponGroupId(po.getId());
                    obj.setCreateTime(new Date());
                    int f = couponGroupStoreWriteDAO.insert(obj);
                    if (f == 0) {
                        throw new BusinessException("未能成功插入数据!");
                    }
                    i += f;
                }
                return i;
            }
            List<Long> queryCouponIds = new ArrayList<>();

            //删除门店不包含的关联关系
            for (CouponGroupStorePO obj : list) {
                if (!storeList.contains(obj.getStoreId())) {
                    CouponGroupStorePO delObj = new CouponGroupStorePO();
                    delObj.setStoreId(obj.getStoreId());
                    delObj.setCouponGroupId(po.getId());
                    int f = couponGroupStoreWriteDAO.deleteByPara(delObj);
                    i += f;
                }
                queryCouponIds.add(obj.getStoreId());
            }
            //库里面不存在的，则新增
            for (Long storeId : storeList) {
                if (!queryCouponIds.contains(storeId)) {
                    CouponGroupStorePO insertObj = new CouponGroupStorePO();
                    insertObj.setStoreId(storeId);
                    insertObj.setCouponGroupId(po.getId());
                    insertObj.setCreateTime(new Date());
                    int f = couponGroupStoreWriteDAO.insert(insertObj);
                    i += f;
                }
            }
        }
        return i;
    }

    @Override
    public int deleteCouponGroupWithTx(CouponGroupPO po) {
        int i;
        i = couponGroupWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");

        // 删除关系
        CouponGroupRelPO couponGroupRelPO_ = new CouponGroupRelPO();
        couponGroupRelPO_.setCouponGroupId(po.getId());
        i = couponGroupRelWriteDAO.deleteByPara(couponGroupRelPO_);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");

        return i;
    }
}
