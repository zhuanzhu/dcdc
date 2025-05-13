package com.egeo.components.order.vo.cake;

import java.io.Serializable;

/**
 * @Description 接口通知全局所需参数
 * @Author lsl
 * @Version V1.0
 **/
public class CakeNoticeBaseVO implements Serializable {

    /**
     * 	是	string	abc	渠道号
     **/
    private String channel_no;

    /**
     * 	是	int	1578550351285	时间戳
     **/
    private Long timestamp;

    /**
     * 是	string	91bbc8bd8cea0ca5b4c8d25f93e10b6a	签名，计算公式：MD5(sha1(channel_no + 渠道号值 + timestamp + 时间戳值 + 渠道私钥(之前api对接时候的私钥)))
     **/
    private String sign;
}
