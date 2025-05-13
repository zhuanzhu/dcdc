package com.egeo.components.product.controller.api;

import java.util.List;

import com.egeo.components.product.vo.AttNameValueVO;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class test {
	// 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
	public static void main(String[] args) {
		try {
			List<AttNameValueVO> list = null;
			String listss = "[{'value':228,'key':212,'name':'有无夹层','mode':1},{'value':233,'key':214,'name':'是否可折叠','mode':1},{'value':231,'key':213,'name':'箱包硬度','mode':1},{'value':'拉链','key':208,'name':'闭合方式','mode':2},{'value':'370*470*170','key':207,'name':'外部尺寸（长宽高mm）','mode':2},{'value':'13.1英寸-14英寸','key':206,'name':'容纳电脑尺寸','mode':2},{'value':[221],'key':210,'name':'性别','mode':3},{'value':[223,224,225,226],'key':211,'name':'内部结构','mode':3},{'value':213,'key':205,'name':'大小','mode':1},{'value':220,'key':209,'name':'防水强度','mode':1}]";
			if(listss != null){
			    JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, AttNameValueVO.class);
			    list = MAPPER.readValue(listss, javaType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
