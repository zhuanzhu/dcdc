package com.egeo.components.user.enums;

import com.egeo.utils.EmptyUtil;

import java.util.Objects;

/**
 * @Description 用户渠道来源
 * @Author lsl
 * @Version V1.0
 **/
public enum UserChannelSourceEnum {
    JSYH("jsyh", "江苏银行",67L),
    DLF("dlf", "德律风",68L),
    YD("yd", "仪电",70L),
    ;

    UserChannelSourceEnum(){

    }

    UserChannelSourceEnum(String channelSource, String desc, Long companyId) {
        this.channelSource = channelSource;
        this.desc = desc;
        this.companyId = companyId;
    }

    private String channelSource;
    private String desc;

    private Long companyId;

    public String getChannelSource() {
        return channelSource;
    }

    public void setChannelSource(String channelSource) {
        this.channelSource = channelSource;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public static Long getCompanyIdByCode(String channelSource){
        if(EmptyUtil.isEmpty(channelSource)){
            return null;
        }
        for (UserChannelSourceEnum value : UserChannelSourceEnum.values()) {
            if(Objects.equals(channelSource,value.getChannelSource())){
                return value.getCompanyId();
            }
        }
        return null;
    }

    public static String getChannelSourceByCompanyId(Long companyId){
        if(EmptyUtil.isEmpty(companyId)){
            return null;
        }
        for (UserChannelSourceEnum value : UserChannelSourceEnum.values()) {
            if(Objects.equals(companyId,value.getCompanyId())){
                return value.getChannelSource();
            }
        }
        return null;
    }
}
