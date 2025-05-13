package com.egeo.components.utils;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.common.ResponseCodeEnum;
import com.egeo.vo.BaseResponse;

/**
 * 
 * @author wht
 * @date 2017年12月6日
 */
public class ResultUtil {

	// 失败
	public static <T> BaseResponse<T> fail(ResponseCodeEnum responseCodeEnum, Class<T> c) {
		BaseResponse<T> msg = new BaseResponse<T>();
		msg.setCode(responseCodeEnum.getCode());
		msg.setMsg(responseCodeEnum.getMsg());
		try {
			msg.setData(c.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return msg;
	} 


	// 失败
	public static <T> BaseResponse<List<T>> fail2List(ResponseCodeEnum responseCodeEnum, Class<T> c) {
		BaseResponse<List<T>> msg = new BaseResponse<List<T>>();
		msg.setCode(responseCodeEnum.getCode());
		msg.setMsg(responseCodeEnum.getMsg());
		msg.setData(new ArrayList<T>());
		return msg;
	}

	// 失败
	public static BaseResponse<?> fail(int code, String mssage) {
		return BaseResponse.fail(code, mssage);
	}

	// 失败
	public static <T> BaseResponse<T> fail(int code, String mssage, Class<T> c) {
		BaseResponse<T> msg = new BaseResponse<T>();
		msg.setCode(code);
		msg.setMsg(mssage);
		try {
			msg.setData(c.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return msg;
	}

	// 失败
	public static <T> BaseResponse<T> fail(ResponseCodeEnum responseCodeEnum) {
		BaseResponse<T> msg = new BaseResponse<T>();
		msg.setCode(responseCodeEnum.getCode());
		msg.setMsg(responseCodeEnum.getMsg());
		return msg;
	}

	// 成功
	public static BaseResponse<Object> successful(Object data) {
		return BaseResponse.success(data);
	}

	// 成功
	public static BaseResponse<?> success() {
		return BaseResponse.success();
	}
}
