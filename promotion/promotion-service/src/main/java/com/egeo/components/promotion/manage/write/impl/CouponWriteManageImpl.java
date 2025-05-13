package com.egeo.components.promotion.manage.write.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.read.CouponStoreReadDAO;
import com.egeo.components.promotion.dao.write.CouponCompanyWriteDAO;
import com.egeo.components.promotion.dao.write.CouponStoreWriteDAO;
import com.egeo.components.promotion.dao.write.CouponWriteDAO;
import com.egeo.components.promotion.manage.write.CouponWriteManage;
import com.egeo.components.promotion.po.CouponCompanyPO;
import com.egeo.components.promotion.po.CouponPO;
import com.egeo.components.promotion.po.CouponStorePO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class CouponWriteManageImpl implements CouponWriteManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CouponWriteDAO couponWriteDAO;
    @Autowired
    private CouponCompanyWriteDAO couponCompanyWriteDAO;
    @Autowired
    private CouponStoreWriteDAO couponStoreWriteDAO;
    @Autowired
    private CouponStoreReadDAO couponStoreReadDAO;

    @Override
    public Long insertCouponWithTx(CouponPO po) {

        int i;
        try {
            i = couponWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        // 建立关系
        for (Long companyId : po.getCompanyList()) {
            CouponCompanyPO couponCompanyPO = new CouponCompanyPO();
            couponCompanyPO.setCompanyId(companyId);
            couponCompanyPO.setCouponId(po.getId());
            i = couponCompanyWriteDAO.insert(couponCompanyPO);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        }
        if (EmptyUtil.isNotEmpty(po.getStoreList())) {
        	// 优惠券关联门店
            for (Long storeId : po.getStoreList()) {
                CouponStorePO couponStorePO = new CouponStorePO();
                couponStorePO.setCouponId(po.getId());
                couponStorePO.setStoreId(storeId);
                couponStorePO.setCreateTime(new Date());
                int flag = couponStoreWriteDAO.insert(couponStorePO);
                if (flag == 0) {
                    throw new BusinessException("未能成功插入数据!");
                }
            }
        }
        return po.getId();
    }

    @Override
    public int updateCouponWithTx(CouponPO po) {
        int i;
        i = couponWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        if (EmptyUtil.isEmpty(po.getCompanyList()))
            return i;
        // 删除关系
        CouponCompanyPO couponCompany = new CouponCompanyPO();
        couponCompany.setCouponId(po.getId());
        i = couponCompanyWriteDAO.deleteByPara(couponCompany);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");

        // 建立关系
        for (Long companyId : po.getCompanyList()) {
            CouponCompanyPO couponCompanyPO = new CouponCompanyPO();
            couponCompanyPO.setCompanyId(companyId);
            couponCompanyPO.setCouponId(po.getId());
            i = couponCompanyWriteDAO.insert(couponCompanyPO);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        }
        
        CouponStorePO couponStorePO = new CouponStorePO();
    	couponStorePO.setCouponId(po.getId());
    	couponStoreWriteDAO.deleteByPara(couponStorePO);
        //门店
        if (EmptyUtil.isNotEmpty(po.getStoreList())) {
        	for (Long stortId : po.getStoreList()) {
                CouponStorePO obj = new CouponStorePO();
                obj.setStoreId(stortId);
                obj.setCouponId(po.getId());
                obj.setCreateTime(new Date());
                int f = couponStoreWriteDAO.insert(obj);
                if (f == 0) {
                    throw new BusinessException("未能成功插入数据!");
                }
                i += f;
            }
        }
        return i;
    }

    @Override
    public int deleteCouponWithTx(CouponPO po) {
        int i;
        i = couponWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
	