package com.egeo.components.cms.service.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.write.BannerWriteService;
import com.egeo.components.cms.manage.write.BannerWriteManage;
import com.egeo.components.cms.converter.BannerConverter;
import com.egeo.components.cms.converter.LinkableButtonConverter;
import com.egeo.components.cms.converter.LinkableButtonPageConverter;
import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.po.BannerPO;
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.components.cms.po.LinkableButtonPagePO;


@Service("bannerWriteService")
public class BannerWriteServiceImpl  implements BannerWriteService {
	@Autowired
	private BannerWriteManage bannerWriteManage;

	@Override
	public Long insertBannerWithTx(BannerDTO dto) {
		BannerPO po = BannerConverter.toPO(dto);
		Long rt = bannerWriteManage.insertBannerWithTx(po);		
		return rt;
	}

	@Override
	public int updateBannerWithTx(BannerDTO dto) {
		BannerPO po = BannerConverter.toPO(dto);
		int rt = bannerWriteManage.updateBannerWithTx(po);		
		return rt;
	}

	@Override
	public int deleteBannerWithTx(BannerDTO dto) {
		BannerPO po = BannerConverter.toPO(dto);
		int rt = bannerWriteManage.deleteBannerWithTx(po);		
		return rt;
	}

	@Override
	public boolean saveBanner(Long bannerId, BannerDTO banner, LinkableButtonDTO lb,List<Long> companyIdList,List<LinkableButtonPageDTO> listLbp,String extParam) {
		BannerPO bannerPO=BannerConverter.toPO(banner);
		LinkableButtonPO lbPO=LinkableButtonConverter.toPO(lb);
		List<LinkableButtonPagePO> lbpPOList = new ArrayList<LinkableButtonPagePO>();
		if(listLbp != null && listLbp.size() > 0) {
			for (LinkableButtonPageDTO linkableButtonPageDTO : listLbp) {
				lbpPOList.add(LinkableButtonPageConverter.toPO(linkableButtonPageDTO));
			}
		}
					
		return bannerWriteManage.saveBannerWithTx(bannerId,bannerPO,lbPO,companyIdList,lbpPOList,extParam);
	}
}
	