package com.egeo.components.product.strategy.impl;

import com.alibaba.fastjson.JSON;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.product.bean.ChannelProductSearchBean;
import com.egeo.components.product.bean.KeyWordSearchCachePageBean;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.condition.CategoryAndJdCondition;
import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dao.read.CategoryReadDAO;
import com.egeo.components.product.dto.JDProductSearchDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.strategy.SearchProductStrategy;
import com.egeo.components.user.client.UserClient;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.JsonResult;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/30 2:06
 * @Version V1.0
 **/
@Service("jDProductStrategyImpl")
public class JDProductStrategyImpl extends ProductStrategyBase implements SearchProductStrategy {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryReadDAO categoryReadDAO;

    @Resource(name="jdProduct")
    private JdProductManage jdProductManage;

    @Resource
    private JedisUtil jedisUtil;

    @Autowired
    private UserClient userClient;

    private final static int MIN_SIZE=10;

    private final static int MAX_SEARCH_TIME=5;

    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.JD.getCode();
    }

    @Override
    public boolean hasSearchNext(ChannelProductSearchBean param) {
        boolean checkRt = checkCatIsLimit(param);
        //不在配置允许查询的前端类目中
        if(!checkRt){
            return false;
        }
        //查看是否有京东后台类目
        List<CategoryAndJdCondition> categorys = categoryReadDAO.findCategoryByCategoryTreeNodes(param.getCategoryTreeNodeIdList());
        addCategorys(categorys,param);
        if(CollectionUtils.isEmpty(param.getCategorys())){
            return false;
        }
        return true;
    }

    private boolean checkCatIsLimit(ChannelProductSearchBean param){
        List<Long>  categoryTreeNodeIdList = param.getCategoryTreeNodeIdList();
        if(CollectionUtils.isEmpty(categoryTreeNodeIdList)){
            return false;
        }
        logger.info("需要查询的前端类目:{}",JSON.toJSONString(categoryTreeNodeIdList));
        List<String> cid1s=new ArrayList<>();
        if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null) {
            if(RuntimeContext.cacheUser().getCompanyId()!=null) {
                List<CompanyConfigDTO> configs = userClient.findUserCompanyConfigs(RuntimeContext.cacheUser().getId());
                for(CompanyConfigDTO one : configs) {
                    if(one.getKey().equalsIgnoreCase("jd.front.category.cats.only")) {
                        if (EmptyUtil.isNotEmpty(one.getValue())){
                            cid1s=Arrays.asList(one.getValue().split(","));
                        }
                        logger.info("设置京东前端类目限制："+one.getValue());
                    }
                }
            }
        }

        if(CollectionUtils.isEmpty(cid1s)){
            return false;
        }

        for (String cid1 : cid1s) {
            for (Long aLong : categoryTreeNodeIdList) {
                if(Objects.equals(cid1,String.valueOf(aLong))){
                    logger.info("前端类目id:{}存在设置京东前端类目限制.........",cid1);
                    return true;
                }
            }
        }
        logger.info("需要查询的前端类目:{}，不在设置京东前端类目限制范围内",JSON.toJSONString(categoryTreeNodeIdList));
        return false;
    }

    private void addCategorys(List<CategoryAndJdCondition> categorys,ChannelProductSearchBean param){
        if(CollectionUtils.isEmpty(categorys)){
            return ;
        }
        for(CategoryAndJdCondition one : categorys) {
            if(one.getJdCategory()!=null && one.getJdCategory()>0) {
                param.getCategorys().add(one.getJdCategory());
            }
        }
    }

    @Override
    public PageResult<StandardUnitCondition> searchChannelProduct(ChannelProductSearchBean param) {
        PageResult<StandardUnitCondition> pageResult = new PageResult<StandardUnitCondition>();
        int cnt = param.getCnt();
        Pagination page = param.getPage();
        String keyWord = param.getKeyWord();
        List<Long> jdCategorys = param.getCategorys();
        setDefaultPageResult(pageResult, page);
        if(CollectionUtils.isEmpty(jdCategorys)){
            return pageResult;
        }
        int currPage = getCurrPage(getProductCode(),jedisUtil,param);
        List<StandardUnitCondition> list = new ArrayList<StandardUnitCondition>();
        /*int platformPage = (cnt==0?0:((cnt/page.getPageSize())+1));
        int jdPage = page.getPageNo()-platformPage;*/
        int jdPage =currPage;
        Set set = new HashSet();
        JDProductSearchDTO search = getJdProductSearchDTO(page, jdCategorys, jdPage,keyWord);
        /*JsonResult<PageResult<JdProductDTO>> jdRslt = jdProductManage.searchOfCategoryLevel2(search);
        if(Objects.isNull(jdRslt) || Objects.isNull(jdRslt.getData()) || CollectionUtils.isEmpty(jdRslt.getData().getList())){
            return pageResult;
        }
        for(JdProductDTO oneJd:jdRslt.getData().getList()) {
            if(set.contains(oneJd.getId())){
                continue;
            }
            set.add(oneJd.getId());
            StandardUnitCondition tmp = new StandardUnitCondition();
            tmp.fromJdProduct(oneJd);
            list.add(tmp);
        }
        if(set.size() >0){
            set.clear();
        }
         pageResult.setTotalSize((((cnt/page.getPageSize())+1)*page.getPageSize())+jdRslt.getData().getTotalSize());
        //下一个渠道分页依据
        param.setCnt((((cnt/page.getPageSize())+1)*page.getPageSize())+jdRslt.getData().getTotalSize());*/

        //缓存本次查询的分页数
        KeyWordSearchCachePageBean keyWordSearchCachePageBean = new KeyWordSearchCachePageBean();
        keyWordSearchCachePageBean.setCurrPageNo(currPage);
        keyWordSearchCachePageBean.setChannelCode(getProductCode());
        keyWordSearchCachePageBean.setTotalCount(0);
        keyWordSearchCachePageBean.setPageSize(page.getPageSize());
        searchJdProductRslt(list,set,search,currPage,currPage,param,keyWordSearchCachePageBean);

        param.setHasRecalculatePageSize(false);

        pageResult.setList(list);
        if(set.size() >0){
            set.clear();
        }

        pageResult.setTotalSize((((cnt/page.getPageSize())+1)*page.getPageSize())+keyWordSearchCachePageBean.getTotalCount());
        param.setCnt((((cnt/page.getPageSize())+1)*page.getPageSize())+keyWordSearchCachePageBean.getTotalCount());
        return pageResult;


    }

    private void searchJdProductRslt(List<StandardUnitCondition> list,Set set,JDProductSearchDTO search,int currPage,final int startPage,ChannelProductSearchBean param,KeyWordSearchCachePageBean keyWordSearchCachePageBean) {
        int searchTimes = currPage-startPage+1;
        logger.info("类目节点:{}轮询第{}次开始",search.getCid2(),searchTimes);
        if(searchTimes >=MAX_SEARCH_TIME){
            logger.info("类目节点：{}无数据时,轮询第{}次,达到最大轮询次数{}次，直接返回",search.getCid2(),searchTimes,MAX_SEARCH_TIME);
            return ;
        }

        if(list.size() >= MIN_SIZE){
            logger.info("类目节点：{}存在数据数:{}数据时,轮询第{}次,达到返回的最小记录数{}条数据，直接返回",list.size(),search.getCid2(),searchTimes,MIN_SIZE);
            return;
        }
        Map<String,Object> checkMap = search.getCatCheckResultMap();
        if(checkMap == null){
            checkMap = new HashMap<>();
        }
        search.setPageIndex(currPage);
        checkMap.put(search.getCid2(),false);
        search.setCatCheckResultMap(checkMap);
        JsonResult<PageResult<JdProductDTO>> jdRslt = jdProductManage.searchOfCategoryLevel2(search);
        if(Objects.nonNull(jdRslt) && Objects.nonNull(jdRslt.getData()) && !CollectionUtils.isEmpty(jdRslt.getData().getList())){
            for(JdProductDTO oneJd:jdRslt.getData().getList()) {
                if(set.contains(oneJd.getId())){
                    continue;
                }
                set.add(oneJd.getId());
                StandardUnitCondition tmp = new StandardUnitCondition();
                tmp.fromJdProduct(oneJd);
                list.add(tmp);
            }
            keyWordSearchCachePageBean.setCurrPageNo(currPage);
            keyWordSearchCachePageBean.setTotalCount(jdRslt.getData().getTotalSize());
            saveRedisPage(keyWordSearchCachePageBean,jedisUtil, param);
            if(!CollectionUtils.isEmpty(search.getCatCheckResultMap()) && list.size() < MIN_SIZE){
                boolean hasExistsFilter = (Boolean)search.getCatCheckResultMap().get(search.getCid2());
                //没有发生过滤
                if(!hasExistsFilter){
                    logger.info("类目节点：{}无数据时没有发生过滤，直接返回,轮询第{}次本次查询结束",search.getCid2(),searchTimes);
                    return;
                }
                logger.info("类目节点：{}小于结果数:{}最小记录数:{}条数据时,从第{}页开始,轮询第{}次,CheckResultMap结果:{}",list.size(),MIN_SIZE,search.getCid2(),startPage,searchTimes, JSON.toJSONString(search.getCatCheckResultMap()));
                searchJdProductRslt(list,set,search,currPage+1,startPage,param,keyWordSearchCachePageBean);
            }
        }else if(jdRslt.getData()!=null && !CollectionUtils.isEmpty(search.getCatCheckResultMap()) && CollectionUtils.isEmpty(jdRslt.getData().getList())){
            logger.info("类目节点：{}无数据时,从第{}页开始,轮询第{}次,CheckResultMap结果:{}",search.getCid2(),startPage,searchTimes, JSON.toJSONString(search.getCatCheckResultMap()));
            keyWordSearchCachePageBean.setTotalCount(jdRslt.getData().getTotalSize());
            keyWordSearchCachePageBean.setCurrPageNo(currPage);
            saveRedisPage(keyWordSearchCachePageBean,jedisUtil, param);
            boolean hasExistsFilter = (Boolean)search.getCatCheckResultMap().get(search.getCid2());
            //没有发生过滤
            if(!hasExistsFilter){
                logger.info("类目节点：{}无数据时没有发生过滤，直接返回,轮询第{}次本次查询结束",search.getCid2(),searchTimes);
                return;
            }
            searchJdProductRslt(list,set,search,currPage+1,startPage,param,keyWordSearchCachePageBean);
        }
        logger.info("类目节点:{}轮询第{}次结束",search.getCid2(),searchTimes);
    }

    private JDProductSearchDTO getJdProductSearchDTO(Pagination page, List<Long> jdCategorys, int jdPage,String keyWord) {
        JDProductSearchDTO search = new JDProductSearchDTO();
        search.setPageIndex(jdPage);
        search.setPageSize(page.getPageSize());
        //排序方式
        setOrderBy(page, search);
        //类目
        search.setCid2(jdCategorys.get(0)+"");
        if(EmptyUtil.isNotEmpty(keyWord) && EmptyUtil.isNotBlank(keyWord)){
            search.setKeyword(keyWord);
        }
        return search;
    }

    private void setDefaultPageResult(PageResult<StandardUnitCondition> pageResult, Pagination page) {
        pageResult.setList(new ArrayList<>());
        pageResult.setTotalSize(0);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
    }

    private void setOrderBy(Pagination page, JDProductSearchDTO search) {
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
    }
}
