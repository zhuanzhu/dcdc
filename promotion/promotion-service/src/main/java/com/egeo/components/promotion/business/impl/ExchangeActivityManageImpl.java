package com.egeo.components.promotion.business.impl;
	

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.ExchangeActivityManage;
import com.egeo.components.promotion.common.DateUtils;
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.components.promotion.dto.ExchangeCouponUnitStatusDTO;
import com.egeo.components.promotion.facade.ExchangeActivityFacade;
import com.egeo.components.promotion.facade.ExchangeCouponUnitStatusFacade;
import com.egeo.components.promotion.facade.ExchangeOrderRecordFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;

@Service("exchangeActivity")
public class ExchangeActivityManageImpl implements ExchangeActivityManage{

	@Resource(name="exchangeActivityFacade")
	private ExchangeActivityFacade exchangeActivityFacade;

    @Resource(name="exchangeCouponUnitStatusFacade")
    private ExchangeCouponUnitStatusFacade exchangeCouponUnitStatusFacade;

    @Resource(name="exchangeOrderRecordFacade")
    private ExchangeOrderRecordFacade exchangeOrderRecordFacade;



	@Override
	public ExchangeActivityDTO findExchangeActivityById(ExchangeActivityDTO dto) {
		return exchangeActivityFacade.findExchangeActivityById(dto);
	}



	@Override
	public PageResult<ExchangeActivityDTO> findExchangeActivityOfPage(ExchangeActivityDTO dto, Pagination page) {
        PageResult<ExchangeActivityDTO> pageResult = exchangeActivityFacade.findExchangeActivityOfPage(dto, page);
        if (EmptyUtil.isNotEmpty(pageResult)) {
            List<ExchangeActivityDTO> dtoList = pageResult.getList();
            for (ExchangeActivityDTO exchangeActivityDTO : dtoList) {
                //组装允许兑换的Unit状态
                ExchangeCouponUnitStatusDTO unitStatusDTO = new ExchangeCouponUnitStatusDTO();
                unitStatusDTO.setExchangeId(exchangeActivityDTO.getId());
                List<ExchangeCouponUnitStatusDTO> unitStatusDTOList = exchangeCouponUnitStatusFacade.findExchangeCouponUnitStatusAll(unitStatusDTO);
                List<Integer> unitStatusList = new ArrayList<Integer>();
                for (ExchangeCouponUnitStatusDTO unitStatus : unitStatusDTOList) {
                    unitStatusList.add(unitStatus.getAllowExchangeUnitStatus());
                }
                exchangeActivityDTO.setUnitStatus(unitStatusList);

                //组装已兑换次数
                int count = exchangeOrderRecordFacade.getCountOfCompletedOrderByExchangeId(exchangeActivityDTO.getId());
                exchangeActivityDTO.setNum(count);

                //组装时间 转成String
                String createTime = DateUtils.format(DateUtils.DATE_TIME_FORMAT, exchangeActivityDTO.getCreateTime());
                String endTime = DateUtils.format(DateUtils.DATE_TIME_FORMAT, exchangeActivityDTO.getEndTime());
                String updateTime =DateUtils.format(DateUtils.DATE_TIME_FORMAT, exchangeActivityDTO.getUpdateTime());
                exchangeActivityDTO.setCreateTimeStr(createTime);
                exchangeActivityDTO.setEndTimeStr(endTime);
                exchangeActivityDTO.setUpdateTimeStr(updateTime);
            }
        }
        return pageResult;
    }

	@Override
	public List<ExchangeActivityDTO> findExchangeActivityAll(ExchangeActivityDTO dto) {
		return exchangeActivityFacade.findExchangeActivityAll(dto);
	}

	@Override
	public Long insertExchangeActivityWithTx(ExchangeActivityDTO dto) {
		return exchangeActivityFacade.insertExchangeActivityWithTx(dto);
	}

	@Override
	public int updateExchangeActivityWithTx(ExchangeActivityDTO dto) {
		return exchangeActivityFacade.updateExchangeActivityWithTx(dto);
	}

	@Override
	public int deleteExchangeActivityWithTx(ExchangeActivityDTO dto) {
		return exchangeActivityFacade.deleteExchangeActivityWithTx(dto);
	}

    /**
     * 模糊查询以旧换新活动列表
     */
    @Override
    public PageResult<ExchangeActivityDTO> fuzzQueryExchangeActivityOfPage(ExchangeActivityDTO dto, Pagination page) {
        PageResult<ExchangeActivityDTO> pageResult = exchangeActivityFacade.fuzzQueryExchangeActivityOfPage(dto, page);
        if (EmptyUtil.isNotEmpty(pageResult)) {
            List<ExchangeActivityDTO> dtoList = pageResult.getList();
            for (ExchangeActivityDTO exchangeActivityDTO : dtoList) {
                //组装允许兑换的Unit状态
                ExchangeCouponUnitStatusDTO unitStatusDTO = new ExchangeCouponUnitStatusDTO();
                unitStatusDTO.setExchangeId(exchangeActivityDTO.getId());
                List<ExchangeCouponUnitStatusDTO> unitStatusDTOList = exchangeCouponUnitStatusFacade.findExchangeCouponUnitStatusAll(unitStatusDTO);
                List<Integer> unitStatusList = new ArrayList<Integer>();
                for (ExchangeCouponUnitStatusDTO unitStatus : unitStatusDTOList) {
                    unitStatusList.add(unitStatus.getAllowExchangeUnitStatus());
                }
                exchangeActivityDTO.setUnitStatus(unitStatusList);

                //组装已兑换次数
                int count = exchangeOrderRecordFacade.getCountOfCompletedOrderByExchangeId(exchangeActivityDTO.getId());
                exchangeActivityDTO.setNum(count);

                //组装时间 转成String
                String createTime = DateUtils.format(DateUtils.DATE_TIME_FORMAT, exchangeActivityDTO.getCreateTime());
                String endTime = DateUtils.format(DateUtils.DATE_TIME_FORMAT, exchangeActivityDTO.getEndTime());
                String updateTime =DateUtils.format(DateUtils.DATE_TIME_FORMAT, exchangeActivityDTO.getUpdateTime());
                exchangeActivityDTO.setCreateTimeStr(createTime);
                exchangeActivityDTO.setEndTimeStr(endTime);
                exchangeActivityDTO.setUpdateTimeStr(updateTime);
            }
        }
        return pageResult;
    }

    @Override
    public int insertOrUpdateExchangeActivityWithTx(ExchangeActivityDTO exchangeActivityDTO) {
        return exchangeActivityFacade.insertOrUpdateExchangeActivityWithTx(exchangeActivityDTO);
    }

    @Override
    public void updateExchangeActivityStatus(Integer status, Long exchangeId) {
        ExchangeActivityDTO exchangeActivityDTO = new ExchangeActivityDTO();
        exchangeActivityDTO.setId(exchangeId);
        exchangeActivityDTO.setStatus(status);
        int i = exchangeActivityFacade.updateExchangeActivityWithTx(exchangeActivityDTO);
    }

    @Override
    public JsonResult<Map<String, Object>> findExchangeActivity(ExchangeActivityDTO dto) {
        return exchangeActivityFacade.findExchangeActivity(dto);

    }
}
	