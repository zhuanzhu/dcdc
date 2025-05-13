package com.egeo.components.product.common;

import java.util.*;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/30 1:15
 * @Version V1.0
 **/
public enum ProductChannelCodeEnum{
    SELF("self",1,0,"自营"),
    JD("jd",2,0,"京东"),
    CAKE("cake",3,0,"蛋糕叔叔"),
    WORLD_BUY("worldBuy",4,0,"全球购"),

    /**正常情况都是返回空列表**/
    DEFAULT("default",100,0,"默认"),
    REMOTE("remote",101,0,"远程"),

    ;

    public static List<ProductChannelCodeEnum> sortedEnums =null;

    public static Map<String,ProductChannelCodeEnum> productChannelCodeMap= new HashMap<>();

     ProductChannelCodeEnum() {
     }

     ProductChannelCodeEnum(String code, int sort,int state, String description) {
        this.code = code;
        this.sort = sort;
        this.state = state;
        this.description = description;
    }

    private String code;

    private int sort;
    /**状态：0正常 1关闭**/
    private int state;

    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    static {
        for (ProductChannelCodeEnum value : ProductChannelCodeEnum.values()) {
            productChannelCodeMap.put(value.getCode(),value);
        }
        EnumSet<ProductChannelCodeEnum> enumSet = EnumSet.allOf(ProductChannelCodeEnum.class);

        sortedEnums = new ArrayList<>(enumSet);
        sortedEnums.sort(new Comparator<ProductChannelCodeEnum>() {
            @Override
            public int compare(ProductChannelCodeEnum o1, ProductChannelCodeEnum o2) {
                return Integer.compare(o1.getSort(),o2.getSort());
            }
        });
    }

    public static void main(String[] args) {
        for (ProductChannelCodeEnum e : sortedEnums) {
            System.out.println(e.name() + ": " + e.getCode() + ", " + e.getSort() + ", " + e.getDescription());
        }
    }
}
