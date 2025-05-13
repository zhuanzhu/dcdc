package com.egeo.components.product.dto.world;

import java.io.Serializable;

/**
 * @Description 全球购通知结果响应
 * @Author lsl
 * @Version V1.0
 **/
public class WorldNoticeResponseDTO implements Serializable {

    private Integer code;

    private String msg;

    public WorldNoticeResponseDTO() {
    }

    public static WorldNoticeResponseDTO success(){
        WorldNoticeResponseDTO rt = new WorldNoticeResponseDTO();
        rt.setCode(200);
        rt.setMsg("接收成功");
        return  rt;
    }

    public static WorldNoticeResponseDTO fail(){
        WorldNoticeResponseDTO rt = new WorldNoticeResponseDTO();
        rt.setCode(0);
        rt.setMsg("接收失败");
        return  rt;
    }

    public static WorldNoticeResponseDTO fail(String message){
        WorldNoticeResponseDTO rt = new WorldNoticeResponseDTO();
        rt.setCode(0);
        rt.setMsg(message);
        return  rt;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
