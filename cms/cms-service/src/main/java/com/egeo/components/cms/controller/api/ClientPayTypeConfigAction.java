package com.egeo.components.cms.controller.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.ClientPayTypeConfigManage;
import com.egeo.components.cms.vo.CmsClientPayTypeConfigVO;
import com.egeo.components.cms.vo.CmsClientVO;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * 第三方支付配置功能
 * Created by 0.0 on 2018/8/30.
 */
@Controller
@RequestMapping("/api/cms/clientPayTypeConfig")
public class ClientPayTypeConfigAction extends BaseSpringController {
    public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource(name="clientPayTypeConfigManageImpl")
    private ClientPayTypeConfigManage clientPayTypeConfigManage;

    /**
     * 查询所有的前端的支付方式
     * @return
     */
    @RequestMapping(value = "/findAllClientPayType",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Map<String,Object>> findAllClientPayType(HttpServletRequest request){

        logger.info("查询所有的前端的支付方式");
        String str = request.getHeader("platformId");
        if (EmptyUtil.isEmpty(str)) {
            return JsonResult.fail("platformId不能为空");
        }
        Long platformId = Long.valueOf(str);
        return clientPayTypeConfigManage.findAllClientPayType(platformId);
    }

    /**
     * 根据客户端类型id查询该客户端的全部第三方支付方式
     */
    @RequestMapping(value = "/findClientPayTypeByClientId",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Map<String,Object>> findClientPayTypeByClientId(Long clientId,HttpServletRequest request){
        logger.info("根据客户端类型id查询该客户端的全部第三方支付方式");
        String str = request.getHeader("platformId");
        if (EmptyUtil.isEmpty(str)) {
            return JsonResult.fail("platformId不能为空");
        }
        Long platformId = Long.valueOf(str);
        if(clientId==null){
            return JsonResult.fail("前端id为null");
        }
        return clientPayTypeConfigManage.findClientPayTypeByClientId(clientId,platformId);
    }

    /**
     * 根据clientid更新第三方支付配置信息
     *
     * @param cmsClientVO
     * @param request
     * @return
     */
    @RequestMapping("/updateClientPayTypeByClientId")
    @ResponseBody
    public JsonResult<Map<String, Object>> updateClientPayTypeByClientId(String cmsClientPayTypeConfigVOList,
                                                                         Long id,
                                                                         String name,
                                                                         String clientPayTypeRemarks,
                                                                        HttpServletRequest request) {

        logger.info("根据客户端类型id查询该客户端的全部第三方支付方式");
        String str = request.getHeader("platformId");
        if (EmptyUtil.isEmpty(str)) {
            return JsonResult.fail("platformId不能为空");
        }
        Long platformId = Long.valueOf(str);
        Gson gson = new Gson();
        List<CmsClientPayTypeConfigVO> fromJson = gson.fromJson(cmsClientPayTypeConfigVOList, new TypeToken<List<CmsClientPayTypeConfigVO>>() {
        }.getType());
        CmsClientVO cmsClientVO = new CmsClientVO();
        cmsClientVO.setId(id);
        cmsClientVO.setName(name);
        cmsClientVO.setClientPayTypeRemarks(clientPayTypeRemarks);
        cmsClientVO.setCmsClientPayTypeConfigVOList(fromJson);
        return clientPayTypeConfigManage.updateClientPayTypeByClientId(cmsClientVO, platformId);

    }


    @RequestMapping(value = "/getClientPayTypeByClientId",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Object> getClientPayTypeByClientId(HttpServletRequest request){
        logger.info("前端获取第三方支付配置信息");
        String str = request.getHeader("platformId");
        if (EmptyUtil.isEmpty(str)) {
            return JsonResult.fail("platformId不能为空");
        }
        Long platformId = Long.valueOf(str);
        String str1=request.getHeader("clientId");
        if(str==null){
            return JsonResult.fail("前端id为null");
        }
        Long clientId = Long.valueOf(str1);
        return clientPayTypeConfigManage.getClientPayTypeByClientId(clientId,platformId);
    }
}
