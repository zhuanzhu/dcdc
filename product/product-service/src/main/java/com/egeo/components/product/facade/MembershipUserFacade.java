package com.egeo.components.product.facade;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.HeadImportRecordsClient;
import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.product.common.LockConfig;
import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.product.service.read.MembershipUserReadService;
import com.egeo.components.product.service.write.MembershipUserWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.lock.LogicLock;


@Component
public class MembershipUserFacade {
	
	@Resource
	private MembershipUserReadService membershipUserReadService;
	
	@Resource
	private MembershipUserWriteService membershipUserWriteService;

	@Autowired
	private HeadImportRecordsClient headImportRecordsReadService;
	@Autowired
	private ImportRecordsClient importRecordsWriteService;
	@Autowired
	private ImportRecordsClient importRecordsReadService;
	@Autowired
	private HeadImportRecordsClient headImportRecordsWriteService;

    @Autowired
    private LockConfig lockConfig;
	
	public MembershipUserDTO findMembershipUserById(MembershipUserDTO dto){
		
		return membershipUserReadService.findMembershipUserById(dto);
	}

	public PageResult<MembershipUserDTO> findMembershipUserOfPage(MembershipUserDTO dto,Pagination page){
		
		return membershipUserReadService.findMembershipUserOfPage(dto, page);
		
	}

	public List<MembershipUserDTO> findMembershipUserAll(MembershipUserDTO dto){
		
		return membershipUserReadService.findMembershipUserAll(dto);
		
	}

	public Long insertMembershipUserWithTx(MembershipUserDTO dto){
		
		return membershipUserWriteService.insertMembershipUserWithTx(dto);
	}

	public int updateMembershipUserWithTx(MembershipUserDTO dto){
		
		return membershipUserWriteService.updateMembershipUserWithTx(dto);
	}

	public int deleteMembershipUserWithTx(MembershipUserDTO dto){
		
		return membershipUserWriteService.deleteMembershipUserWithTx(dto);
		
	}

	public PageResult<MembershipUserDTO> findMembershipUserAllByParams(Long membershipId, Date startTime, Date endTime, Integer statusCode,  Pagination page, List<Long> result) {

		return membershipUserReadService.findMembershipUserAllByParams(membershipId, startTime, endTime, statusCode, page, result);
	}

	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO headImportRecordsDTO) {

		return headImportRecordsReadService.findHeadImportRecordsAll(headImportRecordsDTO);

	}

	public MembershipUserDTO findMembershipUserByMail(Long userId, Long membershipId) {
		return membershipUserReadService.findMembershipUserByMail(userId, membershipId);

	}

    public void insertImportRecords(ImportRecordsDTO importRecordsDTO) {
		importRecordsWriteService.insertImportRecordsWithTx(importRecordsDTO);
	}

	public List<ImportRecordsDTO> findImportRecords(String fileNumber) {
		ImportRecordsDTO dto = new ImportRecordsDTO();
		dto.setFileSequenceNumber(fileNumber);
		return importRecordsReadService.findImportRecordsAll(dto);
	}

	public Long sureImportInfo(List<MembershipUserDTO> membershipUserDTOS, HeadImportRecordsDTO headImportRecordsDTO) {
		// 获取分布式锁
		LogicLock lock = lockConfig.getLockManager().getLogicLock("sureImportInfo");
		 if (lock.tryLock()) {
				long count = 0;
				try{
					for (MembershipUserDTO dto : membershipUserDTOS) {
						membershipUserWriteService.insertMembershipUserWithTx(dto);
						count++;
					}
					headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
					return count;
				}catch (Exception e1){
					throw new BusinessException("确认导入会员数据异常：" + e1.getMessage());
				}finally {
					//释放锁
					try {
						lock.unlock();
					} catch (Exception e) {
						throw new BusinessException("释放掉一个分布式锁失败，请联系管理员");
					}
				} 
		 }else {
			 throw new BusinessException("其他管理员正在导入，请稍后再试");
		 }





	}

	public Boolean updateMembershipUserWithTx(List<Long> membewrshipUserId) {
		return membershipUserWriteService.updateMembershipUserWithTx(membewrshipUserId);
	}

	public boolean updateMembershipUserWithTx(List<Long> membewrshipUserId, Date endTime) {
		return membershipUserWriteService.updateMembershipUserWithTx(membewrshipUserId,endTime);
	}
}
	