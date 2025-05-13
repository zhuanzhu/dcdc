package com.egeo.components.pay.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.MembershipClient;
import com.egeo.components.product.client.MembershipUserClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

/**
 * Created by 0.0 on 2018/9/28.
 */
@Component
public class SoFacade {

    @Autowired
    private SoItemClient soItemReadService;
    @Autowired
    private MembershipClient membershipReadService;
    @Autowired
    private MembershipUserClient membershipUserWriteService;
    @Autowired
    private CommodityProductUnitClient commodityProductUnitReadService;

    public SoItemDTO finSoItemBySoId(Long soId) {
        SoItemDTO soItemDTO = new SoItemDTO();
        soItemDTO.setSoId(soId);
        List<SoItemDTO> all = soItemReadService.findAll(soItemDTO);
        if(EmptyUtil.isEmpty(all)){
            throw new BusinessException("订单项查询异常");
        }
        SoItemDTO dto = all.get(0);
        CommodityProductUnitDTO productUnitDTO = new CommodityProductUnitDTO();
        productUnitDTO.setId(dto.getPuId());
        CommodityProductUnitDTO commodityProductUnitById = commodityProductUnitReadService.findCommodityProductUnitById(productUnitDTO);
        if(EmptyUtil.isEmpty(commodityProductUnitById)){
            throw new BusinessException(dto.getPuId() + "没有对应的pu");
        }
        dto.setSkuId(commodityProductUnitById.getSkuId());
        return dto;
    }

    public MembershipDTO findMembershipBySkuId(Long skuId,Long platformId) {
        MembershipDTO dto = new MembershipDTO();
        dto.setLinkedSkuId(skuId);
        dto.setPlatformId(platformId);
        List<MembershipDTO> all=membershipReadService.findMembershipAll(dto);
        if(EmptyUtil.isEmpty(all)){
            throw new BusinessException("购买的会籍商品未关联会籍");
        }
        return all.get(0);

    }

    public void insertMembershipUser(MembershipUserDTO membershipUserDTO) {
        membershipUserWriteService.insertMembershipUserWithTx(membershipUserDTO);
    }
}
