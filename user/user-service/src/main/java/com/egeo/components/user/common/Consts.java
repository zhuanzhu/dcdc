package com.egeo.components.user.common;

/**
 * Created by guofeng.qin on 2017/4/25 0025.
 */
public class Consts {
	//public static final String TokenData = "TokenData";
	//public static final String Default_Longitude = "121.487899";
	//public static final String Default_Latitude = "31.249162";
	//public static final String HBASE_FAMILY = "info";// hbase列族

	public static enum FxtjEnum {
		ZHIXING("zhixing","执行公开信息"),
		SHIXIN("shixin","失信老赖名单"),
		XIANGAO("xiangao","限制高消费名单"),
		XIANCHU("xianchu","限制出入境名单"),
		CAIPAN("caipan","民商事裁判文书"),
		SHENPAN("shenpan","民商事审判流条"),
		ZUIFAN("zuifan","罪犯及嫌疑人名单"),
		WEIFA("weifa","行政违法记录"),
		QIANSHUI("qianshui","欠税名单"),
		FEIZHENG("feizheng","纳税非正常户"),
		QIANKUAN("qiankuan","催收信息"),
		ZHONGBEN("zhongben","终本案件");
		
		private final String code;
		private final String name;
		private FxtjEnum(String code, String name) {
			this.code = code;
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
		
	}

}
