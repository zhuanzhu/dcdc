package com.egeo.components.order.enums;

public class ThirdConst {

    /**
     * 第三方订单类型
     */
    public static class ThirdPartyType{
        public static final Integer PHONE = 1;
        public static final Integer QC = 2;
        public static final Integer JD = 3;
        public static final Integer CAKE = 4;
        public static final Integer WORLD = 5;
        public static final Integer QM = 9;
    }

    /**
     * 商家id
     */
    public static class Merchant{
        public static final Long JD = 6L;
        public static final Long CAKE = 7L;
        public static final Long WORLD = 8L;
        public static final Long QM = 9L;
    }

    /**
     * 来源
     */
    public static class Source{
        public static final Integer JD = 3;
        public static final Integer CAKE = 4;
        public static final Integer WORLD = 5;
        public static final Integer QM = 9;
    }
}
