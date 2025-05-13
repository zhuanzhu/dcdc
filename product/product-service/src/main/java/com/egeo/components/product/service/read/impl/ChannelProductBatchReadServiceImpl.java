package com.egeo.components.product.service.read.impl;

import com.egeo.components.product.converter.ChannelProductBatchConverter;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.manage.read.ChannelProductBatchReadManage;
import com.egeo.components.product.po.ChannelProductBatchPO;
import com.egeo.components.product.service.read.ChannelProductBatchReadService;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelProductBatchReadService")
public class ChannelProductBatchReadServiceImpl implements ChannelProductBatchReadService {



    @Autowired
    private ChannelProductBatchReadManage channelProductBatchReadManage;

    @Override
    public ChannelProductBatchDTO findChannelProductBatchById(ChannelProductBatchDTO dto) {
        ChannelProductBatchPO po = ChannelProductBatchConverter.toPO(dto);
        ChannelProductBatchPO list = channelProductBatchReadManage.findChannelProductBatchById(po);
        return ChannelProductBatchConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelProductBatchDTO> findChannelProductBatchOfPage(ChannelProductBatchDTO dto, Pagination page) {
        ChannelProductBatchPO po = ChannelProductBatchConverter.toPO(dto);
        PageResult<ChannelProductBatchPO> pageResult = channelProductBatchReadManage.findChannelProductBatchOfPage(po, page);

        List<ChannelProductBatchDTO> list = ChannelProductBatchConverter.toDTO(pageResult.getList());
        PageResult<ChannelProductBatchDTO> result = new PageResult<ChannelProductBatchDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<ChannelProductBatchDTO> findChannelProductBatchAll(ChannelProductBatchDTO dto) {
        ChannelProductBatchPO po = ChannelProductBatchConverter.toPO(dto);
        List<ChannelProductBatchPO> list = channelProductBatchReadManage.findChannelProductBatchAll(po);
        return ChannelProductBatchConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelSupplierProductResponseVO> findChannelProductOfPage(ChannelSupplierProductRequestVO vo, Pagination page){
        PageResult<ChannelSupplierProductResponseVO> pageResult = channelProductBatchReadManage.findChannelProductOfPage(vo,page);
        return pageResult;
    }
}
