package com.egeo.components.product.service.read.impl;

import com.egeo.components.product.condition.ChannelProductPicCondition;
import com.egeo.components.product.converter.ChannelProductPictureConverter;
import com.egeo.components.product.dto.channel.ChannelProductPictureDTO;
import com.egeo.components.product.manage.read.ChannelProductPictureReadManage;
import com.egeo.components.product.po.ChannelProductPicturePO;
import com.egeo.components.product.service.read.ChannelProductPictureReadService;
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
@Service("channelProductPictureReadService")
public class ChannelProductPictureReadServiceImpl implements ChannelProductPictureReadService {



    @Autowired
    private ChannelProductPictureReadManage channelProductPictureReadManage;

    @Override
    public ChannelProductPictureDTO findChannelProductPictureById(ChannelProductPictureDTO dto) {
        ChannelProductPicturePO po = ChannelProductPictureConverter.toPO(dto);
        ChannelProductPicturePO list = channelProductPictureReadManage.findChannelProductPictureById(po);
        return ChannelProductPictureConverter.toDTO(list);
    }

    @Override
    public PageResult<ChannelProductPictureDTO> findChannelProductPictureOfPage(ChannelProductPictureDTO dto, Pagination page) {
        ChannelProductPicturePO po = ChannelProductPictureConverter.toPO(dto);
        PageResult<ChannelProductPicturePO> pageResult = channelProductPictureReadManage.findChannelProductPictureOfPage(po, page);

        List<ChannelProductPictureDTO> list = ChannelProductPictureConverter.toDTO(pageResult.getList());
        PageResult<ChannelProductPictureDTO> result = new PageResult<ChannelProductPictureDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<ChannelProductPictureDTO> findChannelProductPictureAll(ChannelProductPictureDTO dto) {
        ChannelProductPicturePO po = ChannelProductPictureConverter.toPO(dto);
        List<ChannelProductPicturePO> list = channelProductPictureReadManage.findChannelProductPictureAll(po);
        return ChannelProductPictureConverter.toDTO(list);
    }

    @Override
    public List<ChannelProductPicturePO> findChannelPicByProductIds(List<String> productIds, String channelCode){

        return channelProductPictureReadManage.findChannelPicByProductIds(productIds,channelCode);
    }
}
