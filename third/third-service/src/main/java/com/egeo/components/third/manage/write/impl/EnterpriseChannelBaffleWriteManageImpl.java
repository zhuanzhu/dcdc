package com.egeo.components.third.manage.write.impl;

import com.egeo.components.third.dao.write.EnterpriseChannelBaffleWriteDAO;
import com.egeo.components.third.manage.write.EnterpriseChannelBaffleWriteManage;
import com.egeo.components.third.po.EnterpriseChannelBafflePO;
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
public class EnterpriseChannelBaffleWriteManageImpl implements EnterpriseChannelBaffleWriteManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseChannelBaffleWriteDAO enterpriseChannelBaffleWriteDAO;

    @Override
    public Long insertEnterpriseChannelBaffleWithTx(EnterpriseChannelBafflePO po) {

        int i ;
        try {
            i = enterpriseChannelBaffleWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateEnterpriseChannelBaffleWithTx(EnterpriseChannelBafflePO po) {
        int i;
        i = enterpriseChannelBaffleWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteEnterpriseChannelBaffleWithTx(EnterpriseChannelBafflePO po) {
        int i;
        i = enterpriseChannelBaffleWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
