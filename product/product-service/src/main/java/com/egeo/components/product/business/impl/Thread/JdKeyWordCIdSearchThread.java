package com.egeo.components.product.business.impl.Thread;

import com.egeo.components.product.bean.JdKeyWordCIdSearchResultBean;
import com.egeo.components.product.business.JdProductManage;
import com.egeo.components.product.dto.JDProductSearchDTO;
import com.egeo.components.product.dto.JdEnterpriseConfigDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.JdSearchHitResultDTO;
import com.egeo.components.utils.BeanUtil;
import com.egeo.orm.PageResult;
import com.egeo.utils.SpringContextTool;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/28 17:30
 * @Version V1.0
 **/
public class JdKeyWordCIdSearchThread implements Callable<JdKeyWordCIdSearchResultBean> {

    private JDProductSearchDTO oldSearch;

    private JDProductSearchDTO newSearch;

    private List<String> cid1;

    private List<JdProductDTO> rslt = new ArrayList<>();

    private List<JdEnterpriseConfigDTO> jdEnterpriseConfigs;

    public JdKeyWordCIdSearchThread() {
    }


    public JdKeyWordCIdSearchThread(JDProductSearchDTO search, List<String> cid1,List<JdEnterpriseConfigDTO> jdEnterpriseConfigs) {
        this.oldSearch = search;
        this.newSearch = BeanUtil.beanCopy(search,JDProductSearchDTO.class);
        this.cid1 = cid1;
        this.jdEnterpriseConfigs = jdEnterpriseConfigs;
    }


    @Override
    public JdKeyWordCIdSearchResultBean call() throws Exception {
        rslt = new ArrayList<>();
        JdKeyWordCIdSearchResultBean rt = new JdKeyWordCIdSearchResultBean();
        rt.setPageSize(newSearch.getPageSize());
        rt.setPageNo(newSearch.getPageIndex());
        rt.setMaxTotalSize(0);
        if(CollectionUtils.isEmpty(cid1)){
            return rt;
        }
        Integer maxTotalSize = 0;
        Integer pageNo = 0;
        Integer pageSize =0;
        boolean checkResultMapValue = false;
        JdProductManage jdProductManage = SpringContextTool.getBean(JdProductManage.class);
        for (String s : cid1) {
            newSearch.setCid1(s);
            PageResult<JdSearchHitResultDTO> rtPage = jdProductManage.searchJdHitResult(newSearch,rslt,jdEnterpriseConfigs);
            if(Objects.nonNull(rtPage) && Objects.nonNull(rtPage.getTotalSize())){
                //返回最大totalSize
                if(rtPage.getTotalSize()>maxTotalSize){
                    maxTotalSize = rtPage.getTotalSize();
                }
                pageNo = rtPage.getPageNo();
                pageSize = rtPage.getPageSize();
            }
            if(!CollectionUtils.isEmpty(newSearch.getCheckResultMap())){
                Boolean checkRt = (Boolean)newSearch.getCheckResultMap().get(newSearch.getKeyword());
                if(checkRt){
                    checkResultMapValue = true;
                }
            }
        }
        if(!CollectionUtils.isEmpty(rslt)){
            rt.setRslt(rslt);
        }
        rt.setMaxTotalSize(maxTotalSize);
        if(pageSize!=null && pageSize.intValue()>0){
            rt.setPageSize(pageSize);
            rt.setPageNo(pageNo);
        }
        if(checkResultMapValue){
            oldSearch.getCheckResultMap().put(oldSearch.getKeyword(),true) ;
        }
        return rt;
    }




}
