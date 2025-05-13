package com.egeo.components.product.manage.write.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitCombinationCategoryWriteManage;
import com.egeo.components.product.dao.read.StandardUnitCombinationCategoryReadDAO;
import com.egeo.components.product.dao.write.StandardUnitCombinationCategoryWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitCombinationWriteDAO;
import com.egeo.components.product.po.StandardUnitCombinationCategoryPO;
import com.egeo.components.product.po.StandardUnitCombinationPO;
import com.egeo.exception.BusinessException;

@Service
public class StandardUnitCombinationCategoryWriteManageImpl implements StandardUnitCombinationCategoryWriteManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StandardUnitCombinationCategoryWriteDAO standardUnitCombinationCategoryWriteDAO;

    @Autowired
    private StandardUnitCombinationCategoryReadDAO standardUnitCombinationCategoryReadDAO;

    @Autowired
    private StandardUnitCombinationWriteDAO standardUnitCombinationWriteDAO;

    @Override
    public Long insertStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryPO po) {

        int i;
        try {
            i = standardUnitCombinationCategoryWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryPO po) {
        int i;
        i = standardUnitCombinationCategoryWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryPO po) {
        int i;
        i = standardUnitCombinationCategoryWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }

    /**
     * 批量保存su组合和前台类目节点关系
     *
     * @param standardUnitCombinationPO
     * @param categoryTreeNodeIdList
     * @return
     */
    @Override
    public boolean saveStandardUnitCombinationCategoryAllWithTx(StandardUnitCombinationPO standardUnitCombinationPO,
                                                                List<Long> categoryTreeNodeIdList) {
        boolean isTrue = false;
        logger.info("根据商品组合id修改商品组合信息，商品组合id：" + standardUnitCombinationPO.getId() + ",类目id："
                + standardUnitCombinationPO.getCategoryTreeId() + "，排序类型：" + standardUnitCombinationPO.getSortType());
        standardUnitCombinationWriteDAO.update(standardUnitCombinationPO);

        logger.info("根据商品组合id查询商品组合和前台类目关系，商品组合id：" + standardUnitCombinationPO.getId());
        StandardUnitCombinationCategoryPO standardUnitCombinationCategoryPO = new StandardUnitCombinationCategoryPO();
        standardUnitCombinationCategoryPO.setStandardUnitCombinationId(standardUnitCombinationPO.getId());
        //4 是关联后台类目树,用于区别与之前设计,不会因为数据源问题而引起的原有业务异常
        if (EmptyUtil.isNotEmpty(standardUnitCombinationPO.getType())) {
            if (4 == standardUnitCombinationPO.getType()) {
                standardUnitCombinationCategoryPO.setType(1);
            }
        }

        List<StandardUnitCombinationCategoryPO> standardUnitCombinationCategoryList =
                standardUnitCombinationCategoryReadDAO.findAll(standardUnitCombinationCategoryPO,null);
        List<Long> categoryTreeNodeIds = new ArrayList<>();
        for (StandardUnitCombinationCategoryPO suCombinationCategoryPO : standardUnitCombinationCategoryList) {
            categoryTreeNodeIds.add(suCombinationCategoryPO.getCategoryTreeNodeId());
        }

        for (Long categoryTreeNodeId : categoryTreeNodeIdList) {
            //如果不包含则新增
            if (!categoryTreeNodeIds.contains(categoryTreeNodeId)) {
                StandardUnitCombinationCategoryPO suCombinationCategoryPO = new StandardUnitCombinationCategoryPO();
                suCombinationCategoryPO.setStandardUnitCombinationId(standardUnitCombinationPO.getId());
                //4 是关联后台类目树,用于区别与之前设计,不会因为数据源问题而引起的原有业务异常
                if (EmptyUtil.isNotEmpty(standardUnitCombinationPO.getType())) {
                    if (4 == standardUnitCombinationPO.getType()) {
                        suCombinationCategoryPO.setType(1);
                    }
                }
                suCombinationCategoryPO.setCategoryTreeNodeId(categoryTreeNodeId);
                standardUnitCombinationCategoryWriteDAO.insert(suCombinationCategoryPO);
            }
        }

        for (Long categoryTreeNodeId : categoryTreeNodeIds) {
            //如果不包含则删除
            if (!categoryTreeNodeIdList.contains(categoryTreeNodeId)) {
                StandardUnitCombinationCategoryPO suCombinationCategoryPO = new StandardUnitCombinationCategoryPO();
                suCombinationCategoryPO.setCategoryTreeNodeId(categoryTreeNodeId);
                //4 是关联后台类目树,用于区别与之前设计,不会因为数据源问题而引起的原有业务异常
                if (EmptyUtil.isNotEmpty(standardUnitCombinationPO.getType())) {
                    if (4 == standardUnitCombinationPO.getType()) {
                        suCombinationCategoryPO.setType(1);
                    }
                }
                standardUnitCombinationCategoryWriteDAO.deleteByPara(suCombinationCategoryPO);
            }
        }
        isTrue = true;
        return isTrue;
    }
}
	