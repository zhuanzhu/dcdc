package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.PictureCondition;
import com.egeo.components.product.po.PicturePO;
import com.egeo.orm.BaseReadDAO;

public interface PictureReadDAO extends BaseReadDAO<PicturePO>{
	/**
     * 根据产品id查询产品图片信息
     * @param pictureId
     * @return
     */
	List<PicturePO> pictureByProductId(@Param("pictureId")Long pictureId);
	/**
     * su草稿id查询su草稿图片信息
     * @param id
     * @return
     */
	List<PictureCondition> findMPictureByMerchantProdId(@Param("merchantProdId")Long merchantProdId);
	/**
	 * 根据spuid查询spu图片
	 * @param id
	 * @return
	 */
	List<PictureCondition> findByStandardProductUnitId(@Param("standardProductUnitId")Long standardProductUnitId);
	/**
	 * 根据spuid查询sku默认图片
	 * @param id
	 * @return
	 */
	String skuPicUrlByStandardProductUnitId(@Param("standardProductUnitId")Long id);
	/**
	 * 根据suid查询su轮播图信息
	 * @param standardProductUnitId
	 * @param req
	 * @return
	 */
	List<String> findByStandardUnitId(@Param("standardUnitId")Long standardUnitId);
	/**
	 * 根据suid查询su封面图信息
	 * @param standardProductUnitId
	 * @param req
	 * @return
	 */
	String findPictureByStandardUnitId(@Param("standardUnitId")Long standardUnitId);
	/**
	 * 根据suid查询所有su图片信息
	 * @param merchantProductId
	 * @return
	 */
	List<PictureCondition> picturesByStandardUnitId(@Param("standardUnitId")Long standardUnitId);
	/**
	 * 根据su商品id和类型查询su图片信息
	 * @param standardUnitId
	 * @param type 类型：1、列表图片 2、轮播图片 3、web轮播图
	 * @return
	 */
	List<String> findByStandardUnitIdAndType(@Param("standardUnitId")Long standardUnitId, @Param("type")int type);

    Long findLastId();
}
	