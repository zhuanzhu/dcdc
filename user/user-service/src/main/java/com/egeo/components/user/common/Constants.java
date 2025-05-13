package com.egeo.components.user.common;

/**
 * 常量类
 * 
 * @author jinjin 2017-08-20
 */
public final class Constants {
	public static int tick = 1;
	public static boolean flag = true;

	public static final int IOS = 1;
	public static final int Android = 2;
	public static final int Wap = 3;
	public static final int Offline = 4;

	/** 正则system */
	public static final String REGEX_SYSTEM = "cst";
	/** 正则server */
	public static final String REGEX_SERVER = "common";
	/** 正则手机key */
	public static final String REGEX_PHONE = "phoneReg";
	/** 正则身份证key */
	public static final String REGEX_IDCARD = "idCardReg";
	/** 正则个人银行卡key */
	public static final String REGEX_PERSONBANK = "personBankReg";
	/** 正则对公银行卡key */
	public static final String REGEX_ENTERPRISEBANK = "enterpriseBankReg";
	/** 正则统一社会信用代码key */
	public static final String REGEX_ENTERPRISECODE = "enterpriseCodeReg";

	/** 进件appCode */
	public static final String REG_HEADER_APPCODE = "cst";
	/** 进件channelId */
	public static final String REG_HEADER_CHANNELID = "1000";
	/** 进件deviceType */
	public static final String REG_HEADER_DEVICETYPE = "0";

	/** 企业信息 */
	public static final String TYPE_ENTERPRISE_INFO = "enterprise_info";
	/** 企业办公电话 */
	public static final String TYPE_ENTERPRISE_OFFICE_TELEPHONE = "enterprise_office_telephone";
	/** 财务负责人 */
	public static final String TYPE_FINANCE_CHIEF = "finance_chief";
	/** 紧急联系信息 */
	public static final String TYPE_EMERGENCY = "emergency_contact_detail";
	/** 对公银行信息 */
	public static final String TYPE_ENTERPRISE_BANK_INFO = "enterprise_bank_info";
	/** 股东信息 */
	public static final String TYPE_SHAREHOLDERLIST = "share_holder_list";
	/** 法人代表证明 */
	public static final String TYPE_LEGAL_REPRESENTATIVE = "legal_representative";
	/** 央行征信报告 */
	public static final String TYPE_LEGAL_REPRESENTATIVE_PBC_CREDIT_REPORT = "legal_representative_PBC_credit_report";
	/** 企业资金流水 */
	public static final String TYPE_BANK_STATEMENT = "bank_statement";
	/** 房产或车辆财产证明 */
	public static final String TYPE_FINANCIAL_CERTIFICATION = "financial_certification";
	/** 企业征信报告 */
	public static final String TYPE_ENTERPRISE_PBC_CREDIT_REPORT = "enterprise_PBC_credit_report";
	/** 企业资产负债表 */
	public static final String TYPE_BALANCE_SHEET = "balance_sheet";

	/** 个人基本信息 */
	public static final String TYPE_PERSON = "personDetail";
	/** 担保人信息 */
	public static final String TYPE_GUARANTOR = "guarantorDetail";
	/** 直系亲属信息 */
	public static final String TYPE_FAMILY = "familyDetail";
	/** 征信信息 */
	public static final String TYPE_CREDIT_INFO = "creditInfo";
	/** 百融报告 */
	public static final String TYPE_BAIRONG_REPORT = "bairongReport";
	/** 违法涉诉信息 */
	public static final String TYPE_WFSS_INFO = "wfssInfo";
	/** 违法涉诉信息 */
	public static final String TYPE_SDXD_INFO = "sdxdInfo";
	/** 在网时长信息 */
	public static final String TYPE_ZWSC_INFO = "zwscInfo";
	/** 手机实名认证信息 */
	public static final String TYPE_MREALNAME_INFO = "mrealInfo";
	/** 年龄信息 */
	public static final String TYPE_AGE_INFO = "ageInfo";
	/** 特殊名单 */
	public static final String TYPE_TSMD_INFO = "tsmdInfo";
	/** 多头借贷意向 */
	public static final String TYPE_DUOTOU_INFO = "duotouInfo";

	public static final String USER_TYPE_PERSON = "1";

	public static final String USER_TYPE_COMPANY = "2";
	/** 通用状态 0 */
	public static final int STATUS_UNIVERSAO = 0;
	/** 通用状态 1 */
	public static final int STATUS_UNIVERSAT = 1;
	/** 通用状态 2 */
	public static final int STATUS_UNIVERSAE = 2;
	/** 通用状态 3 */
	public static final int STATUS_UNIVERSAR = 3;
	/** 未生效 */
	public static final int STATUS_INVALID = 0;
	/** 用户填写中 */
	public static final int STATUS_FILLING = 10;
	/** 审核中 */
	public static final int STATUS_AUDITING = 20;
	/** 审核通过&待放款 */
	public static final int STATUS_PASSED = 30;
	/** 审核拒绝 */
	public static final int STATUS_REJECTED = 40;
	/** 已放款&待还款 */
	public static final int STATUS_WAITING_REPAY = 50;
	/** 逾期 融时期（超过期限5天内） */
	public static final int STATUS_OUT_OF_DATE = 60;
	/** 已还款 */
	public static final int STATUS_REPAIED = 70;
	/** 逻辑删除,保留数据 */
	public static final int STATUS_REVOKE = 80;
	/** 复贷 默认0 - 1 */
	public static final int STATUS_COMPLEX_LOAN = 0;
	/** 同盾设备 */
	public static final String BLACK_BOX_NAME = "同盾安全设备";
	/** 同盾设备 */
	public static final int BLACK_BOX_KEY = 0;
	/** 百荣 */
	public static final String BAIRONG_NAME = "百荣设备";
	/** 百荣设备 */
	public static final int BAIRONG_KEY = 1;
	/** 白骑士 */
	public static final String BAIQISHI_NAME = "白骑士设备";
	/** 白骑士设备 */
	public static final int BAIQISHI_KEY = 1;
	/** 银行ioc路径 */
	public static final String BANK_IMG_URL = "http://106.14.239.167";
	/** 协议类型 0:服务 1:借款 */
	public static final int PROTOCOL_TYPE_F = 0;
	/** 协议类型 0:服务 1:借款 */
	public static final int PROTOCOL_TYPE_J = 1;
	/** 短信验证码 */
	public static final String SENDCODEKEY = "SENDOMVALIDATECODEKEY";// 放到session中的key
	/** 图片验证码 */
	public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";

	/** 操作类型 0-注册 */
	public static final int HANDLE_TYPE_ZC = 0;
	/** 操作类型 1-登录 */
	public static final int HANDLE_TYPE_DL = 1;

	public static final String Phone = "XXXX-XXXX-XXXX";

	public static final int AREA_CODE_NOT_FOUND = 300;

	public static final String USERINFO_CACHE = "userinfo:";
	public static final String UNIAUTH_USERINFO_CACHE = "UniAuthUserinfo:";
	public static final String OPERATOR_USERINFO_CACHE = "OperatorUserinfo:";

	public static final String USER_ACCESS_TOKEN = "AccessToken:";
	public static final String UNIAUTH_USER_ACCESS_TOKEN = "UniAuthAccessToken:";
	public static final String OPERATOR_ACCESS_TOKEN = "OperatorAccessToken:";

	public static final String USER_REFRESH_TOKEN = "RefreshToken:";
	public static final String UNIAUTH_USER_REFRESH_TOKEN = "UniAuthRefreshToken:";
	public static final String OPERATOR_REFRESH_TOKEN = "OperatorRefreshToken:";

	public static final String DATA_PERMISSION = "data_permission:";

	public static final String SMS_CODE_VALIDATE = "ForgetPwdCode:";

	public static final String USER_OPERATOR_PREFIX = "user:operator:";

	public static final String TOKEN_EXPIRE_CODE = "112";

	public static final String USERDATAOPERATION_JD = "1";

	public static final String USERDATAOPERATION_TB = "2";

	public static final String USERDATAOPERATION_HLW = "3";

	public static final String USERDATAOPERATION_PHONE = "4";

	public static final String USERDATAOPERATION_MAIL = "5";

	public static final String BEFORELOAN_PROTOCOL = "0";

	public static final String USERIDCARD_CACHE = "userIdCard:";

	public static final String USERJOB_CACHE = "userJob:";

	public static final String USERADDRESS_CACHE = "userAddress:";

	public static final String USERCONTACT_CACHE = "userContact:";

	public static final String USERBANKCARD_CACHE = "userBankCard:";

	public static final String USERZHIMA_CACHE = "userZhima:";

	public static final String DEVIDE_ID_NOT_EQUELS = "0";

	public static final String REQUEST_PAY_SUCCESS = "0";

	public static final String USER_REJECT_BASIC_TYPE = "basic_info";
	public static final String USER_REJECT_ATTACH_TYPE = "attach_info";

	public static final String DEFAULT_COMPANY_NAME = "自由职业";
	public static final String INFO_51_SUCCESS_CODE = "1000";// 验证

	/**
	 * 禁闭期判断
	 */
	public static final int CODE_APPLY_ERROR = 107;

	/**
	 * 默认学历
	 */
	public static final int DEFAULT_EDUCATION = 4;

	/**
	 * 默认工作性质
	 */
	public static final int DEFAULT_BUSINESSTYPE = 14;

	/**
	 * 默认关系
	 */
	public static final int DEFAULT_RELATIONCODE = 6;

	/**
	 * 默认产品code
	 */
	public static final String DEFAULT_PRODUCTCODE = "jfg";

	// 未认证
	public static final int USER_DATA_ID_CARD_NO_STATUS = 0;
	// 已认证
	public static final int USER_DATA_ID_CARD_YES_STATUS = 1;
	// 待认证
	public static final int USER_DATA_ID_CARD_WAIT_STATUS = 2;
	// 能修改
	public static final int USER_DATA_ID_CARD_CAN_MODIFY_STATUS = 3;

	public static final String USER_DEFAULT_PWD = "111111";

    public static final Long USER_DEFAULT_COMPANY_ID = 66l;

    public static final Long USER_DEFAULT_ENTERPRISE_ID = 1l;


	/**
	 * 登录状态
	 *
	 */
	public static final class LoginStatus {
		public static final int OFFLINE_STATUS = 0;// 0：离线
		public static final int ONLINE_STATUS = 1;// 1：在线
	}

	public interface userType {
		/**
		 * 账户不存在
		 */
		public static final int CODE_ACCOUNT_NOT_EXIST = 0;
		/**
		 * 设置了密码的用户
		 */
		public static final int CODE_PASSWD_EXIST = 1;
		/**
		 * 没设置密码的用户
		 */
		public static final int CODE_PASSWD_NOT_EXIST = 2;
	}

	public interface lastLoginType {
		/**
		 * 短信登录
		 */
		public static final int CODE_SMS_LOGIN = 2;
		/**
		 * 密码登录
		 */
		public static final int CODE_PASSWD_LOGIN = 1;
		/**
		 * 授权登录
		 */
		public static final int CODE_AUTH_LOGIN = 3;
		/**
		 * 联合登录
		 */
		public static final int CODE_UNION_LOGIN = 4;
		/**
		 * 微信登录
		 */
		public static final int CODE_WECHAT_LOGIN = 5;
		/**
		 * 用户没登录
		 */
		public static final int CODE_NO_LOGIN = -1;
	}

	public static final String[] SYS_CODES = { "bi_system", "cst_system", "permission_sys", "cst_yw" };
	public static final String SYS_CODE_BI = "bi_system";
	public static final String SYS_CODE_CST = "cst_system";
	public static final String SYS_CODE_CST_YW = "cst_yw";
	public static final String SYS_CODE_PERMISSION = "permission_sys";

	/**
	 * 认证状态
	 */
	public static final class AuthState {
		/**
		 * 未认证
		 */
		public static final int NO_AUTH = 1;
		/**
		 * 待认证
		 */
		public static final int WAIT_AUTH = 2;
		/**
		 * 已认证
		 */
		public static final int VALID_AUTH = 3;
		/**
		 * 可修改
		 */
		public static final int ENABLE_MODIFY_AUTH = 4;
		/**
		 * 已过期
		 */
		public static final int OVERDUE_AUTH = 5;
	}

	public static final class UserRegisterLoginType {
		/**
		 * 1:APP/H5
		 */
		public static final String APP_H5 = "1";
		/**
		 * 2:游客（非绑定手机）
		 */
		public static final String TOURIST = "2";
		/**
		 * 3:微信
		 */
		public static final String WECHAT = "3";

	}

	public static final class BankCardType {
		/**
		 * 1:收还卡
		 */
		public static final int RECEIVE = 1;
		/**
		 * 2:还款卡
		 */
		public static final int REPAY = 2;
	}

	public static final class CacheKey{
		public static final String LOGIN_ERROR_TIMES_CACHE_KEY = "loginErrorTimesCacheKey_";
	}
}
