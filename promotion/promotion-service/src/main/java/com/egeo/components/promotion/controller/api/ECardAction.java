package com.egeo.components.promotion.controller.api;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.egeo.components.promotion.business.ECardManage;
import com.egeo.components.promotion.common.Constant;
import com.egeo.components.promotion.converter.ECardConverter;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.dto.ECardStatusDTO;
import com.egeo.components.promotion.vo.ECardVO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.HostUtils;
import com.egeo.utils.Upload;
import com.egeo.utils.excel2.ExcelHeadChecker;
import com.egeo.utils.excel2.ExcelUtil;
import com.egeo.utils.excel2.PropblemReportRowVO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/promotion/eCard")
public class ECardAction extends BaseSpringController {

    @Resource(name = "eCard")
    private ECardManage eCardManage;

	@Autowired
	private Upload uploadService;

    // 业务方法：
    @RequestMapping(value = "/findECardById")
    @ResponseBody
    public JsonResult<ECardVO> findECardById(Long id) {

        ECardDTO dto = new ECardDTO();
        dto.setId(id);
        ECardDTO rt = eCardManage.findECardById(dto);
        return success(ECardConverter.toVO(rt));

    }


    // 业务方法：
    @RequestMapping(value = "/findECardAll")
    @ResponseBody
    public JsonResult<List<ECardVO>> findECardAll(ECardVO vo, HttpServletRequest req) {
        ECardDTO dto = ECardConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        List<ECardDTO> rt = eCardManage.findECardAll(dto);
        return success(ECardConverter.toVO(rt));

    }

    /**
     * 电子卡券按条件分页查询
     *
     * @param vo
     * @param startTimeBegin
     * @param startTimeFinish
     * @param endTimeBegin
     * @param endTimeFinish
     * @param createTimeBegin
     * @param createTimeFinish
     * @param allocationTimeBegin
     * @param allocationTimeFinish
     * @param page
     * @param req
     * @return
     */
    @RequestMapping(value = "/findECardOfPage")
    @ResponseBody
    public JsonResult<PageResult<Map<String, Object>>> findECardOfPage(ECardVO vo,
                                                                       Long startTimeBegin, Long startTimeFinish,
                                                                       Long endTimeBegin, Long endTimeFinish,
                                                                       Long createTimeBegin, Long createTimeFinish,
                                                                       Long allocationTimeBegin, Long allocationTimeFinish,
                                                                       Pagination page, HttpServletRequest req) {
        logger.info("电子卡券按条件分页查询");
        ECardDTO dto = ECardConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        if (EmptyUtil.isNotEmpty(startTimeBegin))
            dto.setStartTimeBegin(new Date(startTimeBegin));
        if (EmptyUtil.isNotEmpty(startTimeFinish))
            dto.setStartTimeFinish(new Date(startTimeFinish));
        if (EmptyUtil.isNotEmpty(endTimeBegin))
            dto.setEndTimeBegin(new Date(endTimeBegin));
        if (EmptyUtil.isNotEmpty(endTimeFinish))
            dto.setEndTimeFinish(new Date(endTimeFinish));
        if (EmptyUtil.isNotEmpty(createTimeBegin))
            dto.setCreateTimeBegin(new Date(createTimeBegin));
        if (EmptyUtil.isNotEmpty(createTimeFinish))
            dto.setCreateTimeFinish(new Date(createTimeFinish));
        if (EmptyUtil.isNotEmpty(allocationTimeBegin))
            dto.setAllocationTimeBegin(new Date(allocationTimeBegin));
        if (EmptyUtil.isNotEmpty(allocationTimeFinish))
            dto.setAllocationTimeFinish(new Date(allocationTimeFinish));

        return success(eCardManage.findECardOfPage(dto, page));

    }


    // 业务方法：返回是插入行的id
    @RequestMapping(value = "/insertECardWithTx")
    @ResponseBody
    public JsonResult<Long> insertECardWithTx(ECardVO vo, HttpServletRequest req) {
        ECardDTO dto = ECardConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        Long rt = eCardManage.insertECardWithTx(dto);
        return success(rt);
    }

    // 业务方法：根据id更新数据
    @RequestMapping(value = "/updateECardByIdWithTx")
    @ResponseBody
    public JsonResult<Integer> updateECardByIdWithTx(ECardVO vo, HttpServletRequest req) {
        ECardDTO dto = ECardConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        int rt = eCardManage.updateECardWithTx(dto);
        return success(rt);
    }

    /**
     * 查询被分配给当前用户的体检券
     *
     * @param vo
     * @param req
     * @return
     */
    @RequestMapping(value = "/myBodyCheck")
    @ResponseBody
    public JsonResult<Map<String, Object>> myBodyCheck(ECardVO vo, Pagination page, HttpServletRequest req) {
        ECardDTO dto = ECardConverter.toDTO(vo);
        String str = req.getHeader("platformId");
        if (EmptyUtil.isNotEmpty(str)) {
            Long platformId = Long.valueOf(str);
            dto.setPlatformId(platformId);
        }
        CacheUser cacheUser = this.getCacheUser();
        dto.setUserId(cacheUser.getId());
        dto.setIsAllocation(1);
        dto.setType(3);
        return eCardManage.myBodyCheck(dto, page);
    }

    /**
     * 电子卡券导入
     *
     * @param source
     * @param remark
     * @param tmplType
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/importECardWithTx")
    @ResponseBody
    public JsonResult<Map<String, Object>> importECardWithTx(String source, String remark, int tmplType, HttpServletRequest req) throws Exception {
        if (tmplType == 8)
            return importECardAutoSend(source, remark, tmplType, req);
        logger.info("电子卡券导入");
        CacheUser userCache = this.getCacheUser();
        Long userId = userCache.getId(); // 用户id
        String userName = userCache.getName();// 用户名称
        String platformId_ = req.getHeader("platformId");
        if (EmptyUtil.isEmpty(platformId_))
            return fail("platformId为空");
        Long platformId = Long.parseLong(platformId_);
        String ip = HostUtils.getClientIP(req);
        // 根据ip获取mac地址
        String mac;
        try {
            mac = HostUtils.getLocalMac(ip);
        } catch (Exception e) {
            throw new BusinessException("获取mac地址异常" + e.getMessage());
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
        Iterator<String> iter = multiRequest.getFileNames();

        MultipartFile file = multiRequest.getFile(iter.next());
        if (file == null)
            return fail("未发现Excel文件");
        List<Map<String, Object>> valueList = null;
        try {
            valueList = ExcelUtil.readExcelData(0, 0, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return fail("Excel文件读取发生异常");
        }

        System.out.println(valueList.size());
        //获取原始文件全名称
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = "";
        try {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("没有文件信息！");
        }
        InputStream inputStream = file.getInputStream();
        InputStream inputStreams = file.getInputStream();
        Workbook wb = null;
        if (suffix.endsWith("xls")) {
            wb = new HSSFWorkbook(inputStream);
        } else {
            wb = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = wb.getSheetAt(0);


        List<Map<String, Object>> list = ExcelUtil.readExcelData(0, 0, inputStreams);
        String chechHeader = ExcelHeadChecker.chechHeader(list, tmplType, false);
        if (EmptyUtil.isNotBlank(chechHeader)) {
            throw new BusinessException(chechHeader);
        }
        Map<String, Object> map = eCardManage.importECardWithTx(list, source, remark, platformId, userId, userName, ip, mac, tmplType);
        List<PropblemReportRowVO> propblemReportRowList = (List<PropblemReportRowVO>) map.get("propblemReportRowList");
        Long importRecordsId = (Long) map.get("importRecordsId");
        for (PropblemReportRowVO propblemReportRowVO : propblemReportRowList) {
            //第几行
            Row row = sheet.getRow(propblemReportRowVO.getLineNo());
            //第几列
            Cell cell = row.createCell(5);
            cell.setCellValue(propblemReportRowVO.getProblem() != null ? propblemReportRowVO.getProblem().toString() : "");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("导出失败_0");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), suffix);

        Map<String, Object> rt = new HashMap<>();
        if (EmptyUtil.isNotEmpty(propblemReportRowList)) {
            rt.put("type", 0);
        } else {
            rt.put("type", 1);
            rt.put("importRecordsId", importRecordsId);
        }
        rt.put("path", upload);
        return success(rt);
    }


    /**
     * 电子卡券导入并且自动发放
     *
     * @param source
     * @param remark
     * @param tmplType
     * @param req
     * @return
     * @throws Exception
     */
    private JsonResult<Map<String, Object>> importECardAutoSend(String source, String remark, int tmplType,
                                                                HttpServletRequest req) throws Exception {
        logger.info("电子卡券导入并且自动发放");
        CacheUser userCache = this.getCacheUser();
        Long userId = userCache.getId(); // 用户id
        String userName = userCache.getName();// 用户名称
        String platformId_ = req.getHeader("platformId");
        if (EmptyUtil.isEmpty(platformId_))
            return fail("platformId为空");
        Long platformId = Long.parseLong(platformId_);
        String ip = HostUtils.getClientIP(req);
        // 根据ip获取mac地址
        String mac;
        try {
            mac = HostUtils.getLocalMac(ip);
        } catch (Exception e) {
            throw new BusinessException("获取mac地址异常" + e.getMessage());
        }
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
        Iterator<String> iter = multiRequest.getFileNames();

        MultipartFile file = multiRequest.getFile(iter.next());
        if (file == null)
            return fail("未发现Excel文件");
        List<Map<String, Object>> valueList = null;
        try {
            valueList = ExcelUtil.readExcelData(0, 0, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return fail("Excel文件读取发生异常");
        }
        //获取原始文件全名称
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        String suffix = "";
        try {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("没有文件信息！");
        }
        InputStream inputStream = file.getInputStream();
        InputStream inputStreams = file.getInputStream();
        Workbook wb = null;
        if (suffix.endsWith("xls")) {
            wb = new HSSFWorkbook(inputStream);
        } else {
            wb = new XSSFWorkbook(inputStream);
        }
        Sheet sheet = wb.getSheetAt(0);

        List<Map<String, Object>> list = ExcelUtil.readExcelData(0, 0, inputStreams);
        String chechHeader = ExcelHeadChecker.chechHeader(list, tmplType, true);
        if (EmptyUtil.isNotBlank(chechHeader)) {
            throw new BusinessException(chechHeader);
        }
        // 电子卡券导入并且自动发放
        Map<String, Object> map = eCardManage.importECardAutoSend(list, source, remark, platformId, userId, userName, ip, mac,tmplType);
        List<PropblemReportRowVO> propblemReportRowList = (List<PropblemReportRowVO>) map.get("propblemReportRowList");
        Long importRecordsId = (Long) map.get("importRecordsId");
        for (PropblemReportRowVO propblemReportRowVO : propblemReportRowList) {
            //第几行
            Row row = sheet.getRow(propblemReportRowVO.getLineNo());
            //第几列
            Cell cell = row.createCell(7);
            cell.setCellValue(propblemReportRowVO.getProblem() != null ? propblemReportRowVO.getProblem().toString() : "");
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wb.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            return fail("导出失败_0");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), suffix);

        Map<String, Object> rt = new HashMap<>();
        if (EmptyUtil.isNotEmpty(propblemReportRowList)) {
            rt.put("type", 0);
        } else {
            rt.put("type", 1);
            rt.put("importRecordsId", importRecordsId);
        }
        rt.put("path", upload);
        return success(rt);
    }

    /**
     * 卡券unit状态[有效/失效]设置
     *
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setStatus", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult<Map<String, Object>> updateECardStatus(@RequestParam String param) {
        Map<String, Object> ress = new HashMap<String, Object>();
        List<String> ids = new ArrayList<String>();
        List<Long> skuIds = new ArrayList<Long>();
        List<ECardStatusDTO> ecardVOList = new ArrayList<ECardStatusDTO>();
        if (EmptyUtil.isEmpty(param)) {
            throw new BusinessException(Constant.REQUEST_PARAM_ERROR);
        }
        Map<String, Object> req = JsonUtils.jsonToPojo(param, Map.class);
        if (EmptyUtil.isEmpty(req) || EmptyUtil.isEmpty(req.get("type")) || EmptyUtil.isEmpty(req.get("data"))) {
            throw new BusinessException(Constant.REQUEST_PARAM_ERROR);
        }
        String type = req.get("type").toString(); //0:无效接口,1:有效接口
        List<Map<String, Object>> data = (List<Map<String, Object>>) req.get("data");
        if (EmptyUtil.isEmpty(data)) {
            throw new BusinessException(Constant.REQUEST_PARAM_ERROR);
        }
        for (Map<String, Object> tempData : data) {
            if (EmptyUtil.isEmpty(tempData.get("id")) || EmptyUtil.isEmpty(tempData.get("skuId"))) {
                throw new BusinessException(Constant.REQUEST_PARAM_ERROR);
            }
            if (Constant.VALID_STATUS == Integer.valueOf(type)) {
                if (EmptyUtil.isEmpty(tempData.get("startTime")) || EmptyUtil.isEmpty(tempData.get("endTime"))) {
                    throw new BusinessException(Constant.REQUEST_PARAM_ERROR);
                }
                tempData.put("startTime", tempData.get("startTime"));
                tempData.put("endTime", tempData.get("endTime"));
            }
            ids.add(tempData.get("id").toString());
            skuIds.add(Long.parseLong(tempData.get("skuId").toString()));
            try {
                ecardVOList.add((ECardStatusDTO) JsonUtils.mapToObject(tempData, ECardStatusDTO.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ress = eCardManage.updateECardStatus(type, ids, skuIds, ecardVOList);
        //throw new BusinessException(Constant.ECARD_UPDATE_FAIL);
        return success(ress);
    }

}
	