package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.ServerApp;
import com.egeo.components.product.converter.SuSerachRuleConverter;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.SuSerachRuleDTO;
import com.egeo.components.product.manage.read.CategoryReadManage;
import com.egeo.components.product.manage.read.StandardProductUnitAttValueReadManage;
import com.egeo.components.product.manage.read.StandardUnitClientReadManage;
import com.egeo.components.product.manage.read.StandardUnitCompanyReadManage;
import com.egeo.components.product.manage.read.StandardUnitPictureReadManage;
import com.egeo.components.product.manage.read.StandardUnitReadManage;
import com.egeo.components.product.manage.read.StandardUnitTagReadManage;
import com.egeo.components.product.manage.read.SuSerachKeywordReadManage;
import com.egeo.components.product.manage.read.SuSerachRuleReadManage;
import com.egeo.components.product.manage.write.SuSerachRuleWriteManage;
import com.egeo.components.product.po.StandardUnitClientPO;
import com.egeo.components.product.po.StandardUnitCompanyPO;
import com.egeo.components.product.po.SuSerachKeywordPO;
import com.egeo.components.product.po.SuSerachRulePO;
import com.egeo.components.product.service.write.SuSerachRuleWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.components.product.service.write.impl.Thread.UpdateListFactory;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("suSerachRuleWriteService")
public class SuSerachRuleWriteServiceImpl  implements SuSerachRuleWriteService {
	private static final XLogger logger = XLogger.getLogger(SuSerachRuleWriteServiceImpl.class);
	@Autowired
	private SuSerachRuleWriteManage suSerachRuleWriteManage;
	
	@Autowired
	private StandardUnitReadManage standardUnitReadManage;
	@Autowired
	private SuSerachRuleReadManage suSerachRuleReadManage;
	@Autowired
	private StandardUnitPictureReadManage standardUnitPictureReadManage;
	@Autowired
	private SuSerachKeywordReadManage suSerachKeywordReadManage;
	
	@Autowired
	private StandardProductUnitAttValueReadManage standardProductUnitAttValueReadManage;
	
	@Autowired
	private StandardUnitTagReadManage standardUnitTagReadManage;
	@Autowired
	private StandardUnitCompanyReadManage standardUnitCompanyReadManage;
	@Autowired
	private StandardUnitClientReadManage standardUnitClientReadManage;

	@Autowired
	private CategoryReadManage categoryReadManage;
	@Resource
	private JedisUtil jedisUtil;
	private Executor executor = Executors.newFixedThreadPool(10);

	@Override
	public Long insertSuSerachRuleWithTx(SuSerachRuleDTO dto) {
		SuSerachRulePO po = SuSerachRuleConverter.toPO(dto);
		Long rt = suSerachRuleWriteManage.insertSuSerachRuleWithTx(po);		
		return rt;
	}

	@Override
	public int updateSuSerachRuleWithTx(SuSerachRuleDTO dto) {
		SuSerachRulePO po = SuSerachRuleConverter.toPO(dto);
		int rt = suSerachRuleWriteManage.updateSuSerachRuleWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSuSerachRuleWithTx(SuSerachRuleDTO dto) {
		SuSerachRulePO po = SuSerachRuleConverter.toPO(dto);
		int rt = suSerachRuleWriteManage.deleteSuSerachRuleWithTx(po);		
		return rt;
	}
	public void syncSaveSuSerachRule(List<StandardUnitDTO> suList, int i){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					sycSuSearch(suList);
					logger.error("更新完成第"+(i+1)+"批数据");

				} catch (Exception e) {
					logger.info("刷新su商品搜索规则数据信息失败:" + e.getMessage());
				}
			}
		}).start();
	}
	private void sycSuSearch(List<StandardUnitDTO> suList){
		List<SuSerachRulePO> addList = new ArrayList<>();
		List<SuSerachRulePO> updateList = new ArrayList<>();
		logger.info("test1");
		for(StandardUnitDTO standardUnitDTO:suList){
			int i = 0;
			logger.info("test2");

			SuSerachRulePO suSerachRulePO = new SuSerachRulePO();
			suSerachRulePO.setStandardUnitId(standardUnitDTO.getId());
			suSerachRulePO.setStandardUnitName(standardUnitDTO.getName());
			//京东不存在关键字
			if(standardUnitDTO.getMerchantId()!=6){
				logger.info("test3");

				// 拼接su关键词信息
				String standardUnitKeyword = jointStandardUnitKeyword(standardUnitDTO);
				logger.info("test4");

				suSerachRulePO.setStandardUnitKeyword(standardUnitKeyword);

				// 根据suId查询su标签信息
				List<String> standardUnitTags = standardUnitTagReadManage.findStandardUnitTagBySuId(standardUnitDTO.getId());
				logger.info("test5");

				if(EmptyUtil.isNotEmpty(standardUnitTags)){


					StringBuilder standardUnitTag = new StringBuilder();
					for (String suTagName : standardUnitTags) {
						standardUnitTag.append(suTagName);
						standardUnitTag.append(",");
					}
					if(EmptyUtil.isNotEmpty(standardUnitTag.toString()))
						suSerachRulePO.setStandardUnitTag(standardUnitTag.substring(0, standardUnitTag.length()-1));
				}
			}


			// 根据suId查询su所属类目
			logger.info("test6");
			List<String> categoryNames = categoryReadManage.findCategoryNameBySuId(standardUnitDTO.getId());
			logger.info("test7");
			if(EmptyUtil.isNotEmpty(categoryNames)){
				StringBuilder standardUnitCategory = new StringBuilder();
				for (String categoryName : categoryNames) {
					standardUnitCategory.append(categoryName);
					standardUnitCategory.append(",");
				}
				if(EmptyUtil.isNotEmpty(standardUnitCategory.toString()))
					suSerachRulePO.setStandardUnitCategory(standardUnitCategory.substring(0, standardUnitCategory.length()-1));
			}

			// 根据suId查询是否存在su搜索规则

			Integer count = suSerachRuleReadManage.findSuSerachRuleListSize(standardUnitDTO.getId());
			logger.info("test8");
			//公司类型/id
			StandardUnitCompanyPO standardUnitCompanyPO = new StandardUnitCompanyPO();
			standardUnitCompanyPO.setStandardUnitId(standardUnitDTO.getId());
			List<StandardUnitCompanyPO> standardUnitCompanyAll = standardUnitCompanyReadManage.findStandardUnitCompanyAll(standardUnitCompanyPO);
			logger.info("test9");
			StringBuffer companyIdString=new StringBuffer() ;
			StringBuffer companyTypeString=new StringBuffer();
			for(StandardUnitCompanyPO companyPO:standardUnitCompanyAll){
				companyIdString.append(companyPO.getCompanyId()+"/");
				companyTypeString.append(companyPO.getCompanyType()+"/");
			}
			//client
			StandardUnitClientPO standardUnitClientPO = new StandardUnitClientPO();
			standardUnitClientPO.setStandardUnitId(standardUnitDTO.getId());
			List<StandardUnitClientPO> standardUnitClientAll = standardUnitClientReadManage.findStandardUnitClientAll(standardUnitClientPO);
			logger.info("test10");
			StringBuffer clientString = new StringBuffer();
			for(StandardUnitClientPO clientPO:standardUnitClientAll){
				clientString.append(clientPO.getClientId()+"/");
			}
			//图片
			String url=standardUnitPictureReadManage.findPictureUrlBySUId(standardUnitDTO.getId());
			logger.info("test11");
			suSerachRulePO.setPictureUrl(url);
			suSerachRulePO.setMerchantId(standardUnitDTO.getMerchantId());
			suSerachRulePO.setCompanyId(companyIdString.toString());
			suSerachRulePO.setCompanyType(companyTypeString.toString());
			suSerachRulePO.setClientId(clientString.toString());

			// 没有新增
			if(count==0){
				suSerachRuleWriteManage.insertSuSerachRuleWithTx(suSerachRulePO);
				logger.info("test12");
			}
			// 否则根据suId更新数据
			else{
				suSerachRuleWriteManage.updateSuSerachRuleBySuIdWithTx(suSerachRulePO);
				logger.info("test13");
			}
		}
		/*if(EmptyUtil.isNotEmpty(addList)){
			logger.error("开始插入");
			suSerachRuleWriteManage.addSuSerachRuleList(addList);
			logger.error("完成插入");
		}
		if(EmptyUtil.isNotEmpty(updateList)){
			logger.error("开始更新");
			suSerachRuleWriteManage.updateSuSerachRuleList(updateList);
			logger.error("完成更新");

		}*/

	}

	@Override
	public void saveSuSerachRule(List<Long> suIdList, List<String> nameList) {
		Integer SIZE=10000;

		List<SuSerachRulePO> res = new ArrayList<>();
		for(int i=0;i<suIdList.size();i++){
			SuSerachRulePO serachRulePO = new SuSerachRulePO();
			serachRulePO.setStandardUnitId(suIdList.get(i));
			serachRulePO.setStandardUnitName(nameList.get(i));
			res.add(serachRulePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理SuSerachRule保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("SuSerachRule",24*60*60,page);
		}catch (Exception e){
			logger.info("[SuSerachRule]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<SuSerachRulePO> list = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * SIZE; j < res.size(); j++) {
					list.add(res.get(j));
				}
			} else {
				for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
					list.add(res.get(j));
				}
			}
			SaveListFactory saveListFactory = new SaveListFactory();
			saveListFactory.setLatch(countDownLatch);
			saveListFactory.setSaveType("SuSerachRule");
			saveListFactory.setSuSerachRuleList(list);
			saveListFactory.setSuSerachRuleWriteManage(suSerachRuleWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();

	}

	@Override
	public void updateSuSerachRuleList(List<Long> suIdList, List<String> nameList) {
		Integer SIZE=10000;

		List<SuSerachRulePO> res = new ArrayList<>();
		for(int i=0;i<suIdList.size();i++){
			SuSerachRulePO serachRulePO = new SuSerachRulePO();
			serachRulePO.setStandardUnitId(suIdList.get(i));
			serachRulePO.setStandardUnitName(nameList.get(i));
			res.add(serachRulePO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理SuSerachRule保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;

		for(int i=0;i<page;i++) {
			List<SuSerachRulePO> list = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * SIZE; j < res.size(); j++) {
					list.add(res.get(j));
				}
			} else {
				for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
					list.add(res.get(j));
				}
			}
			UpdateListFactory factory = new UpdateListFactory();
			factory.setLatch(countDownLatch);
			factory.setSaveType("SuSerachRule");
			factory.setSuSerachRulePOList(list);
			factory.setSuSerachRuleWriteManage(suSerachRuleWriteManage);
			factory.setJedisUtil(jedisUtil);

			executor.execute(factory);
		}
		countDownLatch.countDown();
	}

	/**
	 * 拼接su关键词信息
	 * @param standardUnitDTO
	 * @return
	 */
	private String jointStandardUnitKeyword(StandardUnitDTO standardUnitDTO) {
		// su关键词信息
		String standardUnitKeyword = null;
		if(EmptyUtil.isNotEmpty(standardUnitDTO.getIsSpuKeyword())){
			// 根据spuid查询spu关键字
			List<String> spuKeywords = standardProductUnitAttValueReadManage.findSpuKeywordByStandardProductUnitId(standardUnitDTO.getStandardProductUnitId());
			
			// 查询su关键词信息
			SuSerachKeywordPO suSerachKeywordPO = new SuSerachKeywordPO();
			suSerachKeywordPO.setStandardUnitId(standardUnitDTO.getId());
			List<SuSerachKeywordPO> list = suSerachKeywordReadManage.findSuSerachKeywordAll(suSerachKeywordPO);
			
			//拼接字符
			StringBuilder standardUnitKeyword_ = new StringBuilder();
			// 是否引用关键词: 1.不使用搜索关键词、2.引用产品关键词并添加自定义关键词、3.仅引用产品关键词、4.仅添加自定义关键词
			switch (standardUnitDTO.getIsSpuKeyword()) {
			case 1:
				break;
				
			case 2:
				// 拼接spu关键词
				if(EmptyUtil.isNotEmpty(spuKeywords)){
					for (String spuKeyword : spuKeywords) {
						standardUnitKeyword_.append(spuKeyword);
						standardUnitKeyword_.append(",");
					}
				}
				// 拼接su关键词
				if(EmptyUtil.isNotEmpty(list)){
					for (SuSerachKeywordPO suSerachKeywordPO2 : list) {
						standardUnitKeyword_.append(suSerachKeywordPO2.getKeyword());
						standardUnitKeyword_.append(",");
					}
				}
				break;
				
			case 3:
				// 拼接spu关键词
				if(EmptyUtil.isNotEmpty(spuKeywords)){
					for (String spuKeyword : spuKeywords) {
						standardUnitKeyword_.append(spuKeyword);
						standardUnitKeyword_.append(",");
					}
				}
				break;
				
			case 4:
				// 拼接su关键词
				if(EmptyUtil.isNotEmpty(list)){
					for (SuSerachKeywordPO suSerachKeywordPO2 : list) {
						standardUnitKeyword_.append(suSerachKeywordPO2.getKeyword());
						standardUnitKeyword_.append(",");
					}
				}
				break;

			default:
				throw new BusinessException("未定义关键词类型,suId:" + standardUnitDTO.getId());
			}
			// 判断拼接是否为空，不为空赋值
			if(EmptyUtil.isNotEmpty(standardUnitKeyword_.toString()))
				standardUnitKeyword = standardUnitKeyword_.substring(0, standardUnitKeyword_.length()-1);
		}
		return standardUnitKeyword;
	}
}
	