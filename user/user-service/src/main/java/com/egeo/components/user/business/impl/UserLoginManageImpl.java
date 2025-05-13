package com.egeo.components.user.business.impl;
	

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.belerweb.social.weixin.api.Weixin;
import com.egeo.components.user.bean.CodeToSession;
import com.egeo.components.user.business.LoginManage;
import com.egeo.components.user.dao.write.WxOpenidMapper;
import com.egeo.components.user.dto.WxOpenidDTO;
import com.egeo.components.utils.weixin.WeiXinUtil;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.facade.UserFacade;
import com.egeo.components.utils.QingMeiUtil;
import com.egeo.utils.log.XLogger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UserLoginManage;
import com.egeo.components.user.business.util.CommonUtils;
import com.egeo.components.user.converter.UserLoginConverter;
import com.egeo.components.user.dto.UserLoginDTO;
import com.egeo.components.user.facade.UserLoginFacade;
import com.egeo.components.user.vo.UserLoginVO;
import com.egeo.components.user.vo.UserVO;
import com.egeo.core.Constant.CmsConstant;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.Upload;
import com.egeo.web.JsonResult;
import org.springframework.transaction.annotation.Transactional;

@Service("userLogin")
public class UserLoginManageImpl implements UserLoginManage{
    public XLogger logger = XLogger.getLogger(this.getClass().getName());

	
	@Resource(name="userLoginFacade")
	private UserLoginFacade userLoginFacade;
	@Autowired
	private Upload uploadService;
    @Resource
    private QingMeiUtil qingMeiUtil;
    @Resource
    private UserFacade userFacade;
    @Resource(name = "login")
    private LoginManage loginManage;
    @Autowired
    private WxOpenidMapper wxOpenIdManage;

	@Override
	public void insertLoginLogWithTx(JsonResult<CacheUser> rt, HttpServletRequest req,String loginType,Long keyMessage,Long storeId) {
		
		UserLoginDTO userLogin = new UserLoginDTO();
        int code = rt.getCode();//code 等于 0表示登陆成功
        CacheUser cacheUser = rt.getData();
        userLogin.setCompanyId(cacheUser.getCompanyId());
        userLogin.setPlatformId(cacheUser.getPlatformId());
        userLogin.setLoginResult(code);
        userLogin.setUsername(cacheUser.getName());
        userLogin.setUserId(cacheUser.getId());
        userLogin.setIp(CommonUtils.getUserIp(req));
        userLogin.setBrowser(req.getHeader("User-Agent"));
        userLogin.setClientType(req.getHeader("clientId")==null?2:Integer.parseInt(req.getHeader("clientId")));
        userLogin.setLoginType(loginType);
        
        if(storeId == null) {
			String str = req.getHeader("platformId");
			if(str==null || CmsConstant.CMS_PLATFORM_FGJ == Long.parseLong(str)) {
				storeId = 1L;
			}else if(CmsConstant.CMS_PLATFORM_MYY == Long.parseLong(str)){
				storeId = 2L;
			}
		}
        userLogin.setStoreId(storeId);
        userLogin.setKeyMessage(keyMessage);
        userLoginFacade.insertLoginLogWithTx(userLogin);
		
	}

	@Override
	public void insertLoginExceptionLogWithTx(int code, UserVO user, HttpServletRequest req) {
		
		UserLoginDTO userLogin = new UserLoginDTO();
        userLogin.setLoginResult(code);
        userLogin.setUsername(user.getName());
        userLogin.setCompanyId(user.getCompanyId());
        userLogin.setPlatformId(user.getPlatformId());
        userLogin.setIp(CommonUtils.getUserIp(req));
        userLogin.setBrowser(req.getHeader("User-Agent"));
        userLoginFacade.insertLoginLogWithTx(userLogin);
		
	}

	@Override
	public PageResult<Map<String, Object>> findOfPage(UserLoginVO userVO, Pagination page) {
		
		UserLoginDTO dto = UserLoginConverter.toDTO(userVO);
		dto.setUserId(userVO.getId());
		dto.setBrowser(userVO.getIos());
		if(userVO.getStartTime() != null && userVO.getEndTime() != null) {
			dto.setStartTime(new Date(userVO.getStartTime()));
			dto.setEndTime(new Date(userVO.getEndTime()));
		}
		
		return userLoginFacade.findOfPage(dto,page);
	}

	@Override
	public List<Map<String, Object>> findByUserIds(List<Long> ids,Long startTime,Long endTime)  {
		List<Map<String, Object>> list = userLoginFacade.findByUserIds(ids,startTime,endTime);
		return list;
	}

    @Override
    public String mtLogin(Long userId,String jumpUrl) {
        UserDTO userDTO=userFacade.findUserByID(userId);
        String page=qingMeiUtil.mtLogin(String.valueOf(userId),userDTO.getMobile(),jumpUrl);
        if (EmptyUtil.isEmpty(page) || !page.contains("http")){
            throw new BusinessException("同登失败,请稍后重试");
        }
        return page;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getOpenIdByMiniProgramCode(String jsCode,String encryptedData,String iv,Long platformId) {
        Weixin wx = WeiXinUtil.getMpWeixin();
        CodeToSession session=WeiXinUtil.code2Session(jsCode,wx.getAppId(),wx.getSecret());
        // 获取微信OpenId
        // 判断token是否为空、如果空则说明打了2次、微信票据已使用，微信票据只能使用一次
        String wxOpenId;
        if(Objects.isNull(session) || EmptyUtil.isEmpty(session.getOpenid())){
            wxOpenId = loginManage.findAccessOpenId(jsCode);
            if(EmptyUtil.isEmpty(wxOpenId)){
                throw new BusinessException("请重新获取微信授权");
            }
        }
        // 微信token不为空保存redis缓存，设置一分钟过期
        else{
            wxOpenId = session.getOpenid();
        }
        loginManage.saveWeiXinOpenId(jsCode,wxOpenId);
        // 根据微信OpenId查询关联的用户信息
        UserVO userVO = loginManage.findByWeiXinOpenId(wxOpenId, platformId);
        // 返回微信OpenId和是否有效
        Map<String, Object> map = new HashMap<>();
        map.put("weiXinOpenId", wxOpenId);
        map.put("isValid", EmptyUtil.isNotEmpty(userVO));
        if (EmptyUtil.isEmpty(userVO)) {
            List<WxOpenidDTO> openids = wxOpenIdManage.findByOpenIdNotUser(wxOpenId);
            if(EmptyUtil.isEmpty(openids)) {
                WxOpenidDTO dto = new WxOpenidDTO();
                dto.setWxOpenid(wxOpenId);
                dto.setWxAppid(wx.getAppId());
                wxOpenIdManage.insert(dto);
            }
        }
        return map;
    }

    private JsonResult<Map<String, Object>> wirteSoChildDetailInExcel(List<Map<String, Object>> list) {
        Workbook wsd = new XSSFWorkbook();
        Sheet cloneSheet = wsd.createSheet("登陆信息");
        String[] headArr = {"姓名","员工编号","邮箱","所属公司","登陆时间", "IP地址", "客户端类型", "所属门店", "操作系统", "登陆来源", "关键信息"};

        Row head = cloneSheet.createRow(0);
        for (int i = 0; i < headArr.length; i++) {
            head.createCell(i).setCellValue(headArr[i]);
        }

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            Row row = cloneSheet.createRow(i + 1);
            row.createCell(0).setCellValue(map.get("name")+"");
            row.createCell(1).setCellValue(map.get("memberCode")+"");
            row.createCell(2).setCellValue(map.get("mail")+"");
            row.createCell(3).setCellValue(map.get("companyName")+"");
            row.createCell(4).setCellValue(map.get("loginTime")+"");
            row.createCell(5).setCellValue(map.get("ip")+"");
            row.createCell(6).setCellValue(map.get("clientType")+"");
            row.createCell(7).setCellValue(map.get("store")+"");
            row.createCell(8).setCellValue(map.get("ios")+"");
            row.createCell(9).setCellValue(map.get("loginType")+"");
            row.createCell(10).setCellValue(map.get("keyMessage")+"");
            
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wsd.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "员工登陆记录.xlsx");
        return JsonResult.success("url", upload);
    }

}
	