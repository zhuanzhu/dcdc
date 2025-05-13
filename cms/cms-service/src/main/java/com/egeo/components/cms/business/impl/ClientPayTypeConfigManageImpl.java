package com.egeo.components.cms.business.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.cms.business.ClientPayTypeConfigManage;
import com.egeo.components.cms.converter.CmsPayTypeConverter;
import com.egeo.components.cms.facade.CmsClientFacade;
import com.egeo.components.cms.facade.CmsClientPayTypeConfigFacade;
import com.egeo.components.cms.facade.CmsPayTypeFacade;
import com.egeo.components.cms.vo.CmsClientPayTypeConfigVO;
import com.egeo.components.cms.vo.CmsClientVO;
import com.egeo.components.cms.vo.CmsPayTypeVO;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.ClientPayTypeConfigDTO;
import com.egeo.components.user.dto.PayTypeDTO;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

/**
 * Created by 0.0 on 2018/8/30.
 */
@Component("clientPayTypeConfigManageImpl")
public class ClientPayTypeConfigManageImpl implements ClientPayTypeConfigManage{

    @Resource(name="cmsClientFacade")
    private CmsClientFacade cmsClientFacade;

    @Resource(name = "cmsClientPayTypeConfigFacade")
    private CmsClientPayTypeConfigFacade cmsClientPayTypeConfigFacade;

    @Resource(name = "cmsPayTypeFacade")
    private CmsPayTypeFacade cmsPayTypeFacade;
    /**
     * 查询所有客户端的第三方支付方式
     * @return
     */
    @Override
    public JsonResult<Map<String, Object>> findAllClientPayType(Long platformId) {

        //1.查询所有的客户端类型
        List<ClientDTO> clientAll=cmsClientFacade.getAllClientList(new ClientDTO());
        if(clientAll==null){
            return null;
        }
        List<CmsClientVO> cmsClientVOList = new ArrayList<>();
        //2.根据客户端类型查询每种前端的已配置支付方式
        for(ClientDTO client:clientAll){


            //2.1根据客户端的id在支付配置表中查询所有的第三方支付方式(已按照index_code排序)
            ClientPayTypeConfigDTO dto = new ClientPayTypeConfigDTO();
            dto.setClientId(client.getId());
            dto.setPlatformId(platformId);
            dto.setIsStop(0);
            List<ClientPayTypeConfigDTO>  clientPayTypeConfigList=cmsClientPayTypeConfigFacade.getClientPayTypeByClientIdAndIsStop(dto);
            //该客户端不需要显示
            if(clientPayTypeConfigList.size()==0){
                continue;
            }
            CmsClientVO cmsClientVO = new CmsClientVO();
            //每个客户端的已配置的支付方式的名称
            List<String> payTypeNameList = new ArrayList<>();
            //2.2遍历配置的第三方支付方式,获得每个支付方式的名称
            for(ClientPayTypeConfigDTO clientPayTypeConfigDTO: clientPayTypeConfigList){
                //得到付款方式
                PayTypeDTO payTypeDTO = cmsPayTypeFacade.getPayTypeByCode(clientPayTypeConfigDTO.getPayTypeCode());

                payTypeNameList.add(payTypeDTO.getPayTypeName());
            }
            //2.3封装返回对象信息
            cmsClientVO.setPayTypeName(payTypeNameList);//支付方式名
            cmsClientVO.setId(client.getId());//前端类型id
            cmsClientVO.setClientPayTypeRemarks(client.getClientPayTypeRemarks());//备注信息
            cmsClientVO.setName(client.getName());//前端名
            cmsClientVOList.add(cmsClientVO);


        }
        //3.返回數據
        return JsonResult.success("list",cmsClientVOList);
    }

    @Override
    public JsonResult<Map<String, Object>> findClientPayTypeByClientId(Long clientId,Long platformId) {

        //1.校验该id是否存在
        ClientDTO dto = new ClientDTO();
        dto.setId(clientId);
        ClientDTO clientById = cmsClientFacade.getClientById(dto);
        if(clientById==null){
            return JsonResult.fail(clientId+"前端类型无效");
        }
        CmsClientVO cmsClientVO = new CmsClientVO();

        List<CmsClientPayTypeConfigVO> cmsClientPayTypeConfigVOList = new ArrayList<>();
        //2.查询出已配置的第三方支付方式(按照index_code排序)
        ClientPayTypeConfigDTO payTypeConfigDTO = new ClientPayTypeConfigDTO();
        payTypeConfigDTO.setClientId(clientId);
        payTypeConfigDTO.setPlatformId(platformId);
        int size = cmsClientPayTypeConfigFacade.getClientPayTypeByClientIdAndIsStop(payTypeConfigDTO).size();
        if(size==0){
            return JsonResult.fail("不存在支付方式");
        }
        payTypeConfigDTO.setIsStop(0);

        List<ClientPayTypeConfigDTO> clientPayTypeConfigList = cmsClientPayTypeConfigFacade.getClientPayTypeByClientIdAndIsStop(payTypeConfigDTO);

        //查询每个支付方式的名称
        for(ClientPayTypeConfigDTO cDTO:clientPayTypeConfigList){
            CmsClientPayTypeConfigVO cmsClientPayTypeConfigVO = new CmsClientPayTypeConfigVO();
            PayTypeDTO payTypeByCode = cmsPayTypeFacade.getPayTypeByCode(cDTO.getPayTypeCode());
            cmsClientPayTypeConfigVO.setId(cDTO.getId());
            cmsClientPayTypeConfigVO.setPayTypeCode(cDTO.getPayTypeCode());
            cmsClientPayTypeConfigVO.setPayTypeName(payTypeByCode.getPayTypeName());
            cmsClientPayTypeConfigVO.setIndexCode(cDTO.getIndexCode());
            cmsClientPayTypeConfigVO.setIsStop(0);
            cmsClientPayTypeConfigVOList.add(cmsClientPayTypeConfigVO);
        }
        //确保存在未配置的第三方支付方式
        if(cmsClientPayTypeConfigVOList.size()<size){
            //3.查询未配置(停用的)第三方支付方式(按照创建时间来排序)
            ClientPayTypeConfigDTO payTypeConfigDTOIsStop = new ClientPayTypeConfigDTO();
            payTypeConfigDTOIsStop.setIsStop(1);
            payTypeConfigDTOIsStop.setClientId(clientId);
            List<ClientPayTypeConfigDTO> clientPayTypeConfigIsStopList = cmsClientPayTypeConfigFacade.getClientPayTypeByClientIdAndIsStop(payTypeConfigDTOIsStop);
            //获取已停用的第三方支付方式
            List<Integer> collect = clientPayTypeConfigIsStopList.stream().map(c -> c.getPayTypeCode()).collect(Collectors.toList());
            List<PayTypeDTO> payTypeDTOList= cmsPayTypeFacade.getPayTypeByCodes(collect);
            for (PayTypeDTO payTypeDTO : payTypeDTOList) {
                CmsClientPayTypeConfigVO cmsClientPayTypeConfigVO = new CmsClientPayTypeConfigVO();
                cmsClientPayTypeConfigVO.setIsStop(1);
                cmsClientPayTypeConfigVO.setId(clientPayTypeConfigIsStopList.stream().filter(c->c.getPayTypeCode().equals(payTypeDTO.getPayTypeCode())).findFirst().get().getId());
                cmsClientPayTypeConfigVO.setPayTypeCode(payTypeDTO.getPayTypeCode());
                cmsClientPayTypeConfigVO.setPayTypeName(payTypeDTO.getPayTypeName());
                cmsClientPayTypeConfigVOList.add(cmsClientPayTypeConfigVO);
            }
        }




        //4.拼装返回数据
        cmsClientVO.setId(clientId);
        cmsClientVO.setName(clientById.getName());
        cmsClientVO.setClientPayTypeRemarks(clientById.getClientPayTypeRemarks());
        cmsClientVO.setCmsClientPayTypeConfigVOList(cmsClientPayTypeConfigVOList);
        return JsonResult.success("list",cmsClientVO);
    }

    @Override
    public JsonResult<Map<String, Object>> updateClientPayTypeByClientId(CmsClientVO cmsClientVO, Long platformId) {
        //1.判断参数的是否合法
        List<CmsClientPayTypeConfigVO> cmsClientPayTypeConfigVOList = cmsClientVO.getCmsClientPayTypeConfigVOList();
        Set<Integer> indexCodeSet = new HashSet<>();
        int size=0;
        for(CmsClientPayTypeConfigVO vo:cmsClientPayTypeConfigVOList){
            //选中的支付方式为填写排序code
            if(vo.getIsStop()==0&&EmptyUtil.isEmpty(vo.getIndexCode())){
                return JsonResult.fail("请对支付方式排序");
            }
            if(vo.getIsStop()==1){
                vo.setIndexCode(null);
            }
            vo.setPlatformId(platformId);
            if(vo.getIndexCode()!=null){
                size++;
                indexCodeSet.add(vo.getIndexCode());
            }

        }
        if(size==0){
            return JsonResult.fail("至少启用一项支付方式");
        }
        //判断indexcode是否重复
        if(indexCodeSet.size()!=size){
            return JsonResult.fail("输入的排序参数有重复数值");
        }
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(cmsClientVO.getId());
        clientDTO.setName(cmsClientVO.getName());
        clientDTO.setClientPayTypeRemarks(cmsClientVO.getClientPayTypeRemarks());
        List<ClientPayTypeConfigDTO> clientPayTypeConfigDTOList = new ArrayList<>();
        for(CmsClientPayTypeConfigVO vo:cmsClientPayTypeConfigVOList){
            ClientPayTypeConfigDTO clientPayTypeConfigDTO = new ClientPayTypeConfigDTO();
            clientPayTypeConfigDTO.setId(vo.getId());
            clientPayTypeConfigDTO.setPlatformId(vo.getPlatformId());
            clientPayTypeConfigDTO.setClientId(cmsClientVO.getId());
            clientPayTypeConfigDTO.setIsStop(vo.getIsStop());
            clientPayTypeConfigDTO.setIndexCode(vo.getIndexCode());
            clientPayTypeConfigDTO.setPayTypeCode(vo.getPayTypeCode());
            clientPayTypeConfigDTOList.add(clientPayTypeConfigDTO);
        }
        cmsClientFacade.updateClientPayTypeByClientId(clientDTO,clientPayTypeConfigDTOList);


        return JsonResult.success();
    }

    @Override
    public JsonResult<Object> getClientPayTypeByClientId(Long clientId, Long platformId) {

        //1.校验该id是否存在
        ClientDTO dto = new ClientDTO();
        dto.setId(clientId);
        ClientDTO clientById = cmsClientFacade.getClientById(dto);
        if(clientById==null){
            return JsonResult.fail(clientId+"前端类型无效");
        }
        CmsClientVO cmsClientVO = new CmsClientVO();
        //2.根据clientId查询支付方式配置
        ClientPayTypeConfigDTO clientPayTypeConfigDTO = new ClientPayTypeConfigDTO();
        clientPayTypeConfigDTO.setClientId(clientId);
        clientPayTypeConfigDTO.setIsStop(0);
        clientPayTypeConfigDTO.setPlatformId(platformId);
        List<ClientPayTypeConfigDTO> clientPayTypeConfigDTOS = cmsClientPayTypeConfigFacade.getClientPayTypeByClientIdAndIsStop(clientPayTypeConfigDTO);
        List<Integer> collect = clientPayTypeConfigDTOS.stream().map(c -> c.getPayTypeCode()).collect(Collectors.toList());
        List<PayTypeDTO> payTypeByCodes = collect.stream().map(c -> cmsPayTypeFacade.getPayTypeByCode(c)).collect(Collectors.toList());
        List<CmsPayTypeVO> cmsPayTypeVOS = CmsPayTypeConverter.toVO(payTypeByCodes);

        return JsonResult.success(cmsPayTypeVOS);
    }


}
