package com.egeo.components.product.manage.read;

import com.egeo.components.product.po.ChannelPriceLimitUploadPO;
import com.egeo.components.product.po.JdPriceLimitUploadPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface ChannelPriceLimitUploadReadManage {

	public ChannelPriceLimitUploadPO findChannelPriceLimitUploadById(ChannelPriceLimitUploadPO po);

	public PageResult<ChannelPriceLimitUploadPO> findChannelPriceLimitUploadOfPage(ChannelPriceLimitUploadPO po,Pagination page);

	public List<ChannelPriceLimitUploadPO> findChannelPriceLimitUploadAll(ChannelPriceLimitUploadPO po);
}
