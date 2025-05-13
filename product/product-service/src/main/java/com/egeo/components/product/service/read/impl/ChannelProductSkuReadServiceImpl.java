package com.egeo.components.product.service.read.impl;

import com.egeo.components.product.condition.ChannelProductAndSkuCondition;
import com.egeo.components.product.converter.ChannelProductSkuConverter;
import com.egeo.components.product.dto.ChannelProductAndSkuListDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.manage.read.ChannelProductSkuReadManage;
import com.egeo.components.product.po.ChannelProductSkuPO;
import com.egeo.components.product.service.read.ChannelProductSkuReadService;
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
@Service("channelProductSkuReadService")
public class ChannelProductSkuReadServiceImpl implements ChannelProductSkuReadService {



    @Autowired
    private ChannelProductSkuReadManage channelProductSkuReadManage;

    @Override
    public ChannelProductSkuDTO findChannelProductSkuById(ChannelProductSkuDTO dto) {
        ChannelProductSkuPO po = ChannelProductSkuConverter.toPO(dto);
        ChannelProductSkuPO list = channelProductSkuReadManage.findChannelProductSkuById(po);
        return ChannelProductSkuConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelProductSkuDTO> findChannelProductSkuOfPage(ChannelProductSkuDTO dto, Pagination page) {
        ChannelProductSkuPO po = ChannelProductSkuConverter.toPO(dto);
        PageResult<ChannelProductSkuPO> pageResult = channelProductSkuReadManage.findChannelProductSkuOfPage(po, page);

        List<ChannelProductSkuDTO> list = ChannelProductSkuConverter.toDTO(pageResult.getList());
        PageResult<ChannelProductSkuDTO> result = new PageResult<ChannelProductSkuDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<ChannelProductSkuDTO> findChannelProductSkuAll(ChannelProductSkuDTO dto) {
        ChannelProductSkuPO po = ChannelProductSkuConverter.toPO(dto);
        List<ChannelProductSkuPO> list = channelProductSkuReadManage.findChannelProductSkuAll(po);
        return ChannelProductSkuConverter.toDTO(list);
    }

    @Override
    public List<ChannelProductSkuDTO> findChannelProductSkuBySkuIds(List<String> skuList,String channelCode){
        List<ChannelProductSkuPO> list = channelProductSkuReadManage.findChannelProductSkuBySkuIds(skuList,channelCode);
        return ChannelProductSkuConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelProductAndSkuCondition> getChannelProductAndSkuListOfPage(ChannelProductAndSkuListDTO dto, Pagination page){
       return channelProductSkuReadManage.getChannelProductAndSkuListOfPage(dto,page);
    }

    @Override
    public List<ChannelProductSkuDTO> findChannelProductSkuBySkuCodes(List<String> skuList,String channelCode){
        List<ChannelProductSkuPO> list = channelProductSkuReadManage.findChannelProductSkuBySkuCodes(skuList,channelCode);
        return ChannelProductSkuConverter.toDTO(list);
    }

}
