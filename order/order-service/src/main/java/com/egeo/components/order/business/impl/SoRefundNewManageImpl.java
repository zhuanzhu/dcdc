package com.egeo.components.order.business.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.domain.GoodsDetail;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.common.RefundErrorCodeConstant;
import com.egeo.components.config.client.CardSaltClient;
import com.egeo.components.config.client.CompanyConfigClient;
import com.egeo.components.config.client.SaltClient;
import com.egeo.components.config.dto.CompanyConfigDTO;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.finance.client.AccountBatchClient;
import com.egeo.components.finance.client.AccountFlowClient;
import com.egeo.components.finance.client.CompanyAccountClient;
import com.egeo.components.finance.client.UserAccountClient;
import com.egeo.components.finance.constant.FinBatchConstant;
import com.egeo.components.finance.constant.FlowTypeConstant;
import com.egeo.components.finance.dto.*;
import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.order.business.SoRefundNewManage;
import com.egeo.components.order.business.WorldBuyOrderManage;
import com.egeo.components.order.enums.ThirdConst;
import com.egeo.components.order.vo.RefundItemVo;
import com.egeo.components.order.vo.RefundVo;
import com.egeo.components.order.facade.SoChildFacade;
import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
import com.egeo.components.promotion.vo.BuyCardRefundReqVO;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.components.utils.*;
import com.egeo.exception.BusinessException;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.service.read.*;
import com.egeo.components.order.service.write.*;
import com.egeo.components.pay.client.JdOrderAwaitQueueClient;
import com.egeo.components.pay.client.PayWeixinLogClient;
import com.egeo.components.pay.client.ThirdpartyAwaitQueueClient;
import com.egeo.components.pay.dto.PayWeixinLogDTO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.promotion.client.*;
import com.egeo.components.stock.client.CommodityProductUnitStockRunningWaterClient;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.constant.StockConstant;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.dto.RecoverOrderStockBatchDTO;
import com.egeo.components.stock.dto.UpdateStorePuWarehouseStockDTO;
import com.egeo.components.user.client.*;
import com.egeo.components.user.constant.InfoConstant;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.InsertAndSendMessageDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.vo.InsertOrderPayStatusInfoAndSendVO;
import com.egeo.components.utils.qc.QCUtil;
import com.egeo.dto.HttpServletRequestDTO;
import com.egeo.log.EgeoBusinessLogCommon;
import com.egeo.log.EgeoLog;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import com.egeo.utils.log.XLogger;
import com.egeo.utils.pay.PayUtil;
import com.egeo.utils.thirdparty.RechargePhoneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 退款管理
 * @Author lsl
 * @Date 2024/10/22 16:05
 * @Version V1.0
 **/
@Service
public class SoRefundNewManageImpl implements SoRefundNewManage {


    XLogger logger = XLogger.getLogger(this.getClass().getName());

    @Autowired
    private AccountBatchClient abWrteService;

    @Autowired
    private UserAccountClient uaReadService;

    @Autowired
    private CompanyAccountClient caReadService;

    @Autowired
    private SaltClient saltReadService;

    @Autowired
    private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;

    @Autowired
    private CommodityProductUnitClient commodityProductUnitReadService;

    @Autowired
    private PayWeixinLogClient payWeixinLogReadService;

    @Autowired
    private CommodityProductUnitWarehouseStockClient stockWriteService;

    @Autowired
    private ThirdpartyAwaitQueueClient thirdpartyAwaitQueueWriteService;

    @Autowired
    private SoWriteService soWriteService;

    @Autowired
    private SoReadService soReadService;

    @Autowired
    private SoRefundWriteService soRefundWriteService;

    @Autowired
    private SoRefundReadService soRefundReadService;

    @Autowired
    private SoChildReadService soChildReadService;

    @Autowired
    private SoChildWriteService soChildWriteService;

    @Autowired
    private SoThirdpartyWriteService soThirdpartyWriteService;

    @Autowired
    private SoThirdpartyReadService soThirdpartyReadService;

    @Autowired
    private SoItemReadService soItemReadService;

    @Autowired
    private SoItemWriteService soItemWriteService;

    @Autowired
    private UserClient userReadService;

	/*@Autowired(name = "sendInfoProvider")
	private Provider provider;*/

    @Autowired
    private InfoClient infoWriteService;
    @Autowired
    private InfoTemplateClient infoTemplateReadService;
    @Autowired
    private InfoTemplateSendWayClient infoTemplateSendWayReadService;
    @Autowired
    private StorePuWarehouseStockClient storePuWarehouseStockService;
    @Autowired
    private CompanyClient companyReadService;
    @Autowired
    private SkuClient skuReadService;
    @Autowired
    private CardSaltClient cardSaltWriteService;
    @Autowired
    private ECardClient eCardWriteService;
    @Autowired
    private ECardTempClient eCardTempWriteService;
    @Autowired
    private CouponUnitClient couponUnitReadService;
    @Autowired
    private ExchangeBatchClient exchangeBatchReadService;
    @Autowired
    private QmOrderReadService qmOrderReadService;
    @Autowired
    private QmOrderWriteService qmOrderWriteService;
    @Autowired
    private CouponBatchClient couponBatchReadService;
    @Autowired
    private ExchangeOrderRecordClient exchangeOrderRecordReadService;
    @Autowired
    private CouponUnitClient couponUnitWriteService;
    @Autowired
    private SendInfoClient sendInfoWriteService;
    @Autowired
    private ExchangeOrderRecordClient exchangeOrderRecordWriteService;
    @Autowired
    private CommodityProductUnitStockRunningWaterClient commodityProductUnitStockRunningWaterReadService;
    @Autowired
    private JdOrderAwaitQueueClient jdOrderAwaitQueueWriteService;
    @Autowired
    AccountFlowClient accountFlowClient;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired
    private PayUtil payUtil;
    @Autowired
    private PayExtUtil payExtUtil;
    @Autowired
    private RechargePhoneUtil rechargePhoneUtil;
    @Autowired
    private QCUtil qCUtil;
    @Autowired
    private JdUtils jdUtils;
    @Autowired
    private QingMeiUtil qingMeiUtil;
    @Autowired
    private CompanyConfigClient companyConfig;
    @Autowired
    private CakeUtil cakeUtil;
    private static final String soRefundReason = "用户取消订单"; // 自动取消订单原因

    @Autowired
    private WorldBuyOrderManage worldBuyOrderManage;

    @Autowired
    private PushOrderManage pushOrderManage;

    @Autowired
    private SoRefundItemWriteService soRefundItemWriteService;
    @Resource
    private SoChildFacade soChildFacade;

    @Resource
    private BuyCardClient buyCardClient;

    @Override
    public Map<String,Object> productRefundOrderWithTx(CancelAndRefundOrderExtendsWithTxDTO dto){
        //检查
        checkIsVaild(dto);
       //这里需要做是订单取消还是订单退款判断走不同的分支
        //退款前操作
        refundBefore(dto);
        //执行退款
        Map<String,Object> refundResultMap = refundOrder(dto);
        //退款之后处理
        refundAfter(dto,refundResultMap);
        return  refundResultMap;
    }

    private void refundBefore(CancelAndRefundOrderExtendsWithTxDTO dto) {
        List<SoItemDTO> items = dto.getItems();
        SoDTO order = dto.getOrder();
        checkItems(items,order);
        //填充其他默认值，或存在未填充的值给其填充
        fillDefaultValues(dto, order);
    }

    private void fillDefaultValues(CancelAndRefundOrderExtendsWithTxDTO dto, SoDTO order) {
        if(dto.getUserId()==null){
            dto.setUserId(order.getUserId());
        }
        if(EmptyUtil.isEmpty(dto.getUserName())){
            UserDTO userDTO =  userReadService.findUserByID(dto.getUserId());
            if(userDTO !=null){
                dto.setUserName(userDTO.getName());
            }
        }
    }

    private void checkItems(List<SoItemDTO> items,SoDTO order) {
        List<Long> ids = FHCollectionUtils.listToStrList(items,SoItemDTO::getId);
        //找到需要退款的商品
        List<SoItemDTO> oldSoItemDTOList = soItemReadService.getByIds(ids);
        if(CollectionUtils.isEmpty(oldSoItemDTOList)){
            throw new BusinessException("请选择需要退款的商品");
        }
        Map<Long,SoItemDTO> oldSoItemDTOMap = FHCollectionUtils.listToMap(oldSoItemDTOList,SoItemDTO::getId,e->e);
        List<Long> soChildIds = FHCollectionUtils.listToStrList(oldSoItemDTOList,SoItemDTO::getSoChildId);
        List<SoChildDTO> soChildDTOList = soChildReadService.findSoChildByIdList(soChildIds);
        Map<Long,SoChildDTO> oldSoChildDTOMap = FHCollectionUtils.listToMap(soChildDTOList,SoChildDTO::getId,e->e);
        Map<Long, BigDecimal> everyChildRefundAmountMap = getChildCanRefundAmountMap(items, oldSoItemDTOMap, oldSoChildDTOMap);
        List<RefundItemVo> itemVos=new ArrayList<>();
        for (SoItemDTO item : items) {
            SoItemDTO oldSoItemDTO = oldSoItemDTOMap.get(item.getId());
            //最大可退款金额
            BigDecimal canItemRefundAmount = new BigDecimal(item.getPuCount()).multiply(item.getPrice()).setScale(2);
            BigDecimal canMaxItemRefundAmount = new BigDecimal(item.getPuCount()).multiply(item.getPrice()).setScale(2);
            BigDecimal currChildCanRefundAmount = everyChildRefundAmountMap.get(item.getSoChildId());
            //本次能退完该子订单,那么加上运费来检查够不够
            if(currChildCanRefundAmount.compareTo(BigDecimal.ZERO) ==0){
                //该子订单项商品承担的运费
                BigDecimal canItemRefundFeeAmount = oldSoItemDTO.getDeliveryFeeAver() !=null?oldSoItemDTO.getDeliveryFeeAver():BigDecimal.ZERO;
                //减去已退运费
                canItemRefundFeeAmount = canItemRefundFeeAmount.subtract(item.getRefundDeliveryFee() !=null?item.getRefundDeliveryFee():BigDecimal.ZERO);
                //最大可退款金额加上可退运费
                canItemRefundAmount = canItemRefundAmount.add(canItemRefundFeeAmount);
            }

            //剩余可退金额=最大可退金额-以往已退金额
            canItemRefundAmount = canItemRefundAmount.subtract(oldSoItemDTO.getRefundAmount() !=null?oldSoItemDTO.getRefundAmount():BigDecimal.ZERO);
            //剩余可退金额=剩余可退金额-以往已退运费
            canItemRefundAmount = canItemRefundAmount.subtract(oldSoItemDTO.getRefundDeliveryFee() !=null?oldSoItemDTO.getRefundDeliveryFee():BigDecimal.ZERO);
            //找到本次需要退款金额:
            BigDecimal itemRefundAmount = item.getRefundAmount();
            //是否超过该订单项的最大可退金额
            if(canItemRefundAmount.compareTo(itemRefundAmount) <0){
                logger.info("商品"+oldSoItemDTO.getPuName()+"超过可退金额;canItemRefundAmount:{},itemRefundAmount:{}",
                        canItemRefundAmount,itemRefundAmount);
                throw new BusinessException("商品"+oldSoItemDTO.getPuName()+"超过可退金额");
            }

            RefundItemVo itemVo=new RefundItemVo();
            itemVo.setSkuId(String.valueOf(item.getPuId()));
            itemVo.setSkuName(item.getPuName());
            itemVo.setRefundNum(item.getRefundCount());
            itemVo.setPrice(item.getPrice());
            itemVo.setRefundAmount(item.getRefundAmount());
            itemVo.setRefundDeliveryFee(BigDecimal.ZERO);
            BigDecimal oldRefundAmt = item.getRefundAmount().add(oldSoItemDTO.getRefundAmount() !=null?oldSoItemDTO.getRefundAmount():BigDecimal.ZERO);
            if(oldRefundAmt.compareTo(canMaxItemRefundAmount) >0){
                itemVo.setRefundDeliveryFee(oldRefundAmt.subtract(canMaxItemRefundAmount));
                itemVo.setRefundAmount(item.getRefundAmount().subtract(itemVo.getRefundDeliveryFee()));
            }

            itemVo.setSoItemId(item.getId());
            itemVo.setSource(item.getSource());
            itemVo.setPlatformId(item.getPlatformId());
            itemVos.add(itemVo);
        }
        Long childId = items.get(0).getSoChildId();
        RefundVo refundVo = new RefundVo();
        refundVo.setSoItemDTOS(items);
        refundVo.setChildId(childId);
        refundVo.setRefundItemVos(itemVos);
        order.setRefundVo(refundVo);
    }

    private Map<Long, BigDecimal> getChildCanRefundAmountMap(List<SoItemDTO> items, Map<Long, SoItemDTO> oldSoItemDTOMap, Map<Long, SoChildDTO> oldSoChildDTOMap) {
        Map<Long, BigDecimal> everyChildRefundAmountMap = new HashMap<>();

        for (SoItemDTO item : items) {
            if ((item.getRefundCount() == null || item.getRefundCount().intValue() == 0) && (item.getRefundAmount() == null || item.getRefundAmount().compareTo(BigDecimal.ZERO) == 0)) {
                throw new BusinessException("需要退款的数量或金额不能同时为0");
            }
            SoItemDTO oldSoItemDTO = oldSoItemDTOMap.get(item.getId());
            if (oldSoItemDTO == null) {
                throw new BusinessException("需要退款的商品未找到");
            }

            copyOldItemToNew(item, oldSoItemDTO);

            //最大允许退款数量
            Integer canRefundPuCount = oldSoItemDTO.getPuCount() - oldSoItemDTO.getRefundCount() - (item.getRefundCount() != null ? item.getRefundCount() : 0);
            if (canRefundPuCount < 0) {
                throw new BusinessException("商品" + oldSoItemDTO.getPuName() + "退款数量大于可退数量");
            }
            //找到需要退款金额:
            //若退款金额不为null和0，那么真正退款金额=需要传递进来的金额
            // 若退款金额为null或0，那退款数量不能为空和不能为0，则真正退款金额=退款数量*商品单价
            BigDecimal itemRefundAmount = BigDecimal.ZERO;
            if (item.getRefundAmount() != null && item.getRefundAmount().compareTo(BigDecimal.ZERO) > 0) {
                itemRefundAmount = item.getRefundAmount();
            } else {
                itemRefundAmount = new BigDecimal(item.getRefundCount()).multiply(oldSoItemDTO.getPrice()).setScale(2);
            }
            //归集告知当前每个子订单要退金额数
            BigDecimal everyChildRefundAmount=everyChildRefundAmountMap.getOrDefault(item.getSoChildId(),BigDecimal.ZERO);
            everyChildRefundAmountMap.put(item.getSoChildId(), everyChildRefundAmount.add(itemRefundAmount));
            item.setRefundAmount(itemRefundAmount);
        }
        //每笔子订单剩余多少钱未退
        for (Map.Entry<Long, BigDecimal> entry : everyChildRefundAmountMap.entrySet()) {
            SoChildDTO soChildDTO = oldSoChildDTOMap.get(entry.getKey());
            //可退金额=商品金额+运费-已退运费
            BigDecimal canRefundAmount = soChildDTO.getAmount().add(soChildDTO.getDeliveryFee()).subtract(soChildDTO.getRefundAmount()).subtract(soChildDTO.getRefundDeliveryFee() !=null?soChildDTO.getRefundDeliveryFee():BigDecimal.ZERO);
            //剩余可退=可退金额-本次退款金额
            BigDecimal subAmount = canRefundAmount.subtract(entry.getValue());
            if(subAmount.compareTo(BigDecimal.ZERO) <0){
                throw new BusinessException("超过子订单可退金额");
            }
            //全球购的单笔子订单必须整单退
            if(EmptyUtil.isNotEmpty(soChildDTO.getSource()) && Objects.equals(soChildDTO.getSource(), ThirdConst.Source.WORLD) && subAmount.compareTo(BigDecimal.ZERO) !=0){
                throw new BusinessException("全球购子订单必须整单退");
            }

            entry.setValue(subAmount);
        }
        return everyChildRefundAmountMap;
    }


    private void refundAfter(CancelAndRefundOrderExtendsWithTxDTO dto,Map<String,Object> refundResultMap){
        //退款结果检查
        if(EmptyUtil.isEmpty(refundResultMap) || !refundResultMap.containsKey("result")){
            throw new BusinessException("退款失败");
        }
        String result = refundResultMap.get("result").toString();
        if(!"success".equals(result)){
            throw new BusinessException("退款失败");
        }
        String refundNo = refundResultMap.get("refundNo").toString();
        //退款成功后处理
        List<SoItemDTO> items = dto.getItems();
        List<SoItemDTO> newItems = new ArrayList<>(items);
        String userName = dto.getUserName();
        Long userId = dto.getUserId()!=null?dto.getUserId():dto.getOrder().getUserId();
        SoDTO order = dto.getOrder();

        SoDTO soDTO = (SoDTO)refundResultMap.get("SoDTOChange");
        // 订单已退款金额变动和退款状态变动
        soWriteService.orderRefund(soDTO);
        //额外京东订单处理
        accountFlowClient.orderRefund(soDTO.getOrderCode());
        Map<Long, List<SoItemDTO>> groupedByChildCodeMap = items.stream().collect(Collectors.groupingBy(SoItemDTO::getSoChildId));
        List<Long> needPushChildIds = new ArrayList<>();
        //修改子订单
        updateChildOrder(groupedByChildCodeMap,needPushChildIds);
        //修改订单项
        List<Long> ids = FHCollectionUtils.listToStrList(items,SoItemDTO::getId);
        //找到需要退款的商品
        List<SoItemDTO> oldSoItemDTOList = soItemReadService.getByIds(ids);
        Map<Long,SoItemDTO> oldSoItemDTOMap = FHCollectionUtils.listToMap(oldSoItemDTOList,SoItemDTO::getId,e->e);
        List<Long> childIds = new ArrayList<>();

        for (SoItemDTO item : items) {
            SoItemDTO oldSoItemDTO = oldSoItemDTOMap.get(item.getId());
            BigDecimal canRefundProductAmt = item.getPrice().multiply(new BigDecimal(item.getPuCount())).setScale(2);
            BigDecimal totalRefundAmt = item.getRefundAmount().add(oldSoItemDTO.getRefundAmount() !=null?oldSoItemDTO.getRefundAmount():BigDecimal.ZERO);
            //本次退款金额+以往已退金额大于商品价值金额，那么计算要退的运费
            if(totalRefundAmt.compareTo(canRefundProductAmt) >0){
                BigDecimal fee = totalRefundAmt.subtract(canRefundProductAmt);
                item.setRefundDeliveryFee(fee);
                item.setRefundAmount(item.getRefundAmount().subtract(fee));
            }
            //本次退款金额+以往已退金额等于商品价值金额，那么需要重新计算要承担的运费
            if(totalRefundAmt.compareTo(canRefundProductAmt) ==0){
                if(!childIds.contains(item.getSoChildId())){
                    childIds.add(item.getSoChildId());
                }
            }
            soItemWriteService.updateSoItemRefundWithTx(item);
        }
        //这里需要重新计算承担运费
        if(!CollectionUtils.isEmpty(childIds)){
            for (Long childId:childIds){
                SoChildDTO soChildDTO = soChildReadService.findSoChildById(childId);
                if (StringUtil.nullToZero(soChildDTO.getRefundDeliveryFee()).
                        compareTo(StringUtil.nullToZero(soChildDTO.getDeliveryFee())) !=0){
                    List<SoItemDTO> itemList=soItemReadService.querySoItemsBySoChildId(childId);
                    soChildFacade.reSplitItemFee(itemList,soChildDTO);
                }
            }
        }

        // 发送订单支付状态变更消息
        pushMessage(soDTO);
        pushOrderManage.pushChildOrderToThird(needPushChildIds,dto.getReason());
        //推送退款订单到第三方
        //dto.setItems(newItems);
        pushOrderManage.pushRefundOrder(dto);
        //推送订单信息到第三方
        pushOrderManage.pushOrderInfo(order.getId(),null,null);
        // 5.取消订单成功,记录取消订单的日志
        addLog(dto, order);
        //处理库存
        updateStock(items, userName, userId, order);
        logger.info("退款执行完成:{}",JSON.toJSONString(soDTO));
    }

    private void updateChildOrder(Map<Long, List<SoItemDTO>> groupedByChildCodeMap,List<Long> needPushChildIds) {
        for (Map.Entry<Long, List<SoItemDTO>> entry : groupedByChildCodeMap.entrySet()) {
            Long childId = entry.getKey();
            SoChildDTO soChildDTO = soChildReadService.findSoChildById(childId);
            BigDecimal allRefundAmount = soChildDTO.getRefundAmount() !=null ?soChildDTO.getRefundAmount():BigDecimal.ZERO;
            allRefundAmount = allRefundAmount.add(soChildDTO.getRefundDeliveryFee() !=null?soChildDTO.getRefundDeliveryFee():BigDecimal.ZERO);
            List<SoItemDTO> soItemDTOS = entry.getValue();
            BigDecimal refundAmount = BigDecimal.ZERO;
            Integer refundCount = 0;
            for (SoItemDTO soItemDTO : soItemDTOS) {
                refundAmount = refundAmount.add(soItemDTO.getRefundAmount());
                if(soItemDTO.getRefundCount() !=null){
                    refundCount = refundCount+soItemDTO.getRefundCount();
                }
            }

            SoChildDTO editSoChildDTO = new SoChildDTO();
            editSoChildDTO.setId(childId);
            editSoChildDTO.setRefundAmount(refundAmount);
            allRefundAmount =  allRefundAmount.add(refundAmount);
            if(allRefundAmount.compareTo(soChildDTO.getAmount()) >=0){
                editSoChildDTO.setCancelStatus(1);
                BigDecimal refundDeliveryFee = allRefundAmount.subtract(soChildDTO.getAmount());
                editSoChildDTO.setRefundDeliveryFee(refundDeliveryFee);
                editSoChildDTO.setRefundAmount(refundAmount.subtract(refundDeliveryFee));
                //editSoChildDTO.setDeliveryStatus(OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus());
                logger.info("子订单{}所有退款金额:{},退款运费:{},商品金额{},本次退款金额{},本次更新退款金额为{}",soChildDTO.getChildCode(),allRefundAmount,refundDeliveryFee,soChildDTO.getAmount(),refundAmount,editSoChildDTO.getRefundAmount());
                if(!needPushChildIds.contains(childId)){
                    needPushChildIds.add(childId);
                }
            }
            soChildWriteService.updateSoChildRefundWithTx(editSoChildDTO);
        }
    }

    private void pushMessage(SoDTO soDTO) {
        if (Objects.equals(2, soDTO.getOrderPayStatus())){
            InsertOrderPayStatusInfoAndSendVO vo1 = new InsertOrderPayStatusInfoAndSendVO();
            vo1.setInfoTemplateId(InfoConstant.ORDER_STATUS_PAYED_INFO_ID.getStatus());
            vo1.setOrderCode(soDTO.getOrderCode());
            //已退款
            vo1.setOrderPayStatus(2);
            vo1.setUserId(soDTO.getUserId());
            sendInfoWriteService.insertOrderPayStatusInfoAndSend(vo1);
        }
    }

    private void addLog(CancelAndRefundOrderExtendsWithTxDTO dto, SoDTO order) {
        SoDTO newSoDTO = soReadService.querySoById(order.getId());
        EgeoLog log = new EgeoLog();
        log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
        log.setOperObject("SoRefundNewManageImpl_cancelAndRefundOrderWithTx");
        log.setMsgId(LogConstant.ORDER_CANCEL.getStatus());
        log.setType(LogTypeConstant.SO.getStatus());
        log.setOperatorObjId(newSoDTO.getId());
        log.setOperatorObjCode(newSoDTO.getOrderCode());
        log.setNewObj(newSoDTO);
        log.setOldObj(order);

        EgeoBusinessLogCommon.fillLogValueForBasic(log, dto.getReq());

        try {
            //ActiveMQUtils.recordBusinessLog(log);
           logger.info("订单id:{}退款日志:{}",order.getId(),JSON.toJSONString(log));
        }catch (Exception e) {
            // TODO: handle exception
            logger.error("发送日志消息失败："+ JSON.toJSONString(log));
        }
    }

    private void updateStock(List<SoItemDTO> items, String userName, Long userId, SoDTO order) {
        //校验是否为正式公司
        if(checkCompany(order.getCompanyId())){
            // 4.回滚库存: 根据orderid释放库存(订单项有可能是unit商品,回滚时检验unit存在性,回滚unit冻结库存)
            for (SoItemDTO it : items) {
                if (it.isQM() || it.isCake()|| it.isWorld()){
                    continue;
                }
                CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
                commodityProductUnitDTO.setId(it.getPuId());
                CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService
                        .findCommodityProductUnitById(commodityProductUnitDTO);

                //根据订单ID查询 puIds
                //根据puIds
                List<CommodityProductUnitStockRunningWaterDTO> waterDTOs =
                        commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus(Arrays.asList(order.getOrderCode()), StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
                List<Long> puIdList = new ArrayList<>();
                Set<Long> puIdSet = new HashSet<>();
                for (CommodityProductUnitStockRunningWaterDTO waterDTO : waterDTOs) {
                    puIdSet.add(waterDTO.getCommodityProductUnitId());
                }
                puIdList.addAll(puIdSet);
                List<CommodityProductUnitDTO> commodityProductUnitDTOs =null;
                if(!CollectionUtils.isEmpty(puIdList) && commodityProductUnitDTO2 !=null){
                    commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIdSkuId(com.egeo.utils.StringUtils.longsToStrings(puIdList),commodityProductUnitDTO2.getSkuId());
                }

                if(commodityProductUnitDTOs != null && commodityProductUnitDTOs.size() > 0) {
                    puIdList.add(it.getPuId());
                    commodityProductUnitDTOs.add(commodityProductUnitDTO2);
                    stockWriteService.recoverOrderStockBatch(new RecoverOrderStockBatchDTO(it.getPuId(), it.getPuCount(), order.getOrderCode(),
                            StockConstant.STOCK_STATUS_CANCEL_ORDER_PAYED.getStatus(), userId,
                            userName, null, null, puIdList, commodityProductUnitDTOs));
                }else {
                    if(commodityProductUnitDTO2 !=null){
                        stockWriteService.recoverOrderStock(it.getPuId(), it.getPuCount(), order.getOrderCode(),
                                StockConstant.STOCK_STATUS_CANCEL_ORDER_PAYED.getStatus(),
                                commodityProductUnitDTO2.getProductUnitSerialNumber(), commodityProductUnitDTO2.getName(), userId,
                                userName, "", "");
                    }
                }

            }
            // 更新门店pu库存信息
            storePuWarehouseStockService.updateStorePuWarehouseStock(new UpdateStorePuWarehouseStockDTO(StockConstant.STOCK_STATUS_CANCEL_ORDER_PAYED.getStatus(), order, items));
        }
    }

    private Map<String,Object> refundOrder(CancelAndRefundOrderExtendsWithTxDTO dto){
        Map<String,Object> resultMap = new HashMap<>();
        //默认失败
        resultMap.put("result","fail");
        SoDTO order = dto.getOrder();
        List<SoItemDTO> items = dto.getItems();
        List<String> refundNoList = soRefundReadService.genSoRefundNO();
        String refundNo = refundNoList.get(1);
        //允许退现金最大金额
        BigDecimal canRefundCashAmount = order.getOrderPaidByCash().subtract(order.getRefundCash()!=null?order.getRefundCash():BigDecimal.ZERO);
        //允许退餐卡最大金额
        BigDecimal canRefundFubiAmount = order.getOrderPaidByFubi().subtract(order.getRefundFubi()!=null?order.getRefundFubi():BigDecimal.ZERO);
        //允许退积点最大金额
        BigDecimal canRefundJiDianAmount = order.getOrderPaidByJidian().subtract(order.getRefundJidian()!=null?order.getRefundJidian():BigDecimal.ZERO);
        //允许退购物卡最大金额
        BigDecimal canRefundBuyCardAmount = order.getOrderCardPaid().subtract(order.getRefundCard() !=null?order.getRefundCard():BigDecimal.ZERO);
        //所有可退金额的总和
        BigDecimal canRefundAmount = canRefundCashAmount.add(canRefundFubiAmount).add(canRefundJiDianAmount).add(canRefundBuyCardAmount);
        //根据公司找到退款顺序
        List<String> refundSortList = refundSort(order.getCompanyId());

        //计算当前需要退总金额
        BigDecimal soRefundAmount =BigDecimal.ZERO;
        for (SoItemDTO item : items) {
            soRefundAmount = soRefundAmount.add(item.getRefundAmount());
        }



        //若总的不够退，直接退出
        if(canRefundAmount.compareTo(soRefundAmount)<0){
            throw new BusinessException("超过最大可退款金额");
        }

        //剩余总体可退金额=总体可退-本次退款
        BigDecimal canSubRefundAmount = canRefundAmount.subtract(soRefundAmount);

        //需要退款现金
        BigDecimal soRefundCodeByCash = BigDecimal.ZERO;
        //需要退款积分/餐卡金额
        BigDecimal soRefundCodeByFubi = BigDecimal.ZERO;
        //需要退款积点
        BigDecimal soRefundCodeByJidian = BigDecimal.ZERO;
        //需要退款购物卡金额
        BigDecimal soRefundCodeByBuyCard = BigDecimal.ZERO;
        //剩余需要退金额
        BigDecimal soRefundSubAmount = soRefundAmount;

        //退款顺序减扣
        for (String accType : refundSortList) {
            if(accType.equals("cash")){
                soRefundCodeByCash = getRefundAmount(canRefundCashAmount,soRefundSubAmount);
                soRefundSubAmount = soRefundSubAmount.subtract(soRefundCodeByCash);
            }else if(accType.equals("jidian")){
                soRefundCodeByJidian = getRefundAmount(canRefundJiDianAmount,soRefundSubAmount);
                soRefundSubAmount = soRefundSubAmount.subtract(soRefundCodeByJidian);
            }else if(accType.equals("fubi")){
                soRefundCodeByFubi = getRefundAmount(canRefundFubiAmount,soRefundSubAmount);
                soRefundSubAmount = soRefundSubAmount.subtract(soRefundCodeByFubi);
            }else if(accType.equals("buyCard")){
                soRefundCodeByBuyCard = getRefundAmount(canRefundBuyCardAmount,soRefundSubAmount);
                soRefundSubAmount = soRefundSubAmount.subtract(soRefundCodeByBuyCard);
            }
        }

        logger.info("本次退款总体金额为:{}",soRefundAmount);
        logger.info("本次退款现金金额为:{}",soRefundCodeByCash);
        logger.info("本次退款积点金额为:{}",soRefundCodeByJidian);
        logger.info("本次退款积分金额为:{}",soRefundCodeByFubi);
        logger.info("本次退款购物卡金额为:{}",soRefundCodeByBuyCard);
        dto.setSoRefundCodeByCash(soRefundCodeByCash.toPlainString());
        dto.setSoRefundCodeByFubi(soRefundCodeByFubi.toPlainString());
        dto.setSoRefundCodeByJiDian(soRefundCodeByJidian.toPlainString());
        dto.setSoRefundCodeByBuyCard(soRefundCodeByBuyCard.toPlainString());
        //退现金
        boolean isFullRefund = false;
        if(dto.getAutoRefundCash() !=null && dto.getAutoRefundCash()){
            isFullRefund = refundCash(dto.getUserId(),order,order.getOrderPaidByCash(),items,refundNo);
        }
        //没有了可退金额代表着本次退款后，订单所有的金额全部退款，该订单就是退款订单
        if(canSubRefundAmount.compareTo(BigDecimal.ZERO) ==0){
            isFullRefund = true;
        }else{
            isFullRefund =false;
        }
        logger.info("可退金额:{}",canSubRefundAmount);
        logger.info("是否更新状态标志isFullRefund:{}",isFullRefund);
        SoDTO soDTO = buildEditSoDTO(order,dto, isFullRefund);

        //退到账户,调用接口退款,同时生成资金流水
        List<Map<String,String>> refundMap = soRefundWithTx(soDTO, dto.getReason(), dto.getUserId(), refundNo, refundNo,refundNo, true,dto.getThirdRefundCode(), dto.getReq());

        resultMap.put("isFullRefund",isFullRefund);
        resultMap.put("SoDTOChange",soDTO);
        resultMap.put("refundMap",refundMap);
        resultMap.put("refundNo",refundNo);
        resultMap.put("result","success");
        return resultMap;
    }

    private SoDTO buildEditSoDTO(SoDTO order,CancelAndRefundOrderExtendsWithTxDTO dto, boolean isFullRefund) {
        SoDTO soDTO = new SoDTO();
        soDTO.setId(order.getId());
        soDTO.setOrderCode(order.getOrderCode());
        soDTO.setOrderPayStatus(isFullRefund ? 2 : null);
        soDTO.setRefundCash(new BigDecimal(dto.getSoRefundCodeByCash()));
        soDTO.setRefundFubi(new BigDecimal(dto.getSoRefundCodeByFubi()));
        soDTO.setRefundJidian(new BigDecimal(dto.getSoRefundCodeByJiDian()));
        soDTO.setRefundCard(new BigDecimal(dto.getSoRefundCodeByBuyCard()));
        soDTO.setUserId(order.getUserId());
        soDTO.setOrderConfirmStatus(isFullRefund?2:null);
        soDTO.setOrderStatus(isFullRefund?OrderConstant.ORDER_STATUS_CANCELED.getStatus():null);
        soDTO.setPlatformId(order.getPlatformId());
        soDTO.setRefundVo(order.getRefundVo());
        return soDTO;
    }

    private BigDecimal getRefundAmount(BigDecimal canRefundMaxAmount,BigDecimal soRefundSubAmount){
        //fix-bug 2024-11-05 start 比如该订单已无需退现金，则可退现金max=0，就返回0
        //若最大可退的金额为0，则退0
        if(canRefundMaxAmount.compareTo(BigDecimal.ZERO) ==0){
            return BigDecimal.ZERO;
        }
        //最当前剩余可退金额为0，则退0,比如退到现金时已经,本次退款金额就退完了，后续就无需再退了
        if(soRefundSubAmount.compareTo(BigDecimal.ZERO) ==0){
            return BigDecimal.ZERO;
        }
        //fix-bug 2024-11-05

        //若最大可退现金大于需要退款的金额，那么需要退款金额就全部退现金
        if(canRefundMaxAmount.compareTo(soRefundSubAmount) >=0){
            return soRefundSubAmount;
        }
        return canRefundMaxAmount;
    }

    private void copyOldItemToNew(SoItemDTO item, SoItemDTO oldSoItemDTO) {
        item.setPuId(oldSoItemDTO.getPuId());
        item.setPuName(oldSoItemDTO.getPuName());
        item.setPuCount(oldSoItemDTO.getPuCount());
        item.setChildCode(oldSoItemDTO.getChildCode());
        item.setSoChildId(oldSoItemDTO.getSoChildId());
        item.setExternalProductId(oldSoItemDTO.getExternalProductId());
        item.setExternalSkuId(oldSoItemDTO.getExternalSkuId());
        item.setOrderCode(oldSoItemDTO.getOrderCode());
        item.setPrice(oldSoItemDTO.getPrice());
        item.setPlatformId(oldSoItemDTO.getPlatformId());
        item.setSource(oldSoItemDTO.getSource());
        item.setSnapshot(oldSoItemDTO.getSnapshot());
        item.setUserId(oldSoItemDTO.getUserId());
        item.setTaxCode(oldSoItemDTO.getTaxCode());
        item.setTaxRate(oldSoItemDTO.getTaxRate());
        item.setDeliveryFeeAver(oldSoItemDTO.getDeliveryFeeAver());
        item.setFinalPromotionAver(oldSoItemDTO.getFinalPromotionAver());
        item.setThirdChildCode(oldSoItemDTO.getThirdChildCode());
    }


    private List<String> refundSort(Long companyId){
        List<String> refundSortList = new ArrayList<>();
        refundSortList.add("cash");
        refundSortList.add("jidian");
        refundSortList.add("fubi");
        refundSortList.add("buyCard");
        return refundSortList;
    }

    public boolean refundCash(Long userId, SoDTO order, BigDecimal refundCashAmount, List<SoItemDTO> items, String soRefundCodeByCash) {
        boolean isFullRefund = true;
        if (order.getCashPayType() != null && refundCashAmount.compareTo(BigDecimal.ZERO)>0) {
            isFullRefund = false;
            if (order.getCashPayType() == 1) {
                // 支付宝
                // 设置退款包含的商品列表信息
                List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
                for (SoItemDTO soItemDTO : items) {
                    GoodsDetail goodsDetail = new GoodsDetail();
                    goodsDetail.setGoodsId(soItemDTO.getPuId().toString());
                    goodsDetail.setGoodsName(soItemDTO.getPuName());
                    goodsDetail.setQuantity(soItemDTO.getPuCount().longValue());
                    goodsDetail.setPrice(soItemDTO.getPrice().toString());
                    goodsDetailList.add(goodsDetail);
                }
                // 发送支付宝退款请求
                String errorCode = payUtil.alipayRefundOrder(order.getOrderCode(), soRefundCodeByCash,
                        refundCashAmount, userId, goodsDetailList);

                if (errorCode.equals(RefundErrorCodeConstant.COMMON_REFUND_SCUUESS.getErrorCode())) {
                    // 退款成功
                    isFullRefund = true;
                } else {
                    // 退款失败
                    // 发送预警邮件,走后台退款流程
                    payUtil.sendRefundWarnEmail(order.getOrderCode(), errorCode,
                            RefundErrorCodeConstant.translate(errorCode), Integer.valueOf(2));
                }

            } else if (order.getCashPayType() == 2) {
                // 微信
                // 查询微信的支付日志,获取微信退款订单编号
                PayWeixinLogDTO payWeixinLogDTO = payWeixinLogReadService
                        .queryPayWeixinLogByOrderCode(order.getOrderCode());

                if (payWeixinLogDTO == null){
                    throw new BusinessException("该订单没有微信支付日志,无法自动退款");
                }

                Integer signPlatform = order.getSignPlatform();
//                if (payUtil.getProperty("wx.app.id").equals(payWeixinLogDTO.getAppid())) {
//                    signPlatform = Integer.valueOf(3);
//                } else if (payUtil.getProperty("wx.app.id.native").equals(payWeixinLogDTO.getAppid())) {
//                    signPlatform = Integer.valueOf(1);
//                }
                if (Objects.isNull(signPlatform)){
                    signPlatform = 3;
                }

                // 设置请求参数
                Map<String, String> paraMap = new HashMap<>();
                paraMap.put("out_trade_no", payWeixinLogDTO.getOutTradeNo());
                paraMap.put("transaction_id", payWeixinLogDTO.getTransactionId());
                paraMap.put("out_refund_no", soRefundCodeByCash);
                // 订单金额,单位为分,int类型
                paraMap.put("total_fee", payUtil.priceDecimal2IntString(order.getOrderPaidByCash()));
                // 退款金额,单位为分,int类型
                paraMap.put("refund_fee", payUtil.priceDecimal2IntString(refundCashAmount));
                String url = payUtil.getProperty("wx.refund.url");

                // 发送微信退款请求
                Map<String, String> result = payUtil.sendWeixinPostRequest(paraMap, url, signPlatform, true,order.getPlatformId());
                String returnCode = result.get("return_code"); // 通信标识:SUCCESS/FAIL
                String returnMsg = result.get("return_msg"); // 返回信息
                String resultCode = result.get("result_code"); // 退款申请接收成功与否:SUCCESS/FAIL
                String errCode = result.get("err_code"); // 错误代码
                String errCodeDes = result.get("err_code_des"); // 错误代码描述

                if (returnCode.equals(RefundErrorCodeConstant.COMMON_REFUND_SCUUESS.getErrorCode())) {
                    if (resultCode.equals(RefundErrorCodeConstant.COMMON_REFUND_SCUUESS.getErrorCode())) {
                        // 退款成功
                        isFullRefund = true;
                    } else {
                        // 退款失败
                        // 发送预警邮件,走后台退款流程
                        payUtil.sendRefundWarnEmail(order.getOrderCode(), errCode, errCodeDes, Integer.valueOf(2));
                    }
                } else {
                    // 退款失败
                    // 发送预警邮件,走后台退款流程
                    payUtil.sendRefundWarnEmail(order.getOrderCode(), returnCode,
                            EmptyUtil.isNotEmpty(returnMsg) ? returnMsg : RefundErrorCodeConstant.translate(returnCode),
                            Integer.valueOf(2));
                }

            }
        }
        return isFullRefund;
    }

    private void checkIsVaild(CancelAndRefundOrderExtendsWithTxDTO dto){
        //检查需要退款的商品，item的属性不能为空
        itemBaseCheck(dto);
        //若订单信息为空，则查找到订单信息，回填订单
        SoDTO order = getSoDTO(dto);
        dto.setOrder(order);
        //检查账户是否合法,退款前检查账户是否异常
        checkAccountBeforeRefund(order);
    }

    private void itemBaseCheck(CancelAndRefundOrderExtendsWithTxDTO dto) {
        List<SoItemDTO> items =  dto.getItems();
        if(CollectionUtils.isEmpty(items)){
            throw new BusinessException("参数不合法,退款商品未明确");
        }
        for (SoItemDTO item : items) {
            if(item.getId() == null){
                throw new BusinessException("需要退款的订单项item的id不能为空");
            }
            if(item.getRefundAmount() ==null && item.getRefundCount() ==null){
                throw new BusinessException("需要退款的订单项item对应的退款金额和退款数量不能同时为空");
            }
            boolean isNullOrZeroTogether = !((item.getRefundCount() !=null && item.getRefundCount()>0) || (item.getRefundAmount() !=null && item.getRefundAmount().compareTo(BigDecimal.ZERO) >0));
            if(isNullOrZeroTogether){
                throw new BusinessException("需要退款的订单项item对应的退款金额和退款数量不能同时为空或0");
            }
        }
    }

    private SoDTO getSoDTO(CancelAndRefundOrderExtendsWithTxDTO dto){
        SoDTO order = dto.getOrder();
        if(order != null){
            fillOrderPaidAmount(order);
            return order;
        }
        Long itemId = dto.getItems().get(0).getId();
        SoItemDTO itemDTO = soItemReadService.getById(itemId);
        if(itemDTO == null){
            throw new BusinessException("未到订单项itemId"+itemId+"对应的商品信息");
        }
        order = soReadService.findSoById(itemDTO.getSoId());
        fillOrderPaidAmount(order);
        dto.setOrder(order);
        return order;
    }

    private void fillOrderPaidAmount(SoDTO order) {
        order.setOrderPaidByJidian(order.getOrderPaidByJidian() != null ? order.getOrderPaidByJidian() : BigDecimal.ZERO);
        order.setOrderPaidByCash(order.getOrderPaidByCash() != null ? order.getOrderPaidByCash() : BigDecimal.ZERO);
        order.setOrderPaidByFubi(order.getOrderPaidByFubi() != null ? order.getOrderPaidByFubi() : BigDecimal.ZERO);
    }

    private void checkAccountBeforeRefund(SoDTO soDTO) {
        Integer fubiError = Integer.valueOf(0);
        Integer cashError = Integer.valueOf(1);

        // 查询用户信息
        UserDTO userDTO = userReadService.findUserByID(soDTO.getUserId());
        if (userDTO == null) {
            payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), null, null, fubiError);
            // throw new BusinessException("用户不存在");
            throw new BusinessException("订单取消失败!");
        }

        // 查询用户积分账户和盐
        UserAccountDTO uaFubi = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), 0);
        if (uaFubi == null) {
            payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), userDTO.getMail(), null, fubiError);
            // throw new BusinessException("用户积分账户数据不存在");
            throw new BusinessException("订单取消失败!");
        }
        SaltDTO uaFubiSalt = saltReadService.querySaltByUUID(uaFubi.getUuid());
        if (uaFubiSalt == null) {
            payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), userDTO.getMail(), null, fubiError);
            // throw new BusinessException("用户积分账户加密盐数据不存在");
            throw new BusinessException("订单取消失败!");
        }

        // 查询迩格积分收入账户和盐
        CompanyAccountDTO caFubi = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),1);
        if (caFubi == null) {
            payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), "迩格积分收入账户", null, fubiError);
            // throw new BusinessException("迩格积分收入账户数据不存在");
            throw new BusinessException("订单取消失败!");
        }
        SaltDTO caFubiSalt = saltReadService.querySaltByUUID(caFubi.getUuid());
        if (caFubiSalt == null) {
            payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), caFubi.getName(), null, fubiError);
            // throw new BusinessException("迩格积分收入账户加密盐数据不存在");
            throw new BusinessException("订单取消失败!");
        }

        // 查询用户现金账户和盐
        UserAccountDTO uaCash = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), 3);
        if (uaCash == null) {
            payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), userDTO.getMail(), null, cashError);
            // throw new BusinessException("用户现金账户数据不存在");
            throw new BusinessException("订单取消失败!");
        }
        SaltDTO uaCashSalt = saltReadService.querySaltByUUID(uaCash.getUuid());
        if (uaCashSalt == null) {
            payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), userDTO.getMail(), null, cashError);
            // throw new BusinessException("用户现金账户加密盐数据不存在");
            throw new BusinessException("订单取消失败!");
        }

        // 查询迩格现金收入账户和盐
        CompanyAccountDTO caCash = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),2);
        if (caCash == null) {
            payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), "迩格现金收入账户", null, cashError);
            // throw new BusinessException("迩格现金收入账户数据不存在");
            throw new BusinessException("订单取消失败!");
        }
        SaltDTO caCashSalt = saltReadService.querySaltByUUID(caCash.getUuid());
        if (caCashSalt == null) {
            payUtil.sendRefundWarnEmail(soDTO.getOrderCode(), caCash.getName(), null, cashError);
            // throw new BusinessException("迩格现金入账户加密盐数据不存在");
            throw new BusinessException("订单取消失败!");
        }
    }

    /*
校验是否为正式公司
*/
    private Boolean checkCompany(Long companyId){
        CompanyDTO companyById = companyReadService.findCompanyById(companyId);
        if(EmptyUtil.isEmpty(companyById)){
            throw new BusinessException(companyById+"公司不存在");
        }
        if(companyById.getCompanyType()==0){
            return true;
        }else{
            return false;
        }
    }


    public List<Map<String,String>> soRefundWithTx(SoDTO soDTO, String reason, Long operatorId, String soRefundCodeByFubi,
                                       String soRefundCodeByCash,String soRefundCodeByJiDian, Boolean isCancel, String thirdRefundCode, HttpServletRequestDTO req) {

        List<Map<String,String>> refundNos=new ArrayList<>();
        Long fubiAccountBatchId = null;
        Long cashAccountBatchId = null;
        Long jidianAccountBatchId = null;
        Long buyCardAccountBatchId = null;
        if (soDTO.getRefundFubi().doubleValue() > 0) {
            List<CompanyConfigDTO> configs = userReadService.findUserCompanyConfigs(soDTO.getUserId());
            Integer refundType = 0;
            for(CompanyConfigDTO config : configs) {
                if(config.getKey().equalsIgnoreCase("account.refund.type")) {
                    refundType = Integer.valueOf(config.getValue());
                }
            }
            // 查询用户积分账户和盐
            UserAccountDTO uaFubi = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), refundType);
            if (uaFubi == null){
                throw new BusinessException("用户积分账户数据不存在");
            }

            SaltDTO uaFubiSalt = saltReadService.querySaltByUUID(uaFubi.getUuid());
            if (uaFubiSalt == null){
                throw new BusinessException("用户积分账户加密盐数据不存在");
            }

            // 查询迩格积分收入账户和盐
            CompanyAccountDTO caFubi = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),1);
            if (caFubi == null){
                throw new BusinessException("平台积分收入账户数据不存在");
            }

            SaltDTO caFubiSalt = saltReadService.querySaltByUUID(caFubi.getUuid());
            if (caFubiSalt == null){
                throw new BusinessException("平台积分收入账户加密盐数据不存在");
            }

            // 资金流动  平台--> 个人积分
            List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
            CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
            outFlowAcc.setAccountId(caFubi.getId());
            outFlowAcc.setSalt(caFubiSalt.getSaltValue());
            outFlowAcc.setSum(soDTO.getRefundFubi());
            outFlowAccs.add(outFlowAcc);
            List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
            CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
            inFlowAcc.setAccountId(uaFubi.getId());
            inFlowAcc.setSalt(uaFubiSalt.getSaltValue());
            inFlowAcc.setSum(soDTO.getRefundFubi());
            inFlowAccs.add(inFlowAcc);
            CashFlowResultDTO fubiFlowResultDTO = abWrteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 0, inFlowAccs, 1, true,
                    soDTO.getPlatformId(), FlowTypeConstant.OR_FUBI.getStatus(), soDTO.getId(), soDTO.getOrderCode(),
                    operatorId, FinBatchConstant.ORDER_REFUND_FUBI, null, reason, false, 0));

            fubiAccountBatchId = fubiFlowResultDTO.getBatchId();
            //SendInfoWriteService sendInfoWriteService = new SendInfoWriteServiceImpl(userReadService,provider,infoWriteService, infoTemplateReadService, infoTemplateSendWayReadService);
            // 发送积分变更消息

            InsertAndSendMessageDTO infoDto = new InsertAndSendMessageDTO();
            Map<String,String> infoMap = new HashMap<String,String>();
            infoDto.setInfoTemplateId(InfoConstant.USER_FUBI_CHANGE_INFO_ID.getStatus());
            infoDto.setUserId(soDTO.getUserId());
            infoMap.put("changeFuBi", soDTO.getRefundFubi().toPlainString());
            infoMap.put("changeCause", reason);
            infoDto.setParams(infoMap);
            sendInfoWriteService.insertUserFuBiInfoAndSend(infoDto);

        }
        if (soDTO.getRefundCash().doubleValue() > 0) {
            // 查询用户现金账户和盐
            UserAccountDTO uaCash = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), 3);
            if (uaCash == null){
                throw new BusinessException("用户现金账户数据不存在");
            }
            SaltDTO uaCashSalt = saltReadService.querySaltByUUID(uaCash.getUuid());
            if (uaCashSalt == null){
                throw new BusinessException("用户现金账户加密盐数据不存在");
            }
            // 查询迩格现金收入账户和盐
            CompanyAccountDTO caCash = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),2);
            if (caCash == null){
                throw new BusinessException("平台现金收入账户数据不存在");
            }
            SaltDTO caCashSalt = saltReadService.querySaltByUUID(caCash.getUuid());
            if (caCashSalt == null){
                throw new BusinessException("平台现金入账户加密盐数据不存在");
            }

            // 资金流动 平台现金-- >个人现金
            List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
            CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
            outFlowAcc.setAccountId(caCash.getId());
            outFlowAcc.setSalt(caCashSalt.getSaltValue());
            outFlowAcc.setSum(soDTO.getRefundCash());
            outFlowAccs.add(outFlowAcc);
            List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
            CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
            inFlowAcc.setAccountId(uaCash.getId());
            inFlowAcc.setSalt(uaCashSalt.getSaltValue());
            inFlowAccs.add(inFlowAcc);
            CashFlowResultDTO cashFlowResultDTO = abWrteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 0, inFlowAccs, 1, true,
                    soDTO.getPlatformId(), FlowTypeConstant.OR_CASH.getStatus(), soDTO.getId(), soDTO.getOrderCode(),
                    operatorId, FinBatchConstant.ORDER_REFUND_CASH, null, reason, false, 0));

            cashAccountBatchId = cashFlowResultDTO.getBatchId();
        }

        if (soDTO.getRefundJidian().doubleValue() > 0) {
            Integer refundType = 5;
            // 查询用户积分账户和盐
            UserAccountDTO uaFubi = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), refundType);
            if (uaFubi == null){
                throw new BusinessException("用户积点账户数据不存在");
            }

            SaltDTO uaFubiSalt = saltReadService.querySaltByUUID(uaFubi.getUuid());
            if (uaFubiSalt == null){
                throw new BusinessException("用户积点账户加密盐数据不存在");
            }

            // 查询迩格积分收入账户和盐
            CompanyAccountDTO caFubi = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),1);
            if (caFubi == null){
                throw new BusinessException("平台积点收入账户数据不存在");
            }

            SaltDTO caFubiSalt = saltReadService.querySaltByUUID(caFubi.getUuid());
            if (caFubiSalt == null){
                throw new BusinessException("平台积分收入账户加密盐数据不存在");
            }

            // 资金流动  平台--> 个人积分
            List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
            CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
            outFlowAcc.setAccountId(caFubi.getId());
            outFlowAcc.setSalt(caFubiSalt.getSaltValue());
            outFlowAcc.setSum(soDTO.getRefundJidian());
            outFlowAccs.add(outFlowAcc);
            List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
            CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
            inFlowAcc.setAccountId(uaFubi.getId());
            inFlowAcc.setSalt(uaFubiSalt.getSaltValue());
            inFlowAcc.setSum(soDTO.getRefundJidian());
            inFlowAccs.add(inFlowAcc);
            CashFlowResultDTO fubiFlowResultDTO = abWrteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 0, inFlowAccs, 1, true,
                    soDTO.getPlatformId(), FlowTypeConstant.OR_JIDIAN.getStatus(), soDTO.getId(), soDTO.getOrderCode(),
                    operatorId, FinBatchConstant.ORDER_REFUND_JIDIAN, null, reason, false, 0));

            jidianAccountBatchId = fubiFlowResultDTO.getBatchId();
        }
        if(soDTO.getRefundCard() !=null && soDTO.getRefundCard().doubleValue() >0){
            Integer refundType = 6;
            // 查询用户积分账户和盐
            UserAccountDTO uaFubi = uaReadService.queryUserAccountByUserIdAndType(soDTO.getUserId(), refundType);
            if (uaFubi == null){
                throw new BusinessException("用户积点账户数据不存在");
            }

            SaltDTO uaFubiSalt = saltReadService.querySaltByUUID(uaFubi.getUuid());
            if (uaFubiSalt == null){
                throw new BusinessException("用户积点账户加密盐数据不存在");
            }

            // 查询迩格积分收入账户和盐
            CompanyAccountDTO caFubi = caReadService.querySpecialCompanyAccountByType(soDTO.getPlatformId(),1);
            if (caFubi == null){
                throw new BusinessException("平台积点收入账户数据不存在");
            }

            SaltDTO caFubiSalt = saltReadService.querySaltByUUID(caFubi.getUuid());
            if (caFubiSalt == null){
                throw new BusinessException("平台积分收入账户加密盐数据不存在");
            }

            // 资金流动  平台--> 个人积分
            List<CashFlowAccountDTO> outFlowAccs = new ArrayList<>();
            CashFlowAccountDTO outFlowAcc = new CashFlowAccountDTO();
            outFlowAcc.setAccountId(caFubi.getId());
            outFlowAcc.setSalt(caFubiSalt.getSaltValue());
            outFlowAcc.setSum(soDTO.getRefundCard());
            outFlowAccs.add(outFlowAcc);
            List<CashFlowAccountDTO> inFlowAccs = new ArrayList<>();
            CashFlowAccountDTO inFlowAcc = new CashFlowAccountDTO();
            inFlowAcc.setAccountId(uaFubi.getId());
            inFlowAcc.setSalt(uaFubiSalt.getSaltValue());
            inFlowAcc.setSum(soDTO.getRefundCard());
            inFlowAccs.add(inFlowAcc);
            CashFlowResultDTO fubiFlowResultDTO = abWrteService.unifiedCashFlow(new UnifiedCashFlowDTO(outFlowAccs, 0, inFlowAccs, 1, true,
                    soDTO.getPlatformId(), FlowTypeConstant.OR_CGK.getStatus(), soDTO.getId(), soDTO.getOrderCode(),
                    operatorId, FinBatchConstant.ORDER_REFUND_CGK, null, reason, false, 0));

            buyCardAccountBatchId = fubiFlowResultDTO.getBatchId();
        }
        UserDTO userDTO = userReadService.findUserByID(soDTO.getUserId());
        boolean isDlfUser = false;
        if(userDTO !=null && EmptyUtil.isNotEmpty(userDTO.getChannelSource()) && userDTO.getChannelSource().equals(UserChannelSourceEnum.DLF.getChannelSource())){
            isDlfUser = true;
        }
        if(isCancel){
            // 生成积分退款单(有积分支付)
            if (fubiAccountBatchId != null) {
                SoRefundDTO soRefundDTOByFubi = new SoRefundDTO();
                soRefundDTOByFubi.setSoRefundCode(soRefundCodeByFubi);
                soRefundDTOByFubi.setSoId(soDTO.getId());
                soRefundDTOByFubi.setCreatorId(operatorId);
                soRefundDTOByFubi.setUserId(soDTO.getUserId());
                soRefundDTOByFubi.setSoRefundReason(reason);
                soRefundDTOByFubi.setFubiAccountBatchId(fubiAccountBatchId);
                soRefundDTOByFubi.setSoRefundByFubi(soDTO.getRefundFubi());
                soRefundDTOByFubi.setPlatformId(soDTO.getPlatformId());
                soRefundDTOByFubi.setThirdRefundCode(thirdRefundCode);
                soRefundDTOByFubi.transferByVo(soDTO.getRefundVo());
                if(isDlfUser){
                    soRefundDTOByFubi.setRefundState(0);
                }
                insertSoRefundAndLogWithTx(soRefundDTOByFubi,soDTO.getOrderCode(), req);
                Map<String,String> map = new HashMap<>();
                map.put("fubiCode",soRefundCodeByFubi);
                refundNos.add(map);
            }
            // 生成现金退款单(有现金支付且现金退款成功)
            if (cashAccountBatchId != null) {
                SoRefundDTO soRefundDTOByCash = new SoRefundDTO();
                soRefundDTOByCash.setSoRefundCode(soRefundCodeByCash);
                soRefundDTOByCash.setSoId(soDTO.getId());
                soRefundDTOByCash.setCreatorId(operatorId);
                soRefundDTOByCash.setUserId(soDTO.getUserId());
                soRefundDTOByCash.setSoRefundReason(reason);
                soRefundDTOByCash.setCashAccountBatchId(cashAccountBatchId);
                soRefundDTOByCash.setSoRefundByCash(soDTO.getRefundCash());
                soRefundDTOByCash.setPlatformId(soDTO.getPlatformId());
                soRefundDTOByCash.setThirdRefundCode(thirdRefundCode);
                soRefundDTOByCash.transferByVo(soDTO.getRefundVo());
                insertSoRefundAndLogWithTx(soRefundDTOByCash,soDTO.getOrderCode(), req);
                Map<String,String> map = new HashMap<>();
                map.put("cashCode",soRefundCodeByFubi);
                refundNos.add(map);

            }
            //生成积点退款单
            if (jidianAccountBatchId != null) {
                SoRefundDTO soRefundDTOByJiDian = new SoRefundDTO();
                soRefundDTOByJiDian.setSoRefundCode(soRefundCodeByJiDian);
                soRefundDTOByJiDian.setSoId(soDTO.getId());
                soRefundDTOByJiDian.setCreatorId(operatorId);
                soRefundDTOByJiDian.setUserId(soDTO.getUserId());
                soRefundDTOByJiDian.setSoRefundReason(reason);
                soRefundDTOByJiDian.setJidianAccountBatchId(jidianAccountBatchId);
                soRefundDTOByJiDian.setSoRefundByJidian(soDTO.getRefundJidian());
                soRefundDTOByJiDian.setPlatformId(soDTO.getPlatformId());
                soRefundDTOByJiDian.setThirdRefundCode(thirdRefundCode);
                soRefundDTOByJiDian.transferByVo(soDTO.getRefundVo());
                insertSoRefundAndLogWithTx(soRefundDTOByJiDian,soDTO.getOrderCode(), req);
                Map<String,String> map = new HashMap<>();
                map.put("jiDianCode",soRefundCodeByFubi);
                refundNos.add(map);
            }
            //生成卡劵退款单
            if (buyCardAccountBatchId != null) {
                SoRefundDTO soRefundDTOByBuyCard = new SoRefundDTO();
                soRefundDTOByBuyCard.setSoRefundCode(soRefundCodeByJiDian);
                soRefundDTOByBuyCard.setSoId(soDTO.getId());
                soRefundDTOByBuyCard.setCreatorId(operatorId);
                soRefundDTOByBuyCard.setUserId(soDTO.getUserId());
                soRefundDTOByBuyCard.setSoRefundReason(reason);
                soRefundDTOByBuyCard.setBuyCardAccountBatchId(jidianAccountBatchId);
                soRefundDTOByBuyCard.setSoRefundByBuyCard(soDTO.getRefundCard());
                soRefundDTOByBuyCard.setPlatformId(soDTO.getPlatformId());
                soRefundDTOByBuyCard.setThirdRefundCode(thirdRefundCode);
                soRefundDTOByBuyCard.transferByVo(soDTO.getRefundVo());
                insertSoRefundAndLogWithTx(soRefundDTOByBuyCard,soDTO.getOrderCode(), req);
                Map<String,String> map = new HashMap<>();
                map.put("buyCardCode",soRefundCodeByFubi);
                refundNos.add(map);
            }
        }else{
            // 生成积分和现金退款单(有积分支付,有现金支付且现金退款成功)
            SoRefundDTO soRefundDTO = new SoRefundDTO();
            soRefundDTO.setSoRefundCode(soRefundCodeByFubi);
            soRefundDTO.setSoId(soDTO.getId());
            soRefundDTO.setCreatorId(operatorId);
            soRefundDTO.setUserId(soDTO.getUserId());
            soRefundDTO.setSoRefundReason(reason);
            soRefundDTO.setFubiAccountBatchId(fubiAccountBatchId != null ? fubiAccountBatchId : null);
            soRefundDTO.setSoRefundByFubi(fubiAccountBatchId != null ? soDTO.getRefundFubi() : null);
            soRefundDTO.setCashAccountBatchId(cashAccountBatchId != null ? cashAccountBatchId : null);
            soRefundDTO.setSoRefundByCash(cashAccountBatchId != null ? soDTO.getRefundCash() : null);
            soRefundDTO.setJidianAccountBatchId(jidianAccountBatchId != null ? jidianAccountBatchId : null);
            soRefundDTO.setSoRefundByJidian(jidianAccountBatchId != null ? soDTO.getRefundJidian() : null);
            soRefundDTO.setBuyCardAccountBatchId(buyCardAccountBatchId !=null?buyCardAccountBatchId:null);
            soRefundDTO.setSoRefundByBuyCard(buyCardAccountBatchId !=null?soDTO.getRefundCard():null);
            soRefundDTO.setThirdRefundCode(thirdRefundCode);
            soRefundDTO.transferByVo(soDTO.getRefundVo());
            if(isDlfUser && Objects.nonNull(soRefundDTO.getSoRefundByFubi()) &&
                    soRefundDTO.getSoRefundByFubi().compareTo(BigDecimal.ZERO)>0){
                soRefundDTO.setRefundState(0);
            }
            insertSoRefundAndLogWithTx(soRefundDTO,soDTO.getOrderCode(), req);
        }

        return refundNos;
    }

    /**
     * 新增退款单以及生成退款单日志
     *
     * @param dto
     * @param req
     */
    private void    insertSoRefundAndLogWithTx(SoRefundDTO dto,String orderCode, HttpServletRequestDTO req) {
        saveBuyCardUseDetail(dto,orderCode);
        // 插入退款单
        Long soRefundId = soRefundWriteService.insertSoRefundWithTx(dto);

        // 记录生成退款单日志
        SoRefundDTO soRefundDTO = new SoRefundDTO();
        soRefundDTO.setId(soRefundId);
        SoRefundDTO soRefundDTO_ = soRefundReadService.findSoRefundById(soRefundDTO);
        EgeoLog log = new EgeoLog();
        log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
        log.setOperObject("SoRefundNewManageImpl_soRefundWithTx");
        log.setMsgId(LogConstant.ORDER_REFUND_NEW.getStatus());
        log.setType(LogTypeConstant.SO_REFUND.getStatus());
        log.setOperatorObjId(soRefundDTO_.getId());
        log.setOperatorObjCode(soRefundDTO_.getSoRefundCode());
        log.setNewObj(soRefundDTO_);

        EgeoBusinessLogCommon.fillLogValueForBasic(log, req);

        try {
            ActiveMQUtils.recordBusinessLog(log);
            //logger.info("新增退款单以及生成退款单日志进队列改为打印日志:{}",JSON.toJSONString(log));
        }catch (Exception e) {
            // TODO: handle exception
            logger.error("发送日志消息失败："+ JSON.toJSONString(log));
        }
    }



    private void saveBuyCardUseDetail(SoRefundDTO dto,String orderCode){
        if(dto.getSoRefundByBuyCard()==null || dto.getSoRefundByBuyCard().compareTo(BigDecimal.ZERO) <=0){
            return;
        }
        List<SoRefundItemDTO> list = dto.getSoRefundItemDTOS();
        if(CollectionUtils.isEmpty(list)){
            logger.error("订单id:{},订单:{}未找到订单项退款",dto.getSoId(),orderCode);
            return;
        }
        List<BuyCardItemRefundDTO> dtos = new ArrayList<>();
        for (SoRefundItemDTO soRefundItemDTO : list) {
            BuyCardItemRefundDTO buyCardItemRefundDTO = new BuyCardItemRefundDTO();
            buyCardItemRefundDTO.setItemId(soRefundItemDTO.getSoItemId());
            buyCardItemRefundDTO.setRefundAmount(soRefundItemDTO.getRefundAmount());
            buyCardItemRefundDTO.setRefundNo(soRefundItemDTO.getRefundCode());
            buyCardItemRefundDTO.setRemark(dto.getSoRefundReason());
            buyCardItemRefundDTO.setSoId(dto.getSoId());
            dtos.add(buyCardItemRefundDTO);
        }

        if(!CollectionUtils.isEmpty(dtos)){
            BuyCardRefundReqVO reqVO = new BuyCardRefundReqVO();
            reqVO.setDtos(dtos);
            buyCardClient.buyCardRefund(reqVO);
        }

    }
}
