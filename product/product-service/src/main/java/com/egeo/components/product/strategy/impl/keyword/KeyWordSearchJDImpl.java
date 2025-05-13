package com.egeo.components.product.strategy.impl.keyword;

import com.alibaba.fastjson.JSON;
import com.egeo.components.product.bean.KeyWordSearchBean;
import com.egeo.components.product.bean.KeyWordSearchCachePageBean;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.converter.StandardUnitConverter;
import com.egeo.components.product.dto.JDProductSearchDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.strategy.KeyWordSearchStrategy;
import com.egeo.components.user.client.EnterpriseClient;
import com.egeo.components.user.dto.Enterprise;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/26 15:53
 * @Version V1.0
 **/
@Service("keyWordSearchJDImpl")
public class KeyWordSearchJDImpl  extends KeyWordSearchCommonBase implements KeyWordSearchStrategy {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EnterpriseClient enterpriseService;

    @Resource(name="jdProduct")
    private JdProductManage jdProductManage;

    @Resource
    private JedisUtil jedisUtil;

    private final static int MIN_SIZE=3;

    private final static int MAX_SEARCH_SIZE=5;

    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.JD.getCode();
    }

    @Override
    public PageResult<StandardUnitDTO> getProductByKeyWord(KeyWordSearchBean bean) {
        Pagination page = bean.getPage();
        String name = bean.getName();
        BigDecimal userBalance = bean.getUserBalance();
        //Long companyId = bean.getCompanyId();
        List<StandardUnitDTO> standardUnitDTOList = new ArrayList<>();
        PageResult<StandardUnitDTO> result = new PageResult<StandardUnitDTO>();
        JDProductSearchDTO search = new JDProductSearchDTO();
        int currPage = getCurrPage(getProductCode(),jedisUtil,bean);
        search.setKeyword(name);
        search.setPageIndex(currPage);
        search.setPageSize(page.getPageSize());
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
      /*  if(companyId!=null) {
            Enterprise enterprise = enterpriseService.findById(companyId.intValue());
        }*/
        if(userBalance!=null) {
            search.setPriceMax(userBalance.intValue());
        }
        jdSearch(bean, page, standardUnitDTOList, search, currPage,currPage);
        result.setList(standardUnitDTOList);
        result.setPageNo(page.getPageNo());
        result.setPageSize(page.getPageSize());
        return result;
    }

    private void jdSearch(KeyWordSearchBean bean, Pagination page, List<StandardUnitDTO> standardUnitDTOList, JDProductSearchDTO search, int currPage,final int startPage) {
        int searchTimes = currPage-startPage+1;
        logger.info("关键字:{}轮询第{}次开始",search.getKeyword(),searchTimes);
        if(searchTimes >=MAX_SEARCH_SIZE){
            logger.info("关键字：{}无数据时,轮询第{}次,达到最大轮询次数{}次，直接返回",search.getKeyword(),searchTimes,MAX_SEARCH_SIZE);
            return;
        }
        //达到返回的最小记录数
        if(standardUnitDTOList.size() >=MIN_SIZE){
            logger.info("关键字：{}无数据时,轮询第{}次,达到返回的最小记录数{}条数据，直接返回",search.getKeyword(),searchTimes,MIN_SIZE);
            return;
        }
        Map<String,Object> checkMap = new HashMap<>();
        checkMap.put(bean.getName(),false);
        search.setCheckResultMap(checkMap);
        search.setPageIndex(currPage);
        JsonResult<PageResult<JdProductDTO>> jdRslt = jdProductManage.search(search);
        //logger.info("关键字:{}轮询第{}次查询到结果:{}",search.getKeyword(),searchTimes,JSON.toJSONString(jdRslt));
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
            //缓存本次查询的分页数
            KeyWordSearchCachePageBean keyWordSearchCachePageBean = new KeyWordSearchCachePageBean();
            keyWordSearchCachePageBean.setCurrPageNo(currPage);
            keyWordSearchCachePageBean.setChannelCode(getProductCode());
            keyWordSearchCachePageBean.setTotalCount(jdRslt.getData().getTotalSize());
            keyWordSearchCachePageBean.setPageSize(page.getPageSize());
            saveRedisPage(keyWordSearchCachePageBean,jedisUtil, bean);
        }else if(jdRslt.getData()!=null && !CollectionUtils.isEmpty(search.getCheckResultMap()) && CollectionUtils.isEmpty(jdRslt.getData().getList())){
            logger.info("关键字：{}无数据时,从第{}页开始,轮询第{}次,CheckResultMap结果:{}",search.getKeyword(),startPage,searchTimes, JSON.toJSONString(search.getCheckResultMap()));
            boolean hasExistsFilter = (Boolean)search.getCheckResultMap().get(search.getKeyword());
            //没有发生过滤
            if(!hasExistsFilter){
                logger.info("关键字：{}无数据时没有发生过滤，直接返回",search.getKeyword());
                return;
            }
            KeyWordSearchCachePageBean keyWordSearchCachePageBean = new KeyWordSearchCachePageBean();
            keyWordSearchCachePageBean.setCurrPageNo(currPage);
            keyWordSearchCachePageBean.setChannelCode(getProductCode());
            keyWordSearchCachePageBean.setTotalCount(jdRslt.getData().getTotalSize());
            keyWordSearchCachePageBean.setPageSize(page.getPageSize());
            saveRedisPage(keyWordSearchCachePageBean,jedisUtil, bean);
            jdSearch(bean, page, standardUnitDTOList, search, currPage+1,startPage);
        }
        logger.info("关键字:{}轮询第{}次结束",search.getKeyword(),searchTimes);
    }
}
