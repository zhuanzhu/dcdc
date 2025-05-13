package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.PictureDTO;

public interface PictureReadService {

    PictureDTO findById(PictureDTO dto);

    List<PictureDTO> findAll(PictureDTO dto);
    /**
     * 根据产品id查询产品图片信息
     * @param pictureId
     * @return
     */
	List<PictureDTO> pictureByProductId(Long pictureId);
	/**
     * su草稿id查询su草稿图片信息
     * @param id
     * @return
     */
	List<PictureDTO> findMPictureByMerchantProdId(Long merchantProdId);
	/**
	 * 根据spuid查询spu图片
	 * @param id
	 * @return
	 */
	List<PictureDTO> findByStandardProductUnitId(Long standardProductUnitId);
	/**
	 * 根据suid查询su轮播图信息
	 * @param standardProductUnitId
	 * @param req
	 * @return
	 */
	List<String> findByStandardUnitId(Long standardUnitId);
	/**
	 * 根据suid查询su封面图信息
	 * @param standardProductUnitId
	 * @param req
	 * @return
	 */
	String findPictureByStandardUnitId(Long standardUnitId);
	/**
	 * 根据suid查询所有su图片信息
	 * @param merchantProductId
	 * @return
	 */
	List<PictureDTO> picturesByStandardUnitId(Long merchantProductId);
	/**
	 * 根据su商品id和类型查询su图片信息
	 * @param standardUnitId
	 * @param type 类型：1、列表图片 2、轮播图片 3、web轮播图
	 * @return
	 */
	List<String> findByStandardUnitIdAndType(Long standardUnitId, int type);

    Long findLastId();
}
	