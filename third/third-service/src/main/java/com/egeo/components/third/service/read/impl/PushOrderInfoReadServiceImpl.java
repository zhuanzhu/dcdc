package com.egeo.components.third.service.read.impl;

import com.egeo.components.third.converter.PushOrderInfoConverter;
import com.egeo.components.third.dto.PushOrderInfoDTO;
import com.egeo.components.third.manage.read.PushOrderInfoReadManage;
import com.egeo.components.third.po.PushOrderInfoPO;
import com.egeo.components.third.service.read.PushOrderInfoReadService;
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
@Service("pushOrderInfoReadService")
public class PushOrderInfoReadServiceImpl implements PushOrderInfoReadService {


    @Autowired
    private PushOrderInfoReadManage pushOrderInfoReadManage;

    @Override
    public PushOrderInfoDTO findPushOrderInfoById(PushOrderInfoDTO dto) {
        PushOrderInfoPO po = PushOrderInfoConverter.toPO(dto);
        PushOrderInfoPO list = pushOrderInfoReadManage.findPushOrderInfoById(po);
        return PushOrderInfoConverter.toDTO(list);
    }

    @Override
    public PageResult<PushOrderInfoDTO> findPushOrderInfoOfPage(PushOrderInfoDTO dto, Pagination page) {
        PushOrderInfoPO po = PushOrderInfoConverter.toPO(dto);
        PageResult<PushOrderInfoPO> pageResult = pushOrderInfoReadManage.findPushOrderInfoOfPage(po, page);

        List<PushOrderInfoDTO> list = PushOrderInfoConverter.toDTO(pageResult.getList());
        PageResult<PushOrderInfoDTO> result = new PageResult<PushOrderInfoDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<PushOrderInfoDTO> findPushOrderInfoAll(PushOrderInfoDTO dto) {
        PushOrderInfoPO po = PushOrderInfoConverter.toPO(dto);
        List<PushOrderInfoPO> list = pushOrderInfoReadManage.findPushOrderInfoAll(po);
        return PushOrderInfoConverter.toDTO(list);
    }
}
