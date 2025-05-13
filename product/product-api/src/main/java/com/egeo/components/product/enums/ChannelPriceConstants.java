package com.egeo.components.product.enums;

public  final class ChannelPriceConstants {



		/**
		 * 显示维度为商品维度还是sku(spec)维度
		 * true：商品维度
		 * false:sku或规格维度
		 */
		public static final Boolean IS_GOOD_OR_SKU=false;

		/**
		 * 是否放弃无规格的商品
		 * true：放弃
		 * false:不放弃
		 */
		public static final Boolean IS_GIVE_UP_UN_FOUND_SPEC=false;

		/**
		 * 企业价格key
		 */
		public static final String ENTERPRISE_PRICE_KEY = "enterprisePrice";

		/**
		 * 渠道价格key
		 */
		public static final String CHANNEL_PRICE_KEY = "channelPrice";

		/**
		 * 市场价格key
		 */
		public static final String MARKET_PRICE_KEY = "marketPrice";

		/**
		 * 毛利key
		 */
		public static final String PROFIT_KEY = "profit";

		/**
		 * 销售价key
		 */
		public static final String SALE_PRICE_KEY = "salePrice";

		/**
		 * 毛利是否超过设置的最小毛利key
		 */
		public static final String IS_MORE_THAN_GROSS_MARGIN_MIN = "isMoreThanGrossMarginMin";

		/**
		 * 是否超过设置的金额范围key
		 */
		public static final String IS_MORE_THAN_PRICE_SCOPE = "isMoreThanPriceScope";

	/**
	 * 审核价格类型
	 */
	public static final String PRICE_TYPE = "priceType";

	/**
	 * 审核价格结果
	 */
	public static final String PRICE_AUDIT = "priceAudit";

	/**
	 * 审核价格值
	 */
	public static final String PRICE_VALUE = "priceValue";
	}
