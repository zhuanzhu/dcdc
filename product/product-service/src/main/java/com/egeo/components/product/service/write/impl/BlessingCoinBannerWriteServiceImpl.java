package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.write.BlessingCoinBannerWriteService;
import com.egeo.components.product.manage.read.BlessingCoinBannerCompanyReadManage;
import com.egeo.components.product.manage.write.BlessingCoinBannerCompanyWriteManage;
import com.egeo.components.product.manage.write.BlessingCoinBannerWriteManage;
import com.egeo.components.product.converter.BlessingCoinBannerConverter;
import com.egeo.components.product.dto.BlessingCoinBannerDTO;
import com.egeo.components.product.po.BlessingCoinBannerCompanyPO;
import com.egeo.components.product.po.BlessingCoinBannerPO;

import com.egeo.utils.EmptyUtil;

@Service("blessingCoinBannerWriteService")
public class BlessingCoinBannerWriteServiceImpl  implements BlessingCoinBannerWriteService {
	@Autowired
	private BlessingCoinBannerWriteManage blessingCoinBannerWriteManage;

	@Autowired
	private BlessingCoinBannerCompanyWriteManage blessingCoinBannerCompanyWriteManage;

	@Autowired
	private BlessingCoinBannerCompanyReadManage blessingCoinBannerCompanyReadManage;

	@Override
	public Long insertBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto, List<Long> companyIds) {
		BlessingCoinBannerPO po = BlessingCoinBannerConverter.toPO(dto);
		Long rt = blessingCoinBannerWriteManage.insertBlessingCoinBannerWithTx(po);
		if (EmptyUtil.isNotEmpty(rt)) {
			// 如果添加成功后添加轮播图关系信息
			for (Long long1 : companyIds) {
				BlessingCoinBannerCompanyPO blessingCoinBannerCompanyPO = new BlessingCoinBannerCompanyPO();
				blessingCoinBannerCompanyPO.setBlessingCoinBannerId(rt);
				blessingCoinBannerCompanyPO.setCompanyId(long1);
				blessingCoinBannerCompanyWriteManage.insertBlessingCoinBannerCompanyWithTx(blessingCoinBannerCompanyPO);
			}

		}
		return rt;
	}

	@Override
	public int updateBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto, List<Long> companyIds) {

		BlessingCoinBannerPO po = BlessingCoinBannerConverter.toPO(dto);
		int rt = blessingCoinBannerWriteManage.updateBlessingCoinBannerWithTx(po);
		if (rt != 0) {
			// 根据轮播图id查询轮播图公司关系信息
			BlessingCoinBannerCompanyPO blessingCoinBannerCompanyPO = new BlessingCoinBannerCompanyPO();
			blessingCoinBannerCompanyPO.setBlessingCoinBannerId(dto.getId());
			List<BlessingCoinBannerCompanyPO> blessingCoinBannerCompanyList = blessingCoinBannerCompanyReadManage
					.findBlessingCoinBannerCompanyAll(blessingCoinBannerCompanyPO);
			//拼接公司id集合
			List<Long> companyList = new ArrayList<>();
			for (Long companyId : companyIds) {
				for (BlessingCoinBannerCompanyPO blessingCoinBannerCompanyPO2 : blessingCoinBannerCompanyList) {
					//如果有放入公司id集合中
					if (blessingCoinBannerCompanyPO2.getCompanyId().equals(companyId)) {
						companyList.add(companyId);
						break;
					}
				}

			}
			
			//根据公司id集合和轮播图id删除轮播图公司集合删除非公司集合中的关系
			blessingCoinBannerCompanyWriteManage.delByBlessingCoinBannerIdCompanyIds(dto.getId(),companyList);
			
			//添加不存在关系表中的公司关系
			for (Long companyId : companyIds) {
				boolean isSave = true;
				for (BlessingCoinBannerCompanyPO blessingCoinBannerCompanyPO2 : blessingCoinBannerCompanyList) {
					//如果有就不添加
					if (blessingCoinBannerCompanyPO2.getCompanyId().equals(companyId)) {
						isSave = false;
						break;
					}
				}
				//添加轮播图关系信息
				if(isSave){
					BlessingCoinBannerCompanyPO blessingCoinBannerCompany = new BlessingCoinBannerCompanyPO();
					blessingCoinBannerCompany.setBlessingCoinBannerId(dto.getId());
					blessingCoinBannerCompany.setCompanyId(companyId);
					blessingCoinBannerCompanyWriteManage.insertBlessingCoinBannerCompanyWithTx(blessingCoinBannerCompany);
				}

			}
		}
		return rt;
	}

	@Override
	public int deleteBlessingCoinBannerWithTx(BlessingCoinBannerDTO dto) {
		BlessingCoinBannerPO po = BlessingCoinBannerConverter.toPO(dto);
		int rt = blessingCoinBannerWriteManage.deleteBlessingCoinBannerWithTx(po);
		return rt;
	}
}
