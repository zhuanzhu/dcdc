package com.egeo.components.product.manage.read.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;

import javax.annotation.Resource;

import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.bean.QueryProductWhereCacheVO;
import com.egeo.components.product.business.CakeProductManage;
import com.egeo.components.product.business.impl.Thread.CakeProductThread;
import com.egeo.components.product.business.impl.Thread.CommonThreadPoolExecutor;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.enums.ProductRedisKeyEnum;
import com.egeo.components.product.facade.ChannelProductFacade;
import com.egeo.components.product.strategy.ProductFactory;
import com.egeo.components.product.strategy.SearchProductStrategy;
import com.egeo.components.product.vo.ChannelProductDetailRequestVO;
import com.egeo.components.product.vo.ChannelProductDetailVO;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;
import com.egeo.components.third.dto.EnterpriseServiceRouteRequestDTO;
import com.egeo.components.third.dto.EnterpriseServiceRouteResponseDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.JsonUtils;
import com.egeo.entity.CacheUser;
import com.egeo.util.security.MD5Util;
import com.egeo.utils.StringUtils;
import com.egeo.utils.cache.JedisUtil;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.condition.CategoryAndJdCondition;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dao.read.CategoryReadDAO;
import com.egeo.components.product.dao.read.CategoryTreeNodeCategoryReadDAO;
import com.egeo.components.product.dao.read.SerachSortReadDAO;
import com.egeo.components.product.dao.read.StandardUnitCombinationCategoryReadDAO;
import com.egeo.components.product.dao.read.StandardUnitCombinationReadDAO;
import com.egeo.components.product.dao.read.StandardUnitCombinationSuReadDAO;
import com.egeo.components.product.dao.read.StandardUnitCombinationTagReadDAO;
import com.egeo.components.product.dao.read.StandardUnitReadDAO;
import com.egeo.components.product.dao.write.StandardUnitCombinationSuWriteDAO;
import com.egeo.components.product.dto.JDProductSearchDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.manage.read.StandardUnitReadManage;
import com.egeo.components.product.po.SerachSortPO;
import com.egeo.components.product.po.StandardUnitCombinationCategoryPO;
import com.egeo.components.product.po.StandardUnitCombinationPO;
import com.egeo.components.product.po.StandardUnitCombinationSuPO;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.springframework.util.CollectionUtils;


@Service
public class StandardUnitReadManageImpl implements StandardUnitReadManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StandardUnitReadDAO standardUnitReadDAO;

    @Autowired
    private CategoryTreeNodeCategoryReadDAO categoryTreeNodeCategoryReadDAO;
    @Autowired
    private CategoryReadDAO categoryReadDAO;

    @Autowired
    private StandardUnitCombinationReadDAO standardUnitCombinationReadDAO;

    @Autowired
    private StandardUnitCombinationCategoryReadDAO standardUnitCombinationCategoryReadDAO;

    @Autowired
    private StandardUnitCombinationSuReadDAO standardUnitCombinationSuReadDAO;
    @Autowired
    private StandardUnitCombinationSuWriteDAO standardUnitCombinationSuWriteDAO;

    @Autowired
    private StandardUnitCombinationTagReadDAO standardUnitCombinationTagReadDAO;

    @Autowired
    private SerachSortReadDAO serachSortReadDAO;

	@Resource(name="jdProduct")
	private JdProductManage jdProductManage;

    @Autowired
	private ProductFactory productFactory;

    @Resource(name="cakeProductManage")
    private CakeProductManage cakeProductManage;

    @Autowired
    private ChannelServiceConfigClient channelServiceConfigClient;


    @Resource
    private ChannelProductFacade channelProductFacade;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private JedisUtil jedisUtil;

    public StandardUnitPO findStandardUnitById(StandardUnitPO po) {
        StandardUnitPO standardUnitpo = new StandardUnitPO();
        standardUnitpo.setId(po.getId());
        return standardUnitReadDAO.findById(standardUnitpo);
    }

    public PageResult<StandardUnitPO> findStandardUnitOfPage(StandardUnitPO po, Pagination page) {

        PageResult<StandardUnitPO> pageResult = new PageResult<StandardUnitPO>();
        List<StandardUnitPO> list = null;

        int cnt = standardUnitReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.findOfPage(po, page);
        } else {
            list = new ArrayList<StandardUnitPO>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;

    }

    public List<StandardUnitPO> findStandardUnitAll(StandardUnitPO po) {

        return standardUnitReadDAO.findAll(po,null);
    }

    /**
     * 根据su草稿id集合查询su信息
     *
     * @param ids
     * @return
     */
    @Override
    public List<StandardUnitCondition> findBymerchantProdId(List<Long> ids) {
        // TODO Auto-generated method stub
        return standardUnitReadDAO.findBymerchantProdId(ids);
    }

    /**
     * 根据spuid查询所有su的条数
     *
     * @param standardProductUnitId
     * @return
     */
    @Override
    public int countRecord(Long standardProductUnitId) {
        // TODO Auto-generated method stub
        return standardUnitReadDAO.countRecord(standardProductUnitId);
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
    public PageResult<StandardUnitCondition> findStandardUnitOfPageAPP(StandardUnitPO po, Pagination page) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;

        int cnt = standardUnitReadDAO.countOfPageAPP(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.findStandardUnitOfPageAPP(po, page);
        } else {
            list = new ArrayList<StandardUnitCondition>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    /**
     * 根据类目节点id查询su商品信息
     *
     * @param vo
     * @param req
     * @return
     */
    @Override
    public PageResult<StandardUnitCondition> standardUnitStockByCategoryTreeNodeId(StandardUnitPO po, Pagination page) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;

        int cnt = standardUnitReadDAO.countStandardUnitStockByCategoryTreeNodeIdAPP(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.standardUnitStockByCategoryTreeNodeId(po, page);
        } else {
            list = new ArrayList<StandardUnitCondition>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    /**
     * 根据条件查询所有上架suid和名称
     *
     * @return
     */
    @Override
    public List<StandardUnitPO> findStandardUnitIdAndName(StandardUnitPO po) {
        // TODO Auto-generated method stub
        return standardUnitReadDAO.findStandardUnitIdAndName(po,null);
    }

    /**
     * 根据类目节点id集合查询su商品信息
     *
     * @param couponType
     *@param categoryTreeNodeIdList
     * @param page   @return
     */
    @Override
    public PageResult<StandardUnitCondition> standardUnitByFunctionModuleId(Integer couponType, StandardUnitPO po, List<Long> categoryTreeNodeIdList, Integer companyType, Pagination page) {
        PageResult<StandardUnitCondition> pageResult = getPageResult(page);
        //如果后台类目节点为空直接返回
        if (EmptyUtil.isEmpty(categoryTreeNodeIdList)) {
            return pageResult;
        }
        //构建需要的查询参数
        ChannelProductSearchBean param = new ChannelProductSearchBean(couponType,po,categoryTreeNodeIdList,companyType,page);
        //后面这里可以做配置化
        for (ProductChannelCodeEnum sortedEnum : ProductChannelCodeEnum.sortedEnums) {
            //该渠道是否开放
            if(Objects.equals(sortedEnum.getState(),1)){
                continue;
            }
            SearchProductStrategy searchProduct = productFactory.getSearchProductStrategy(sortedEnum.getCode());
            if(!searchProduct.hasSearchNext(param)){
                continue;
            }
            //具体策略中去执行查询
            PageResult<StandardUnitCondition> rt = searchProduct.searchChannelProduct(param);
            //若是结果不为空就返回，否则继续下一次循环
            if(Objects.nonNull(rt) && !CollectionUtils.isEmpty(rt.getList())){
               return rt;
            }
        }
        //若是策略中每一个具体的都未查询到结果，返回上方初始化的结果
        return pageResult;
    }

    private PageResult<StandardUnitCondition> getPageResult(Pagination page) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        pageResult.setList(new ArrayList<>());
        pageResult.setTotalSize(0);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    /**
     * 根据类目节点id集合查询su商品信息
     *
     * @param couponType
     *@param categoryTreeNodeIdList
     * @param page   @return
     */
    //@Override
    public PageResult<StandardUnitCondition> standardUnitByFunctionModuleIdBak(Integer couponType, StandardUnitPO po, List<Long> categoryTreeNodeIdList, Integer companyType, Pagination page) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;
        //如果后台类目节点为空直接返回
        if (EmptyUtil.isEmpty(categoryTreeNodeIdList)) {
            list = new ArrayList<StandardUnitCondition>();
            pageResult.setList(list);
            pageResult.setTotalSize(0);
            pageResult.setPageNo(page.getPageNo());
            pageResult.setPageSize(page.getPageSize());
            return pageResult;
        }
        //查看是否有京东后台类目
        List<CategoryAndJdCondition> categorys = categoryReadDAO.findCategoryByCategoryTreeNodes(categoryTreeNodeIdList);
        //TODO 新增供应商后台类目表（可以根据渠道+类目id查询到该渠道是否有需要查询的类目）
        List<Long> jdCategorys = new ArrayList<Long>();
        if(categorys!=null && categorys.size()>0) {
        	for(CategoryAndJdCondition one : categorys) {
        		if(one.getJdCategory()!=null && one.getJdCategory()>0) {
        			jdCategorys.add(one.getJdCategory());
        		}
        	}
        }
        int cnt = standardUnitReadDAO.countstandardUnitByFunctionModuleId(couponType,po, categoryTreeNodeIdList, companyType);
        //如果本公司的产品还存在就先显示本公司的产品
        if (cnt>0 && cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.standardUnitByFunctionModuleId(couponType,po, categoryTreeNodeIdList, companyType, page);
        }
        if (EmptyUtil.isEmpty(list) || list.size()<page.getPageSize()){
        	//如果本公司产品不存在就搜索京东的产品
            if(jdCategorys!=null && jdCategorys.size()>0) {
                if (EmptyUtil.isEmpty(list)){
                    list = new ArrayList<StandardUnitCondition>();
                }
            	int platformPage = (cnt==0?0:((cnt/page.getPageSize())+1));
            	int jdPage = page.getPageNo()-platformPage;
            	JDProductSearchDTO search = new JDProductSearchDTO();
            	search.setPageIndex(jdPage);
            	search.setPageSize(page.getPageSize()-list.size());
            	//排序方式
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
            	//类目
            	search.setCid2(jdCategorys.get(0)+"");
            	JsonResult<PageResult<JdProductDTO>> jdRslt = jdProductManage.searchOfCategoryLevel2(search);
            	pageResult.setTotalSize(0);
            	if(jdRslt.getData()!=null && jdRslt.getData().getList()!=null && jdRslt.getData().getList().size()>0) {
                	for(JdProductDTO oneJd:jdRslt.getData().getList()) {
                		StandardUnitCondition tmp = new StandardUnitCondition();
                		if(EmptyUtil.isNotEmpty(oneJd.getName())){
                            tmp.fromJdProduct(oneJd);
                            if(Objects.nonNull(tmp.getStatus()) && tmp.getStatus().equals(Integer.valueOf(3))){
                                list.add(tmp);
                            }
                        }
                	}

                    pageResult.setList(list);
                    pageResult.setTotalSize((((cnt/page.getPageSize())+1)*page.getPageSize())+jdRslt.getData().getTotalSize());
            	}

                /*if(jdCategorys!=null && jdCategorys.size()>0) {
                    pageResult.setTotalSize(cnt+50*page.getPageSize());
                }*/
                pageResult.setPageNo(page.getPageNo());
                pageResult.setPageSize(page.getPageSize());
                return pageResult;
            }else {
            	list = new ArrayList<StandardUnitCondition>();
            }

        }


        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        if(jdCategorys!=null && jdCategorys.size()>0) {
            pageResult.setTotalSize(cnt+50*page.getPageSize());
        }
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    /**
     * 根据类目节点id查询su列表信息
     *  @param queryId
     * @param saleWay
     * @param page  @return
     * @param buyType
     */
    @Override
    public PageResult<StandardUnitCondition> standardUnitByCategoryTreeNodeId(
            Integer saleWay, Long categoryTreeNodeId,
            Long platformId,
            BigDecimal userBalance,
            Long clientId,
            Long companyId,
            Integer companyType,
            Long storeId,
            Pagination page,
            Integer buyType) {
        //根据前台类目节点id查询后台类目id信息
        List<Long> categoryTreeNodeIdList = categoryTreeNodeCategoryReadDAO.findCategoryIdsByCategoryTreeNodeId(categoryTreeNodeId);
        StandardUnitPO standardUnitPO = new StandardUnitPO();
        //判断后台类目树是否是京东类目树
        standardUnitPO.setPlatformId(platformId);
        standardUnitPO.setClientId(clientId);
        standardUnitPO.setCompanyId(companyId);
        standardUnitPO.setSalePrice(userBalance);
        standardUnitPO.setStoreId(storeId);
        standardUnitPO.setSaleWay(saleWay);
        if (buyType != null && buyType == 0 ) {
            standardUnitPO.setBuyType(null);
        }else {
            standardUnitPO.setBuyType(buyType);
        }

        return standardUnitByFunctionModuleId(null,standardUnitPO, categoryTreeNodeIdList, companyType, page);
    }

    @Override
    public PageResult<StandardUnitCondition> standardUnitByStandardUnitCombinationId(Integer couponType, Integer saleWay, Long standardUnitCombinationId, Long platformId, BigDecimal userBalance, Long clientId, Long enterpriseId, Long companyId, Integer companyType, Long storeId, Pagination page, Integer buyType) {
        return standardUnitByStandardUnitCombinationId(couponType,saleWay,standardUnitCombinationId,platformId,userBalance,clientId,enterpriseId,companyId,companyType,storeId,page,buyType,null,null,null,null);
    }

    @Override
    public PageResult<StandardUnitCondition> standardUnitByStandardUnitCombinationId(Integer couponType, Integer saleWay, Long standardUnitCombinationId, Long platformId, BigDecimal userBalance,
                                                                                     Long clientId,Long enterrprisetId, Long companyId, Integer companyType,
                                                                                     Long storeId, Pagination page, Integer buyType,Integer sellState,Integer showAll,Boolean isUserClient,String keyWord) {
        //buyType == 0 查询全部
        if (buyType != null && buyType == 0) {
            buyType = null;
        }
        //根据su组合id查询su组合详情
        StandardUnitCombinationPO standardUnitCombinationPO = new StandardUnitCombinationPO();
        standardUnitCombinationPO.setId(standardUnitCombinationId);
        StandardUnitCombinationPO standardUnitCombinationPO2 = standardUnitCombinationReadDAO.findById(standardUnitCombinationPO);
        if (standardUnitCombinationPO2 == null)
            return null;
        logger.info("是否前端:{},组合id:{},组合名称:{},组合最小毛利:{}",isUserClient,standardUnitCombinationPO2.getId(),standardUnitCombinationPO2.getCombinationName(),standardUnitCombinationPO2.getCombinationMinProfit());
        if (standardUnitCombinationPO2.getType() == 1) {
            page.setOrderBy("sus.sort_value");
            logger.info("根据su商品类型商品组合id查询su商品列表，su商品组合id：" + standardUnitCombinationId);
            return findByTypeSU(couponType,saleWay,standardUnitCombinationId, userBalance, platformId, clientId,enterrprisetId, companyId, companyType, storeId, page, buyType,null);
        }
        if (standardUnitCombinationPO2.getType() == 2) {
            logger.info("根据类目节点类型商品组合id查询su商品列表，su商品组合id：" + standardUnitCombinationId);
            return findByTypeCategoryTreeNode(couponType,saleWay,standardUnitCombinationId, userBalance, platformId, clientId, companyId, companyType, storeId, page, buyType,enterrprisetId);
        }
        if (standardUnitCombinationPO2.getType() == 3) {
            logger.info("根据标签类型商品组合id查询su商品列表，su商品组合id：" + standardUnitCombinationId);
            return findByTypeTag(couponType,saleWay,standardUnitCombinationId, userBalance, platformId, clientId, companyId, companyType, storeId, page, buyType);
        }
        if (standardUnitCombinationPO2.getType() == 6) {
            logger.info("根据第三方商品库商品组合id查询su商品列表，su商品组合id：" + standardUnitCombinationId);
            page.setOrderBy("sort_value,id");
            if(page.getOrderBy()!=null && page.getOrderBy().length()>0) {
                if(page.getOrderBy().equalsIgnoreCase("sales_volume")) {
                    page.setOrderBy("sort_value,id");
                }else if(page.getOrderBy().equalsIgnoreCase("sales_volume desc")) {
                    page.setOrderBy("sort_value,id");
                }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price")) {
                    page.setOrderBy("sort_price,id");
                }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price desc")) {
                    page.setOrderBy("sort_price desc");
                }
            }
            return findThirdSuBySU(standardUnitCombinationId, null, page,standardUnitCombinationPO2,isUserClient,keyWord);
        }
        if (standardUnitCombinationPO2.getType() == 10) {
            logger.info("根据第三方商品库商品组合id查询su商品列表，su商品组合id：" + standardUnitCombinationId);

            page.setOrderBy("sort_value,id");
            if(page.getOrderBy()!=null && page.getOrderBy().length()>0) {
                if(page.getOrderBy().equalsIgnoreCase("sales_volume")) {
                    page.setOrderBy("sort_value,id");
                }else if(page.getOrderBy().equalsIgnoreCase("sales_volume desc")) {
                    page.setOrderBy("sort_value,id");
                }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price")) {
                    page.setOrderBy("sort_price,id");
                }else if(page.getOrderBy().equalsIgnoreCase("su.sale_price desc")) {
                    page.setOrderBy("sort_price desc");
                }
            }
            return findMixSuBySU(couponType,saleWay,standardUnitCombinationId, userBalance, platformId,
                    clientId,enterrprisetId, companyId, companyType, storeId, page, buyType,null,sellState,showAll,standardUnitCombinationPO2,isUserClient,keyWord);
        }
        return null;
    }

    /**
     * 根据标签类型商品组合id查询su商品列表
     *
     *
     *
     * @param couponType
     * @param saleWay
     * @param standardUnitCombinationId
     * @param userBalance
     * @param platformId
     * @param clientId
     * @param companyId
     * @param page
     * @param buyType
     * @return
     */
    private PageResult<StandardUnitCondition> findByTypeTag(Integer couponType, Integer saleWay, Long standardUnitCombinationId, BigDecimal userBalance,
                                                            Long platformId, Long clientId, Long companyId, Integer companyType, Long storeId, Pagination page, Integer buyType) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;

        int cnt = standardUnitReadDAO.countByTypeTag(couponType,saleWay,standardUnitCombinationId, userBalance, clientId, companyId, companyType, platformId, storeId, buyType,null);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.findByTypeTag(couponType,saleWay,standardUnitCombinationId, userBalance, clientId, companyId, companyType, platformId, storeId, page, buyType,null);
        } else {
            list = new ArrayList<StandardUnitCondition>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    /**
     * 根据类目节点类型商品组合id查询su商品列表
     *
     *
     *
     * @param couponType
     * @param saleWay
     * @param standardUnitCombinationId
     * @param platformId
     * @param clientId
     * @param companyId
     * @param page
     * @param buyType
     * @return
     */
    private PageResult<StandardUnitCondition> findByTypeCategoryTreeNode(Integer couponType, Integer saleWay, Long standardUnitCombinationId, BigDecimal userBalance,
                                                                         Long platformId, Long clientId, Long companyId, Integer companyType, Long storeId, Pagination page, Integer buyType,Long enterrprisetId) {
        List<Long> categoryTreeNodeIds = new ArrayList<>();
        //根据su商品组合id查询前台类目节点id
        StandardUnitCombinationCategoryPO standardUnitCombinationCategoryPO = new StandardUnitCombinationCategoryPO();
        standardUnitCombinationCategoryPO.setStandardUnitCombinationId(standardUnitCombinationId);
        List<StandardUnitCombinationCategoryPO> standardUnitCombinationCategoryList = standardUnitCombinationCategoryReadDAO.findAll(standardUnitCombinationCategoryPO,null);
        for (StandardUnitCombinationCategoryPO standardUnitCombinationCategoryPO2 : standardUnitCombinationCategoryList) {
            //根据前台类目节点id查询后台类目id信息
            List<Long> categoryTreeNodeIdList = categoryTreeNodeCategoryReadDAO.findCategoryIdsByCategoryTreeNodeId(standardUnitCombinationCategoryPO2.getCategoryTreeNodeId());
            categoryTreeNodeIds.addAll(categoryTreeNodeIdList);
        }
        //list去重
        List<Long> newList = new ArrayList<Long>(new HashSet<Long>(categoryTreeNodeIds));

        StandardUnitPO standardUnitPO = new StandardUnitPO();
        standardUnitPO.setPlatformId(platformId);
        standardUnitPO.setClientId(clientId);
        standardUnitPO.setCompanyId(companyId);
        standardUnitPO.setSalePrice(userBalance);
        standardUnitPO.setStoreId(storeId);
        standardUnitPO.setSaleWay(saleWay);
        standardUnitPO.setBuyType(buyType);
        //return standardUnitByFunctionModuleId(couponType,standardUnitPO, newList, companyType, page);
        return standardUnitByFunctionModuleId(couponType,standardUnitPO, newList, companyType, page,enterrprisetId,null,null);
    }

    /**
     * 根据su商品类型商品组合id查询su商品列表
     *
     *
     *
     * @param couponType
     * @param saleWay
     * @param standardUnitCombinationId
     * @param platformId
     * @param clientId
     * @param companyId
     * @param page
     * @param buyType
     * @return
     */
    private PageResult<StandardUnitCondition> findByTypeSU(Integer couponType, Integer saleWay, Long standardUnitCombinationId, BigDecimal userBalance, Long platformId,
                                                           Long clientId,Long enterpriseId, Long companyId, Integer companyType, Long storeId, Pagination page, Integer buyType,Integer frontSerialNumber) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;

        int cnt = standardUnitReadDAO.countstandardUnitByStandardUnitCombinationId(couponType,
                saleWay,standardUnitCombinationId, userBalance, clientId, enterpriseId,companyId, companyType, platformId, storeId, buyType,frontSerialNumber,null);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.standardUnitByStandardUnitCombinationId(couponType,
                    saleWay,standardUnitCombinationId, userBalance, clientId,null, companyId, companyType, platformId, storeId, page, buyType,frontSerialNumber);
        } else {
            list = new ArrayList<StandardUnitCondition>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }
    private PageResult<StandardUnitCondition> findMixSuBySU(Integer couponType, Integer saleWay, Long standardUnitCombinationId, BigDecimal userBalance, Long platformId,
            Long clientId,Long enterpriseId, Long companyId, Integer companyType, Long storeId, Pagination page, Integer buyType,Integer frontSerialNumber,Integer sellState,Integer showAll,StandardUnitCombinationPO standardUnitCombinationPO2,Boolean isUserClient,String keyWord) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCombinationSuPO> listTmp = new ArrayList<StandardUnitCombinationSuPO>();
        List<StandardUnitCondition> data = new ArrayList<StandardUnitCondition>();
        StandardUnitCombinationSuPO po = new StandardUnitCombinationSuPO();
        po.setStandardUnitCombinationId(standardUnitCombinationId);
        po.setSellState(sellState);
        po.setThirdSkuName(keyWord);
        int cnt = standardUnitCombinationSuReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            listTmp = standardUnitCombinationSuReadDAO.findOfPage(po, page);
        } else {
        	listTmp = new ArrayList<StandardUnitCombinationSuPO>();
        }
        //分类处理数据详情
        List<Long> listTmpSelf = null;
        List<StandardUnitCondition> dataSelf = null;
        List<StandardUnitCondition> dataJd = null;
        List<StandardUnitCombinationSuPO> listTmpJd = null;
        for(StandardUnitCombinationSuPO one :listTmp) {
        	if(one.getSource()==3) {
        		if(listTmpJd==null) {
        			listTmpJd = new ArrayList<StandardUnitCombinationSuPO>();
        		}
        		if(dataJd == null) {
        			dataJd =  new ArrayList<StandardUnitCondition>();
        		}
        		listTmpJd.add(one);
        	}else if(one.getSource()<3) {
        		if(listTmpSelf==null) {
        			listTmpSelf = new ArrayList<Long>();
        		}
        		listTmpSelf.add(one.getStandardUnitId());
        	}
        }
        //处理京东数据
        if(listTmpJd!=null && listTmpJd.size()>0) {
            findJdSuBySU(dataJd, listTmpJd,standardUnitCombinationPO2,isUserClient);
        }
        //处理自营数据
        if(listTmpSelf!=null && listTmpSelf.size()>0) {
            logger.info("商品组合查询执行前查看企业公司id:{}",companyId);
            dataSelf = standardUnitReadDAO.standardUnitByStandardUnitCombinationIdAndSuId(null,
            		null,standardUnitCombinationId, null, null,null, companyId, null, null, 1l, null, null,null,listTmpSelf,showAll);
        }
        Map<Long,StandardUnitCondition> dataJdMap=new HashMap<>();
        if (EmptyUtil.isNotEmpty(dataJd)){
            dataJd.forEach(item->dataJdMap.put(item.getId(),item));
        }
        Map<Long,StandardUnitCondition> dataSelfMap=new HashMap<>();
        if (EmptyUtil.isNotEmpty(dataSelf)){
            dataSelf.forEach(item->dataSelfMap.put(item.getId(),item));
        }
        for(StandardUnitCombinationSuPO one :listTmp) {
        	StandardUnitCondition oneData = null;
        	if(one.getSource()==3) {
                if (dataJdMap.containsKey(one.getStandardUnitId())){
                    //京东下线
                    /*if(isUserClient == null || !isUserClient){
                        oneData = dataJdMap.get(one.getStandardUnitId());
                    }*/
                    oneData = dataJdMap.get(one.getStandardUnitId());
                }
        	}else if(one.getSource()<3) {
                if (dataSelfMap.containsKey(one.getStandardUnitId())){
                    oneData = dataSelfMap.get(one.getStandardUnitId());
                }
        	}
        	if(oneData!=null) {
            	data.add(oneData);
        	}
        }
        //抽出蛋糕叔叔的查询方法
        addCakeProductToDataList(listTmp, data,standardUnitCombinationPO2,isUserClient);
        //全球购的查询方法
        addWorldProductToDataList(listTmp,data,standardUnitCombinationPO2,isUserClient);
        pageResult.setList(data);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        if(CollectionUtils.isEmpty(data)){
            logger.info("是否前端:{},组合id:{}第{}页返回无数据了:{}",isUserClient,standardUnitCombinationId,page.getPageNo(),JSON.toJSONString(pageResult));
        }
        return pageResult;
    }


    private void findSelfSuBySU(List<StandardUnitCondition> data,List<Long> suIds,Long standardUnitCombinationId) {

        data = standardUnitReadDAO.standardUnitByStandardUnitCombinationIdAndSuId(null,
        		null,standardUnitCombinationId, null, null,null, null, null, null, 1l, null, null,null,suIds,null);

    }

    /**
     * 处理商品组合中的京东商品详情
     *
     *
     *
     * @param data  返回值
     * @param listTmp 待处理的京东数据
     * @return
     */
    private void findJdSuBySU(List<StandardUnitCondition> data,List<StandardUnitCombinationSuPO> listTmp,StandardUnitCombinationPO standardUnitCombinationPO2,Boolean isUserClient) {

        List<JdProductDTO> jdReq = new ArrayList<JdProductDTO>();
        for(StandardUnitCombinationSuPO one :listTmp) {
        	if(one.getSource()!=null&&one.getSource().intValue()==3) {
        		if(one.getSnapshot()==null || one.getSnapshot().length()<10) {
            		JdProductDTO jdDto = new JdProductDTO();
            		jdDto.setId(one.getStandardUnitId());
            		jdReq.add(jdDto);
        		}else {
        			JdProductDTO jdDto = JSON.parseObject(one.getSnapshot(), JdProductDTO.class);
        			if(jdDto.getUpdateTime()==null ||(jdDto.getUpdateTime().getTime()+7*24*3600*1000)<System.currentTimeMillis()) {
        				JdProductDTO jdDto2 = new JdProductDTO();
        				jdDto2.setId(one.getStandardUnitId());
                		jdReq.add(jdDto2);
            		}else {
                		jdReq.add(jdDto);
            		}
        		}
        	}

        }
        long start = System.currentTimeMillis();
        List<JdProductDTO> jdData = jdProductManage.findJdProductByIds(jdReq);
        Map<Long,JdProductDTO> jdDataMap=new HashMap<>();
        if (EmptyUtil.isNotEmpty(jdData)){
            jdData.forEach(jd->{
                if (jd.getId()!=null){
                    jdDataMap.put(jd.getId(),jd);
                }
            });
        }
        logger.info("获取京东数据耗时(ms)："+(System.currentTimeMillis()-start));
    	for(StandardUnitCombinationSuPO oneOld :listTmp) {
			Long oldSkuId = oneOld.getStandardUnitId();
            if(oldSkuId==null) {
                continue;
            }
            if (jdDataMap.containsKey(oldSkuId)){
                JdProductDTO one=jdDataMap.get(oldSkuId);
                if (Objects.nonNull(one)){
                    Long skuId = one.getId();
                    if(oneOld.getSource()!=null&&oneOld.getSource().intValue()==3) {
                        Boolean needCache = false;
                        if(oneOld.getSnapshot()==null || oneOld.getSnapshot().length()<10) {
                            needCache = true;
                        }else {
                            JdProductDTO jdDto = JSON.parseObject(oneOld.getSnapshot(), JdProductDTO.class);
                            if(jdDto.getUpdateTime()==null ||(jdDto.getUpdateTime().getTime()+7*24*3600*1000)<System.currentTimeMillis()) {
                                needCache = true;
                            }
                        }
                        if(needCache) {
                            //logger.info("更新产品组合中京东数据缓存："+skuId.longValue()+"  "+JSON.toJSONString(one));
                            oneOld.setSnapshot(JSON.toJSONString(one));
                            standardUnitCombinationSuWriteDAO.update(oneOld);
                        }

                    }

                    StandardUnitCondition tmp = new StandardUnitCondition();
                    tmp.fromJdProduct(one);
                    BigDecimal customProfit = one.getCustomProfit();
                    boolean isMoreThanMinProfit =checkIsMoreMinProfit(standardUnitCombinationPO2, isUserClient, customProfit);
                   if(isMoreThanMinProfit){
                       data.add(tmp);
                   }
                    BigDecimal cfgProfit = standardUnitCombinationPO2 !=null?standardUnitCombinationPO2.getCombinationMinProfit():null;
                   //logger.info("京东,商品名称:{},商品id:{}是否前端:{},是否被添加:{},毛利:{},设置的毛利:{}",tmp.getName(),tmp.getId(),isUserClient,isMoreThanMinProfit,customProfit,cfgProfit);

                }
            }

    	}

    }

    private static boolean IS_CHECK=true;

    private boolean checkIsMoreMinProfit(StandardUnitCombinationPO standardUnitCombinationPO2, Boolean isUserClient, BigDecimal customProfit) {
       //是否检查
        if(!IS_CHECK){
            return true;
        }
        //未说明来源是需要过滤
        if(isUserClient ==null){
            return false;
        }
        //来自后台管理端的请求，不过滤
        if(!isUserClient){
            return true;
        }
        //未设置组合最小毛利不过滤
       if(standardUnitCombinationPO2 ==null || standardUnitCombinationPO2.getCombinationMinProfit()==null){
           return true;
       }
       //未计算出毛利，不添加到其中
        if(customProfit == null){
           return false;
       }
        //超过组合设置的毛利了
        if(standardUnitCombinationPO2.getCombinationMinProfit().compareTo(customProfit) >0){
           return false;
        }
        return true;
    }

    /**
     * 根据su商品类型商品组合id查询su商品列表
     *
     *
     *
     * @param couponType
     * @param saleWay
     * @param standardUnitCombinationId
     * @param platformId
     * @param clientId
     * @param companyId
     * @param page
     * @param buyType
     * @return
     */
    private PageResult<StandardUnitCondition> findThirdSuBySU(Long standardUnitCombinationId,Integer source,Pagination page,StandardUnitCombinationPO standardUnitCombinationPO2,Boolean isUserClient,String keyWord) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCombinationSuPO> listTmp = new ArrayList<StandardUnitCombinationSuPO>();
        List<StandardUnitCondition> data = new ArrayList<StandardUnitCondition>();
        StandardUnitCombinationSuPO po = new StandardUnitCombinationSuPO();
        po.setStandardUnitCombinationId(standardUnitCombinationId);
        po.setSource(source);
        po.setThirdSkuName(keyWord);
        int cnt = standardUnitCombinationSuReadDAO.countOfPage(po);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            listTmp = standardUnitCombinationSuReadDAO.findOfPage(po, page);
        } else {
        	listTmp = new ArrayList<StandardUnitCombinationSuPO>();
        }
        //优化抽出京东查询方法
        //京东下线
        /*if(isUserClient==null || !isUserClient){
            addJdProductToDataList(listTmp, data, standardUnitCombinationPO2,isUserClient);
        }*/
        addJdProductToDataList(listTmp, data, standardUnitCombinationPO2,isUserClient);
        //抽出蛋糕叔叔的查询方法
        addCakeProductToDataList(listTmp, data,standardUnitCombinationPO2,isUserClient);
        //全球购的查询方法
        addWorldProductToDataList(listTmp,data,standardUnitCombinationPO2,isUserClient);

        pageResult.setList(data);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }
;

    private void addWorldProductToDataList(List<StandardUnitCombinationSuPO> listTmp, List<StandardUnitCondition> data,StandardUnitCombinationPO standardUnitCombinationPO2,Boolean isUserClient) {
        List<String>  worldProductIdList = new ArrayList<>();
        for(StandardUnitCombinationSuPO one : listTmp) {
            if (Objects.isNull(one.getSource()) || one.getSource().intValue() != 5) {
                continue;
            }
            if (StringUtils.isEmpty(one.getSnapshot()) || one.getSnapshot().length() < 10) {
                worldProductIdList.add(one.getThirdSkuId());
            } else {
                ChannelProductDetailVO worldProductDetailDTO = JSON.parseObject(one.getSnapshot(), ChannelProductDetailVO.class);
                ChannelProductDTO productsDTO = worldProductDetailDTO.getChannelProductDTO();
                if (StringUtils.isNotEmpty(productsDTO.getProductId())) {
                    worldProductIdList.add(productsDTO.getProductId());
                }
            }
        }
        if(CollectionUtils.isEmpty(worldProductIdList)){
            return;
        }
        long start = System.currentTimeMillis();
        List<ChannelProductDetailVO>  searchList = new ArrayList<>();
        for (String s : worldProductIdList) {
            ChannelProductDetailRequestVO vo = new ChannelProductDetailRequestVO();
            vo.setProductId(s);
            vo.setChannelCode(ProductChannelCodeEnum.WORLD_BUY.getCode());
            ChannelProductDetailVO channelProductDetailVO = channelProductFacade.getChannelProductDetail(vo);
            if(Objects.isNull(channelProductDetailVO)){
                continue;
            }
            searchList.add(channelProductDetailVO);
        }
        logger.info("获取全球购古商品详情数据耗时(ms)："+(System.currentTimeMillis()-start));
        if(CollectionUtils.isEmpty(searchList)){
            return;
        }
        Map<String,ChannelProductDetailVO> channelProductMap = new HashMap<>();
        for (ChannelProductDetailVO worldProductDetailDTO : searchList) {
            ChannelProductDTO productsDTO = worldProductDetailDTO.getChannelProductDTO();
            if(productsDTO ==null){
                continue;
            }
            channelProductMap.put(productsDTO.getProductId(),worldProductDetailDTO);
        }

        if(CollectionUtils.isEmpty(channelProductMap)){
            return;
        }
        for(StandardUnitCombinationSuPO one : listTmp) {
            if(one.getSource() ==null || one.getSource().intValue() !=5){
                continue;
            }
            ChannelProductDetailVO worldProductDetailDTO = channelProductMap.get(one.getThirdSkuId());

            if(Objects.isNull(worldProductDetailDTO)){
                logger.info("未从map中获取到商品{}",one.getStandardUnitId());
                continue;
            }
            ChannelProductDTO productsDTO = worldProductDetailDTO.getChannelProductDTO();
            //将商品详情中商品信息转换成需要的格式
            if(Objects.isNull(productsDTO)){
                continue;
            }
            ChannelProductDescriptionDTO descriptionDTO = worldProductDetailDTO.getDescriptionDTO();
            List<ChannelProductBatchDTO> batchDTOList =  worldProductDetailDTO.getBatchDTOList();
            ChannelProductDetailRequestVO getCurrBatchVO = new ChannelProductDetailRequestVO();
            getCurrBatchVO.setProductId(one.getThirdSkuId());
            getCurrBatchVO.setSkuId(String.valueOf(one.getStandardUnitId()));
            ChannelProductBatchDTO channelProductBatchDTO = channelProductFacade.getCurrBatch(getCurrBatchVO,batchDTOList);
            if(channelProductBatchDTO ==null){
                logger.info("商品组合中处理规格时未找到规格",one.getThirdSkuId());
                continue;
            }
            List<ChannelProductSkuDTO>  skuList = worldProductDetailDTO.getSkuList();
            StandardUnitCondition tmp = new StandardUnitCondition();
           boolean isCanSell =  checkIsCanSellState(productsDTO, channelProductBatchDTO, skuList);

            tmp.setSaleWay(1);
            tmp.setPictureUrl(worldProductDetailDTO.getProductImg());
            tmp.setContent(Objects.nonNull(descriptionDTO)?descriptionDTO.getContent():null);
            tmp.setCommodityTemplateId(2L);
            tmp.setSource(ThirdConst.Source.WORLD);
            tmp.setName(productsDTO.getName());
            tmp.setIsVisible(productsDTO.getIsAvailable());
            tmp.setBuyType(1);
            tmp.setStatus(isCanSell?3:4);
            tmp.setSalePrice(channelProductBatchDTO.getPrice());
            tmp.setMarketPrice(new BigDecimal(channelProductBatchDTO.getPriceMarket()));
            tmp.setName(productsDTO.getTitle());
            tmp.setId(channelProductBatchDTO.getId());
            tmp.setChannelProductId(productsDTO.getProductId());
            tmp.setStandardProductUnitId(Long.valueOf(productsDTO.getId()));
            tmp.setSupplierPrice(channelProductBatchDTO.getPriceSettleMent());
            BigDecimal profit = getProfit(tmp);
            boolean isMoreThanMinProfit = checkIsMoreMinProfit(standardUnitCombinationPO2,isUserClient,profit);
            if(isMoreThanMinProfit){
                data.add(tmp);
            }
            BigDecimal cfgProfit = standardUnitCombinationPO2 !=null?standardUnitCombinationPO2.getCombinationMinProfit():null;
            //logger.info("全球购商品名称:{},商品id:{},规格id:{}是否前端:{},是否被过滤:{},毛利:{},设置的毛利:{}",tmp.getName(),tmp.getChannelProductId(),tmp.getId(),isUserClient,isMoreThanMinProfit,profit,cfgProfit);

            //TODO 更新缓存的一个过程...
            boolean isNeedUpdate = false;
            if(StringUtils.isEmpty(one.getSnapshot()) || one.getSnapshot().length()<10){
                //logger.info("更新产品组合中全球购数据缓存："+one.getStandardUnitId()+"  "+JSON.toJSONString(one));
                one.setSnapshot(JSON.toJSONString(worldProductDetailDTO));
                one.setThirdSkuName(tmp.getName());
                one.setSortPrice(tmp.getSalePrice());
                isNeedUpdate = true;
            }
            Integer sellState = isCanSell?1:0;
            if(!Objects.equals(sellState,one.getSellState())){
                one.setSellState(sellState);
                isNeedUpdate = true;
            }
            if(isNeedUpdate){
                one.setCheckTime(new Date());
                standardUnitCombinationSuWriteDAO.update(one);
            }
        }
    }

    private boolean checkIsCanSellState(ChannelProductDTO productsDTO, ChannelProductBatchDTO channelProductBatchDTO, List<ChannelProductSkuDTO> skuList) {
        //批次规格状态不可售
        if(channelProductBatchDTO.getStatus() == null || channelProductBatchDTO.getStatus().intValue() !=1){
            return false;
        }
        ChannelProductSkuDTO channelProductSkuDTOOne = null;
        for (ChannelProductSkuDTO channelProductSkuDTO : skuList) {
            if(channelProductBatchDTO.getLinkSkuId().equals(channelProductSkuDTO.getExternalSkuId())){
                channelProductSkuDTOOne = channelProductSkuDTO;
                break;
            }
        }
        if(channelProductSkuDTOOne !=null && channelProductSkuDTOOne.getState() !=null && channelProductSkuDTOOne.getState().intValue() !=1){
            return false;
        }
        if(productsDTO.getStatus() !=null && productsDTO.getStatus().intValue() !=1){
            return false;
        }
        return true;
    }

    private Map<String,CakeProductDetailDTO> threadCakeProduct(List<String>  cakeProductIdList){
        if(CollectionUtils.isEmpty(cakeProductIdList)){
            return new HashMap<>();
        }
        Long enterpriseId = getEnterpriseId();
        // 使用固定数量的线程池，避免高并发导致服务器线程无限制的增长
        ThreadPoolExecutor executor = CommonThreadPoolExecutor.getInstance();
        // 主线程优先拿到最先完成的任务的返回值，而不管它们加入线程池的顺序。
        CompletionService<Map<String, CakeProductDetailDTO>> completionService = new ExecutorCompletionService<>(executor);
        List<Future<Map<String,CakeProductDetailDTO>>> results = new ArrayList<>();
        Future<Map<String,CakeProductDetailDTO>> future = null;
        //设置每个子列表的大小，不是切分成多少个
        int partitionSize =5;
        if(cakeProductIdList.size() >1 && cakeProductIdList.size() <=10){
            partitionSize =2;
        }
        if(cakeProductIdList.size() >10 && cakeProductIdList.size() <=20){
            partitionSize =3;
        }
        if(cakeProductIdList.size()>50 && cakeProductIdList.size()<=100){
            partitionSize = 10;
        }
        if(cakeProductIdList.size() >100){
            partitionSize =20;
        }
        List<List<String>> smallerLists = Lists.partition(cakeProductIdList, partitionSize);
        for (List<String> smallList : smallerLists) {
            CakeProductThread cakeProductThread = new CakeProductThread();
            cakeProductThread.setProductIds(smallList);
            cakeProductThread.setEnterpriseId(enterpriseId);
            future = completionService.submit(cakeProductThread);
            results.add(future);
        }
        Map<String,CakeProductDetailDTO> resultMap = new HashMap<>();
        for (Future<Map<String,CakeProductDetailDTO>> fut : results) {
            try {
                Map<String,CakeProductDetailDTO> rtMap =  fut.get();
                if(!CollectionUtils.isEmpty(rtMap)){
                    resultMap.putAll(fut.get());
                }
            }catch (Exception e){
                logger.error("线程池中获取蛋糕叔叔商品发生异常:{}",e);
            }
        }
        return resultMap;
    }

    private void addCakeProductToDataList(List<StandardUnitCombinationSuPO> listTmp, List<StandardUnitCondition> data,StandardUnitCombinationPO standardUnitCombinationPO2,Boolean isUserClient) {
        List<String>  cakeProductIdList = new ArrayList<>();
        Map<String,CakeProductDetailDTO> cakeMap = new HashMap<>();
        for(StandardUnitCombinationSuPO one : listTmp) {
            if (Objects.isNull(one.getSource()) || one.getSource().intValue() != 4) {
                continue;
            }
            boolean isUpdate = one.getCheckTime()==null ||(one.getCheckTime().getTime()+1*3600*1000)<System.currentTimeMillis();
            if (EmptyUtil.isEmpty(one.getSnapshot()) || one.getSnapshot().length() <10 || isUpdate) {
                cakeProductIdList.add(one.getThirdSkuId());
            } else {
                try {
                    CakeProductDetailDTO cakeDTO = JSON.parseObject(one.getSnapshot(), CakeProductDetailDTO.class);
                    cakeMap.put(cakeDTO.getProduct().getId(),cakeDTO);
                }catch (Exception e){
                    logger.info("蛋糕叔叔获取表里已缓存的数据发生异常:{}",e);
                    cakeProductIdList.add(one.getThirdSkuId());
                }
            }
        }


            long start = System.currentTimeMillis();
            Map<String,CakeProductDetailDTO> newCakeMap = threadCakeProduct(cakeProductIdList);
            logger.info("获取组合中蛋糕叔叔商品详情数据耗时(ms)："+(System.currentTimeMillis()-start));
            if(!CollectionUtils.isEmpty(newCakeMap)){
                cakeMap.putAll(newCakeMap);
            }
            if(CollectionUtils.isEmpty(cakeMap)){
                return;
            }
            for(StandardUnitCombinationSuPO one : listTmp) {
                if(one.getSource() ==null || one.getSource().intValue() !=4){
                    continue;
                }
                CakeProductDetailDTO cakeProductDetailDTO = cakeMap.get(one.getThirdSkuId());

                if(Objects.isNull(cakeProductDetailDTO) || CollectionUtils.isEmpty(cakeMap)){
                    logger.info("未从map中获取到商品{}",one.getStandardUnitId());
                    continue;
                }
                CakeProductDetailProductsDTO productsDTO = cakeProductDetailDTO.getProduct();
                //将商品详情中商品信息转换成需要的格式
                if(Objects.isNull(productsDTO)){
                    continue;
                }
                List<CakeProductDetailSpecsDTO> specsDTOS = cakeProductDetailDTO.getSpecs();
                Map<String,CakeProductDetailSpecsDTO> specsDTOMap = FHCollectionUtils.listToMap(specsDTOS,CakeProductDetailSpecsDTO::getId,e->e);

                CakeProductDetailSpecsDTO specsDTO = specsDTOMap.get(String.valueOf(one.getStandardUnitId()));
                if(specsDTO ==null){
                    logger.info("商品组合中处理规格时未找到规格",one.getThirdSkuId());
                    continue;
                }
                StandardUnitCondition tmp = new StandardUnitCondition();
                tmp.fromCakeProduct(productsDTO);
                tmp.setSalePrice(new BigDecimal(specsDTO.getPrice()));
                tmp.setMarketPrice(new BigDecimal(specsDTO.getMarket_price()));
                tmp.setName(productsDTO.getTitle());
                tmp.setId(Long.valueOf(specsDTO.getId()));
                tmp.setChannelProductId(productsDTO.getId());
                tmp.setStandardProductUnitId(Long.valueOf(productsDTO.getId()));
                tmp.setSupplierPrice(new BigDecimal(specsDTO.getClearing_price()));
                tmp.setStatus(Objects.equals(productsDTO.getStatus(),"1")?3:4);
                BigDecimal profit = getProfit(tmp);
                boolean isMoreThanMinProfit = checkIsMoreMinProfit(standardUnitCombinationPO2,isUserClient,profit);
                if(isMoreThanMinProfit){
                    data.add(tmp);
                }
                BigDecimal cfgProfit = standardUnitCombinationPO2 !=null?standardUnitCombinationPO2.getCombinationMinProfit():null;
                //logger.info("蛋糕叔叔商品名称:{},商品id:{},规格id:{}是否前端:{},是否被过滤:{},毛利:{},设置的毛利:{}",tmp.getName(),tmp.getChannelProductId(),tmp.getId(),isUserClient,isMoreThanMinProfit,profit,cfgProfit);
                int sellStatus = Objects.equals(productsDTO.getStatus(),"1")?1:0;
                //TODO 更新缓存的一个过程...
                boolean isNeedUpdate = false;
                if(StringUtils.isEmpty(one.getSnapshot()) || one.getSnapshot().length()<10 || cakeProductIdList.contains(productsDTO.getId())){
                    //logger.info("更新产品组合中蛋糕叔叔数据缓存："+one.getStandardUnitId()+"  "+JSON.toJSONString(one));
                    one.setSnapshot(JSON.toJSONString(cakeProductDetailDTO));
                    one.setSellState(sellStatus);
                    one.setThirdSkuName(tmp.getName());
                    one.setSortPrice(tmp.getSalePrice());
                    if(EmptyUtil.isNotEmpty(productsDTO.getBuy_count()) && EmptyUtil.isNotBlank(productsDTO.getBuy_count())){
                        one.setSortSalesNum(Integer.valueOf(productsDTO.getBuy_count()));
                    }
                    isNeedUpdate = true;
                }
                if(isNeedUpdate){
                    one.setCheckTime(new Date());
                    standardUnitCombinationSuWriteDAO.update(one);
                }

        }
    }

    private BigDecimal getProfit(StandardUnitCondition tmp) {
        BigDecimal profit= null;
        try {
            if (tmp.getSupplierPrice().compareTo(BigDecimal.ZERO)>0 && tmp.getSalePrice().compareTo(BigDecimal.ZERO)>0){
                BigDecimal grossProfit= tmp.getSalePrice().subtract(tmp.getSupplierPrice());
                profit=grossProfit.divide(tmp.getSalePrice(),4,BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("组合中计算毛利发生异常:{}",e);
        }
        return profit;
    }

    private void addJdProductToDataList(List<StandardUnitCombinationSuPO> listTmp, List<StandardUnitCondition> data,StandardUnitCombinationPO standardUnitCombinationPO2,Boolean isUserClient) {
        List<JdProductDTO> jdReq = new ArrayList<JdProductDTO>();
        for(StandardUnitCombinationSuPO one : listTmp) {
        	if(one.getSource()!=null&&one.getSource().intValue()==3) {
        		if(one.getSnapshot()==null || one.getSnapshot().length()<10) {
            		JdProductDTO jdDto = new JdProductDTO();
            		jdDto.setId(one.getStandardUnitId());
            		jdReq.add(jdDto);
        		}else {
        			JdProductDTO jdDto = JSON.parseObject(one.getSnapshot(), JdProductDTO.class);
        			if(jdDto.getUpdateTime()==null ||(jdDto.getUpdateTime().getTime()+7*24*3600*1000)<System.currentTimeMillis()) {
        				JdProductDTO jdDto2 = new JdProductDTO();
        				jdDto2.setId(one.getStandardUnitId());
                		jdReq.add(jdDto2);
            		}else {
                		jdReq.add(jdDto);
            		}
        		}
        	}

        }
        long start = System.currentTimeMillis();
        List<JdProductDTO> jdData = jdProductManage.findJdProductByIds(jdReq);
        logger.info("获取京东数据耗时(ms)："+(System.currentTimeMillis()-start));
    	for(StandardUnitCombinationSuPO oneOld :listTmp) {
			Long oldSkuId = oneOld.getStandardUnitId();

            for(JdProductDTO one:jdData) {
    			Long skuId = one.getId();


    			if(skuId==null || oldSkuId==null) {
    				continue;
    			}
        		if(skuId.longValue()==oldSkuId.longValue()) {
            		if(oneOld.getSource()!=null&&oneOld.getSource().intValue()==3) {
            			Boolean needCache = false;
                		if(oneOld.getSnapshot()==null || oneOld.getSnapshot().length()<10) {
                			needCache = true;
                		}else {
                			JdProductDTO jdDto = JSON.parseObject(oneOld.getSnapshot(), JdProductDTO.class);
                    		if(jdDto.getUpdateTime()==null ||(jdDto.getUpdateTime().getTime()+7*24*3600*1000)<System.currentTimeMillis()) {
                    			needCache = true;
                    		}
                		}
                		if(needCache) {
                			//logger.info("更新产品组合中京东数据缓存："+skuId.longValue()+"  "+JSON.toJSONString(one));
                			oneOld.setSnapshot(JSON.toJSONString(one));
                			standardUnitCombinationSuWriteDAO.update(oneOld);
                		}

                	}
        		}

                StandardUnitCondition tmp = new StandardUnitCondition();
                tmp.fromJdProduct(one);
                BigDecimal customProfit = one.getCustomProfit();
                boolean isMoreThanMinProfit =checkIsMoreMinProfit(standardUnitCombinationPO2, isUserClient, customProfit);
                if(isMoreThanMinProfit){
                    data.add(tmp);
                }
                BigDecimal cfgProfit = standardUnitCombinationPO2 !=null?standardUnitCombinationPO2.getCombinationMinProfit():null;
                //logger.info("京东,商品名称:{},商品id:{}是否前端:{},是否被添加:{},毛利:{},设置的毛利:{}",tmp.getName(),tmp.getId(),isUserClient,isMoreThanMinProfit,customProfit,cfgProfit);
        	}
        }
    }

    /**
     * su商品组合选择商品_su商品列表
     *
     * @return
     */
    @Override
    public PageResult<StandardUnitPO> findBaseByConditionOfPage(Long standardUnitCombinationId, StandardUnitPO po, Pagination page) {
        PageResult<StandardUnitPO> pageResult = new PageResult<StandardUnitPO>();
        List<StandardUnitPO> list = null;

        int cnt = standardUnitReadDAO.countfindBaseByConditionOfPage(standardUnitCombinationId, po,null);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.findBaseByConditionOfPage(standardUnitCombinationId, po, page);
        } else {
            list = new ArrayList<StandardUnitPO>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;

    }

    /**
     * 根据su商品id查询su商品信息
     *
     * @param standardUnitId
     * @return
     */
    @Override
    public StandardUnitCondition findStandardUnitById(Long standardUnitId) {
        // TODO Auto-generated method stub
        return standardUnitReadDAO.findStandardUnitById(standardUnitId);
    }

    @Override
    public List<StandardUnitPO> findByStandardUnitCombinationId(Long standardUnitCombinationId, Long platformId) {
        //根据su组合id查询su组合详情
        StandardUnitCombinationPO standardUnitCombinationPO = new StandardUnitCombinationPO();
        standardUnitCombinationPO.setId(standardUnitCombinationId);
        StandardUnitCombinationPO standardUnitCombinationPO2 = standardUnitCombinationReadDAO.findById(standardUnitCombinationPO);
        if (standardUnitCombinationPO2 != null) {
        	if (standardUnitCombinationPO2.getType() == 1) {
        		logger.info("根据su商品类型商品组合id查询所有su商品信息，su商品组合id：" + standardUnitCombinationId);
        		return findAllByTypeSU(standardUnitCombinationId, platformId);
        	}
        	if (standardUnitCombinationPO2.getType() == 2) {
        		logger.info("根据类目节点类型商品组合id查询所有su商品信息，su商品组合id：" + standardUnitCombinationId);
        		return findAllByTypeCategoryTreeNode(standardUnitCombinationId, platformId);
        	}
        	if (standardUnitCombinationPO2.getType() == 3) {
        		logger.info("根据标签类型商品组合id查询所有su商品信息，su商品组合id：" + standardUnitCombinationId);
        		return findAllByTypeTag(standardUnitCombinationId, platformId);
        	}
        }
        return null;
    }

    /**
     * 根据标签类型商品组合id查询所有su商品信息
     *
     * @param standardUnitCombinationId
     * @param platformId
     * @return
     */
    private List<StandardUnitPO> findAllByTypeTag(Long standardUnitCombinationId, Long platformId) {
        // 根据su组合id查询tagId信息
        StandardUnitCombinationTagPO standardUnitCombinationTagPO = new StandardUnitCombinationTagPO();
        standardUnitCombinationTagPO.setStandardUnitCombinationId(standardUnitCombinationId);
        List<StandardUnitCombinationTagPO> standardUnitCombinationTagList = standardUnitCombinationTagReadDAO.findAll(standardUnitCombinationTagPO,null);
        if (EmptyUtil.isNotEmpty(standardUnitCombinationTagList)) {
            List<Long> tagIds = new ArrayList<>();
            for (StandardUnitCombinationTagPO standardUnitCombinationTagPO2 : standardUnitCombinationTagList) {
                tagIds.add(standardUnitCombinationTagPO2.getTagId());
            }
            // 根据商品标签id集合查询su商品信息
            return standardUnitReadDAO.findByTagIds(tagIds);
        }

        return null;
    }

    /**
     * 根据类目节点类型商品组合id查询所有su商品信息
     *
     * @param standardUnitCombinationId
     * @param platformId
     * @return
     */
    private List<StandardUnitPO> findAllByTypeCategoryTreeNode(Long standardUnitCombinationId, Long platformId) {
        List<Long> categoryTreeNodeIds = new ArrayList<>();
        // 根据su商品组合id查询前台类目节点id
        StandardUnitCombinationCategoryPO standardUnitCombinationCategoryPO = new StandardUnitCombinationCategoryPO();
        standardUnitCombinationCategoryPO.setStandardUnitCombinationId(standardUnitCombinationId);
        List<StandardUnitCombinationCategoryPO> standardUnitCombinationCategoryList = standardUnitCombinationCategoryReadDAO.findAll(standardUnitCombinationCategoryPO,null);
        for (StandardUnitCombinationCategoryPO standardUnitCombinationCategoryPO2 : standardUnitCombinationCategoryList) {
            // 根据前台类目节点id查询后台类目id信息
            List<Long> categoryTreeNodeIdList = categoryTreeNodeCategoryReadDAO.findCategoryIdsByCategoryTreeNodeId(standardUnitCombinationCategoryPO2.getCategoryTreeNodeId());
            categoryTreeNodeIds.addAll(categoryTreeNodeIdList);
        }
        // list去重
        List<Long> newList = new ArrayList<Long>(new HashSet<Long>(categoryTreeNodeIds));
        if (EmptyUtil.isNotEmpty(newList)) {
            // 根据类目节点id查询su商品信息
            return standardUnitReadDAO.findByStandardProductUnitIds(newList);
        }
        return null;
    }

    /**
     * 根据su商品类型商品组合id查询所有su商品信息
     *
     * @param standardUnitCombinationId
     * @param platformId
     * @return
     */
    private List<StandardUnitPO> findAllByTypeSU(Long standardUnitCombinationId, Long platformId) {

        // 根据su商品类型商品组合id查询所有su商品id
        StandardUnitCombinationSuPO standardUnitCombinationSuPO = new StandardUnitCombinationSuPO();
        standardUnitCombinationSuPO.setStandardUnitCombinationId(standardUnitCombinationId);
        List<StandardUnitCombinationSuPO> standardUnitCombinationSuList = standardUnitCombinationSuReadDAO.findAll(standardUnitCombinationSuPO,null);

        if (EmptyUtil.isNotEmpty(standardUnitCombinationSuList)) {
            List<Long> standardUnitIds = new ArrayList<>();
            for (StandardUnitCombinationSuPO standardUnitCombinationSuPO2 : standardUnitCombinationSuList) {
                standardUnitIds.add(standardUnitCombinationSuPO2.getStandardUnitId());
            }
            // 根据suid集合查询su商品信息
            return standardUnitReadDAO.findByStandardUnitIds(standardUnitIds);
        }
        return null;
    }

    /**
     * 根据条件分页查询su商品及扩展信息
     *
     * @param dto
     * @param page
     * @return
     */
    @Override
    public PageResult<StandardUnitCondition> findStandardUnitExtendOfPage(StandardUnitPO po, Pagination page, List<Long> standardUnitIdList) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;

        int cnt = standardUnitReadDAO.countStandardUnitExtendOfPage(po, standardUnitIdList);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.findStandardUnitExtendOfPage(po, page, standardUnitIdList);
        } else {
            list = new ArrayList<StandardUnitCondition>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    @Override
    public PageResult<StandardUnitPO> queryStandardUnitListByBlurry(StandardUnitPO po, Long excludeId, Pagination page) {
        PageResult<StandardUnitPO> pageResult = new PageResult<StandardUnitPO>();
        List<StandardUnitPO> list = null;

        int cnt = standardUnitReadDAO.countStandardUnitListByBlurry(po, excludeId);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.queryStandardUnitListByBlurry(po, excludeId, page);
        } else {
            list = new ArrayList<StandardUnitPO>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    @Override
    public PageResult<StandardUnitCondition> findByKeywordOfPage(Integer saleWay, Long storeId, String name, BigDecimal userBalance, Long clientId,
                                                                 Long companyId, Long platformId, Integer companyType, Pagination page, Integer buyType) {
        if (EmptyUtil.isEmpty(page.getOrderBy())) {
            List<SerachSortPO> serachSortList = serachSortReadDAO.findAll(new SerachSortPO(),null);
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < serachSortList.size(); i++) {
                if (i >= serachSortList.size() - 1) {
                    stringBuffer.append(serachSortList.get(i).getRegulation());
                } else {
                    stringBuffer.append(serachSortList.get(i).getRegulation());
                    stringBuffer.append(",");
                }
            }
            page.setOrderBy(stringBuffer.toString());
        }
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;
        if(name.equalsIgnoreCase("自营")) {
        	name = null;
        }
        Integer i = standardUnitReadDAO.countByKeywordOfPage(saleWay,storeId,name, userBalance, clientId, companyId, companyType,null, platformId, buyType,null);
        int cnt = 0;
        if (EmptyUtil.isNotEmpty(i))
            cnt = i;
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.findByKeywordOfPage(saleWay,storeId,name, userBalance, clientId, companyId,null, platformId,companyType, page, buyType,null);
        } else {
            list = new ArrayList<StandardUnitCondition>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

    @Override
    public List<Long> querySuCombinationBySuId(Long suId) {
        List<Long> combinationIdList = new ArrayList<Long>();
        List<Long> combinationListBySu = standardUnitReadDAO.querySuCombinationBySu(suId);
        List<Long> combinationListByTag = standardUnitReadDAO.querySuCombinationByTag(suId);
        List<Long> combinationListByTreeNode = standardUnitReadDAO.querySuCombinationByTreeNode(suId);

        combinationIdList.addAll(combinationListBySu);
        combinationIdList.removeAll(combinationListByTag);
        combinationIdList.addAll(combinationListByTag);
        combinationIdList.removeAll(combinationListByTreeNode);
        combinationIdList.addAll(combinationListByTreeNode);

        return combinationIdList;
    }

    @Override
    public List<StandardUnitPO> findByStandardUnitName(Long platformId, Long standardUnitId, String standardUnitName) {
        return standardUnitReadDAO.findByStandardUnitName(platformId,standardUnitId, standardUnitName,null);
    }

    @Override
    public PageResult<StandardUnitCondition> standardUnitStockByCategoryTreeNodeId(Pagination page,
                                                                                   Map<String, Object> keys) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;
        int cnt = standardUnitReadDAO.countSuByCategoryTreeNodeIds(keys);
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.suByCategoryTreeNodeIdsOfPage(keys, page);
        } else {
            list = new ArrayList<StandardUnitCondition>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

	@Override
	public PageResult<StandardUnitCondition> findStandardUnitByStoreMenuIdOfPage(Long storeMenuNodeId, Long platformId,
			Pagination page) {
		PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;

        int cnt = standardUnitReadDAO.countStandardUnitByStoreMenuIdOfPage(storeMenuNodeId,platformId);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.findStandardUnitByStoreMenuIdOfPage(storeMenuNodeId,platformId, page);
        } else {
            list = new ArrayList<StandardUnitCondition>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
	}

	@Override
	public PageResult<StandardUnitCondition> findStandardUnitByStoreIdOfPage(Long storeId, Long platformId,
			Pagination page) {
		PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;

        int cnt = standardUnitReadDAO.countStandardUnitByStoreIdOfPage(storeId,platformId);

        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = standardUnitReadDAO.findStandardUnitByStoreIdOfPage(storeId,platformId, page);
        } else {
            list = new ArrayList<StandardUnitCondition>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
	}

	@Override
    public PageResult<StandardUnitCondition> querySuByCategoryTreeNodeIds(Pagination page,
                                                                                   Map<String, Object> keys, List<Long> frontCategoryTreeNodeIds) {
		List<Long> backendCategoryTreeNodeIds = null;
		if (EmptyUtil.isNotEmpty(frontCategoryTreeNodeIds)) {
			backendCategoryTreeNodeIds = categoryTreeNodeCategoryReadDAO.findCategoryIdsByCategoryTreeNodeIds(frontCategoryTreeNodeIds);
    	}

    	PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        List<StandardUnitCondition> list = null;
        int cnt = 0;
        if (EmptyUtil.isNotEmpty(backendCategoryTreeNodeIds)) {
        	keys.put("categoryTreeNodeIds", backendCategoryTreeNodeIds);
        	cnt = standardUnitReadDAO.countSuByCategoryTreeNodeIds(keys);
            if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
                page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
                list = standardUnitReadDAO.suByCategoryTreeNodeIdsOfPage(keys, page);
            } else {
                list = new ArrayList<StandardUnitCondition>();
            }
        } else {
        	list = new ArrayList<StandardUnitCondition>();
        }
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
    }

	@Override
	public int countCouponSuBySuId(Long suId, Long storeId, Long companyId, Integer companyType, Long platformId) {
		return standardUnitReadDAO.countCouponSuBySuId(suId, storeId, companyId, companyType, platformId);
	}

	@Override
	public int countCouponSuBySuCombinationId(Long suCombinationId, Long storeId, Long companyId, Integer companyType, Long platformId) {
		return standardUnitReadDAO.countCouponSuBySuCombinationId(suCombinationId, storeId, companyId, companyType, platformId);
	}

	@Override
	public List<Map<String, Object>> findSpuInfo(List<Long> suIdList) {
		return standardUnitReadDAO.findSpuInfo(suIdList);
	}

	@Override
	public List<Map<String, Object>> findPictureInfo(List<Long> suIdList) {
		return standardUnitReadDAO.findPictureInfo(suIdList);
	}

    @Override
    public Long findLastId() {
        return standardUnitReadDAO.findLastId();
    }

    @Override
    public Integer findStandardUnitCount() {
        return standardUnitReadDAO.findStandardUnitCount();
    }

    @Override
    public List<StandardUnitPO> findStandardUnitAllByPage(Integer i, Integer pageSize) {
        return standardUnitReadDAO.findStandardUnitAllByPage( i, pageSize);
    }

    /**
     * 根据类目节点id查询su列表信息
     * @param queryId
     * @param saleWay
     * @param page  @return
     * @param buyType
     */
    @Override
    public PageResult<StandardUnitCondition> standardUnitByCategoryTreeNodeId(
            Integer saleWay, Long categoryTreeNodeId,
            Long platformId,
            BigDecimal userBalance,
            Long clientId, Long companyId,Long enterpriseId,
            Integer companyType,
            Long storeId,
            Pagination page,
            Integer buyType,String keyWord){
        //根据前台类目节点id查询后台类目id信息
        List<Long> categoryTreeNodeIdList = categoryTreeNodeCategoryReadDAO.findCategoryIdsByCategoryTreeNodeId(categoryTreeNodeId);
        StandardUnitPO standardUnitPO = new StandardUnitPO();
        //判断后台类目树是否是京东类目树
        standardUnitPO.setPlatformId(platformId);
        standardUnitPO.setClientId(clientId);
        standardUnitPO.setCompanyId(companyId);
        standardUnitPO.setSalePrice(userBalance);
        standardUnitPO.setStoreId(storeId);
        standardUnitPO.setSaleWay(saleWay);
        if (buyType != null && buyType == 0 ) {
            standardUnitPO.setBuyType(null);
        }else {
            standardUnitPO.setBuyType(buyType);
        }

        return standardUnitByFunctionModuleId(null,standardUnitPO, categoryTreeNodeIdList, companyType, page,enterpriseId,categoryTreeNodeId,keyWord);
    }



    public PageResult<StandardUnitCondition> standardUnitByFunctionModuleId(Integer couponType, StandardUnitPO po, List<Long> categoryTreeNodeIdList, Integer companyType, Pagination page,Long enterpriseId,Long categoryTreeNodeId,String keyWord) {
        PageResult<StandardUnitCondition> pageResult = getPageResult(page);
        //如果后台类目节点为空直接返回
        if (EmptyUtil.isEmpty(categoryTreeNodeIdList)) {
            return pageResult;
        }
        //构建需要的查询参数
        ChannelProductSearchBean param = new ChannelProductSearchBean(couponType,po,categoryTreeNodeIdList,companyType,page);
        param.setEnterpriseId(enterpriseId);
        param.setFrontCategoryTreeNodeId(categoryTreeNodeId);
        param.setKeyWord(keyWord);
       // getStartQueryWhere(param,page.getPageNo());
        //路由可用渠道
        EnterpriseServiceRouteRequestDTO dto = new EnterpriseServiceRouteRequestDTO(enterpriseId.intValue(),ChannelServiceNameEnum.GOOD_LIST.getChannelServiceName(),ChannelServiceTypeEnum.REQ.getChannelServiceType());
        EnterpriseServiceRouteResponseDTO responseDTO = channelServiceConfigClient.routeService(dto);
        //没有可用渠道
        if(null ==responseDTO || CollectionUtils.isEmpty(responseDTO.getList())){
            return pageResult;
        }
        setDefaultRedisPage(param);

        List<EnterpriseChannelServiceDTO> list = responseDTO.getList();
        logger.info("企业:{},路由:{}个渠道,第几页:{}",enterpriseId,list.size(),page.getPageNo());
        int channelQueryNum = 0;
        boolean isLastChannel = false;
        for (EnterpriseChannelServiceDTO channelServiceDTO : list) {
            param.setChannelCode(channelServiceDTO.getChannelCode());
            param.setChannelServiceDTO(channelServiceDTO);
            PageResult<StandardUnitCondition> rt = getStandardUnitConditionPageResult(param, channelServiceDTO,pageResult);
            //logger.info("渠道:{},请求结果:{}", channelServiceDTO.getChannelCode(),JsonUtils.objectToJson(rt));
            if(channelQueryNum == list.size() -1){
                isLastChannel = true;
            }
            if(!isLastChannel && Objects.nonNull(rt)){
                //只有将totalSize的值改变大一点，isFinished才会为false
                rt.setTotalSize(rt.getPageSize()*rt.getPageNo()+100);
            }
            channelQueryNum++;
            //setEndQueryWhere(param,rt);
            //若是结果不为空就返回，否则继续下一次循环
            if(Objects.nonNull(rt) && !CollectionUtils.isEmpty(rt.getList())){
                return rt;
            }
        }
        //若是策略中每一个具体的都未查询到结果，返回上方初始化的结果
        return pageResult;
    }

    private PageResult<StandardUnitCondition> getStandardUnitConditionPageResult(ChannelProductSearchBean param, EnterpriseChannelServiceDTO channelServiceDTO,PageResult<StandardUnitCondition> pageResult) {
        SearchProductStrategy searchProduct = productFactory.getSearchProductStrategy(ProductChannelCodeEnum.REMOTE.getCode());
        //为了不浪费之前的已有的代码，比如京东渠道和蛋糕叔叔
        if(productFactory.checkHasSupportSearchProduct(channelServiceDTO.getChannelCode())){
            searchProduct = productFactory.getSearchProductStrategy(channelServiceDTO.getChannelCode());
        }
        //是否下一步查询
        //searchProduct.hasSearchNext(param);
        if(!searchProduct.hasSearchNext(param)){
            PageResult<StandardUnitCondition> nullPageResult = new PageResult<StandardUnitCondition>();
            nullPageResult.setList(new ArrayList<>());
            nullPageResult.setTotalSize(0);
            nullPageResult.setPageNo(pageResult.getPageNo());
            nullPageResult.setPageSize(pageResult.getPageSize());
            return nullPageResult;
        }
        //具体策略中去执行查询
        return searchProduct.searchChannelProduct(param);
    }



    private Long getEnterpriseId() {
        if(RuntimeContext.cacheUser()==null || (RuntimeContext.cacheUser().isNotPlatformUser() && RuntimeContext.cacheUser().getEnterpriseId()==null)) {
            if(RuntimeContext.cacheUser().getCompanyId()!=null) {
                CompanyDTO company = companyClient.findCompanyById(RuntimeContext.cacheUser().getCompanyId());
                if(company.getEnterpriseId()!=null) {
                    logger.info("用户："+RuntimeContext.cacheUser().getLoginName()+"  登录公司id:"+RuntimeContext.cacheUser().getCompanyId().longValue()+" 代理id为空并填充成功");
                    RuntimeContext.cacheUser().setEnterpriseId(company.getEnterpriseId());
                }
            }
        }
        return RuntimeContext.cacheUser().getEnterpriseId();
    }

    private void setDefaultRedisPage(ChannelProductSearchBean param){
        CacheUser cacheUser =  RuntimeContext.cacheUser();
        String redisKey = String.valueOf(param.getFrontCategoryTreeNodeId());
        if(cacheUser!=null){
            redisKey = String.valueOf(cacheUser.getId())+String.valueOf(param.getFrontCategoryTreeNodeId());
        }
        redisKey = ProductRedisKeyEnum.getRedisKeyString(redisKey,ProductRedisKeyEnum.USER_KEY_WORD_CAT_KEY);
        param.setSearchRedisKey(redisKey);
        Pagination page =  param.getPage();
        if(page.getPageNo() <=1){
            jedisUtil.del(redisKey);
        }
    }
}
