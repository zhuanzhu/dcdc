package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.MerchantProductCompanyPO;
import com.egeo.orm.BaseWriteDAO;

public interface MerchantProductCompanyWriteDAO extends BaseWriteDAO<MerchantProductCompanyPO> {
	/**
	 * 根据拼接的su草稿企业关系id集合批量删除
	 * @param merchantProductCompanyIds
	 * @return
	 */
	int deleteByMerchantProductCompanyIdsWithTx(@Param("ids")List<Long> merchantProductCompanyIds);
	/**
	 * 批量保存su商品公司信息
	 * @param merchantProductCompanys
	 */
	int insertAll(@Param("poList")List<MerchantProductCompanyPO> merchantProductCompanys);

    void saveMerchantProductCompany(@Param("poList")List<MerchantProductCompanyPO> merchantProductCompanyPOList);
}
	