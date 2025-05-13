package com.egeo.components.third.manage.write.impl;

import com.egeo.components.third.dao.write.EnterpriseBizConfigWriteDAO;
import com.egeo.components.third.manage.write.EnterpriseBizConfigWriteManage;
import com.egeo.components.third.po.EnterpriseBizConfigPO;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class EnterpriseBizConfigWriteManageImpl implements EnterpriseBizConfigWriteManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseBizConfigWriteDAO enterpriseBizConfigWriteDAO;

    @Override
    public Long insertEnterpriseBizConfigWithTx(EnterpriseBizConfigPO po) {

        int i ;
        try {
            i = enterpriseBizConfigWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateEnterpriseBizConfigWithTx(EnterpriseBizConfigPO po) {
        int i;
        i = enterpriseBizConfigWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteEnterpriseBizConfigWithTx(EnterpriseBizConfigPO po) {
        int i;
        i = enterpriseBizConfigWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
