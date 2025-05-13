package com.egeo.components.order.scheduler;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.egeo.components.order.business.PushOrderManage;
import com.egeo.components.promotion.client.BuyCardClient;
import com.egeo.components.promotion.dto.UserCardRecordDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.egeo.common.LogConstant;
import com.egeo.common.LogTypeConstant;
import com.egeo.exception.BusinessException;
import com.egeo.components.order.controller.api.SoDeliveryAction;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.facade.DeliveryCompanyFacade;
import com.egeo.components.order.facade.SoPackageFacade;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.log.EgeoLog;
import com.egeo.utils.ActiveMQUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.LogUtil;

@Component
@ConditionalOnResource(resources= {"file:./scheduler.properties"})
@PropertySource(value = {"file:./scheduler.properties"},ignoreResourceNotFound = false, encoding = "UTF-8", name = "scheduler.properties")
public class SoSchedulerJob {

	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource(name = "soShedulerFacade")
	private SoShedulerFacade soFacade;

	@Resource(name = "soPackageFacade")
	private SoPackageFacade soPackageFacade;

	@Resource(name = "userShedulerFacade")
	private UserShedulerFacade userFacade;

	@Resource(name = "userQuitShedulerFacade")
	private UserQuitShedulerFacade userQuitFacade;

	@Autowired
	private DeliveryCompanyFacade deliveryCompanyFacade;

	@Resource(name = "accountShedulerFacade")
	private AccountShedulerFacade accountFacade;

	@Resource(name = "couponShedulerFacade")
	private CouponShedulerFacade couponFacade;

	@Resource(name = "esCardShedulerFacade")
	private EsCardShedulerFacade esCardFacade;

	@Resource(name = "productShedulerFacade")
	private ProductShedulerFacade productFacade;
	@Autowired
	private SoDeliveryAction soDelivery;
	@Resource
	private QingMeiOrderScheduleFacade qingMeiOrderScheduleFacade;

	@Resource
	private PushOrderManage pushOrderManage;

	@Resource
	private BuyCardClient buyCardClient;

	@Value("${order.delivery.refresh:3}")
	private int before ;

	@Autowired
	private JedisUtil jedisUtil;
	public SoSchedulerJob(){
		System.out.println("启动定时器2...");
	}
	/*
	 * 一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。 按顺序依次为 1 秒（0~59） 2 分钟（0~59） 3 小时（0~23） 4
	 * 天（0~31） 5 月（0~11） 6 星期（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
	 * 7.年份（1970－2099）
	 */
	// 每5分钟执行一次 0 0/5 * * * ?
	@Scheduled(cron = "${scheduled.cron.cancelSoJob}")
	public void cancelSoJob() {
		logger.info("定时器任务:取消支付超时订单");
		//查询所有未支付且创建时间已经超过30分钟的订单
		List<SoDTO> soDtoList = soFacade.findAllunpayOrders();
		if (EmptyUtil.isNotEmpty(soDtoList)) {
			for (SoDTO soDTO : soDtoList) {
				logger.info("soList:"+soDTO.getId());
				try {
					// 订单取消日志记录
					SoDTO oldSoDTO = soFacade.findSoById(soDTO.getId());
					SoChildDTO soChildDTO = new SoChildDTO();
					soChildDTO.setSoId(soDTO.getId());

					// 改变订单状态
					soFacade.changeOrderStatus(soDTO.getOrderCode(),soDTO.getId(), OrderConstant.ORDER_STATUS_CANCELED.getStatus(),soDTO.getUserId());
					List<SoItemDTO> list = soFacade.findSoItemListByOrderCode(soDTO.getId());

					SoDTO newSoDTO = soFacade.findSoById(soDTO.getId());
					//如果是以旧换新的订单,修改记录表
					if(newSoDTO.getSaleWay()==8){
						ExchangeOrderRecordDTO recordDTO = new ExchangeOrderRecordDTO();
						recordDTO.setConversionStatus(Integer.valueOf(2));
						recordDTO.setOrderCode(newSoDTO.getOrderCode());
						int rt=soFacade.updateExchangeOrderRecordByOrderCodeWithTx(recordDTO);

						//如果是以旧换新订单需要释放旧unit
						List<ExchangeOrderRecordDTO> exchangeOrderRecordByOrderCode = soFacade.findExchangeOrderRecordByOrderCode(newSoDTO.getOrderCode());
						if(EmptyUtil.isEmpty(exchangeOrderRecordByOrderCode)||exchangeOrderRecordByOrderCode.size()>1){
							logger.info("当前订单对应的以旧换新记录有误,orderCode="+newSoDTO.getOrderCode());
							throw new BusinessException("当前订单对应的以旧换新记录有误,请联系管理员");
						}
						jedisUtil.delLock(exchangeOrderRecordByOrderCode.get(0).getOldUnitCode());
						logger.info("定时任务解锁旧unit");
						//soFacade.updateCouponUnitRemoveLock(exchangeOrderRecordByOrderCode.get(0).getOldUnitCode());

					}
					if(newSoDTO.getOrderStatus() == 10){
						// 解锁冻结的库存
						soFacade.unFreeseOrderStock(list, soDTO.getOrderCode());

						// 优惠卷相关
						soFacade.updateCouponByCancelOrderWithTx(soDTO.getId());

					}

					if (!oldSoDTO.getOrderStatus().equals(newSoDTO.getOrderStatus())) {
						// 判断是否修改了订单状态
						EgeoLog log = new EgeoLog();
						log.setModuleName(LogConstant.ORDER_MANAGEMENT.getComment());
						log.setOperObject("SoSchedulerJob_cancelSoJob");
						log.setMsgId(LogConstant.ORDER_CANCEL.getStatus());
						log.setType(LogTypeConstant.SO.getStatus());
						log.setOperatorObjId(newSoDTO.getId());
						log.setOperatorObjCode(newSoDTO.getOrderCode());
						log.setOldObj(oldSoDTO);
						log.setNewObj(newSoDTO);
						log.setMsgContent(JSON.toJSONString(LogUtil.getObjDifference(log.getOldObj(), log.getNewObj())));
						log.setTime(new Date());
						log.setPlatformId(newSoDTO.getPlatformId());
						ActiveMQUtils.recordBusinessLog(log);
						logger.info("记录订单自动取消日志成功,订单id: " + newSoDTO.getId());
						pushOrderManage.pushOrderInfo(newSoDTO.getId(),null,null);
						pushOrderManage.pushOrderToThird(newSoDTO.getId(),"自动取消");
					}

				} catch (Exception e) {
					logger.error("OrderCode = " + soDTO.getOrderCode() + ":" + e.getMessage());
				}
			}
		}

		logger.info("定时任务:刷新订单支付状态");
		soFacade.changeSoPayStatusJob();
	}

	@Scheduled(cron = "${scheduled.cron.soDeliveryRefresh}")
	public void soDeliveryRefresh() {
		logger.info("定时器任务:刷新订单快递信息");
		//查询所有未支付且创建时间已经超过30分钟的订单
		soPackageFacade.refreshSoPackageUnReceive(before);

	}
	/**
	 * 每日0点执行, user,userExtend,userCount离职、资产回收日期
	 */
	@Scheduled(cron = "${scheduled.cron.userInvalid}")
	//@Scheduled(cron="0 0/5 * * * ?" ) //做测试用
	public void userInvalid() {
		logger.info("定时任务:用户离职、资产回收");
		userQuitFacade.aboutUserInvalid();
	}

	/**
	 * 订单超过14天定时确认收货
	 */
	@Scheduled(cron = "${scheduled.cron.confirmReceiptSoJob}")
	//@Scheduled(cron="0 0/5 * * * ? " )//作为测试使用
	public void confirmReceiptSoJob() {
		logger.info("定时任务:自动确认收货");
		soFacade.confirmReceiptSoJob();
	}

	/**
	 * 每日0点执行,刷新渠道的日下载量
	 */
	@Scheduled(cron = "${scheduled.cron.refreshChannelDailyDownloadCount}")
	// @Scheduled(cron="0 0/2 * * * ? " ) //做测试用
	public void refreshChannelDailyDownloadCount() {
		logger.info("定时任务:刷新渠道日下载量");
		userFacade.refreshDownloadDailyDownloadCount();
	}

	/**
	 * 每隔五分钟请求券仓购买卡券结果
	 */
	@Scheduled(cron = "${scheduled.cron.confirmQCResult}")
	public void confirmQCResult() {
		logger.info("定时任务:请求券仓购买卡券结果");
		soFacade.confirmQCResult();
	}

    /**
     * 隐藏超过已领取日期的优惠卷
     */
    @Scheduled(cron = "${scheduled.cron.hideCouponBatch}")
	//@Scheduled(cron="0 0/5 * * * ?" ) //做测试用
    public void hideCouponBatch() {
    	logger.info("定时任务:隐藏超过已领取日期的优惠卷");
    	couponFacade.hideCouponBatch();
    }

    /**
     * 失效已过期的unit
     */
	@Scheduled(cron = "${scheduled.cron.cardIsValid}")
	//    @Scheduled(cron="0 0/5 * * * ?" ) //做测试用
    public void cardIsValid() {
    	logger.info("定时任务:失效以过期的unit");
    	esCardFacade.cardIsValid();
    }

    /**
     * 更新su搜索表数据
     */
    @Scheduled(cron = "${scheduled.cron.syncSaveSuSerachRule}")
//     @Scheduled(cron="0 0/2 * * * ? " ) //做测试用
    public void syncSaveSuSerachRule() {
    	logger.info("定时任务:更新su搜索表数据");
    	productFacade.syncSaveSuSerachRule();
    }

    /**
	 * 发送订单履约预警邮件，每天 8:30 发送
	 */
	@Scheduled(cron = "${scheduled.cron.sendOrderWarningMail}")
	//@Scheduled(cron="0 0/5 * * * ?" ) //做测试用

	public void sendOrderWarningMail() {
		logger.info("定时任务:发送订单履约预警邮件");
		try {
			soFacade.sendOrderWarningMail();
		} catch (Exception e) {
			logger.error("发送订单履约预警邮件异常", e);
		}
	}

	/**
	 * 当 Unit 剩余有效期=预警期+1 天时，系统将自动在当天上午 8:30 分
	 * 统一发出提醒邮件。
	 */
	@Scheduled(cron = "${scheduled.cron.sendprecautiousWarningMail}")
	public void sendprecautiousWarningMail() {
		logger.info("定时任务:发送失效预警邮件");
		try {
			soFacade.sendPrecautiousWarningMail(2L);
			soFacade.sendPrecautiousWarningMail(7L);
		} catch (Exception e) {
			logger.error("发送失效预警邮件异常", e);
		}
	}

//	@Scheduled(cron = "0 3/5 * * * ?")
	@Scheduled(cron = "${scheduled.cron.sendMembershipNotify}")
	public void sendMembershipNotify() {
		logger.info("定时任务:发送会籍失效提醒");
		try {
			productFacade.sendMembershipNotify(30);
			productFacade.sendMembershipNotify(1);
		} catch (Exception e) {
			logger.error("发送会籍失效提醒异常", e);
		}
	}

//	@Scheduled(cron = "0 3/5 * * * ?")
	@Scheduled(cron = "${scheduled.cron.sendMembershipAuthorityChangeNotify}")
	public void sendMembershipAuthorityChangeNotify() {
		logger.info("定时任务:发送会籍权限变更提醒");
		try {
			productFacade.sendMembershipAuthorityChangeNotify();
		} catch (Exception e) {
			logger.error("发送会籍权限变更提醒异常", e);
		}
	}


	/**
	 * 每隔五分钟请求话费充值结果
	 */
	@Scheduled(cron = "${scheduled.cron.confirmRechargeResult}")
	public void confirmRechargeResult() {
		logger.info("定时任务线下1010:请求话费充值结果");
		soFacade.confirmRechargeResult();
	}
	/**
	 * 每一个小时执行,查询京东支付情况
	 */
	@Scheduled(cron = "${scheduled.cron.jdOrderPayStatus}")
	public void jdOrderPayStatus() {
		logger.info("定时任务1010:查询京东支付情况");
		soFacade.jdOrderPayStatus(jedisUtil);
	}



	/**
	 * 每5分钟进行一次预占库存
	 */
	@Scheduled(cron = "${scheduled.cron.jdOrderConfirm}")
	public void jdOrderConfirm() {
		logger.info("定时任务pre:每5分钟进行一次预占库存");
		soFacade.jdOrderConfirm();
	}



	/**
	 * 每天8点半检查京东账户余额
	 * 统一发出提醒邮件。
	 */
	@Scheduled(cron = "${scheduled.cron.checkJdAccount}")
	public void checkJdAccount() {
		logger.info("定时任务:检查京东账户余额");
		soFacade.checkJdAccount(jedisUtil);
	}



	/**
	 * 每一个小时执行,查询京东订单状态
	 */
	@Scheduled(cron = "${scheduled.cron.jdOrderStatus}")
	//测试
	//@Scheduled(cron = "0 0/5 * * * ? ")
	public void jdOrderStatus() {
		logger.info("定时任务:查询京东订单状态");
        try {
            soFacade.jdOrderStatus(jedisUtil);
        }catch (BusinessException businessException){
            logger.error("jdOrderStatus:{}", businessException.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("jdOrderStatus:{}", e);
        }
	}

	/**
	 * 每一个小时执行,同步清美订单支付结果
	 */
	@Scheduled(cron = "${scheduled.cron.syncQMPayStatus}")
	//@Scheduled(cron = "0 0/5 * * * ? ")
	public void syncQMSuccessPayOrders() {
		logger.info("定时任务:同步清美订单支付结果");
		qingMeiOrderScheduleFacade.syncSuccessPayOrders();
	}

	@Scheduled(cron = "${scheduled.cron.cancelUserCard}")
	public void cancelUserCard() {
		logger.info("定时器任务:卡劵过期");
		buyCardClient.cancelUserCard(new UserCardRecordDTO());

	}
}
