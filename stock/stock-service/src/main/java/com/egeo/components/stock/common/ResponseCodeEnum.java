package com.egeo.components.stock.common;

public enum ResponseCodeEnum {
	SUCCESS(0, "成功"), 
	ERROR(1, "网络异常，请重试"), 
	API_ERROR(100,"调用外部接口异常"),
	PARAM_IS_NULL(150000, "有参数为空"),
	ILLEGAL_PARAM(150001, "非法参数"),
	AUTH_INCOMPLETE(150002, "【%s】不完整，请重新认证"),
	ADDRESSBOOK_NULL(150003, "通讯录为空"), 
	MSG_NULL(150004,"没有信息"),
	USER_NOT_FOUND(150005,"该用户不存在"),
	NEED_LOGIN(150006,"请重新登录"),
	TOKEN_NOT_FOUND(150007,"token不存在"),
	NO_SAME_PASSWORD(150008,"2次输入密码不一致"),
	SHORT_PASSWORD(150009,"密码长度不能小于6位"),
	PASSWORD_INCORRECT(150010,"原始密码不正确"),
	ID_CARD_NO_EXIST(150011,"身份证信息不存在"),
	ID_CARD_NO_AUTH(150012,"身份证未认证"),
	NULL_CARD_BIN(150013,"无卡BIN信息"),
	ID_CARK_NOT_FOUND(150014,"身份证信息不存在"),
	USER_ID_CARD_NOT_FOUND(150015,"用户主卡不存在"),
	AUTHORIZATION_FAIL(150016,"授权失败，请重新授权"),
	USER_BINDING_ID_CARD(150017, "用户已绑定收款卡，无法修改身份认证信息"),
	NO_FOUND_BANKCACK(150018, "银行卡不存在"),
	ID_CARD_OCCIPIED(150019,"已实名认证，无需再认证"),
	USER_ID_CARD_NO_REPEAT(150020,"此身份证已绑定过，不可重复"),
	ID_CARD_ERROR(150021,"身份证号格式不正确"),
	NAME_ERROR(150022,"用户名字效验错误"),
	SEX_CHECK_FAIL(150023,"性别格式错误"),
    USER_TYPE_CHECK_FAIL(150024,"用户类型校验出错"),
    USER_JOB_CHECK_FAIL(150025,"工作枚举类型校验出错"),
    USER_EDU_CHECK_FAIL(150026,"学历枚举类型校验出错"),
    BANKCARD_IS_EXIST(150027,"当前卡已绑定过"),
    BANKCARD_IS_EXIST_TWO(150028,"收款卡已绑定，绑定失败"),
    HAVE_OVER_DUE (150029, "您有一笔借款未结清，无法申请借款"),
	CONFINE_MES (150030, "您的资质不符，暂时无法申请借款"),
	ID_CARD_UPGRADE_ERROR(150031,"当前用户存在收款卡，升级失败"),
	UNKONW_OPERATOR(150032,"未知运营商认证"),
	DEVICE_NOT_FOUND (150033,"未找到第三方设备信息"),
	DATA_NOT_FOUND(150034,"找不到数据"),
	PHONE_FORMAT_ERROR(150035,"手机号格式不正确"),
	NOT_ALLOW_REGISTER_ERROR(150036,"该手机号异常,不支持注册"),
	USER_REGISTER(150037,"用户已注册"),
	USER_NO_REGISTER(150038,"用户未注册"),
	IDENTIFYING_CODE_ERROR(150039,"短信验证码错误"),
	CUE_UPDATE_PASSWORD(150040,"密码输入错误5次，请修改密码"),
	PASSWORD_OR_USER_INCORRECT(150041,"用户或密码不正确"),
	UPDATE_PASSWORD_ERROR(150042,"修改密码异常"),
	CONTACTS_SAME(150043,"2个紧急联系人不能一样"),
	GAME_CONFIG_NOT_FOUND(150044,"未找到游戏配置"),
	OPENID_NOT_FOUND(150044,"获取openId失败"),
	NO_FOUND_ZONE(150045,"没有当前区号"),
	NO_BIND_BANK_CARD (150046,"您未绑定银行卡，请重试"),
	BIND_BANK_CARD_ERR (150047, "支付功能升级中，请稍后再试"),
	NOT_CREDIT_CARD(150048, "非信用卡"),
	DAY_ASTRICT(150049,"获取短信超限,请24小时后再试"),
	MSG_QUICK(150050,"获取短信频率过快"),
	NOFOUND_OPENID(150051,"OpenId无对应用户"),
	NO_OPENID(150052,"无效的OpenId"),
	USER_HAVE_MOBILE(150053,"您已绑定过手机号，不允许再次绑定"),
	MOBILE_BIND(150054,"该手机号已绑定其他帐号"),
	WECHAT_AUTH_FAIL(150055,"微信授权登录失败"),
	WECHAT_USERINFO_FAIL(150056,"微信获取用户信息失败"),
	ACCESS_TOURIST_ACCOUNT_LIMIT(150057,"获取游客账号次数已达上限"),
	ACCESS_TOURIST_ACCOUNT_QUICK(150058,"获取游客账户频率过快，请稍后再试"),
	LOAN_INFO_INVALID_PARAM(150100, "手机与身份证至少一个必传"), 
	NO_AOTU(150101,"授权失败，请重新授权"),
	DEVICE_ID_NULL(150125, "设备ID不允许为空"),
	DIFFERENT_ID_CARD(150126, "手机号存在身份证不一致"), 
	DIFFERENT_MOBILE(150127, "身份证存在手机号不一致"),
	MSG_MOBILE_AREA_FAIL (150128, "获取手机归属地失败"),
	XJBK_CREDENTIALS(150129,"进件渠道一致"),
	APP_CONFIG_NULL(150130,"app配置不存在"),
	NO_AULL_CONFIG (150131,"无对应认证项配置"),
	USER_LOCKED(150132,"用户已锁定"),
	USER_FREEZE(150133,"用户已删除"),
	GRAPH_CODE(150134,"图形验证码输入错误"),
	ENTERPRISE_EXIST(150135,"该企业信息已存在"),
	NO_ENTERPRISE(150136,"企业不存在"),
	REJECT_SWITCH(150137,"禁止切换企业"),
	USER_UNIQUE_EXIST(150138,"该信息已注册"),
	HAVE_OVER_DUE2(1500139, "您有一笔借款未结清"),
	CONTACTS_SAME2(150140,"紧急联系人手机号不能与借款人一致"),
	
	PARAM_HAS_EXIST(150200, "【%s】已存在"), 
	PARAM_IS_INVALID(150201, "%s错误"),
	OPERATION_FAIL(150202, "操作执行失败"),
	OPERATION_FAIL_NOT_DELETE(150203, "操作失败,有【%s】未删除"),
	IP_ACCESS_ACCOUNT_LIMIT(150204,"今日创建游客账号次数已达上限，请明日再试"),
	NO_UNIAUTHMENU(150205,"无系统权限"),
	REAL_NAME_AUTH_FAIL(150206,"实名认证失败，请重新认证"),
	ID_CARD_EXIST(150207,"身份证已绑定其他账号，如需修改请联系客服"),
	USER_UNIQUE_INFO_FAIL(150208,"保存用户要素信息失败"),
	USER_INFO_FAIL(150209,"保存用户信息失败"),
	USER_INFO_UPDATE_FAIL(150210,"修改用户信息失败"),
	PARAM_NULL_ENTERPRISEINFO(150211,"企业信息必填参数为空"),
	PARAM_NULL_FINANCECHIEF(150212,"财务负责人必填参数为空"),
	PARAM_NULL_EMERGENCY(150213,"紧急联系人必填参数为空"),
	PARAM_NULL_BANKINFO(150214,"对公银行必填参数为空"),
	PARAM_NULL_PERSON(150215,"借款人信息必填参数为空"),
	PARAM_NULL_FAMILY(150216,"直系亲属信息必填参数为空"),
	AUTH_DATA_UNAVAILBOE_FAIL(150217,"用户认证数据失效失败"),
	CONTACTS_NO_FOUND(150218,"紧急联系人不存在"),
	MESSAGE_UPLOAD(150219,"短信已上传"),
	ADDRESSBOOK_UPLOAD(150220,"通讯录已上传"),
	OPERATOR_NO_REGISTER(150221,"业务员不存在"),
	COMPANY_NO_EXIST(150222,"企业信息不存在"),
	COMPANY_INFO_NO_EXIST(150223,"查询app企业进件信息不存在"),
	MERNO_EXIST(150224,"统一社会信用代码已存在"),
	PERSON_LOGIN_REJECT(150225,"个人用户不允许登录"),
	SAVE_REAL_AUTH_FAIL(150226,"保存实名认证信息失败"),
	PARAM_NULL_LEGAL_REPRESENTATIVE(150227,"法人代表证明必填参数为空"),
	PARAM_NULL_OFFICE_TELEPHONE(150228,"企业办公电话必填参数为空"),
	USER_NOT_EXIST(150229,"用户不存在"),
	PERESON_NO_EXIST(150230,"个人信息不存在"),
	PERSON_BANK_ERROR(150231,"个人银行卡格式不正确"),
	ENTERPRISE_BANK_ERROR(150232,"企业对公银行卡格式不正确"),
	ENTERPRISE_CODE_ERROR(150233,"统一社会信用代码格式不正确"),
	AGE_ERROR(150234,"年龄格式不正确"),
	MQ_EXCEPTION_RECORD_NOT_EXIST(105235,"MQ异常消息不存在"),
	SET_NEW_PWD_OVERDUE(105236,"修改密码操作已过期，请重试"),
	ENTERPRISE_LEGAL_NOT_EXIST(150237,"企业法人不存在"),
	USER_ORDER_NOT_EXIST(150238,"用户订单错误"),
	USER_NAME_NOT_MATCH(150239,"用户姓名不一致"),
	ID_CARD_NOT_MATCH(150240,"用户身份证不一致")
	;

   
    private Integer code;
    private String msg;

    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
	public static ResponseCodeEnum getByCode(int code) {
		ResponseCodeEnum[] all_type = ResponseCodeEnum.values();
		for (ResponseCodeEnum one : all_type) {
			if (one.getCode() == code) {
				return one;
			}
		}
		return ResponseCodeEnum.ERROR;
	}

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
