package com.egeo.components.third.common;

import lombok.Getter;

/**
 * 格式化的规则集
 *  [{"ruteValues":"Y","ruteName":"checkIsNull"}]
 * [{"ruteValues":"1","ruteName":"minLen"},{"ruteValues":"yyyy-MM-dd HH:mm:ss","ruteName":"dateFormat"}]
 * [{"ruteValues":"100","ruteName":"maxLen"},{"ruteValues":"1","ruteName":"minLen"}]
 * [{"ruteValues":"2","ruteName":"fieldScale"}]
 * [{"ruteValues":"","ruteName":"rmbYToF"}]
 * [{"ruteValues":[{"上海,上海市,北京,北京市,重庆,重庆市":"市辖区"},{"海南":"海南省"}],"ruteName":"containConvert"}] 输入值上海,输出市辖区
 * [{"ruteValues":[{"上海市,北京市,重庆市":"市辖区"},{"海南省":"海南省"}],"ruteName":"includeConvert"}] 输入值上海,输出市辖区,输入值海南,输出海南省
 */
public enum RuteNameEnum {

	//检查
	RUTE_NAME_CHECK_NULL("checkIsNull","检查是否为空规则"),
	RUTE_NAME_CHECK_MIN("minLen","检查最小长度"),
	RUTE_NAME_CHECK_MAX("maxLen","检查最大长度"),
	RUTE_NAME_CHECK_EQ("eq","检查等于某个阈值"),
	RUTE_NAME_CHECK_UNEQ("uneq","检查不等于某个阈值"),

	//转换
	RUTE_NAME_DATE_FORMAT("dateFormat","日期格式化"),
	RUTE_NAME_SCALE_FORMAT("fieldScale","保留小数点格式化"),
	RUTE_NAME_CONVER_STATUS("converStatus","转换状态"),
	RUTE_NAME_FILE_BASE64("FileUrlDownBase64","文件二进制转base64"),

	RUTE_NAME_BASE64_EN("base64en","字符转base64加密"),

	RUTE_NAME_BASE64_DE("base64de","字符转base64解密"),
	RUTE_NAME_CURR_DATE("currDate","若为空获取当前时间"),
	RUTE_NAME_SET_DEFAULT_VALUE("setDefaultVaule","若为空设置默认值"),
	RUTE_NAME_RMB_Y_TO_F("rmbYToF","将人民币元转换为分"),
	RUTE_NAME_RMB_F_TO_Y("rmbFToY","将人民币分转换为元"),
	RUTE_NAME_CONTAIN("containConvert","包含在集合中,转换成某一个值"),
	RUTE_NAME_INCLUDE("includeConvert","包含某个字符串,转成某一个值"),
	;

	@Getter
	private String ruteName;

	@Getter
	private String msg;

	private RuteNameEnum(String ruteName, String msg) {
		this.ruteName = ruteName;
		this.msg = msg;
	}



}
