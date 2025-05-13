package com.egeo.components.product.manage.write.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dao.read.StandardUnitCombinationCategoryReadDAO;
import com.egeo.components.product.dao.write.StandardUnitCombinationCategoryWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitCombinationWriteDAO;
import com.egeo.components.product.po.StandardUnitCombinationCategoryPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CategoryTreeNodeCategoryWriteManage;
import com.egeo.components.product.dao.read.CategoryTreeNodeCategoryReadDAO;
import com.egeo.components.product.dao.write.CategoryTreeNodeCategoryWriteDAO;
import com.egeo.components.product.po.CategoryTreeNodeCategoryPO;
import com.egeo.exception.BusinessException;

@Service
public class CategoryTreeNodeCategoryWriteManageImpl implements CategoryTreeNodeCategoryWriteManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CategoryTreeNodeCategoryWriteDAO categoryTreeNodeCategoryWriteDAO;

    @Autowired
    private CategoryTreeNodeCategoryReadDAO categoryTreeNodeCategoryReadDAO;
    @Autowired
    private StandardUnitCombinationCategoryWriteDAO standardUnitCombinationCategoryWriteDAO;
    @Autowired
    private StandardUnitCombinationCategoryReadDAO standardUnitCombinationCategoryReadDAO;

    @Override
    public Long insertCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryPO po) {

        int i;
        try {
            i = categoryTreeNodeCategoryWriteDAO.insert(po);
            if (i == 0)
                throw new BusinessException("未能成功插入数据!");
        } catch (DuplicateKeyException e) {
            logger.error("", e);
            throw new BusinessException("路径必须唯一!");
        }
        return po.getId();
    }

    @Override
    public int updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryPO po) {
        int i;
        i = categoryTreeNodeCategoryWriteDAO.update(po);
        if (i == 0)
            throw new BusinessException("未能成功更新数据!");
        return i;
    }

    @Override
    public int deleteCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryPO po) {
        int i;
        i = categoryTreeNodeCategoryWriteDAO.delete(po);
        if (i == 0)
            throw new BusinessException("未能成功删除数据!");
        return i;
    }

    /**
     * 批量添加前台类目节点与后台类目节点关系
     *
     * @param frontCategoryTreeNodeId
     * @param queenCategoryTreeNodeIdList
     * @return
     */
    @Override
    public boolean insertCategoryTreeNodeCategoryAllWithTx(Long frontCategoryTreeNodeId,
                                                           List<Long> queenCategoryTreeNodeIdList) {
        boolean isTrue = false;
        CategoryTreeNodeCategoryPO categoryTreeNodeCategoryPO = new CategoryTreeNodeCategoryPO();
        categoryTreeNodeCategoryPO.setFrontCategoryTreeNodeId(frontCategoryTreeNodeId);
        List<CategoryTreeNodeCategoryPO> categoryTreeNodeCategoryList = categoryTreeNodeCategoryReadDAO.findAll(categoryTreeNodeCategoryPO,null);
        List<Long> CategoryTreeNodeCategoryIds = new ArrayList<>();
        for (CategoryTreeNodeCategoryPO categoryTreeNodeCategoryPO2 : categoryTreeNodeCategoryList) {
            CategoryTreeNodeCategoryIds.add(categoryTreeNodeCategoryPO2.getQueenCategoryTreeNodeId());
        }

        for (Long queenCategoryTreeNodeId : queenCategoryTreeNodeIdList) {
            //判断是否新增,数据库不包含则新增
            if (!CategoryTreeNodeCategoryIds.contains(queenCategoryTreeNodeId)) {
                CategoryTreeNodeCategoryPO categoryTreeNodeCategoryPO2 = new CategoryTreeNodeCategoryPO();
                categoryTreeNodeCategoryPO2.setFrontCategoryTreeNodeId(frontCategoryTreeNodeId);
                categoryTreeNodeCategoryPO2.setQueenCategoryTreeNodeId(queenCategoryTreeNodeId);
                categoryTreeNodeCategoryWriteDAO.insert(categoryTreeNodeCategoryPO2);
            }
        }

        for (Long queenCategoryTreeNodeId : CategoryTreeNodeCategoryIds) {
            //判断是否删除，如果不包含则删除
            if (!queenCategoryTreeNodeIdList.contains(queenCategoryTreeNodeId)) {
                CategoryTreeNodeCategoryPO categoryTreeNodeCategoryPO2 = new CategoryTreeNodeCategoryPO();
                categoryTreeNodeCategoryPO2.setQueenCategoryTreeNodeId(queenCategoryTreeNodeId);
                categoryTreeNodeCategoryWriteDAO.deleteByPara(categoryTreeNodeCategoryPO2);
            }
        }
        isTrue = true;

        return isTrue;
    }

    @Override
    public boolean insertCtnAndSucWithTx(Long frontCategoryTreeNodeId, List<Long> queenCategoryTreeNodeIds, List<Long> standardUnitCombinationIds) {
        //前台类目树关联后台目录树
        boolean isTrue = false;
        List<Long> CategoryTreeNodeCategoryIds = new ArrayList<>();
        CategoryTreeNodeCategoryPO ctnc = new CategoryTreeNodeCategoryPO();
        ctnc.setFrontCategoryTreeNodeId(frontCategoryTreeNodeId);
        List<CategoryTreeNodeCategoryPO> ctncList = categoryTreeNodeCategoryReadDAO.findAll(ctnc,null);
        for (CategoryTreeNodeCategoryPO categoryTreeNodeCategoryPO2 : ctncList) {
            CategoryTreeNodeCategoryIds.add(categoryTreeNodeCategoryPO2.getQueenCategoryTreeNodeId());
        }
        for (Long queenCategoryTreeNodeId : queenCategoryTreeNodeIds) {
            //判断是否新增,数据库不包含则新增
            if (!CategoryTreeNodeCategoryIds.contains(queenCategoryTreeNodeId)) {
                CategoryTreeNodeCategoryPO categoryTreeNodeCategoryPO2 = new CategoryTreeNodeCategoryPO();
                categoryTreeNodeCategoryPO2.setFrontCategoryTreeNodeId(frontCategoryTreeNodeId);
                categoryTreeNodeCategoryPO2.setQueenCategoryTreeNodeId(queenCategoryTreeNodeId);
                categoryTreeNodeCategoryWriteDAO.insert(categoryTreeNodeCategoryPO2);
            }
        }
        for (Long queenCategoryTreeNodeId : CategoryTreeNodeCategoryIds) {
            //判断是否删除，如果不包含则删除
            if (!queenCategoryTreeNodeIds.contains(queenCategoryTreeNodeId)) {
                CategoryTreeNodeCategoryPO categoryTreeNodeCategoryPO2 = new CategoryTreeNodeCategoryPO();
                categoryTreeNodeCategoryPO2.setQueenCategoryTreeNodeId(queenCategoryTreeNodeId);
                categoryTreeNodeCategoryWriteDAO.deleteByPara(categoryTreeNodeCategoryPO2);
            }
        }
        //前台类目树关联商品组合
        List<Long> sucIds = new ArrayList<>();
        StandardUnitCombinationCategoryPO sucPO = new StandardUnitCombinationCategoryPO();
        sucPO.setCategoryTreeNodeId(frontCategoryTreeNodeId);
        List<StandardUnitCombinationCategoryPO> succList = standardUnitCombinationCategoryReadDAO.findAll(sucPO,null);
        for (StandardUnitCombinationCategoryPO suc : succList) {
            sucIds.add(suc.getStandardUnitCombinationId());
        }
        for (Long sucId : standardUnitCombinationIds) {
            //新商品组合信息在原始库商品组合中不存在,则新增
            if (!sucIds.contains(sucId)) {
                StandardUnitCombinationCategoryPO suc = new StandardUnitCombinationCategoryPO();
                suc.setStandardUnitCombinationId(sucId);
                suc.setCategoryTreeNodeId(frontCategoryTreeNodeId);
                standardUnitCombinationCategoryWriteDAO.insert(suc);
            }
        }
        for (Long id : sucIds) {
            //新商品组合信息不包含原商品组合,则删除
            if (!standardUnitCombinationIds.contains(id)) {
                StandardUnitCombinationCategoryPO succ = new StandardUnitCombinationCategoryPO();
                succ.setStandardUnitCombinationId(id);
                standardUnitCombinationCategoryWriteDAO.deleteByPara(succ);
            }
        }
        isTrue = true;
        return isTrue;
    }
}
	