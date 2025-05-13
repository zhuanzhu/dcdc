package com.egeo.components.product.service.write.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.MembershipUserConverter;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.product.manage.read.MembershipReadManage;
import com.egeo.components.product.manage.write.MembershipUserWriteManage;
import com.egeo.components.product.po.MembershipPO;
import com.egeo.components.product.po.MembershipUserPO;
import com.egeo.components.product.service.write.MembershipUserWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;

@Service("membershipUserWriteService")
public class MembershipUserWriteServiceImpl  implements MembershipUserWriteService {
	private static final XLogger logger = XLogger.getLogger(MembershipUserWriteServiceImpl.class);
	@Autowired
	private MembershipUserWriteManage membershipUserWriteManage;
	@Autowired
	private MembershipReadManage membershipReadManage;

	@Override
	public Long insertMembershipUserWithTx(MembershipUserDTO dto) {
		MembershipUserPO po = MembershipUserConverter.toPO(dto);
		Long rt = membershipUserWriteManage.insertMembershipUserWithTx(po);		
		return rt;
	}

	@Override
	public int updateMembershipUserWithTx(MembershipUserDTO dto) {
		MembershipUserPO po = MembershipUserConverter.toPO(dto);
		int rt = membershipUserWriteManage.updateMembershipUserWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMembershipUserWithTx(MembershipUserDTO dto) {
		MembershipUserPO po = MembershipUserConverter.toPO(dto);
		int rt = membershipUserWriteManage.deleteMembershipUserWithTx(po);		
		return rt;
	}

	@Override
	public Boolean updateMembershipUserWithTx(List<Long> membewrshipUserId) {
		return membershipUserWriteManage.updateMembershipUserWithTx(membewrshipUserId);

	}

	@Override
	public boolean updateMembershipUserWithTx(List<Long> membewrshipUserId, Date endTime) {
		return membershipUserWriteManage.updateMembershipUserWithTx(membewrshipUserId,  endTime);
	}

	@Override
	public void giveUserMembershipByOrder(Long userId, Long platformId, Long skuId) {
		//根据skuID和platformId查询会籍
		MembershipPO po = new MembershipPO();
		po.setLinkedSkuId(skuId);
		po.setPlatformId(platformId);
		List<MembershipPO> membershipAll = membershipReadManage.findMembershipAll(po);
		if(EmptyUtil.isEmpty(membershipAll )){
			throw new BusinessException("购买的会籍商品未关联会籍");
		}else if(membershipAll.size()>1){
			logger.info("[购买的会籍商品关联多个会籍]"+membershipAll);
			throw new BusinessException("购买的会籍商品关联多个会籍");
		}
		MembershipPO membershipPO = membershipAll.get(0);

		Long membershipId = membershipPO.getId();
		MembershipUserPO membershipUserPO = new MembershipUserPO();

		Date start = new Date();
		//有效期单位
		int unit = membershipPO.getValidPeriodUnit();
		//有效期值
		int val = membershipPO.getValidPeriodVal();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int mouth = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		Long value = 0L;
		switch (unit) {
			case 1:
				year = year + 1;
				break;
			case 2:
				mouth = mouth + 1;
				break;
			case 3:
				day = day + 1;
				break;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = year + "" + mouth + day;
		Date end = new Date();
		try {
			end = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		membershipUserPO.setStartTime(start);
		membershipUserPO.setEndTime(end);
		membershipUserPO.setUserId(userId);
		membershipUserPO.setMembershipId(membershipId);
		membershipUserPO.setStatusCode(1);
		membershipUserWriteManage.insertMembershipUserWithTx(membershipUserPO);



	}
}
	