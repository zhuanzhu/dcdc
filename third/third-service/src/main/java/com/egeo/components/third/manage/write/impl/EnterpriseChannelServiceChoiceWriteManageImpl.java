package com.egeo.components.third.manage.write.impl;

import com.egeo.components.third.dao.write.EnterpriseChannelServiceChoiceWriteDAO;
import com.egeo.components.third.manage.write.EnterpriseChannelServiceChoiceWriteManage;
import com.egeo.components.third.po.EnterpriseChannelServiceChoicePO;
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
public class EnterpriseChannelServiceChoiceWriteManageImpl implements EnterpriseChannelServiceChoiceWriteManage {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseChannelServiceChoiceWriteDAO enterpriseChannelServiceChoiceWriteDAO;

    @Override
    public Long insertEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoicePO po) {

        int i ;
        try {
            i = enterpriseChannelServiceChoiceWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoicePO po) {
        int i;
        i = enterpriseChannelServiceChoiceWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteEnterpriseChannelServiceChoiceWithTx(EnterpriseChannelServiceChoicePO po) {
        int i;
        i = enterpriseChannelServiceChoiceWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }
}
