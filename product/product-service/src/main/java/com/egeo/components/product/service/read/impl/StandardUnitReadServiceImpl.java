package com.egeo.components.product.service.read.impl;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import com.egeo.components.product.bean.KeyWordSearchBean;
import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.common.PageResults;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.enums.ProductRedisKeyEnum;
import com.egeo.components.product.facade.ChannelProductSkuFacade;
import com.egeo.components.product.strategy.ProductFactory;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.StringUtil;
import com.egeo.config.RuntimeContext;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.cache.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.converter.StandardUnitConverter;
import com.egeo.components.product.manage.read.StandardUnitCombinationReadManage;
import com.egeo.components.product.manage.read.StandardUnitReadManage;
import com.egeo.components.product.po.StandardUnitCombinationPO;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.components.product.service.read.CategoryTreeNodeCategoryReadService;
import com.egeo.components.product.service.read.StandardUnitReadService;
import com.egeo.components.user.client.EnterpriseClient;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;
import org.springframework.util.CollectionUtils;

@Service("standardUnitReadService")
public class StandardUnitReadServiceImpl  implements StandardUnitReadService {
	private static final XLogger logger = XLogger.getLogger(StandardUnitReadServiceImpl.class);
    @Autowired
    private StandardUnitReadManage standardUnitReadManage;

    @Autowired
    private StandardUnitCombinationReadManage standardUnitCombinationReadManage;

    @Autowired
    private CategoryTreeNodeCategoryReadService categoryTreeNodeCategoryReadService;

    @Autowired
    private EnterpriseClient enterpriseService;

	@Resource(name="jdProduct")
	private JdProductManage jdProductManage;
    @Resource(name="cakeProductManage")
    private CakeProductManage cakeProductManage;

    @Resource
    private ChannelProductSkuFacade channelProductSkuFacade;

    @Resource
    private ProductFactory productFactory;

    @Resource
    private JedisUtil jedisUtil;

    @Override
    public StandardUnitDTO findStandardUnitById(StandardUnitDTO dto) {
        StandardUnitPO po = StandardUnitConverter.toPO(dto);
        StandardUnitPO list = standardUnitReadManage.findStandardUnitById(po);
        return StandardUnitConverter.toDTO(list);
    }

    @Override
    public PageResult<StandardUnitDTO> findStandardUnitOfPage(StandardUnitDTO dto, Pagination page) {
        StandardUnitPO po = StandardUnitConverter.toPO(dto);
        PageResult<StandardUnitPO> pageResult = standardUnitReadManage.findStandardUnitOfPage(po, page);

        List<StandardUnitDTO> list = StandardUnitConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public List<StandardUnitDTO> findStandardUnitAll(StandardUnitDTO dto) {
        StandardUnitPO po = StandardUnitConverter.toPO(dto);
        List<StandardUnitPO> list = standardUnitReadManage.findStandardUnitAll(po);
        return StandardUnitConverter.toDTO(list);
    }

    /**
     * 根据su草稿id集合查询su信息
     *
     * @param ids
     * @return
     */
    @Override
    public List<StandardUnitDTO> findBymerchantProdId(List<Long> ids) {
        // id集合为空直接返回
        if (EmptyUtil.isEmpty(ids))
            return null;
        List<StandardUnitCondition> list = standardUnitReadManage.findBymerchantProdId(ids);
        List<StandardUnitDTO> standardUnitDTOs = new ArrayList<>();
        for (StandardUnitCondition standardUnitCondition : list) {
            StandardUnitDTO standardUnitDTO = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
            standardUnitDTO.setMerchantName(standardUnitCondition.getMerchantName());
            standardUnitDTO.setSalesVolume(standardUnitCondition.getSalesVolume());
            standardUnitDTOs.add(standardUnitDTO);
        }
        return standardUnitDTOs;
    }

    /**
     * 根据spuid查询所有su的条数
     *
     * @param standardProductUnitId
     * @return
     */
    @Override
    public int countRecord(Long standardProductUnitId) {

        return standardUnitReadManage.countRecord(standardProductUnitId);
    }

    /**
     * app商品列表
     *
     * @param vo
     * @param page
     * @param req
     * @return
     */
    @Override
    public PageResult<StandardUnitDTO> findStandardUnitOfPageAPP(StandardUnitDTO standardUnitDTO, Pagination page) {
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>();
        StandardUnitPO po = StandardUnitConverter.toPO(standardUnitDTO);
        PageResult<StandardUnitCondition> pageResult = standardUnitReadManage.findStandardUnitOfPageAPP(po, page);
        List<StandardUnitCondition> standardUnitConditionList = pageResult.getList();
        for (StandardUnitCondition standardUnitCondition : standardUnitConditionList) {
            StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
            standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
            standardUnitDTOList.add(standardUnitDTO2);
        }
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        result.setList(standardUnitDTOList);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    /**
     * 根据类目节点id查询su商品信息
     *
     * @param vo
     * @param req
     * @return
     */
    @Override
    public PageResult<StandardUnitDTO> standardUnitStockByCategoryTreeNodeId(StandardUnitDTO standardUnitDTO,
                                                                             Pagination page) {
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>();
        StandardUnitPO po = StandardUnitConverter.toPO(standardUnitDTO);
        PageResult<StandardUnitCondition> pageResult = standardUnitReadManage.standardUnitStockByCategoryTreeNodeId(po,
                page);
        List<StandardUnitCondition> standardUnitConditionList = pageResult.getList();
        for (StandardUnitCondition standardUnitCondition : standardUnitConditionList) {
            StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
            standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
            standardUnitDTOList.add(standardUnitDTO2);
        }
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        result.setList(standardUnitDTOList);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    /**
     * 根据条件查询所有上架suid和名称
     *
     * @param vo
     * @param req
     * @return
     */
    @Override
    public List<StandardUnitDTO> findStandardUnitIdAndName(StandardUnitDTO dto) {
        List<StandardUnitPO> list = standardUnitReadManage.findStandardUnitIdAndName(StandardUnitConverter.toPO(dto));
        return StandardUnitConverter.toDTO(list);
    }

    /**
     * 根据类目节点id集合查询su商品信息
     *
     * @param dto
     * @param categoryTreeNodeIdList
     * @param page
     * @return
     */
    @Override
    public PageResult<StandardUnitDTO> standardUnitByFunctionModuleId(StandardUnitDTO dto,
                                                                      List<Long> categoryTreeNodeIdList, Integer companyType, Pagination page) {
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>();
        StandardUnitPO po = StandardUnitConverter.toPO(dto);
        PageResult<StandardUnitCondition> pageResult = standardUnitReadManage.standardUnitByFunctionModuleId(null, po,
                categoryTreeNodeIdList, companyType, page);
        List<StandardUnitCondition> standardUnitConditionList = pageResult.getList();
        for (StandardUnitCondition standardUnitCondition : standardUnitConditionList) {
            StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
            standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
            standardUnitDTOList.add(standardUnitDTO2);
        }
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        result.setList(standardUnitDTOList);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public PageResult<StandardUnitDTO> standardUnitByType(Integer couponType, Integer saleWay, Long queryId, Integer type, Long platformId, BigDecimal userBalance, Long clientId, Long enterpriseId, Long companyId, Integer companyType, Long storeId, Pagination page, Integer buyType) {
        return standardUnitByType(couponType,saleWay,queryId,type,platformId,userBalance,clientId,enterpriseId,companyId,companyType,storeId,page,buyType,null,null);
    }

    /**
     * 根据前台类目节点id、商品集合id分页查询su商品信息 queryId：查询id clientId:客户端id（1、app）
     * companyId：公司id type类型：1、类目节点id，2、商品集合id
     */
    @Override
    public PageResult<StandardUnitDTO> standardUnitByType(Integer couponType, Integer saleWay, Long queryId, Integer type, Long platformId,
                                                          BigDecimal userBalance, Long clientId,Long enterpriseId, Long companyId, Integer companyType,
                                                          Long storeId, Pagination page, Integer buyType,Integer sellState,String keyWord) {
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        List<StandardUnitDTO> standardUnitDTOList = null;
        // type类型：1、类目节点id，2、商品集合id
        if (type == 1) {
        	if (EmptyUtil.isEmpty(page.getOrderBy())) {
        		page.setOrderBy("su.update_time desc");
        	}
            // 根据类目节点id查询su列表信息
            PageResult<StandardUnitCondition> pageResult = standardUnitReadManage
                    .standardUnitByCategoryTreeNodeId(saleWay,queryId, platformId, userBalance, clientId, companyId,enterpriseId, companyType, storeId, page, buyType,keyWord);
            List<StandardUnitCondition> standardUnitConditionList = pageResult.getList();

            if(standardUnitConditionList!=null && standardUnitConditionList.size()>0) {
            	standardUnitDTOList = new ArrayList<>(standardUnitConditionList.size());
                for (StandardUnitCondition standardUnitCondition : standardUnitConditionList) {
                    StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
                    standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
                    standardUnitDTO2.setSalesVolume(standardUnitCondition.getSalesVolume());
                    //standardUnitDTO2.setStoreId(standardUnitCondition.getStoreId());
                    standardUnitDTO2.setCommodityTemplateId(standardUnitCondition.getCommodityTemplateId());
                    if(!StringUtil.isEmpty(standardUnitCondition.getChannelProductId())){
                        standardUnitDTO2.setCode(standardUnitCondition.getChannelProductId());
                    }
                    standardUnitDTOList.add(standardUnitDTO2);
                }
                result.setList(standardUnitDTOList);
            }
            result.setPageNo(pageResult.getPageNo());
            result.setPageSize(pageResult.getPageSize());
            result.setTotalSize(pageResult.getTotalSize());
        } else if (type == 2) {
            // 根据su组合id查询su组合信息
            StandardUnitCombinationPO standardUnitCombinationPO = new StandardUnitCombinationPO();
            standardUnitCombinationPO.setId(queryId);
            StandardUnitCombinationPO standardUnitCombinationPO2 = standardUnitCombinationReadManage
                    .findStandardUnitCombinationById(standardUnitCombinationPO);
            // 如果不为空查询、为空直接返回空数据
            if (EmptyUtil.isNotEmpty(standardUnitCombinationPO2)) {
                // su商品类型排序只有一个所以不用选择
                if(EmptyUtil.isEmpty(page.getOrderBy())){
                    if (standardUnitCombinationPO2.getType() == 2 || standardUnitCombinationPO2.getType() == 3) {
                        // 前台app排序规则不为空则为su组合定义排序
                        if (EmptyUtil.isEmpty(page.getOrderBy())) {
                            Integer sortType = standardUnitCombinationPO2.getSortType();
                            if (sortType == null) {
                                sortType = 1;
                            }
                            if (sortType == 1) {
                                page.setOrderBy("sales_volume desc,su.update_time desc");
                            } else if (sortType == 2) {
                                page.setOrderBy("su.update_time desc");
                            } else if(sortType == 3) {
                                page.setOrderBy("su.front_serial_number , su.id desc ");
                            }
                        }
                    }
                }


                // 根据商品集合id查询su商品信息
                PageResult<StandardUnitCondition> pageResult = standardUnitReadManage
                        .standardUnitByStandardUnitCombinationId(couponType,saleWay,queryId, platformId, userBalance, clientId,enterpriseId, companyId, companyType,
                                storeId ,page, buyType,sellState,null,true,keyWord);
                if (EmptyUtil.isNotEmpty(pageResult)) {
                    List<StandardUnitCondition> standardUnitConditionList = pageResult.getList();
                    standardUnitDTOList = new ArrayList<>(standardUnitConditionList.size());
                    for (StandardUnitCondition standardUnitCondition : standardUnitConditionList) {
                        StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
                        standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
                        standardUnitDTO2.setSalesVolume(standardUnitCondition.getSalesVolume());
                        standardUnitDTO2.setCommodityTemplateId(standardUnitCondition.getCommodityTemplateId());
                        //standardUnitDTO2.setStoreId(standardUnitCondition.getStoreId());
                        if(!StringUtil.isEmpty(standardUnitCondition.getChannelProductId())){
                            standardUnitDTO2.setCode(standardUnitCondition.getChannelProductId());
                        }
                        standardUnitDTOList.add(standardUnitDTO2);
                    }
                    result.setList(standardUnitDTOList);
                    result.setPageNo(pageResult.getPageNo());
                    result.setPageSize(pageResult.getPageSize());
                    result.setTotalSize(pageResult.getTotalSize());
                }
            } else {
                logger.info("没有suId为" + queryId + "的su组合信息");
                standardUnitDTOList = new ArrayList<>();
                result.setList(standardUnitDTOList);
                result.setPageNo(page.getPageNo());
                result.setPageSize(page.getPageSize());
                result.setTotalSize(0);
            }

        }

        return result;
    }

    /**
     * su商品组合选择商品_su商品列表
     *
     * @param vo
     * @param req
     * @return
     */
    @Override
    public PageResult<StandardUnitDTO> findBaseByConditionOfPage(Long standardUnitCombinationId,
                                                                 StandardUnitDTO standardUnitDTO, Pagination page) {
        StandardUnitPO po = StandardUnitConverter.toPO(standardUnitDTO);
        PageResult<StandardUnitPO> pageResult = standardUnitReadManage
                .findBaseByConditionOfPage(standardUnitCombinationId, po, page);

        List<StandardUnitDTO> list = StandardUnitConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    /**
     * 根据su商品id查询su商品信息
     *
     * @param standardUnitId
     * @return
     */
    @Override
    public StandardUnitDTO findStandardUnitById(Long standardUnitId) {
        StandardUnitCondition standardUnitCondition = standardUnitReadManage.findStandardUnitById(standardUnitId);
        StandardUnitDTO standardUnitDTO = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
        if (standardUnitCondition != null) {
        	standardUnitDTO.setSalesVolume(standardUnitCondition.getSalesVolume());
            standardUnitDTO.setCommodityTemplateId(standardUnitCondition.getCommodityTemplateId());
            standardUnitDTO.setContent(standardUnitCondition.getContent());
            standardUnitDTO.setContentUrl(standardUnitCondition.getContentUrl());
            standardUnitDTO.setPictureUrl(standardUnitCondition.getPictureUrl());
            standardUnitDTO.setRelevanceSuName(standardUnitCondition.getRelevanceSuName());
            standardUnitDTO.setRelevanceSuCommodityTemplateId(standardUnitCondition.getRelevanceSuCommodityTemplateId());
        }
        return standardUnitDTO;
    }

    /**
     * 根据su组合id查询su商品信息
     *
     * @param standardUnitCombinationId
     * @return
     */
    @Override
    public List<StandardUnitDTO> findByStandardUnitCombinationId(Long standardUnitCombinationId, Long platformId) {
        List<StandardUnitPO> standardUnitList = standardUnitReadManage
                .findByStandardUnitCombinationId(standardUnitCombinationId, platformId);
        return StandardUnitConverter.toDTO(standardUnitList);
    }

    /**
     * 根据条件分页查询su商品信息
     *
     * @param dto
     * @param page
     * @return
     */
    @Override
    public PageResult<StandardUnitDTO> findStandardUnitExtendOfPage(StandardUnitDTO dto, Pagination page,
                                                                    List<Long> standardUnitIdList) {
        StandardUnitPO po = StandardUnitConverter.toPO(dto);
        PageResult<StandardUnitCondition> pageResult = standardUnitReadManage.findStandardUnitExtendOfPage(po, page,
                standardUnitIdList);
        List<StandardUnitDTO> list = new ArrayList<>();
        List<StandardUnitCondition> standardUnitConditionList = pageResult.getList();
        for (StandardUnitCondition standardUnitCondition : standardUnitConditionList) {
            StandardUnitDTO standardUnitDTO = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
            standardUnitDTO.setSalesVolume(standardUnitCondition.getSalesVolume());
            standardUnitDTO.setCommodityTemplateId(standardUnitCondition.getCommodityTemplateId());
            standardUnitDTO.setContent(standardUnitCondition.getContent());
            standardUnitDTO.setContentUrl(standardUnitCondition.getContentUrl());
            standardUnitDTO.setPictureUrl(standardUnitCondition.getPictureUrl());
            standardUnitDTO.setMerchantName(standardUnitCondition.getMerchantName());
            list.add(standardUnitDTO);
        }
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public PageResult<StandardUnitDTO> queryStandardUnitListByBlurry(StandardUnitDTO dto, Long excludeId, Pagination page) {
        PageResult<StandardUnitPO> pageResult = standardUnitReadManage
                .queryStandardUnitListByBlurry(StandardUnitConverter.toPO(dto), excludeId, page);
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        result.setList(StandardUnitConverter.toDTO(pageResult.getList()));
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
    }

    @Override
    public PageResult<StandardUnitDTO> findByKeywordOfPage(Integer saleWay, Long storeId, String name, BigDecimal userBalance, Long clientId,
                                                               Long companyId, Long platformId, Integer companyType, Pagination page, Integer buyType) {

        KeyWordSearchBean bean = new KeyWordSearchBean();
        bean.setSaleWay(saleWay);
        bean.setStoreId(storeId);
        bean.setName(name);
        bean.setUserBalance(userBalance);
        bean.setClientId(clientId);
        bean.setCompanyId(companyId);
        bean.setPlatformId(platformId);
        bean.setCompanyType(companyType);
        bean.setPage(page);
        bean.setBuyType(buyType);
        Long userId = RuntimeContext.cacheUser().getId();
        String redisKey = userId+ MD5Util.MD5(bean.getName());
        redisKey = ProductRedisKeyEnum.getKeyWordSearchPageKey(redisKey);
        bean.setKeyWordRedisKey(redisKey);
        if(page.getPageNo() <=1){
            jedisUtil.del(redisKey);
        }
        if ((!name.equalsIgnoreCase("自营")) && saleWay != null && saleWay.intValue() == 0 && (buyType == null || buyType.intValue() == 0)) {
            List<ProductChannelCodeEnum> channelCodeEnums =  getQueryKeyWordChannelCodeList();
            for (ProductChannelCodeEnum channelCodeEnum : channelCodeEnums) {
                PageResult<StandardUnitDTO>  pageResult = productFactory.getKeyWordSearchProductStrategy(channelCodeEnum.getCode()).getProductByKeyWord(bean);
                if(Objects.nonNull(pageResult) && !CollectionUtils.isEmpty(pageResult.getList())){
                    PageResults results=new PageResults();
                    results.copy(pageResult);
                    results.setList(pageResult.getList());
                    return pageResult;
                }
            }
        }else{
            PageResult<StandardUnitDTO>  pageResult = productFactory.getKeyWordSearchProductStrategy(ProductChannelCodeEnum.SELF.getCode()).getProductByKeyWord(bean);
            return pageResult;
        }
        PageResult<StandardUnitDTO>  pageResult = new PageResult<>();
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotalSize(0);
        return pageResult;
    }
    private List<ProductChannelCodeEnum> getQueryKeyWordChannelCodeList(){
        List<ProductChannelCodeEnum> list = new ArrayList<>();
        list.add(ProductChannelCodeEnum.JD);
        list.add(ProductChannelCodeEnum.SELF);
        list.add(ProductChannelCodeEnum.CAKE);
        list.add(ProductChannelCodeEnum.WORLD_BUY);
        return list;
    }

    /**即将放弃**/
    //@Override
    @Deprecated
    public PageResult<StandardUnitDTO> findByKeywordOfPagebak(Integer saleWay, Long storeId, String name, BigDecimal userBalance, Long clientId,
                                                           Long companyId, Long platformId, Integer companyType, Pagination page, Integer buyType) {
    	List<StandardUnitCondition> list = null;


    	PageResult<StandardUnitCondition> pageResult = standardUnitReadManage.findByKeywordOfPage(saleWay,storeId,name, userBalance, clientId, companyId, platformId, companyType, page, buyType);
        int cnt = pageResult.getTotalSize();
        int totalCnt = cnt;
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        if (cnt>0 && cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = pageResult.getList();
            List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>();
            for (StandardUnitCondition standardUnitCondition : list) {
                StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
                standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
                standardUnitDTO2.setCommodityTemplateId(standardUnitCondition.getCommodityTemplateId());
                standardUnitDTO2.setSalesVolume(standardUnitCondition.getSalesVolume());
                standardUnitDTOList.add(standardUnitDTO2);
            }
            result.setList(standardUnitDTOList);
            result.setPageNo(pageResult.getPageNo());
            result.setPageSize(pageResult.getPageSize());
            result.setTotalSize(pageResult.getTotalSize());
        }
        if((!name.equalsIgnoreCase("自营")) && saleWay!=null && saleWay.intValue()==0 && (buyType==null || buyType.intValue()==0)){

        	JDProductSearchDTO search = new JDProductSearchDTO();
        	/*int platformPage = (totalCnt==0?0:((totalCnt/page.getPageSize())+1));
        	if(totalCnt%page.getPageSize()>0) {
        		platformPage = platformPage-1;
        	}
        	int jdPage = page.getPageNo()-platformPage;
        	if(jdPage <=0){
                jdPage =1;
            }*/
        	search.setKeyword(name);
        	search.setPageIndex(page.getPageNo());
        	search.setPageSize(page.getPageSize());
        	List<StandardUnitDTO> standardUnitDTOList = result.getList();
        	if(standardUnitDTOList==null) {
        		standardUnitDTOList = new ArrayList<>();
        	}
        	//排序方式
            search.setSortType("sort_totalsales15_desc");
        	if(page.getOrderBy()!=null && page.getOrderBy().length()>0) {
        		if(page.getOrderBy().equalsIgnoreCase("sales_volume")) {
        			search.setSortType("sale_asc");
        		}else if(page.getOrderBy().equalsIgnoreCase("sales_volume desc")) {
        			search.setSortType("sale_desc");
        		}else if(page.getOrderBy().equalsIgnoreCase("su.sale_price")) {
        			search.setSortType("price_asc");
        		}else if(page.getOrderBy().equalsIgnoreCase("su.sale_price desc")) {
        			search.setSortType("price_desc");
        		}
        	}
        	if(companyId!=null) {
        		Enterprise enterprise = enterpriseService.findById(companyId.intValue());
        	}
        	if(userBalance!=null) {
        		search.setPriceMax(userBalance.intValue());
        	}

        	JsonResult<PageResult<JdProductDTO>> jdRslt = jdProductManage.search(search);
        	list = new ArrayList<StandardUnitCondition>();
        	pageResult.setTotalSize(0);
        	if(jdRslt.getData()!=null && jdRslt.getData().getList()!=null && jdRslt.getData().getList().size()>0) {
            	for(JdProductDTO oneJd:jdRslt.getData().getList()) {
            		StandardUnitCondition tmp = new StandardUnitCondition();
            		tmp.fromJdProduct(oneJd);
            		StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(tmp);
                    standardUnitDTO2.setPictureUrl(tmp.getPictureUrl());
                    standardUnitDTO2.setCommodityTemplateId(tmp.getCommodityTemplateId());
                    standardUnitDTO2.setSalesVolume(tmp.getSalesVolume());
                    standardUnitDTOList.add(standardUnitDTO2);
            	}
                result.setList(standardUnitDTOList);
                pageResult.setList(list);
                pageResult.setTotalSize((((cnt/page.getPageSize())+1)*page.getPageSize())+jdRslt.getData().getTotalSize());
                result.setPageNo(page.getPageNo());
                result.setPageSize(page.getPageSize());
                totalCnt = totalCnt+jdRslt.getData().getTotalSize();
        	}


            //全球购查询

            ChannelProductAndSkuListDTO worldQueryDTO = new ChannelProductAndSkuListDTO();
            worldQueryDTO.setSkuProductName(name);
            worldQueryDTO.setChannelCode(ProductChannelCodeEnum.WORLD_BUY.getCode());
            Pagination worldPage = new Pagination();
            worldPage.setPageNo(page.getPageNo());
            worldPage.setPageSize(page.getPageSize());
            worldPage.setOrderBy("sku.sku_market_price asc");
            if(page.getOrderBy()!=null && page.getOrderBy().length()>0) {
                if(page.getOrderBy().equalsIgnoreCase("sales_volume")) {
                  //销量升序
                }else if(page.getOrderBy().equalsIgnoreCase("sales_volume desc")) {
                    //销量降序
                }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price")) {
                    worldPage.setOrderBy("sku.sku_market_price asc");
                }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price desc")) {
                    worldPage.setOrderBy("sku.sku_market_price desc");
                }

            }
            PageResult<StandardUnitCondition> worldPageRt =  channelProductSkuFacade.getChannelProductAndSkuListOfPage(worldQueryDTO,null,worldPage);
           if(Objects.nonNull(worldPageRt) && !CollectionUtils.isEmpty(worldPageRt.getList())){
               for (StandardUnitCondition tmp : worldPageRt.getList()) {
                   StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(tmp);
                   standardUnitDTO2.setPictureUrl(tmp.getPictureUrl());
                   standardUnitDTO2.setCommodityTemplateId(tmp.getCommodityTemplateId());
                   standardUnitDTO2.setSalesVolume(tmp.getSalesVolume());
                   standardUnitDTO2.setCode(tmp.getChannelProductId());
                   standardUnitDTOList.add(standardUnitDTO2);
               }
               totalCnt =totalCnt+worldPageRt.getTotalSize();
               result.setPageNo(page.getPageNo());
               result.setPageSize(page.getPageSize());
               if(pageResult.getTotalSize() == 0){
                   pageResult.setList(list);
               }
               pageResult.setTotalSize(pageResult.getTotalSize()+worldPageRt.getTotalSize());
           }


            //***************蛋糕叔叔查询**********************/
            CakeProductSearchDTO cakeSearch = new CakeProductSearchDTO();
            cakeSearch.setPage(page.getPageNo());
            cakeSearch.setSize(page.getPageSize());
            cakeSearch.setSearch_title(name);
            cakeSearch.setSort_price_type("2");
            if(page.getOrderBy().equalsIgnoreCase("su.sale_price desc")) {
                cakeSearch.setSort_price_type("1");
            }
            //JsonResult<List<CakeProductDTO>>  cakeProductRt =  cakeProductManage.searchOfCategoryLevel2(cakeSearch);
            JsonResult<List<CakeProductDTO>>  cakeProductRt =  cakeProductManage.searchList(cakeSearch);
            if(Objects.nonNull(cakeProductRt) && !CollectionUtils.isEmpty(cakeProductRt.getData())){
                List<CakeProductDTO> cakeProductDTOS = cakeProductRt.getData();
                for (CakeProductDTO oneCake : cakeProductDTOS) {
                    StandardUnitCondition tmp = new StandardUnitCondition();
                    tmp.fromCakeProduct(oneCake);
                    StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(tmp);
                    standardUnitDTO2.setPictureUrl(tmp.getPictureUrl());
                    standardUnitDTO2.setCommodityTemplateId(tmp.getCommodityTemplateId());
                    standardUnitDTO2.setSalesVolume(tmp.getSalesVolume());
                    standardUnitDTO2.setCode(oneCake.getId());
                    List<CakeSpecsDTO> tempList = oneCake.getSpecs();
                    if(!CollectionUtils.isEmpty(tempList)){
                        Optional<CakeSpecsDTO> minSalePriceCondition = FHCollectionUtils.findMinT(tempList,CakeSpecsDTO::getPrice);
                        minSalePriceCondition.ifPresent(condition -> {
                            standardUnitDTO2.setSalePrice(new BigDecimal(condition.getPrice()));
                            standardUnitDTO2.setMarketPrice(new BigDecimal(condition.getMarket_price()));
                            standardUnitDTO2.setId(Long.valueOf(condition.getSpec_id()));
                            standardUnitDTO2.setName(oneCake.getTitle());
                        });
                    }
                    standardUnitDTOList.add(standardUnitDTO2);

                }
                if(pageResult.getTotalSize() == 0){
                    pageResult.setList(list);
                }
                result.setPageNo(page.getPageNo());
                result.setPageSize(page.getPageSize());
                pageResult.setTotalSize(pageResult.getTotalSize()+cakeProductDTOS.size());
                totalCnt = totalCnt+cakeProductDTOS.size();
            }
        	pageResult.setPageNo(page.getPageNo());
            pageResult.setPageSize(page.getPageSize());

            result.setList(standardUnitDTOList);
            result.setTotalSize(totalCnt);
            if(CollectionUtils.isEmpty(standardUnitDTOList)){
                result.setTotalSize(0);
            }
            return result;
        }
        /*

        if(pageResult.getList()!=null && pageResult.getList().size()>0 ) {


        }else {}*/

        return result;
    }



    @Override
    public List<Long> querySuCombinationBySuId(Long suId) {

        return standardUnitReadManage.querySuCombinationBySuId(suId);
    }

    @Override
    public Map<Long, List<Long>> findSuCombinationMap(Long suId, Long platformId) {
    	Map<Long, List<Long>> map = new HashMap<Long, List<Long>>();
    	List<Long> suCombIdList = standardUnitReadManage.querySuCombinationBySuId(suId);
    	for (Long suCombId : suCombIdList) {
    	    if(suCombId == null){
    	       continue;
            }
    		if (!map.containsKey(suCombId)) {
    			List<Long> suIdList = new ArrayList<>();

    			List<StandardUnitDTO> suList = findByStandardUnitCombinationId(suCombId, platformId);
    			if (EmptyUtil.isNotEmpty(suList)) {
                    for (StandardUnitDTO su : suList) {
                        suIdList.add(su.getId());
                    }
                }
    			map.put(suCombId, suIdList);
    		}
    	}
    	return map;
    }

    @Override
    public List<StandardUnitDTO> findByStandardUnitName(Long platformId, Long standardUnitId, String standardUnitName) {
        List<StandardUnitPO> standardUnitList = standardUnitReadManage.findByStandardUnitName(platformId,standardUnitId, standardUnitName);
        return StandardUnitConverter.toDTO(standardUnitList);
    }

    @Override
    public PageResult<StandardUnitDTO> querySuByCategoryTreeNodeIds(Pagination page, Map<String, Object> param) {
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>();
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        PageResult<StandardUnitCondition> pageResult =
                standardUnitReadManage.standardUnitStockByCategoryTreeNodeId(page, param);
        if (!EmptyUtil.isEmpty(pageResult)) {
            List<StandardUnitCondition> standardUnitConditionList = pageResult.getList();
            for (StandardUnitCondition standardUnitCondition : standardUnitConditionList) {
                StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
                standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
                standardUnitDTOList.add(standardUnitDTO2);
            }
            result.setList(standardUnitDTOList);
            result.setPageNo(pageResult.getPageNo());
            result.setPageSize(pageResult.getPageSize());
            result.setTotalSize(pageResult.getTotalSize());
        }
        return result;
    }

	@Override
	public PageResult<StandardUnitDTO> findStandardUnitByStoreMenuIdOfPage(
			Long storeMenuNodeId,Long platformId, Pagination page) {
        PageResult<StandardUnitCondition> pageResult =
        		standardUnitReadManage.findStandardUnitByStoreMenuIdOfPage(storeMenuNodeId,platformId, page);
        List<StandardUnitCondition> standardUnitConditionList = pageResult.getList();
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>(standardUnitConditionList.size());
        for (StandardUnitCondition standardUnitCondition : standardUnitConditionList) {
            StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
            standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
            standardUnitDTO2.setCommodityTemplateId(standardUnitCondition.getCommodityTemplateId());
            standardUnitDTO2.setSalesVolume(standardUnitCondition.getSalesVolume());
            standardUnitDTOList.add(standardUnitDTO2);
        }
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        result.setList(standardUnitDTOList);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public PageResult<StandardUnitDTO> findStandardUnitByStoreIdOfPage(
			Long storeId, Long platformId, Pagination page) {
		PageResult<StandardUnitCondition> pageResult =
        		standardUnitReadManage.findStandardUnitByStoreIdOfPage(storeId,platformId, page);
        List<StandardUnitCondition> standardUnitConditionList = pageResult.getList();
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>(standardUnitConditionList.size());
        for (StandardUnitCondition standardUnitCondition : standardUnitConditionList) {
            StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
            standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
            standardUnitDTO2.setCommodityTemplateId(standardUnitCondition.getCommodityTemplateId());
            standardUnitDTO2.setSalesVolume(standardUnitCondition.getSalesVolume());
            standardUnitDTOList.add(standardUnitDTO2);
        }
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        result.setList(standardUnitDTOList);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}


	@Override
    public PageResult<StandardUnitDTO> querySuByFrontCategoryTreeNodeIds(Pagination page, Map<String, Object> param, List<Long> frontCategoryTreeNodeIds) {
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>();
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        PageResult<StandardUnitCondition> pageResult =
        		standardUnitReadManage.querySuByCategoryTreeNodeIds(page, param, frontCategoryTreeNodeIds);
        if (!EmptyUtil.isEmpty(pageResult)) {
            List<StandardUnitCondition> standardUnitConditionList = pageResult.getList();
            for (StandardUnitCondition standardUnitCondition : standardUnitConditionList) {
                StandardUnitDTO standardUnitDTO2 = StandardUnitConverter.toDTOFromCondition(standardUnitCondition);
                standardUnitDTO2.setPictureUrl(standardUnitCondition.getPictureUrl());
                standardUnitDTOList.add(standardUnitDTO2);
            }
            result.setList(standardUnitDTOList);
            result.setPageNo(pageResult.getPageNo());
            result.setPageSize(pageResult.getPageSize());
            result.setTotalSize(pageResult.getTotalSize());
        }
        return result;
    }

	@Override
	public int countCouponSuBySuId(Long suId, Long storeId, Long companyId, Integer companyType, Long platformId) {
		return standardUnitReadManage.countCouponSuBySuId(suId, storeId, companyId, companyType, platformId);
	}

	@Override
	public int countCouponSuBySuCombinationId(Long suCombinationId, Long storeId, Long companyId, Integer companyType, Long platformId) {
		return standardUnitReadManage.countCouponSuBySuCombinationId(suCombinationId, storeId, companyId, companyType, platformId);
	}

	/**
	 * 通过suId列表查询 商品模板
	 */
	@Override
	public List<Map<String, Object>> findSpuInfo(List<Long> suIdList) {
		return standardUnitReadManage.findSpuInfo(suIdList);
	}

	/**
	 * 通过suId列表查询图片
	 */
	@Override
	public List<Map<String, Object>> findPictureInfo(List<Long> suIdList) {
		return standardUnitReadManage.findPictureInfo(suIdList);
	}

    @Override
    public List<Long> findSuIdByStandardUnitCombinationId(Long suCombId, Long platformId) {
        List<StandardUnitPO> standardUnitList = standardUnitReadManage
                .findByStandardUnitCombinationId(suCombId, platformId);
        List<Long> suIdList = new ArrayList<>();
        if(EmptyUtil.isNotEmpty(standardUnitList)){
            for(StandardUnitPO po:standardUnitList){
                suIdList.add(po.getId());
            }
        }
        return suIdList;
    }

    @Override
    public Long findLastId() {
        return standardUnitReadManage.findLastId();
    }

    @Override
    public Integer findStandardUnitCount() {
        return standardUnitReadManage.findStandardUnitCount();
    }

    @Override
    public List<StandardUnitDTO> findStandardUnitAllByPage(Integer i, Integer pageSize) {
        return StandardUnitConverter.toDTO(standardUnitReadManage.findStandardUnitAllByPage( i, pageSize));
    }

}
